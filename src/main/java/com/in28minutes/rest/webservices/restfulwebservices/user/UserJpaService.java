package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import com.in28minutes.rest.webservices.restfulwebservices.model.Post;
import com.in28minutes.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJpaService {

    private final UserRepository userRepository;



    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("user " + id + " not found");
        }
        return user.get();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }




}
