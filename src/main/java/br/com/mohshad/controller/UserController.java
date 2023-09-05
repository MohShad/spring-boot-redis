package br.com.mohshad.controller;

import br.com.mohshad.model.ApiResponseDTO;
import br.com.mohshad.model.entity.User;
import br.com.mohshad.repository.UserRepository;
import br.com.mohshad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            this.userService.saveUser(user);
            return new ResponseEntity(new ApiResponseDTO(true, "User created."),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponseDTO(false, "Failed to create user."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll")
    public List<User> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return users;
    }

    @GetMapping("getById/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {

        try {
            if (!userRepository.findById(id).isPresent()) {
                return new ResponseEntity(new ApiResponseDTO(false, "User ID not found."),
                        HttpStatus.NOT_FOUND);
            }
            Optional<User> user = userService.getUserById(id);

            return new ResponseEntity<User>(user.get(), HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
