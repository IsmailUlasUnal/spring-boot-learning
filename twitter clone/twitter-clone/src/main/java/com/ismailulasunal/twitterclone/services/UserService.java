package com.ismailulasunal.twitterclone.services;

import com.ismailulasunal.twitterclone.exceptions.EmailAlreadyTakenException;
import com.ismailulasunal.twitterclone.exceptions.UserDoesNotExistException;
import com.ismailulasunal.twitterclone.models.ApplicationUser;
import com.ismailulasunal.twitterclone.dtos.RegisterationObject;
import com.ismailulasunal.twitterclone.models.Role;
import com.ismailulasunal.twitterclone.repositories.RoleRepository;
import com.ismailulasunal.twitterclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApplicationUser updateUser(ApplicationUser user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();

        }
    }
    public ApplicationUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public ApplicationUser registerUser(RegisterationObject registerationObject) {
        ApplicationUser user = new ApplicationUser();
        user.setFirstName(registerationObject.getFirstName());
        user.setLastName(registerationObject.getLastName());
        user.setEmail(registerationObject.getEmail());
        user.setDateOfBirth(registerationObject.getDob());

        String name = user.getFirstName() + user.getLastName();

        boolean nameTaken = true;
        String tempName = "";

        while (nameTaken) {
            tempName = generateUsername(name);
            if (userRepository.findByUsername(tempName).isEmpty())
                nameTaken = false;
        }

        user.setUsername(tempName);


        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        user.setAuthorities(roles);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }

    }

    public void generateEmailVerification(String username) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);

        user.setVerification(generateVerificationNumber());
    }

    private Long generateVerificationNumber() {
        return (long) Math.floor(Math.random() * 100_000_000);
    }

    private String generateUsername(String name) {
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }


}
