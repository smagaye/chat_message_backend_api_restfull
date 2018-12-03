package com.smag.chatmessage.repositories;

import com.smag.chatmessage.modele.Discussion;
import com.smag.chatmessage.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface DiscussionRepository extends JpaRepository<Discussion,Integer> {
    @Transactional
    public void delete(Discussion discussion);
    @Transactional
    public void deleteByIdDiscussion(String id);
    public Discussion findByTransmetterAndReceiver(String transmitter , String receiver);
    public Discussion findByIdDiscussion(String id);
    public List<Discussion> findByTransmetter(String id);
    public List<Discussion> findByReceiver(String id);
    public List<Discussion> findByTransmetterOrReceiver(String idT,String idR);

    @Query("select d from Discussion d where d.transmetter=?1 or d.receiver=?1")
    List<Discussion> findDiscussionByIdUser(String id);
}
