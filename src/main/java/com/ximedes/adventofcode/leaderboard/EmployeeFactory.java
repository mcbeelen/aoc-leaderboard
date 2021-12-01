package com.ximedes.adventofcode.leaderboard;

import static com.ximedes.adventofcode.leaderboard.Employee.EmployeeBuilder.anEmployee;

public class EmployeeFactory {
    public Employee build(Member member) {
        return anEmployee()
                .withId(member.getId())
                .withName(findName(member))
                .withStars(member.getStars())
                .withAvatarUrl(findUrl(member.getId()))
                .build();
    }

    private String findName(Member member) {
        switch (member.getId()) {
            case "185974" : return "Marco Beelen"; // mcbeelen
        }

        return member.getName();
    }

    private String findUrl(String id) {
        switch (id) {
            case "185974" : return "https://ca.slack-edge.com/T0GV7A4PM-U0YBWCEG1-6a2fbd905060-48"; // mcbeelen
            default:
                return getRandomAvatar(id);
        }

    }

    private String getRandomAvatar(String id) {

        if (Integer.valueOf(id) % 2 == 0) {
            return "https://api.adorable.io/avatars/48/" + id + ".png";
        }

        return "https://robohash.org/" + id;
    }
}
