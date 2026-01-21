package com.testebackend.centraliza.DTO;

import java.time.Instant;

public record MessageResponseDTO(
        Long id,
        Long conversationId,
        Long senderId,
        String content,
        Instant timestamp
) {}
