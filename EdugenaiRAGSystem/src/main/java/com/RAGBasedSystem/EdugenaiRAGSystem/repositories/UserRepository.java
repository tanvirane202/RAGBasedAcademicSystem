package com.RAGBasedSystem.EdugenaiRAGSystem.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RAGBasedSystem.EdugenaiRAGSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}


