package com.zosh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private List<Long> followers = new ArrayList<>();
    private List<Long> followings = new ArrayList<>();

    //@OneToMany(mappedBy = "user")
    //private List<Post> post;

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
    //private List<Post> savedPost;
    //private List<Long> savedPost = new ArrayList<>();
}
