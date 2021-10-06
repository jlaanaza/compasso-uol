package com.compasso.challenge.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class ProductDTO {
	
    private Long id;

    @NotNull(message="Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode ser vazio.")
    private String name;

    @NotNull(message="Descrição não pode ser nula.")
    @NotEmpty(message = "Descrição não pode ser vazia.")
    private String description;

    @NotNull(message="Preço não pode ser nulo.")
    private Double price;

}
