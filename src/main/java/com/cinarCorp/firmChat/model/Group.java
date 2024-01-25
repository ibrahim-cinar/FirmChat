package com.cinarCorp.firmChat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "tbl_groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseEntity {

    private String groupName;
    private String description;
    private String groupImage;
    private String groupAdmin;
    @ManyToMany(mappedBy = "groups",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> messages;


}
