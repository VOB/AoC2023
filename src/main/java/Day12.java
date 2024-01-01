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

        List<Integer> unknownIndexes = new ArrayList<>();
        for (int i=0; i<line.length(); i++) {
            if (line.charAt(i) == '?') {
                unknownIndexes.add(i);
            }
        }

        List<List<Integer>> indexCombinations = getUnknownIndexCombinations(unknownIndexes);
        int possibleArrangements = 0;

        for (List<Integer> indexCombination : indexCombinations) {
            String forLine = line.replaceAll("\\?", "\\.");
            for (int index : indexCombination) {
                forLine = forLine.substring(0, index) + "#" + forLine.substring(index+1);
            }
            if (isArrangementPossible(forLine, damagedGroups)) {
                possibleArrangements++;
            }
        }

        return possibleArrangements;
    }

    private List<List<Integer>> getUnknownIndexCombinations(List<Integer> unknownIndexes) {
        List<List<Integer>> indexCombinations = new ArrayList<>();

        List<Boolean> combinationPatterns = new ArrayList<>();
        for (int i=0; i<unknownIndexes.size();i++) {
            combinationPatterns.add(false);
        }

        while (combinationPatterns.contains(false)) {
            indexCombinations.add(createIndexCombination(combinationPatterns, unknownIndexes));

            for (int i=0; i<combinationPatterns.size(); i++) {
                if (!combinationPatterns.get(i)) {
                    combinationPatterns.set(i, true);
                    break;
                } else {
                    combinationPatterns.set(i, false);
                }
            }
        }

        //All ? is # (not created in while loop)
        indexCombinations.add(unknownIndexes);


        return indexCombinations;
    }

    private List<Integer> createIndexCombination(List<Boolean> combinationPatterns, List<Integer> unknownIndexes) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i=0; i<unknownIndexes.size(); i++) {
            if (combinationPatterns.get(i)) {
                indexes.add(unknownIndexes.get(i));
            }
        }
        return indexes;
    }

    public boolean isArrangementPossible(String line, List<Integer> damagedGroups) {
        if (!line.contains("#")) {
            return false;
        }

        while (line.contains("..")) {
            line = line.replaceAll("\\.\\.", "\\.");
        }

        while (line.charAt(0) == '.') {
            line = line.substring(1);
        }

        while (line.charAt(line.length()-1) == '.') {
            line = line.substring(0, line.length()-1);
        }

        List<String> lineList = Arrays.asList(line.split("\\."));

        List<Integer> parsedLine = lineList.stream().map(String::length).toList();

        return parsedLine.equals(damagedGroups);
    }

    public int part2() {
        return 0;
    }
}
