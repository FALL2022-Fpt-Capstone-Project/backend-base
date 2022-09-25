package com.example.backendbase.user.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "tb.user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    public User(Long id, String username, String ePassword) {
        this.id = id;
        this.username = username;
        this.ePassword = ePassword;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", length = 16)
    private String username;

    @Column(name = "encrypted_password")
    private String ePassword;

    @Column(name = "identity_id")
    private int identityId;

    @Column(name = "user_infor_id")
    private int userInforId;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @JoinTable(name = "tb.user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
