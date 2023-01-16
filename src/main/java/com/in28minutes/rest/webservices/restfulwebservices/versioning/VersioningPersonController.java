package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import com.in28minutes.rest.webservices.restfulwebservices.model.Person;
import com.in28minutes.rest.webservices.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public Person getFirstVersionOfPerson(){
        return new Person("Aviv Horovitz");

    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2("Aviv","Horovitz");

    }

    @GetMapping(path="/person",params = "version=1")
    public Person getFirstVersionOfPersonRequestParam(){
        return new Person("Aviv Horovitz");

    }

    @GetMapping(path="person",params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam(){
        return new PersonV2("Aviv","Horovitz");

    }

    @GetMapping(path="/person/header",headers = "X-API-VERSION=1")
    public Person getFirstVersionOfPersonHeader(){
        return new Person("Aviv Horovitz");

    }
}
