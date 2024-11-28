package com.example.maternityhome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name="created_at", nullable=false, updatable=false)
    private java.sql.Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    // Getters and Setters
}