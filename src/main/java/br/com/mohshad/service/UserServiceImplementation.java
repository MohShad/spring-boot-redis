package br.com.mohshad.service;

import br.com.mohshad.model.entity.User;
import br.com.mohshad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        giveSomeDelay();
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    @Cacheable(value = "user")
    public Optional<User> getUserById(Long id) {
        giveSomeDelay();
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

    private void giveSomeDelay() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
