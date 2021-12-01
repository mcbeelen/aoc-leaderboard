package com.ximedes.adventofcode.leaderboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

public class XimedesRanking {

    private Comparator<Integer> ci =  Comparator.reverseOrder();
    private final ListMultimap<Integer, Employee> ranking = MultimapBuilder.treeKeys(ci).arrayListValues().build();

    private final List<Integer> starsPerRanking = new ArrayList<>();

    private final EmployeeFactory employeeFactory = new EmployeeFactory();


    public XimedesRanking(Leaderboard leaderboard) {

        leaderboard.getMembers().entrySet().stream()
                .forEach(entry -> ranking.put(entry.getValue().getStars(), employeeFactory.build(entry.getValue())));


        starsPerRanking.addAll(ranking.keySet());

    }


    public Rank getRanking(int place) {
        Integer starsForRank = starsPerRanking.get(place);

        Integer position = 1 + numberOfPlayersWithHigherRank(place);
        return new Rank(position, place, starsForRank, ranking.get(starsForRank));

    }

    private int numberOfPlayersWithHigherRank(int place) {
        if (place == 0 ) {
            return 0;
        }
        return IntStream.range(0, place)
                .map(it -> (getRanking(it).getEmployees().size()))
                .sum();
    }

    public List<Rank> getRankings() {
        return IntStream.range(0, starsPerRanking.size())
                .mapToObj(it -> (getRanking(it)))
                .collect(Collectors.toList());

    }
}
