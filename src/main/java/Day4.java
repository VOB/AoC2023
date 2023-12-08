import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day4() {
        input = textParser.lines("src/main/Day4");
    }

    public int part1() {
        int sum = 0;
        for (String line : input) {
            sum += calculateCardPoints(line.split(": ")[1]);
        }
        return sum;
    }

    private int calculateCardPoints(String line) {
        int totalMatches = 0;
        List<Integer> winningNumbers = extractNumbers(line.split("\\|")[0]);
        List<Integer> numbersYouHave = extractNumbers(line.split("\\|")[1]);

        for (int winningNumber : winningNumbers) {
            if (numbersYouHave.contains(winningNumber)) {
                totalMatches++;
            }
        }

        if (totalMatches == 0) {
            return 0;
        }

        return (int) Math.pow(2, totalMatches-1);
    }

    private List<Integer> extractNumbers(String numberString) {
        String trimmedNumberString = numberString.replace("  ", " ").trim();
        return Arrays.stream(trimmedNumberString.split(" "))
                .map(Integer::parseInt).toList();
    }
}
