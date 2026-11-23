package org.example.springboot_app_people.repository;

public interface PeopleRepository {

    String findByEmail (String email);
}
