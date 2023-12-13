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
            marginOfError *= getNumberOfPossibleWaysToWin(BigInteger.valueOf(times.get(i)), BigInteger.valueOf(distances.get(i)));
        }

        return marginOfError;
    }

    public int part2() {
        BigInteger time = new BigInteger(input.get(0).split(": ")[1].replaceAll("\\s", ""));
        BigInteger distance = new BigInteger(input.get(1).split(": ")[1].replaceAll("\\s", ""));

        return getNumberOfPossibleWaysToWin(time, distance);
    }

    public int getNumberOfPossibleWaysToWin(BigInteger raceTime, BigInteger recordDistance) {

        int possibleWays = 0;

        BigInteger buttonHeld = BigInteger.ZERO;

        while (buttonHeld.compareTo(raceTime) < 0) {
            BigInteger distance = buttonHeld.multiply(raceTime.subtract(buttonHeld));
            if (recordDistance.compareTo(distance) < 0) {
                possibleWays++;
            }
            buttonHeld = buttonHeld.add(BigInteger.ONE);
        }

        return possibleWays;
    }


}
