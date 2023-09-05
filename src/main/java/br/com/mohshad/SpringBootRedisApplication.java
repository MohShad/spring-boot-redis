package br.com.mohshad;

import br.com.mohshad.model.entity.User;
import br.com.mohshad.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRedisApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

    @PostConstruct
    @Transactional
    public void init() {

        User user01 = new User(1l, "Software Engineer", "John");
        this.userRepository.save(user01);
        User user02 = new User(2l, "Database Admin", "Woo");
        this.userRepository.save(user02);
        User user03 = new User(3l, "DevOps Engineer", "Mike");
        this.userRepository.save(user03);
    }
}
