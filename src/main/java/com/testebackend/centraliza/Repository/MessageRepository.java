package com.testebackend.centraliza.Repository;

import com.testebackend.centraliza.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    //Usei para estudar o @Query e estudei o Derived Query
    //Nos estudos o que mais senti familiaridade foi o @Query, uma que consigo ter mais controle e outra que visualmente fica mais visível a interpretação
    @Query(value = "SELECT * FROM message WHERE id_conversation_fk = :idConversation ORDER BY timestamp ASC",
            nativeQuery = true)
    List<MessageEntity> findMessagesByConversation(@Param("idConversation") Long idConversation);

    @Query(value = "SELECT * FROM message WHERE id_conversation_fk = :idConversation ORDER BY timestamp DESC LIMIT 1",
            nativeQuery = true)
    Optional<MessageEntity> findLastMessage(@Param("idConversation") Long idConversation);

    //Deixei comentado para mostrar como ficou
    //List<MessageEntity> findByConversationEntityIdOrderByTimestampAsc(Long idConversation);
    //Optional<MessageEntity> findFirstByConversationEntityIdOrderByTimestampDesc(Long idConversation);
}
