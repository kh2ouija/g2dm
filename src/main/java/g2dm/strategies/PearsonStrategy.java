package g2dm.strategies;

import g2dm.RatingsOperations;
import g2dm.dto.UserAndScore;
import g2dm.dto.UserAndWeight;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

/**
 * @author sp
 */
public class PearsonStrategy extends AbstractSimilarityStrategy {

    @Override
    public BiFunction<Map<String, Double>, Map<String, Double>, Double> getSimilarityFunction() {
        return RatingsOperations::pearsonCorrelation;
    }

    @Override
    public Comparator<UserAndScore> getUserScoreComparator() {
        return (u1, u2) -> Double.compare(u2.getScore(), u1.getScore());
    }

    @Override
    protected List<UserAndWeight> computePie(List<UserAndScore> usersWithScores) {
        double sum = usersWithScores.stream().mapToDouble(UserAndScore::getScore).sum();
        return usersWithScores.stream()
                .map(uws -> new UserAndWeight(uws.getUser(), uws.getScore() / sum))
                .collect(toList());
    }

}
