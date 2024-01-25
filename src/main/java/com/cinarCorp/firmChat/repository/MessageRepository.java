package com.cinarCorp.firmChat.repository;

import com.cinarCorp.firmChat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
}
