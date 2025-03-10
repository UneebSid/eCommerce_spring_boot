package com.training.ecommerce.rest.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users"
)
public class User
{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;
@Column(name = "user_name", nullable = false, unique = true)
    private String username;
@Column(name = "email", nullable = false, unique = true)
    private String email;
@Column(nullable = false,unique = true)
    private String password;

@ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
)
@JoinTable(
       name = "user_roles",
        joinColumns = @JoinColumn(
                name = "user_id",
                referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "role_id",
                referencedColumnName = "id"
        )
)
    private Set<Role> roles;

}
