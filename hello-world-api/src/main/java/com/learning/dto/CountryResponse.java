package com.learning.dto;

//classe countryDto
public class CountryResponse {
	
	//parametros da classe
	private Integer id;
	private String name;
	private Long population;
	
	
	//constructor sem parametros
	public CountryResponse() {
		
	}
	
	//constructor
	public CountryResponse(Integer id, String name, Long population) {
		this.id = id;
		this.name = name;
		this.population = population;
	}

	
	//métodos getters e setters de id, name e population
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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
		return "CountryDto [id= " + id + ", name= " + name + ", population= " + population + "]";
	}
	
	
	
}
