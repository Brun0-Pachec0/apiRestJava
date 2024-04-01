package com.learning.dto;

//classe countryDto
public class CountryRequest {
	
	//parametros da classe
	
	private String name;
	private Long population;
	
	
	//constructor sem parametros
	public CountryRequest() {
		
	}
	
	//constructor
	public CountryRequest(Integer id, String name, Long population) {
		this.name = name;
		this.population = population;
	}

	
	//métodos getters e setters de id, name e population

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPopulation() {
		return population;
	}


	public void setPopulation(Long population) {
		this.population = population;
	}

	
	//transforma as informações em uma string para melhor visualização.
	@Override
	public String toString() {
		return "CountryResponse " + "name= " + name + ", population= " + population + "]";
	}
	
	
	
}
