package com.testebackend.centraliza.Service;

import com.testebackend.centraliza.Entity.ConversationEntity;
import com.testebackend.centraliza.Entity.UserEntity;
import com.testebackend.centraliza.Repository.ConversationRepository;
import com.testebackend.centraliza.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ConversationEntity createConversation(List<Long> idsUser){
        if(idsUser == null || idsUser.size() != 2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A conversa precisa de 2 usuários");
        }

        ConversationEntity conversation = new ConversationEntity();

        for(Long idUser : idsUser){
            UserEntity user = userRepository.findById(idUser)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário " + idUser + "Não encontrado"));

            conversation.addParticipant(user);
        }
        return conversationRepository.save(conversation);
    }
}
