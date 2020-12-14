package com.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString(of={"name", "rating"})
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter
    private String name;
    @Setter
    private int rating;
    @Setter
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seance> seances;

    public Movie(String name, int rating){
        this.name = name;
        this.rating = rating;
        seances = new ArrayList<>();
    }
}