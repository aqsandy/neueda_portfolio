package com.citi.portfolio.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="jokes")
public class joke implements Serializable{

    @Column(name="JokeID")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int jokeid;

    @Column(name="joke")
    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getJokeid() {
        return jokeid;
    }

    public void setJokeid(int jokeid) {
        this.jokeid = jokeid;
    }

    public joke(){};
}
