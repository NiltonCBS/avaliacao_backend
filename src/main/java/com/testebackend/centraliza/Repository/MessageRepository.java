package com.testebackend.centraliza.Repository;

import com.testebackend.centraliza.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
