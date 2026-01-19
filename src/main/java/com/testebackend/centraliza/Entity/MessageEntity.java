package com.testebackend.centraliza.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long id;

    private String contentMessage;

    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "idUser_fk")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "idConversarion_fk")
    private ConversationEntity conversationEntity;
}
