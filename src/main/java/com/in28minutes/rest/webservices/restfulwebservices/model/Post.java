package com.in28minutes.rest.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=5)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
