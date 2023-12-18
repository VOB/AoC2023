import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 {

    TextParser textParser = new TextParser();
    List<List<String>> input;
    char[] directionSequence;
    Map<String, String> nodes = new HashMap<>();

    public Day8() {
        input = textParser.blocks("src/main/Day8");
        directionSequence = input.get(0).get(0).toCharArray();
        for (String node : input.get(1)) {
            nodes.put(node.split(" =")[0], node.split("= ")[1].replaceAll("[(,)]", ""));
        }
    }

    public Day8(String path) {
        input = textParser.blocks(path);
        directionSequence = input.get(0).get(0).toCharArray();
        for (String node : input.get(1)) {
            nodes.put(node.split(" =")[0], node.split("= ")[1].replaceAll("[(,)]", ""));
        }
    }

    public int part1() {
        String startNode = "AAA";
        List<String> endNode = List.of("ZZZ");
        return stepsToEnd(startNode, endNode);
    }

    public long part2() {
        List<String> startNodes = nodes.keySet().stream()
                .filter(node -> node.charAt(2) == 'A').toList();
        List<String> endNodes = nodes.keySet().stream()
                .filter(node -> node.charAt(2) == 'Z').toList();

        List<Integer> stepsForEach = new ArrayList<>();
        for (String startNode : startNodes) {
            stepsForEach.add(stepsToEnd(startNode, endNodes));
        }

        long leastCommonMultiplier = 1;
        for (Integer steps : stepsForEach) {
            leastCommonMultiplier = lcm(steps, leastCommonMultiplier);
        }

        return leastCommonMultiplier;
    }

    private int stepsToEnd(String startNode, List<String> endNode) {
        int steps = 0;
        int iterator = 0;
        while (!endNode.contains(startNode)) {
            char direction = directionSequence[iterator];

            String nodeValues = nodes.get(startNode);
            switch (direction) {
                case 'L' -> startNode = nodeValues.split(" ")[0];
                case 'R' -> startNode = nodeValues.split(" ")[1];
            }

            steps++;
            if (iterator < directionSequence.length-1) {
                iterator++;
            } else {
                iterator = 0;
            }
        }

        return steps;
    }

    private long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }
}
