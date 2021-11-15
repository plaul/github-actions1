package dk.plaul.demo1.rest;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.plaul.demo1.entity.Person;
import dk.plaul.demo1.repositories.PersonRepository;
import dk.plaul.demo1.errors.NotFoundException;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

  PersonRepository personRepository;

  public PersonResource(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  // @ConditionalOnProperty(name = "features.time-endpoint-enabled", havingValue =
  // "true")
  @GetMapping("/server/server-time")
  public String getServerTime() {
    return "{\"servtime\":\"" + new Date().toString() + "\"}";
  }

  @RequestMapping("/server/api-version")
  public String getVersion() {
    return "{\"version\": 2.0}";
  }

  // @Value("${features.time-endpoint-enabled}")
  // public String timePoint;

  // @RequestMapping("/val")
  // public String getVal() {
  // return timePoint;
  // }

  @GetMapping
  Iterable<Person> getAll() {
    return personRepository.findAll();
  }

  @GetMapping("/{id}")
  Person getOne(@PathVariable("id") int id) {
    return personRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

  @PostMapping()
  Person addOne(@RequestBody Person person) {
    return personRepository.save(person);
  }

  @PutMapping("/{id}")
  Person editOne(@RequestBody Person person, @PathVariable("id") int id) {
    // We will change this to handle error
    Person p = personRepository.findById(id).get();
    p.setFirstName(person.getFirstName());
    p.setLastName(person.getLastName());
    return personRepository.save(p);
  }

  @DeleteMapping("/{id}")
  void deleteOne(@PathVariable("id") int id) {
    personRepository.deleteById(id);
  }

}
