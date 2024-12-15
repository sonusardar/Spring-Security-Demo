package com.example.demo.repository;

import com.example.demo.entity.Blogs;
import com.example.demo.entity.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blogs,Integer> {
    public List<Blogs> findByAuther(UserDtls userDtls);
}