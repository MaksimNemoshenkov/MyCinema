package models;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    private Seance seance;

    private int place;

    public Ticket(){}

    public Ticket(int place){
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
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