package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.in28minutes.rest.webservices.restfulwebservices.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostJpaService postJpaService;

    public PostController(PostJpaService postJpaService) {
        this.postJpaService = postJpaService;
    }

    @GetMapping(path="/posts")
    public List<Post>getAllPost(){
        return postJpaService.retrieveAllPosts();
    }


}
