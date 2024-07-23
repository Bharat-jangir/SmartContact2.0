package com.scm.entities;

import jakarta.persistence.*;

@Entity
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String title;


    @ManyToOne
    private Contact contact;
}
