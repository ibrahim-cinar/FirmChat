package com.cinarCorp.firmChat.repository;

import com.cinarCorp.firmChat.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,String> {
}
