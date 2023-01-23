package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.model.Post;
import com.in28minutes.rest.webservices.restfulwebservices.model.User;
import com.in28minutes.rest.webservices.restfulwebservices.post.PostJpaService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class UserController {
    private final UserJpaService userJpaService;
    private final PostJpaService postJpaService;


    public UserController(UserJpaService userJpaService, PostJpaService postJpaService) {
        this.userJpaService = userJpaService;
        this.postJpaService = postJpaService;
    }
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userJpaService.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> findUserById(@PathVariable int id){
        var entityModel=EntityModel.of(userJpaService.findOne(id));
        var link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        System.out.println("user "+ user);
        var savedUser=userJpaService.createUser(user);
        var location= ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        userJpaService.deleteUser(id);
    }


    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id){
        return postJpaService.retrievePostForUser(id);
    }


    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody @Valid Post post){
        var savedPost=postJpaService.createPostForUser(id,post);
        var location= ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedPost.getId()).
                toUri();
        return ResponseEntity.created(location).build();

    }

}
