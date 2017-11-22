package br.com.testeapi.ws.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import br.com.testeapi.domain.Person;
import br.com.testeapi.service.PersonService;

@Path("/person")
public class PersonWS {
	
	PersonService service;
	
	public PersonWS() {
		service = new PersonService();
	}
	
	@DELETE
	@Path("/deletePerson")
	public Boolean deletePerson(Person person) {
		return true;
	}
	
	@GET
	@Path("/findPersonByID/{id}")
	public Person findPersonByID(Long id) {
		return service.findById(id);
	}
	
	@POST
	@Path("/savePerson")
	public Boolean savePerson(Person person) {
		service.save(person);
		return true;
	}
	
	
}
