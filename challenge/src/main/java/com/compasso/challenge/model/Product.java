package com.compasso.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_sequence")
    @SequenceGenerator(name="product_sequence", sequenceName="SEQ_PROD")
    private Long id;

    @NotNull(message="Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode ser vazio.")
    @Column(name = "NAME")
    private String name;

    @NotNull(message="Descrição não pode ser nula.")
    @NotEmpty(message = "Descrição não pode ser vazia.")
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message="Preço não pode ser nulo.")
    @Column(name="PRICE")
    private Double price;

}