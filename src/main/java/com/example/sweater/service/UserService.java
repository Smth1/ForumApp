package com.example.sweater.service;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;
        }
    }

    public boolean addUser(User user) {
        User userFromDb = this.userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            this.userRepo.save(user);
            this.sendMessage(user);
            return true;
        }
    }

    public void addAdmin(User user) {
        user.setRoles(Set.of(Role.USER, Role.ADMIN));
        userRepo.save(user);
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s \nWelcome to Sweater. Please visit next link: http://localhost:8080/activate/%s", user.getUsername(), user.getActivationCode());
            this.mailSender.send(user.getEmail(), "Activation code", message);
        }

    }

    public boolean activateUser(String code) {
        User user = this.userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        } else {
            user.setActivationCode((String)null);
            this.userRepo.save(user);
            return true;
        }
    }

    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Enum::name).collect(Collectors.toSet());
        user.getRoles().clear();
        Iterator var5 = form.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        this.userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = email != null && !email.equals(userEmail) || userEmail != null && !userEmail.equals(email);
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        this.userRepo.save(user);
        if (isEmailChanged) {
            this.sendMessage(user);
        }

    }
}
