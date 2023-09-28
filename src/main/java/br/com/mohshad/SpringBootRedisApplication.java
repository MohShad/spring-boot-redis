package br.com.mohshad;

import br.com.mohshad.model.entity.User;
import br.com.mohshad.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRedisApplication {

    private UserRepository userRepository;

    public SpringBootRedisApplication(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            User user01 = new User(1l, "John", "Software Engineer");
            userRepository.save(user01);
            User user02 = new User(2l, "Woo", "Database Admin");
            userRepository.save(user02);
            User user03 = new User(3l, "Mike", "DevOps Engineer");
            userRepository.save(user03);
            User user04 = new User(4l, "Mohammad", "Software Engineer");
            userRepository.save(user04);
        };
    }
}
