package ru.denisss.footballplayerscatalog.entity;

import javax.persistence.*;
import java.text.ParseException;

@Entity
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private Gender gender;
    private String birthday;

    @ManyToOne
    private Team team;
    private Country country;

    public Footballer() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = Country.valueOf(country);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(String teamName) {
        this.team = new Team(teamName);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws ParseException {
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", team=" + team +
                ", country=" + country +
                '}';
    }
}
