package de.oglimmer.lazydemo.dao;

import de.oglimmer.lazydemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
