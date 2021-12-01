package com.ximedes.adventofcode.leaderboard;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {

    private final RankingService rankingService;

    @Autowired
    public LeaderboardController(RankingService rankingService) {
        this.rankingService = rankingService;
    }




    @RequestMapping("/")
    String index(Map<String, Object> model) throws IOException {

        Deque<Rank> podiumOrder = new LinkedList<>();


        List<Rank> rankings = rankingService.getRankings();

        for (int i = 0; i < rankings.size(); i++) {
            Rank rank = rankings.get(i);
            if (i % 2 == 0) {
                podiumOrder.addLast(rank);
            } else {
                podiumOrder.addFirst(rank);
            }

        }

        model.put("rankings", podiumOrder);

        model.put("timestampOfRefresh", rankingService.getTimestampOfRefresh());

        model.put("timestampOfNextfRefresh", rankingService.getTimestampOfNextRefresh());

        model.put("releaseVersion", System.getenv("HEROKU_RELEASE_VERSION"));

        return "index";
    }
}
