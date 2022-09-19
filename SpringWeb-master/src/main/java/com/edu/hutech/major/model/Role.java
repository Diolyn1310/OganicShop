package com.edu.hutech.major.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    
    @OneToMany(mappedBy = "role")
    private List<UserRole> usersRole;
    
    public Role (int id, String name) {
    	this.id = id;
    	this.name = name;
    }
    
}//create table mapping trong db
