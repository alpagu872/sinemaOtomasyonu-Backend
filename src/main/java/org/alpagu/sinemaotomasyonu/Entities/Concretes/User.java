package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.alpagu.sinemaotomasyonu.Business.Concretes.UserServiceImpl;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Enums.Role;

import java.io.Serializable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

@Entity
@Data
@Table(name = "\"user\"")
public class User implements Serializable {

    @Id
    @Column(name = "web_user_id", length = 5)
    private String webUserId;

    @Column(name = "first_name", length = 15)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "email_id", length = 30)
    private String emailId;

    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "password", length = 70, nullable = false)
    private String password;

    @Size(max = 32)
    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "role")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;


}

