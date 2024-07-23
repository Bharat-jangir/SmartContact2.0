package com.scm.serrvices;

import com.scm.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean booleanisUserExistByEmail(String email);
    List<User>getAllUSers();

    User getUserByEmail(String email);
}
