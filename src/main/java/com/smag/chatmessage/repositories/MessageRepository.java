package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findByIdDiscussion(String idDiscussion);

    @Query("SELECT m FROM Message m , Discussion d where m.idDiscussion=d.idDiscussion and d.transmetter=?1 and d.receiver=?1 and  m.idMessage=?1")
    public Message findMessage(String transmetter,String receiver, String idMessage);
}
