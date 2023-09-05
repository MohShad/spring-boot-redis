package br.com.mohshad.service;

import br.com.mohshad.model.entity.User;
import br.com.mohshad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

}
