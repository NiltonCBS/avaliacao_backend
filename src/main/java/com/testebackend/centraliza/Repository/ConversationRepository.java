package com.testebackend.centraliza.Repository;

import com.testebackend.centraliza.Entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
}
