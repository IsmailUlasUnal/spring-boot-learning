package com.ismailulasunal.twitterclone.controllers;

import com.ismailulasunal.twitterclone.exceptions.EmailAlreadyTakenException;
import com.ismailulasunal.twitterclone.exceptions.UserDoesNotExistException;
import com.ismailulasunal.twitterclone.models.ApplicationUser;
import com.ismailulasunal.twitterclone.dtos.RegisterationObject;
import com.ismailulasunal.twitterclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken() {
        return new ResponseEntity<>("The email you provided is already in use.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesntExist() {
        return new ResponseEntity<>("The user you are looking for doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegisterationObject registerationObject) { // RegisterationObject
        return userService.registerUser(registerationObject);
    }

    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body) {
        String username = body.get("username");
        String phone = body.get("phone");

        ApplicationUser user = userService.getUserByUsername(username);
        user.setPhone(phone);

        return userService.updateUser(user);
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> createEmailVerification(@RequestBody LinkedHashMap<String, String> body) {
        userService.generateEmailVerification(body.get("username"));

        return new ResponseEntity<>("Verification code generated, email sent", HttpStatus.OK);
    }

}
