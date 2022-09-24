package com.example.backendbase.user.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb.identity")
@Getter
@Setter
@RequiredArgsConstructor
public class Identity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "identity_date")
    private String identityDate;

    @Column(name = "identity_published_address")
    private String identityPublishedAddress;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "image", length = 2^31-1 )
    private String image;

}
