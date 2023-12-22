import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day12() {
        input = textParser.lines("src/main/Day12");
    }

    public Day12(String path) {
        input = textParser.lines(path);
    }

    public int part1() {
        int sum = 0;
        for (String line : input) {
            String springs = line.split(" ")[0];
            List<Integer> damagedGroups = Arrays.stream(line.split(" ")[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            sum += getPossibleArrangements(springs, damagedGroups);
        }
        return sum;
    }

    private int getPossibleArrangements(String line, List<Integer> damagedGroups) {
        int possibleArrangements = 0;
        while (line.length() > damagedGroups.size()) {
            possibleArrangements+=getOnePossibleArrangement(line, new ArrayList<>(damagedGroups));
            line = line.substring(1);
        }
        return possibleArrangements;
    }

    private int getPossibleArrangementsv2(String line, List<Integer> damagedGroups) {
        List<String> segments = Arrays.asList(line.split("\\."));
        int possibleArrangements = 0;


        while (line.length() > damagedGroups.size()) {
            possibleArrangements+=getOnePossibleArrangement(line, new ArrayList<>(damagedGroups));
            line = line.substring(1);
        }


        return possibleArrangements;
    }

    private int getOnePossibleArrangement(String line, List<Integer> damagedGroups) {

        while (line.charAt(0) == '.') {
            line = line.substring(1);
        }

        int minimumLength = damagedGroups.size()-1;
        for (int damaged : damagedGroups) {
            minimumLength+= damaged;
        }

        if (line.length() < minimumLength) {
            return 0;
        }

        int damagedGroup = damagedGroups.get(0);
        for (int i=0; i<damagedGroup; i++) {
            if (line.charAt(i) == '.') {
                return getOnePossibleArrangement(line.substring(i), damagedGroups);
            }
        }
        damagedGroups.remove(0);
        if (damagedGroups.isEmpty()) {
            return 1;
        }

        return getOnePossibleArrangement(line.substring(damagedGroup+1), damagedGroups);
    }

    public int part2() {
        return 0;
    }
}
