package dk.plaul.demo1.repositories;


import org.springframework.data.repository.CrudRepository;

import dk.plaul.demo1.entity.Person;

public interface PersonRepository extends CrudRepository<Person,Integer> {

}

