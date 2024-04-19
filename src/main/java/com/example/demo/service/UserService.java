package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User update(User user, long id) {
        Optional<User> user1 = userRepository.findById(id);

        if(user1.isPresent()) {
            User u = user1.get();
            u.setUsername(user.getUsername());
            userRepository.save(u);
            return u;
        }

        return null;
    }

    public Optional<User> Login(String username, String password) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if(user.isPresent()) if (HashingUtil.checkPassword(password, user.get().getPassword())) {
            Optional<User> user1 = Optional.of(user.get());
            return user1;
        }
        return null;
    }
}
