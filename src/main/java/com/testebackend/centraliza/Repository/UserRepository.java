package com.testebackend.centraliza.Repository;


import com.testebackend.centraliza.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
