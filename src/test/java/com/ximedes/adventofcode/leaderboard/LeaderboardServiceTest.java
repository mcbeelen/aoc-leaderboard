package com.ximedes.adventofcode.leaderboard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;

import java.io.IOException;
import org.junit.Test;

public class LeaderboardServiceTest {

    @Test
    public void loadLeaderboard() throws IOException {

        LeaderboardService service = new LeaderboardService();

        assertThat(service.fetchLeaderboardAsJson(), any(String.class) );



    }
}
