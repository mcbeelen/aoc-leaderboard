package com.ximedes.adventofcode.leaderboard;

import java.util.Map;

public class Leaderboard {

    private String event;
    private String owner_id;

    private Map<String, Member> members;

    public Leaderboard() {
    }

    public Leaderboard(Map<String, Member> members) {
        this.members = members;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Member> members) {
        this.members = members;
    }
}
