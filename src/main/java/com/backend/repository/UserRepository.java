package com.backend.repository;

import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select id from user where email LIKE %1 and password=%2", nativeQuery = true)
    boolean login(String email, String password);

    boolean existsByEmailAndPassword(String email,String password);
}
