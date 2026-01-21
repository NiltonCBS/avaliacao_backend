package com.testebackend.centraliza.Service;

import com.testebackend.centraliza.Entity.ConversationEntity;
import com.testebackend.centraliza.Entity.MessageEntity;
import com.testebackend.centraliza.Entity.UserEntity;
import com.testebackend.centraliza.Repository.ConversationRepository;
import com.testebackend.centraliza.Repository.MessageRepository;
import com.testebackend.centraliza.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Transactional
    public MessageEntity sendMessage(Long idConversation, Long idUser, String contentMessage){
        ConversationEntity conversation = conversationRepository.findById(idConversation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada"));

        UserEntity user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        boolean isParticipant = conversation.getParticipantsConversation()
                .stream()
                .anyMatch(participant -> participant.getId().equals(idUser));

        if(!isParticipant){
            throw new SecurityException("O usuário não pertence a está conversa.");
        }

        MessageEntity message = new MessageEntity();
        message.setConversationEntity(conversation);
        message.setUserEntity(user);
        message.setContentMessage(contentMessage);
        message.setTimestamp(Instant.now());

        return messageRepository.save(message);
    }

    public List<MessageEntity> getMessagesByConversation(Long idConversation) {
        if (!conversationRepository.existsById(idConversation)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada");
        }
        return messageRepository.findMessagesByConversation(idConversation);
    }

    public String getLastMessageContent(Long idConversation) {
        if (!conversationRepository.existsById(idConversation)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversa não encontrada");
        }

        return messageRepository.findLastMessage(idConversation)
                .map(MessageEntity::getContentMessage)
                .orElse("Nenhuma mensagem ainda");
    }

}
