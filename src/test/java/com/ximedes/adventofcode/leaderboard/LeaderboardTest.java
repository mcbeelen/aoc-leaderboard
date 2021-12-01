package com.ximedes.adventofcode.leaderboard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

public class LeaderboardTest {

    public static final Gson GSON = new GsonBuilder().create();

    @Test
    public void itShouldBeAbleToParseJsonFromAdventOfCodeIntoLeaderboard() throws IOException {


        try(InputStream resourceAsStream = this.getClass().getResourceAsStream("/example.json")) {

            Leaderboard leaderboard = GSON.fromJson(readJson(resourceAsStream), Leaderboard.class);

            assertThat(leaderboard.getEvent(), is("2017"));

            assertThat(leaderboard.getMembers().keySet(), hasSize(6));
            assertThat(leaderboard.getMembers().get("185916").getStars(), is(12));
            assertThat(leaderboard.getMembers().get("192427").getStars(), is(10));
        }

    }

    private String readJson(InputStream resourceAsStream) throws IOException {
        return CharStreams.toString(new InputStreamReader(resourceAsStream));
    }
}
