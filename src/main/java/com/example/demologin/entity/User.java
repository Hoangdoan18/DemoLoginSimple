package com.example.demologin.entity;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
// Entity data type returned is JSON
@Table(name = "user")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name",nullable = false)
    private String fullname;

    @Column(name = "email", unique = true, columnDefinition = "varchar(200) NOT NULL")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phone",length = 11)
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    //DEFAULT = USER
    @Type(type = "json") // define json type to this field
    @Column(name = "role",columnDefinition = "json NOT NULL")
    private List<String> role;

    @Column(name = "status", columnDefinition = "BOOLEAN NOT NULL")
    private boolean status;

    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;
//    @OneToOne
//    @JoinColumn(name = "identity_card_id")
//    private IdentityCard identityCard;
}
