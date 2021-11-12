package dk.plaul.demo1.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import dk.plaul.demo1.entity.Person;
import dk.plaul.demo1.repositories.PersonRepository;


@Configuration
public class DataSetup implements CommandLineRunner {

  PersonRepository personRepository;

  public DataSetup(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    personRepository.save(new Person("Peter", "Olsen"));
    personRepository.save(new Person("Hanne", "Olsen"));
  }
}
