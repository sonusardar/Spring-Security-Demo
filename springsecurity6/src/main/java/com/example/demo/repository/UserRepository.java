package com.example.demo.repository;

import com.example.demo.entity.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserDtls,Integer> {



    public boolean existsByEmail(String email);

    public UserDtls findByEmail(String email);

}
