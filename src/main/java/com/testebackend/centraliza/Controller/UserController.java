package com.testebackend.centraliza.Controller;

import com.testebackend.centraliza.DTO.ConversationResponseDTO;
import com.testebackend.centraliza.DTO.UserResponseDTO;
import com.testebackend.centraliza.Entity.UserEntity;
import com.testebackend.centraliza.Repository.UserRepository;
import com.testebackend.centraliza.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/{userId}/conversations")
    public List<ConversationResponseDTO> getUserConversations(@PathVariable Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return user.getConversations().stream()
                .map(c -> new ConversationResponseDTO(
                        c.getId(),
                        c.getParticipantsConversation().stream()
                                .map(p -> new UserResponseDTO(p.getId(), p.getNameUser()))
                                .collect(Collectors.toSet()),
                        c.getCreatedAt(),
                        messageService.getLastMessageContent(c.getId()) // Busca o conteúdo aqui
                )).toList();
    }
}
