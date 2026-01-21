package com.testebackend.centraliza.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_message")
    private Long id;

    @Column(length = 500)
    private String contentMessage;

    private Instant timestamp = Instant.now();

    @ManyToOne
    @JoinColumn(name = "idUser_fk", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "idConversation_fk", nullable = false)
    private ConversationEntity conversationEntity;
}
