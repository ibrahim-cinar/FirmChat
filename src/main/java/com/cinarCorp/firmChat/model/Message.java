package com.cinarCorp.firmChat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor

public class Message extends BaseEntity {
    private String messageText;
    private List<User> users;
    private List<Group> groups;
}
