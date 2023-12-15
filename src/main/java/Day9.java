import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day9 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day9() {
        input = textParser.lines("src/main/Day9");
    }

    public Day9(String path) {
        input = textParser.lines(path);
    }

    public long part1() {
        long extrapolatedValueSum = 0;
        for (String line : input) {
            extrapolatedValueSum += extrapolateHistory(line);
        }

        return extrapolatedValueSum;
    }

    public long extrapolateHistory(String line) {
        List<Long> history = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();

        return findNextHistoryValue(history);
    }

    private long findNextHistoryValue(List<Long> history) {
        List<Long> interpolationValues = new ArrayList<>();

        for (int i=1; i<history.size(); i++) {
            interpolationValues.add(history.get(i) - history.get(i-1));
        }

        List<Long> filterZeroes = interpolationValues.stream().filter(i -> i != 0).toList();
        if (filterZeroes.size() != 0) {
            return history.get(history.size()-1) + findNextHistoryValue(interpolationValues);
        } else {
            return history.get(history.size()-1);
        }
    }

    public long part2() {
        long extrapolatedValueSum = 0;
        for (String line : input) {
            extrapolatedValueSum += extrapolateHistoryBeginning(line);
        }

        return extrapolatedValueSum;
    }

    public long extrapolateHistoryBeginning(String line) {
        List<Long> history = new ArrayList<>(Arrays.stream(line.split(" ")).map(Long::parseLong).toList());

        Collections.reverse(history);
        return findNextHistoryValue(history);
    }
}
