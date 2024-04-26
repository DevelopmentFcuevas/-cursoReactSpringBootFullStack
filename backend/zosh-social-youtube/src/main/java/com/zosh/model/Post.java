package com.zosh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private String image;
    private String video;
    private LocalDateTime createdAt;

    //@JsonIgnore
    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();
    //private List<User> liked;
    //private List<Long> liked = new ArrayList<>();
}
