package com.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table (name = "seances")
public class Seance {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Setter
    private String date;

    @Setter
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public Seance (String date){
        tickets = new ArrayList<>();
        this.date = date;
    }

     public void addTicket(Ticket ticket){
        tickets.add(ticket);
     }

     public void removeTicket(Ticket ticket){
        tickets.remove(ticket);
     }

    @Override
    public String toString(){
        return "Hall: " + hall.getName() +
                "Data: " + date +
                "Film: " + movie.getName();
    }
}