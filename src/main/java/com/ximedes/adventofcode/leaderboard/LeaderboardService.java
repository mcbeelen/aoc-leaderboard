package com.ximedes.adventofcode.leaderboard;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.http.client.fluent.Request.Get;

import java.io.IOException;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardService.class);

    private static final String LEADERBOARD_AS_JSON_URL = "https://adventofcode.com/2020/leaderboard/private/view/0000000.json";
    private static final String SESSION = "your_cookie";

    private CookieStore cookieStore;

    private final Gson gson = new GsonBuilder().create();

    public LeaderboardService() {
        cookieStore = new BasicCookieStore();
        BasicClientCookie sessionCookie = new BasicClientCookie("session", this.SESSION);
        sessionCookie.setDomain("adventofcode.com");
        cookieStore.addCookie(sessionCookie);
    }

    public Optional<Leaderboard> loadLeaderboard() {
        try {
            try {
                return of(gson.fromJson(fetchLeaderboardAsJson(), Leaderboard.class));
            } catch (JsonSyntaxException e) {
                LOGGER.error("Data received from private leaderboard isn't JSON", e);
                LOGGER.warn(fetchLeaderboardAsJson());
                return empty();
            }
        } catch (IOException e) {
            LOGGER.error("Failed to loadLeaderboard data", e);
            return empty();
        }
    }

    public String fetchLeaderboardAsJson() throws IOException {
        Executor executor = Executor.newInstance();
        executor.use(cookieStore);

        Request request = Get(LEADERBOARD_AS_JSON_URL)
                .connectTimeout(5000)
                .socketTimeout(5000);
        return executor.execute(request)
                .returnContent().asString();

    }
}
