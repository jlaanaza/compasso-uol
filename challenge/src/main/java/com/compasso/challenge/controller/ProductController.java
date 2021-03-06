package com.compasso.challenge.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.challenge.dto.ProductDTO;
import com.compasso.challenge.exception.ExceptionError;
import com.compasso.challenge.model.Product;
import com.compasso.challenge.service.ProductService;
import com.compasso.challenge.utils.ConvertUtils;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	ConvertUtils convert = new ConvertUtils();

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {

		List<Product> response = productService.getAll();

		return new ResponseEntity<List<ProductDTO>>(convert.convertToDTO(response), new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDTO>> search(@RequestParam(required = false) String q,
			@RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price) {

		List<Product> response = productService.search(q, min_price, max_price);

		return new ResponseEntity<List<ProductDTO>>(convert.convertToDTO(response), new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		Product response = null;
		try {
			response = productService.findById(id);
		} catch (NoSuchElementException noSuchException) {
			return new ResponseEntity<ProductDTO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProductDTO>(convert.convertToDTO(response), new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody Product product) {

		Product response = productService.create(product);

		return new ResponseEntity<ProductDTO>(convert.convertToDTO(response), new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@RequestBody Product product, @PathVariable(value = "id") Long id) {
		Product productDb = null;
		try {
			productDb = productService.findById(id);
		} catch (NoSuchElementException noSuchException) {
			return new ResponseEntity<ProductDTO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		productDb.setName(product.getName());
		productDb.setDescription(product.getDescription());
		productDb.setPrice(product.getPrice());

		Product response = productService.update(productDb);

		return new ResponseEntity<ProductDTO>(convert.convertToDTO(response), new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
		try {
			productService.delete(id);
		} catch (EmptyResultDataAccessException emptyException) {
			return new ResponseEntity<ProductDTO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>(null, new HttpHeaders(), HttpStatus.OK);
	}


	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ExceptionError> handleConstraintViolationException(ConstraintViolationException e) {

		Map<String, List<String>> errors = new HashMap<String, List<String>>();

		e.getConstraintViolations().forEach((constraintViolation) -> {
			if (errors.containsKey(constraintViolation.getPropertyPath().toString())) {
				List<String> listErrors = errors.get(constraintViolation.getPropertyPath().toString());
				listErrors.add(constraintViolation.getMessage());
				errors.put(constraintViolation.getPropertyPath().toString(), listErrors);
			} else {
				errors.put(constraintViolation.getPropertyPath().toString(),
						new ArrayList<String>(Arrays.asList(constraintViolation.getMessage())));
			}
		});

		ExceptionError exceptionError = new ExceptionError(HttpStatus.BAD_REQUEST.value(), errors);
		return new ResponseEntity<ExceptionError>(exceptionError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
