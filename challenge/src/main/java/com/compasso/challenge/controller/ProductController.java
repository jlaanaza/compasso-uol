package com.compasso.challenge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.challenge.dto.ProductDTO;
import com.compasso.challenge.model.Product;
import com.compasso.challenge.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {

        List<Product> list = productService.getAll();

        return new ResponseEntity<List<ProductDTO>>(convertToDTO(list), new HttpHeaders(), HttpStatus.OK);
    }
	
	private ProductDTO convertToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    private List<ProductDTO> convertToDTO(List<Product> listProduct){
        List<ProductDTO> lista = new ArrayList<>();
        listProduct.forEach( (product) -> {
        	ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setDescription(product.getDescription());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            lista.add(dto);
        });
        return lista;
    }

}
