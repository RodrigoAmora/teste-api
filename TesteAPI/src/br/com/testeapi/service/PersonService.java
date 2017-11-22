package br.com.testeapi.service;

import br.com.testeapi.dao.impl.mongodb.PersonDao;
import br.com.testeapi.domain.Person;

public class PersonService {
	
	PersonDao personDao;
	
	public PersonService() {
		personDao = new PersonDao();
	}
	
	public Person findById(Long id) {
		return personDao.findById(id);
	}
	
	public Boolean save(Person person) {
		return personDao.save(person);
	}
}
