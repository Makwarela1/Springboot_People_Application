package org.example.springboot_app_people.repository;

import org.example.springboot_app_people.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository <People, Long> {

    String findByEmail (String email);

    boolean existsByEmail (String email);
}
