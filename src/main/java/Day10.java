import java.util.*;

public class Day10 {

    TextParser textParser = new TextParser();
    char[][] input;

    Map<Character, List<Character>> shapes = new HashMap<>();

    public Day10() {
        List<String> lines = textParser.lines("src/main/Day10");
        input = new char[lines.get(0).length()][lines.size()];
        for (int i=0; i<lines.size(); i++) {
            input[i] = lines.get(i).toCharArray();
        }

        addShapes();
    }

    public int part1() {
        List<List<Integer>> positions = collectPositions(input);

        return (int) (positions.size()/2D);
    }

    public int part2() {
        return 0;
    }

    private List<List<Integer>> collectPositions(char[][] input) {
        List<Integer> startingPosition = findStartingPosition(input);
        List<List<Integer>> positions = findInitialDirection(startingPosition);
        positions = addNextPosition(List.of(startingPosition), startingPosition);

        return positions;
    }

    private List<List<Integer>> findInitialDirection(List<Integer> startingPosition) {
        if (startingPosition.get(0) < input.length) {
            char southCharacter = input[startingPosition.get(0)][startingPosition.get(1)];
            if (southCharacter == '|' || southCharacter == 'L' || southCharacter == 'J') {
                return List.of(startingPosition, List.of(startingPosition.get(0), startingPosition.get(1)));
            }
        }

        return List.of(startingPosition);
    }

    private List<List<Integer>> addNextPosition(List<List<Integer>> positions, List<Integer> previous) {
        List<Integer> startingPosition = positions.get(0);

        return positions;
    }

    private List<Integer> findStartingPosition(char[][] input) {
        for (int i=0; i<input.length;i++) {
            for (int j=0; j<input[i].length; j++){
                if (input[i][j] == 'S'){
                    return List.of(i, j);
                }
            }
        }
        //No S present in input
        return List.of();
    }

    private void addShapes() {
        shapes.put('|', List.of('n', 's'));
        shapes.put('-', List.of('e', 'w'));
        shapes.put('L', List.of('n', 'e'));
        shapes.put('J', List.of('n', 'w'));
        shapes.put('7', List.of('s', 'w'));
        shapes.put('F', List.of('s', 'e'));
    }
}
