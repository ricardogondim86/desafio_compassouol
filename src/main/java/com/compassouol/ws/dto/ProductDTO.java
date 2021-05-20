package com.compassouol.ws.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Getter
public class ProductDTO {

	@NotBlank(message = "{name.not.blank}")
	private String name;
	
	@NotBlank(message = "{description.not.blank}")
	private String description;
	
	@Positive(message = "{price.positive}")
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}	

}
