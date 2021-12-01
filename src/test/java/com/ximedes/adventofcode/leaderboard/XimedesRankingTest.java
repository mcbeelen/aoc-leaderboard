package com.ximedes.adventofcode.leaderboard;

import static com.ximedes.adventofcode.leaderboard.LeaderboardTest.GSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.common.io.CharStreams;
import org.junit.Test;

public class XimedesRankingTest {

    @Test
    public void itShouldConvertRawLeaderboardDataToXimedesRanking() throws IOException {

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/example.json");
        Leaderboard leaderboard = GSON.fromJson(readJson(resourceAsStream), Leaderboard.class);

        XimedesRanking ranking = new XimedesRanking(leaderboard);

        Rank first = ranking.getRanking(0);
        assertThat(first.getStars(), is(12));
        assertThat(first.getEmployees(), hasSize(2));

        Rank second = ranking.getRanking(1);
        assertThat(second.getStars(), is(11));
        assertThat(second.getEmployees(), hasSize(1));
        assertThat(second.getPosition(), is(3));

        Rank third = ranking.getRanking(2);
        assertThat(third.getStars(), is(10));
        assertThat(third.getEmployees(), hasSize(2));

        Rank fourth = ranking.getRanking(3);
        assertThat(fourth.getStars(), is(3));
        assertThat(fourth.getEmployees(), hasSize(1));
        assertThat(fourth.getPosition(), is(6));

    }

    private String readJson(InputStream resourceAsStream) throws IOException {
        return CharStreams.toString(new InputStreamReader(resourceAsStream));
    }
}
