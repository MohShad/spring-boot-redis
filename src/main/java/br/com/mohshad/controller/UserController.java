package br.com.mohshad.controller;

import br.com.mohshad.model.ApiResponseDTO;
import br.com.mohshad.model.entity.User;
import br.com.mohshad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDTO> createUser(@Validated @RequestBody User user) {
        try {
            this.userService.saveUser(user);
            return new ResponseEntity(new ApiResponseDTO(true, "User created."),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return this.handleException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                return new ResponseEntity(user, HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponseDTO(false, "User not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return this.handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleException(Exception e) {
        logger.error(String.valueOf(e));
        return new ResponseEntity<>(new ApiResponseDTO(false, "Operation Failed."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
