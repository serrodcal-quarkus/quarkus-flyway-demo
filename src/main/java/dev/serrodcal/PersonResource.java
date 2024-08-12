package dev.serrodcal;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Path("/v1/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @POST
    @ResponseStatus(201)
    @Transactional
    public void createPerson(@Valid NewPersonRequest newPersonRequest) {
        Log.info("PersonResource.createPerson()");
        Log.debug(newPersonRequest.toString());

        this.personRepository.persistAndFlush(newPersonRequest.toPersonEntity());
    }

    @GET
    @SessionScoped
    public List<PersonResponse> getPersons() {
        Log.info("PersonResource.getPersons()");

        return this.personRepository.listAll().stream().map(i -> i.toResponse()).toList();
    }

    @GET
    @Path("/{personId}")
    @SessionScoped
    public PersonResponse getPersonById(@PathParam("personId") @Min(value = 0, message = "id cannot be negative") Long personId) {
        Log.info("PersonResource.getPersonById()");

        return this.personRepository.findById(personId).toResponse();
    }

    @PUT
    @Transactional
    public PersonResponse updatePerson(@Valid UpdatePersonRequest updatePersonRequest) {
        Log.info("PersonResource.updatePerson()");

        PersonEntity person = this.personRepository.findById(updatePersonRequest.id());

        if (Objects.isNull(person))
            throw new NoSuchElementException("person does not exist");

        person.name = updatePersonRequest.name();
        person.age = updatePersonRequest.age();
        person.persistAndFlush();

        return person.toResponse();
    }

    @DELETE
    @Path("/{personId}")
    @Transactional
    public void deletePerson(@PathParam("personId") @Min(value = 0, message = "id cannot be negative") Long personId) {
        Log.info("PersonResource.deletePerson()");

        PersonEntity person = this.personRepository.findById(personId);
        if (Objects.isNull(person))
            throw new NoSuchElementException("person does not exist");

        person.delete();
    }

}
