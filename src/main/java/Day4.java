import java.util.Arrays;
import java.util.List;

public class Day4 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day4() {
        input = textParser.lines("src/main/Day4");
    }

    public Day4(String input) {
        this.input = textParser.lines(input);
    }

    public int part1() {
        int sum = 0;

        for (String line : input) {
            sum += calculateCardPoints(line.split(": ")[1]);
        }

        return sum;
    }

    private int calculateCardPoints(String line) {
        int totalMatches = getMatchesForCard(line);

        if (totalMatches == 0) {
            return 0;
        }

        return (int) Math.pow(2, totalMatches-1);
    }

    public int part2() {
        int amountOfCards = 0;

        for (int i=0; i<input.size(); i++) {
            amountOfCards++;
            amountOfCards += calculateCardsWon(i);
        }

        return amountOfCards;
    }

    private int calculateCardsWon(int index) {
        return calculateCardsWon(index, 0);
    }

    private int calculateCardsWon(int index, int totalSum) {
        int cardMatches = getMatchesForCard(input.get(index).split(": ")[1]);
        totalSum += cardMatches;

        index++;
        int count = 0;
        while (index < input.size() && count < cardMatches) {
            totalSum += calculateCardsWon(index, 0);
            index++;
            count++;
        }

        return totalSum;
    }

    private int getMatchesForCard(String line) {
        int totalMatches = 0;
        List<Integer> winningNumbers = extractNumbers(line.split("\\|")[0]);
        List<Integer> numbersYouHave = extractNumbers(line.split("\\|")[1]);

        for (int winningNumber : winningNumbers) {
            if (numbersYouHave.contains(winningNumber)) {
                totalMatches++;
            }
        }

        return totalMatches;
    }

    private List<Integer> extractNumbers(String numberString) {
        String trimmedNumberString = numberString.replace("  ", " ").trim();
        return Arrays.stream(trimmedNumberString.split(" "))
                .map(Integer::parseInt).toList();
    }
}
