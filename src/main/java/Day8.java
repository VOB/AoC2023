import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String currentNode = "AAA";
        String endNode = "ZZZ";
        int steps = 0;
        int iterator = 0;
        while (!currentNode.equals(endNode)) {
            char direction = directionSequence[iterator];

            String nodeValues = nodes.get(currentNode);
            switch (direction) {
                case 'L' -> currentNode = nodeValues.split(" ")[0];
                case 'R' -> currentNode = nodeValues.split(" ")[1];
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

    public int part2() {
        return 0;
    }
}
