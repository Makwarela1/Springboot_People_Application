package org.example.springboot_app_people.controller;

import jakarta.validation.Valid;
import org.example.springboot_app_people.dto.PeopleRequest;
import org.example.springboot_app_people.dto.PeopleResponse;
import org.example.springboot_app_people.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController (PeopleService peopleService){this.peopleService = peopleService;}

    //CREATE
    @PostMapping
    public ResponseEntity<PeopleResponse> createPerson(@Valid @RequestBody PeopleRequest request) {
        PeopleResponse created = peopleService.createPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponse> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(peopleService.getById(id));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<PeopleResponse>> getAllPeople() {
        return ResponseEntity.ok(peopleService.getAllPeople());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PeopleResponse> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody PeopleRequest request) {
        return ResponseEntity.ok(peopleService.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        peopleService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
