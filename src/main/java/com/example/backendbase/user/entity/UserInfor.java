package com.example.backendbase.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb.user_information")
@Getter
@Setter
@RequiredArgsConstructor
public class UserInfor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "address")
    private String address;
}
