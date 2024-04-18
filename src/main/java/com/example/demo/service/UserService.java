package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
}
