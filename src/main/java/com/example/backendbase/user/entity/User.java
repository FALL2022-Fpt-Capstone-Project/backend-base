package com.example.backendbase.user.entity;

import com.example.backendbase.manager.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
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
    @JsonProperty("user_name")
    private String username;

    @Column(name = "encrypted_password")
    @JsonIgnore
    private String ePassword;

    @Column(name = "identity_id")
    @JsonIgnore
    private int identityId;

    @Column(name = "full_name")
    @JsonProperty("full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column(name = "created_date")
    @JsonProperty("created_date")
    private Timestamp createdDate;

    @Column(name = "is_owner", columnDefinition = "BOOL")
    @ColumnDefault("FALSE")
    @JsonProperty("is_owner")
    private Boolean isOwner;

    @Column(name = "is_deactive", columnDefinition = "BOOL")
    @ColumnDefault("FALSE")
    @JsonProperty("is_deactive")
    private Boolean isDeactive;

    @JoinTable(name = "tb.user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonProperty("roles")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("address_id")
    private Address address;


}
