package com.ximedes.adventofcode.leaderboard;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RankingService.class);


    private static final String REFRESH_SCHEDULE = "0 0/15 6-23 * * *";
    private final CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(REFRESH_SCHEDULE);

    private final LeaderboardService leaderboardService;

    private XimedesRanking ximedesRanking = new XimedesRanking(new Leaderboard(emptyMap()));
    private LocalDateTime timestampOfRefresh = now().minusDays(1).withHour(23).withMinute(59);

    @Autowired
    public RankingService(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @PostConstruct
    @Scheduled(cron = REFRESH_SCHEDULE)
    public void refreshRanking() {
        Optional<Leaderboard> leaderboard = leaderboardService.loadLeaderboard();
        if (leaderboard.isPresent()) {
            LOGGER.info("Refreshed the leaderboard");
            updateRanking(leaderboard.get());
        }
    }

    public void updateRanking(Leaderboard leaderboard) {
        ximedesRanking = new XimedesRanking(leaderboard);
        timestampOfRefresh = now(ZoneId.of("Europe/Amsterdam"));
    }

    public LocalDateTime getTimestampOfRefresh() {
        return timestampOfRefresh;
    }

    public LocalDateTime getTimestampOfNextRefresh() {

        Date next = cronSequenceGenerator.next(new Date());
        return LocalDateTime.ofInstant(next.toInstant(), ZoneId.of("Europe/Amsterdam"));
    }
    public List<Rank> getRankings() {
        return ximedesRanking.getRankings()
                .stream().filter((rank -> rank.getStars() > 0)).collect(toList());
    }
}
