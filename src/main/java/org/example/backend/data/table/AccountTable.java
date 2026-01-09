package org.example.backend.data.table;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Entity
@Table(name = "account")

public class AccountTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private int age;

    @Column
    private String password;

    @Column
    private String email;

}
