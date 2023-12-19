import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day6() {
        input = textParser.lines("src/main/Day6");
    }

    public Day6(String input) {
        this.input = textParser.lines(input);
    }

    public int part1() {
        List<Integer> times = Arrays.stream((input.get(0).split(": ")[1].trim().split("\\s+"))).map(Integer::parseInt).toList();
        List<Integer> distances = Arrays.stream((input.get(1).split(": ")[1].trim().split("\\s+"))).map(Integer::parseInt).toList();

        int marginOfError = 1;

        for (int i = 0; i < times.size(); i++) {
            marginOfError *= getNumberOfPossibleWaysToWin(times.get(i), distances.get(i));
        }

        return marginOfError;
    }

    public int part2() {
        long time = Long.parseLong(input.get(0).split(": ")[1].replaceAll("\\s", ""));
        long distance = Long.parseLong(input.get(1).split(": ")[1].replaceAll("\\s", ""));

        return getNumberOfPossibleWaysToWin(time, distance);
    }

    public int getNumberOfPossibleWaysToWin(long raceTime, long recordDistance) {

        int possibleWays = 0;

        long buttonHeld = 0;

        while (buttonHeld < raceTime) {
            long distance = buttonHeld * (raceTime-buttonHeld);
            if (recordDistance < distance) {
                possibleWays++;
            }
            buttonHeld++;
        }

        return possibleWays;
    }


}
