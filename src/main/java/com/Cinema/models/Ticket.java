package com.Cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private Seance seance;
    @Setter
    private int place;

    public Ticket(int place){
        this.place = place;
    }

    @Override
    public String toString(){
        return "Hall: " + seance.getHall().getName() +
                "place: " + place +
                 "Data: " + seance.getDate() +
                "Film: " + seance.getMovie().getName();
    }
}