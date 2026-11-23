package org.example.springboot_app_people.dto;

public record PeopleResponse(
        Long id,
        String name,
        String email
) {
}
