import java.util.ArrayList;
import java.util.List;

public class BreakingRecords {

    public static void main(String[] args) {

        List<Integer> scores = new ArrayList<>(List.of(12, 24, 10, 24, 48));
        List<Integer> result = breakingRecordsBasic(scores);
        System.out.printf("Basic | Max: %d Min: %d%n", result.get(0), result.get(1));
        List<Integer> result2 = breakingRecordsModern(scores);
        System.out.printf("Modern | Max: %d Min: %d%n", result2.get(0), result2.get(1));

    }

    public static List<Integer> breakingRecordsBasic(List<Integer> arr) {

        int size = arr.size();
        int minScore = arr.get(0);
        int maxScore = arr.get(0);
        int maxBroken = 0;
        int minBroken = 0;

        for (int i = 1; i < size; i++) {
            int target = arr.get(i);
            if (target < minScore) {
                minScore = target;
                minBroken++;
            }
            if (target > maxScore) {
                maxScore = target;
                maxBroken++;
            }
        }

        return List.of(maxBroken, minBroken);

    }

    /**
     * 
     * @param arr
     * @return
     */
    public static List<Integer> breakingRecordsModern(List<Integer> scores) {
        if (scores.isEmpty())
            return List.of(0, 0);

        var initialScore = scores.get(0);
        var state = new Object() {
            int maxRecord = initialScore;
            int minRecord = initialScore;
            int maxBreaks = 0;
            int minBreaks = 0;
        };

        // Process remaining games with streams
        scores.stream()
                .skip(1) // Skip first game (baseline)
                .forEach(score -> {
                    if (score > state.maxRecord) {
                        state.maxBreaks++;
                        state.maxRecord = score;
                    } else if (score < state.minRecord) {
                        state.minBreaks++;
                        state.minRecord = score;
                    }
                });

        return List.of(state.maxBreaks, state.minBreaks);
    }

}
