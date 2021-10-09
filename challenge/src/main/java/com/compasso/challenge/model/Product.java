package com.compasso.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_sequence")
    @SequenceGenerator(name="product_sequence", sequenceName="SEQ_PRODUCT",allocationSize = 1)
    private Long id;

    @NotNull(message="Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode ser vazio.")
    @Column(name = "NAME")
    @Length(min = 1, max = 200, message="O nome deve ter no máximo 200 caracteres")
    private String name;

    @NotNull(message="Descrição não pode ser nula.")
    @NotEmpty(message = "Descrição não pode ser vazia.")
    @Column(name = "DESCRIPTION")
    @Length(min = 1, max = 200, message="A descrição deve ter no máximo 200 caracteres")
    private String description;

    @NotNull(message="Preço não pode ser nulo.")
    @Column(name="PRICE")
    @DecimalMin(value = "0", message = "O valor precisa ser positivo")
    @DecimalMax(value = "999999",message = "Valor alto demais")
    private Double price;

}
