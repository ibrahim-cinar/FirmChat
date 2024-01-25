package com.cinarCorp.firmChat.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseEntity {

    private String groupName;
    private String description;
    private String groupImage;
    private String groupAdmin;
    private List<User> users;

}
