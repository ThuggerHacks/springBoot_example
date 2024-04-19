package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p ORDER BY p.id DESC")
    List<User> findAllOrderByIdDesc();

    @Query("SELECT p FROM User p WHERE p.username = :username")
    Optional<User> findByUsername(String username);
}
