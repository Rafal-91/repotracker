package com.repotracker.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Generated
@Getter
@Setter
@ToString
@Table(name = "repo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "owner"})
})
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false, unique = true)
    private String name;

    public Repo(){
    }

    public Repo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public Repo(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }
}
