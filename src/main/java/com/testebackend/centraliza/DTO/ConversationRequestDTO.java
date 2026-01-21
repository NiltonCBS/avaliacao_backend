package com.testebackend.centraliza.DTO;

import java.util.List;

public record ConversationRequestDTO(List<Long> userIds) {
}
