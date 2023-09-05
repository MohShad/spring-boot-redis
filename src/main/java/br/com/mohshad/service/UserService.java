package br.com.mohshad.service;

import br.com.mohshad.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);
}
