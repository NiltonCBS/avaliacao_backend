package com.testebackend.centraliza.DTO;

import java.time.Instant;
import java.util.Set;

public record ConversationResponseDTO(
        Long id,
        Set<UserResponseDTO> participants,
        Instant createdAt,
        String lastMessage
){}
