package com.testebackend.centraliza.Controller;

import com.testebackend.centraliza.DTO.*;
import com.testebackend.centraliza.Service.ConversationService;
import com.testebackend.centraliza.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<ConversationResponseDTO> create(@RequestBody ConversationRequestDTO dto) {
        var conversation = conversationService.createConversation(dto.userIds());

        var participants = conversation.getParticipantsConversation().stream()
                .map(p -> new UserResponseDTO(p.getId(), p.getNameUser()))
                .collect(Collectors.toSet());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ConversationResponseDTO(conversation.getId(), participants, conversation.getCreatedAt(), null));
    }

    @PostMapping("/{conversationId}/messages")
    public ResponseEntity<MessageResponseDTO> sendMessage(
            @PathVariable Long conversationId,
            @RequestBody MessageRequestDTO dto) {

        var message = messageService.sendMessage(conversationId, dto.senderId(), dto.content());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponseDTO(
                        message.getId(),
                        message.getConversationEntity().getId(),
                        message.getUserEntity().getId(),
                        message.getContentMessage(),
                        message.getTimestamp()
                ));
    }

    @GetMapping("/{conversationId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getMessages(@PathVariable Long conversationId) {

        var messages = messageService.getMessagesByConversation(conversationId);

        var response = messages.stream()
                .map(m -> new MessageResponseDTO(
                        m.getId(),
                        m.getConversationEntity().getId(),
                        m.getUserEntity().getId(),
                        m.getContentMessage(),
                        m.getTimestamp()))
                .toList();

        return ResponseEntity.ok(response);
    }
}
