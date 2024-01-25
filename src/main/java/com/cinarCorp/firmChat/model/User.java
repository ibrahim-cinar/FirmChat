package com.cinarCorp.firmChat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_users")
public class User extends BaseEntity {

    @NotBlank
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    private boolean isActive;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name = "authorities",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;
    
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"))
    private List<Group> groups;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> messages;

    public User( String username,
                String password, String firstName, String lastName,
                String email, String phoneNumber,
                boolean isActive, List<Role> authorities) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.authorities = authorities;
    }

    public User(String username, String email, String phoneNumber, List<Group> groups) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.groups = groups;
    }
}
