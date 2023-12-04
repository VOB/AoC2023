import java.util.List;

public class Day2 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day2() {
        input = textParser.lines("src/main/Day2");
    }


    public int part1() {
        int sum = 0;
        for (String line : input) {
            sum += sumOfAllPossibleGameIds(line);
        }
        return sum;
    }

    private int sumOfAllPossibleGameIds(String line) {
        if (playOneGame(line)) {
            return extractGameId(line);
        }

        return 0;
    }

    private int extractGameId(String line) {
        String game = line.split(":")[0];
        return Integer.parseInt(game.split("Game ")[1]);
    }

    private boolean playOneGame(String line) {
        String allRounds = line.split(": ")[1];
        for( String round : allRounds.split(";")) {
            if (!roundIsPossible(round)) {
                return false;
            }
        }
        return true;
    }

    private boolean roundIsPossible(String line) {
        String[] sortedCubes = line.split(",");
        for (String singleColorCubes : sortedCubes ) {
            if (singleColorCubes.contains("blue")) {
                if (14 < cubeCount(singleColorCubes, "blue")) {
                    return false;
                }
            }
            if (singleColorCubes.contains("red")) {
                if (12 < cubeCount(singleColorCubes, "red")) {
                    return false;
                }
            }
            if (singleColorCubes.contains("green")) {
                if (13 < cubeCount(singleColorCubes, "green")) {
                    return false;
                }
            }
        }
        return true;
    }

    private int cubeCount(String stack, String regex) {
        String cubeCount = stack.replace(regex, "").trim();
        return Integer.parseInt(cubeCount);
    }
}
