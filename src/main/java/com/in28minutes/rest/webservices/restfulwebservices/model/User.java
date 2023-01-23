package com.in28minutes.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity(name="user_details")
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Size(min=2,message = "Name should have at least 2 characters")
    private String name;
    @Past(message = "Birth date should be in the past")
    @NotNull(message = "birthDate should not be null")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user",cascade =  { CascadeType.REMOVE })
    @JsonIgnore()
    private List<Post> posts;

    public User(){

    }

    public User(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }
}
