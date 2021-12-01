package com.ximedes.adventofcode.backdoor;

import com.ximedes.adventofcode.leaderboard.Leaderboard;
import com.ximedes.adventofcode.leaderboard.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackdoorController {

    private final RankingService rankingService;

    @Autowired
    public BackdoorController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

//
//    @PostMapping(path = "/backdoor", consumes = "application/json")
//    public String updateLeaderboard(@RequestBody Leaderboard leaderboard) {
//        rankingService.updateRanking(leaderboard);
//        return rankingService.getTimestampOfRefresh().toString();
//    }


}
