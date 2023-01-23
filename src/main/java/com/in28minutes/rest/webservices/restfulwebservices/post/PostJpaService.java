package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.model.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserJpaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostJpaService {

    private final UserJpaService userJpaService;

    private final PostRepository postRepository;

    public PostJpaService(UserJpaService userJpaService, PostRepository postRepository) {
        this.userJpaService = userJpaService;
        this.postRepository = postRepository;
    }

    public Post createPostForUser(int id, Post post){
        var user=userJpaService.findOne(id);
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> retrievePostForUser(int id){
        return userJpaService.findOne(id).getPosts();
    }
}
