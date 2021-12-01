package com.ximedes.adventofcode.leaderboard;

import java.util.List;

public class Rank {

    private final Integer position;
    private final Integer place;
    private final Integer stars;
    private final List<Employee> employees;
    private final Integer numberOfMembers;


    public Rank(Integer position, Integer place, Integer stars, List<Employee> employees) {
        this.position = position;
        this.place = place;

        this.stars = stars;
        this.employees = employees;
        numberOfMembers = employees.size();
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getStars() {
        return stars;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }
}
