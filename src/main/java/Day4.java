import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            sum += calculateCardPoints(line);
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
        List<Integer> cards = input.stream().map(this::getCardIndex).collect(Collectors.toList());
        List<Integer> matchesForCard = input.stream().map(this::getMatchesForCard).toList();

        for (int i=0; i<cards.size(); i++) {
            int cardMatches = matchesForCard.get(cards.get(i)-1);
            while (cardMatches > 0) {
                cards.add(cards.get(i)+cardMatches);
                cardMatches--;
            }
        }
        return cards.size();
    }

    private int getMatchesForCard(String card) {
        int totalMatches = 0;
        int lineIndex = getCardIndex(card);
        List<Integer> winningNumbers = extractNumbers(card.split(": ")[1].split("\\|")[0]);
        List<Integer> numbersYouHave = extractNumbers(card.split(": ")[1].split("\\|")[1]);

        for (int winningNumber : winningNumbers) {
            if (numbersYouHave.contains(winningNumber) && totalMatches < input.size()-lineIndex) {
                totalMatches++;
            }
        }

        return totalMatches;
    }

    private int getCardIndex(String card) {
        return Integer.parseInt(card.split("Card ")[1].trim().split(": ")[0]);
    }

    private List<Integer> extractNumbers(String numberString) {
        String trimmedNumberString = numberString.replace("  ", " ").trim();
        return Arrays.stream(trimmedNumberString.split(" "))
                .map(Integer::parseInt).toList();
    }
}
