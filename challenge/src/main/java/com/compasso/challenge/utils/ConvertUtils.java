package com.compasso.challenge.utils;

import java.util.ArrayList;
import java.util.List;

import com.compasso.challenge.dto.ProductDTO;
import com.compasso.challenge.model.Product;

public class ConvertUtils {
	
	public ProductDTO convertToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setDescription(product.getDescription());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		return dto;
	}

	public List<ProductDTO> convertToDTO(List<Product> listProduct) {
		List<ProductDTO> lista = new ArrayList<>();
		listProduct.forEach((product) -> {
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
