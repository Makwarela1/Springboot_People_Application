package org.example.springboot_app_people.service;


import org.example.springboot_app_people.dto.PeopleRequest;
import org.example.springboot_app_people.dto.PeopleResponse;
import org.example.springboot_app_people.entity.People;
import org.example.springboot_app_people.exceptions.DuplicateResourceException;
import org.example.springboot_app_people.exceptions.ResourceNotFoundException;
import org.example.springboot_app_people.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    private PeopleResponse mapToResponse (People people){
        return new PeopleResponse(people.getId(), people.getName(), people.getEmail());
    }

    //CREATE
    @Transactional
    public PeopleResponse createPerson (PeopleRequest request){
        // prevent duplicate email
        if (peopleRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("A person with that email already exists");
        }

        People people = new People();
        people.setName(request.name());
        people.setEmail(request.email());

        People savedPeople = peopleRepository.save(people);
        return mapToResponse(savedPeople);
    }
    // READ ONE
    @Transactional (readOnly = true)
    public PeopleResponse getById (Long id){
        People people = findPersonOrThrow(id);
        return mapToResponse(people);
    }

    // READ ALL
    @Transactional (readOnly = true)
    public List <PeopleResponse> getAllPeople (){
        return peopleRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    // UPDATE
    @Transactional
    public PeopleResponse update (Long id, PeopleRequest request){
        People people = findPersonOrThrow(id);

        if(!people.getEmail().equals(request.email()) && peopleRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("A person with the email alreadye exists");
        }
        people.setName(request.name());
        people.setEmail(request.email());


        People updatedPeople = peopleRepository.save(people);
        return mapToResponse(updatedPeople);
    }

    // DELETE
    @Transactional
    public void deletePerson (Long id){
        People people = findPersonOrThrow(id);
        peopleRepository.delete(people);
    }

    private People findPersonOrThrow (Long id){
        return peopleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This id already exists for a laptop"));
    }
}

