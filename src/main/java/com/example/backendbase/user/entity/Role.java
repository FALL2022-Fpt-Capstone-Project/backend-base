package com.example.backendbase.user.entity;

import com.example.backendbase.security.enums.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb.role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;
}
