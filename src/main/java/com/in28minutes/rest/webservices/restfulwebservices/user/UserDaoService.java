package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> USERS=new ArrayList<>();

    private static int usersCount=0;

    static {
        USERS.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        USERS.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(25)));
        USERS.add(new User(++usersCount,"Jim", LocalDate.now().minusYears(20)));
    }

    public List<User>findAll(){
        return USERS;
    }

    public  User findOne(int id){
        var optionalUser=USERS.stream().filter(user -> user.getId()==id).findFirst();
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("id: "+ id);
        }
        return optionalUser.get();
    }

    public User save(User user){
        user.setId(++usersCount);
        USERS.add(user);
        return user;
    }

    public void deleteById(int id){
        var optionalUser=USERS.removeIf(user -> user.getId()==id);
        if(!optionalUser){
            throw new UserNotFoundException("id: "+ id);
        }
    }
}
