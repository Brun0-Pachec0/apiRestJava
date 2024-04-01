package com.learning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.CountryRequest;
import com.learning.dto.CountryResponse;


//marker for show to the program here it's a controller and requestMapping, to inform the end point. 
@RestController
@RequestMapping("/country")
public class HelloWorldController {
	
	//class parameter
	//list, will receive CountryDto type, it's like an object than we created, went instantiated how a ArrayList.
	private List<CountryResponse> countries = insertCountries();
	
	
	//method for include countries when start.
	private List<CountryResponse> insertCountries() {
		var listOfCountries = new ArrayList<CountryResponse>();
		listOfCountries.add(new CountryResponse(1, "Brazil", 215_000_000L));
		listOfCountries.add(new CountryResponse(2, "China", 1_400_000_000L));
		listOfCountries.add(new CountryResponse(3, "Alemanha", 83_000_000L));
		listOfCountries.add(new CountryResponse(4, "Argentina", 45_000_000L));
		return listOfCountries;
	}

	
	
	//CREATE - POST countryDto it's a type of data.
	@PostMapping //post type and end point marker.
	public ResponseEntity<CountryResponse> save(@RequestBody final CountryRequest request) {
		
		//automatic id generator
		Integer id = countries.size();
		var response = new CountryResponse(id, request.getName(), request.getPopulation());
		
		countries.add(response);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}
	
	


	//READ - GET /country
	//- read all - Return a complete list of countries and informations. It's not necessary!
	//@GetMapping //get type end point mark.
	public ResponseEntity<List<CountryResponse>> getAll() {
		return ResponseEntity.ok(countries);
	}
	
	//- read specific GET /country/{id}
	@GetMapping("/{id}")
	public ResponseEntity<CountryResponse> findBy(@PathVariable("id") final long id) { //pathvariable significa que na url eu vou enviar o id daquele recurso.
		
		//find
		for(var country: countries) {
			if(country.getId() == id) {
				return ResponseEntity.ok(country);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	//- filtering GET /country - using ?prefix=
	@GetMapping
	public ResponseEntity<List<CountryResponse>> getAll(@RequestParam(name = "prefix", required = false) final String prefix) {
		
		if (Objects.isNull(prefix)) {
			return ResponseEntity.ok(countries);
		} else {
			var listOfCountries = 
					countries.stream()
							.filter(countryDto -> countryDto.getName().startsWith(prefix))
							.collect(Collectors.toList());
			return ResponseEntity.ok(listOfCountries);
		}
	}
	
	
	
	//UPDATE - PUT(to update the hole information)/PATCH (to update some specific information) funciona como find e update, porque ele pcisa primeiro encontra o que precisa ser modificado
	@PutMapping("/{id}")
	public ResponseEntity<CountryResponse> update(@PathVariable("id") final int id, @RequestBody final CountryRequest request) {
		
		//find
		CountryResponse countryDto = null;
		for (var country : countries) {
			if (country.getId() == id) {
				countryDto = country;
			}
		}
		
		//update
		if(Objects.nonNull(countryDto)) {
			countryDto.setName(request.getName());
			countryDto.setPopulation(request.getPopulation());
			return ResponseEntity.ok(countryDto);
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
	
	
	
	//DELETE - DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
		
		
		
		//find
		int index = -1;
		for (int i = 0; i < countries.size(); i++) {
			if(countries.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		
		//remove
		if(index >= 0) {
			countries.remove(index);
		}
		
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}
}
