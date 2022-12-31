package com.backend.repository;

import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE email LIKE ':email' and password LIKE ':password'", nativeQuery = true)
    User getUsuario(@Param("email")String email,@Param("password")String password);

    boolean existsByEmailAndPassword(String email,String password);
}
