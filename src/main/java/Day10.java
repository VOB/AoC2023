import java.awt.*;
import java.util.*;
import java.util.List;

public class Day10 {

    TextParser textParser = new TextParser();
    char[][] input;

    Map<Character, List<String>> shapes = new HashMap<>();

    public Day10() {
        input = textParser.linesAsCharArray("src/main/Day10");
        addShapes();
    }

    public Day10(String path) {
        input = textParser.linesAsCharArray(path);
        addShapes();
    }

    public int part1() {
        List<Point> positions = collectPositions(input);

        return (int) (positions.size()/2D);
    }

    public int part2() {
        List<Point> positions = collectPositions(input);
        List<String> loopMatcher = new ArrayList<>();
        loopMatcher.add("F");
        loopMatcher.add("7");
        loopMatcher.add("|");
        loopMatcher.add("S");
        int enclosedByLoop = 0;
        boolean insideLoop = false;
        for (int i=0; i<input.length; i++) {
            for (int j=0; j<input[i].length; j++) {
                Point pointMatcher = new Point(i, j);
                if (positions.stream().anyMatch(pointMatcher::equals) && loopMatcher.contains("" + input[i][j])) {
                    insideLoop = !insideLoop;
                }
                if (insideLoop && positions.stream().noneMatch(pointMatcher::equals)) {
                    enclosedByLoop++;
                }
            }
        }
        return enclosedByLoop;
    }

    protected List<Point> collectPositions(char[][] input) {
        Point startingPosition = findStartingPosition(input);
        List<Point> positions = findInitialDirection(startingPosition);
        positions = traverseLoop(positions);

        return positions;
    }

    private List<Point> findInitialDirection(Point startingPosition) {
        if (startingPosition.x < input.length) {
            char southCharacter = input[startingPosition.x+1][startingPosition.y];
            if (southCharacter == '|' || southCharacter == 'L' || southCharacter == 'J') {
                return List.of(startingPosition, new Point(startingPosition.x+1, startingPosition.y));
            }
        }
        if (startingPosition.x > 0) {
            char northCharacter = input[startingPosition.x-1][startingPosition.y];
            if (northCharacter == '|' || northCharacter == '7' || northCharacter == 'F') {
                return List.of(startingPosition, new Point(startingPosition.x+1, startingPosition.y));
            }
        }

        if (startingPosition.y < input[startingPosition.x].length) {
            char eastCharacter = input[startingPosition.x][startingPosition.y+1];
            if (eastCharacter == '-' || eastCharacter == 'J' ||  eastCharacter == '7') {
                return List.of(startingPosition, new Point(startingPosition.x, startingPosition.y+1));
            }
        }

        //Assumption that there exists a loop, therefore no need to check 4th direction

        return List.of(startingPosition);
    }

    private List<Point> traverseLoop(List<Point> inputList) {
        List<Point> positions = new ArrayList<>(inputList);

        while (true) {
            Point currentPoint = positions.get(positions.size()-1);
            Point previousPoint = positions.get(positions.size()-2);
            char currentCharacter = input[currentPoint.x][currentPoint.y];

            List<String> directions = new ArrayList<>(shapes.get(currentCharacter));

            if (currentPoint.x < previousPoint.x) {
                directions.remove("s");
            }
            if (currentPoint.x > previousPoint.x) {
                directions.remove("n");
            }
            if (currentPoint.y < previousPoint.y) {
                directions.remove("e");
            }
            if (currentPoint.y > previousPoint.y) {
                directions.remove("w");
            }

            switch (directions.get(0)) {
                case "n" -> positions.add(new Point(currentPoint.x-1, currentPoint.y));
                case "s" -> positions.add(new Point(currentPoint.x+1, currentPoint.y));
                case "e" -> positions.add(new Point(currentPoint.x, currentPoint.y+1));
                case "w" -> positions.add(new Point(currentPoint.x, currentPoint.y-1));
            }

            if (input[positions.get(positions.size()-1).x][positions.get(positions.size()-1).y] == 'S') {
                return positions;
            }
        }
    }

    private Point findStartingPosition(char[][] input) {
        for (int i=0; i<input.length;i++) {
            for (int j=0; j<input[i].length; j++){
                if (input[i][j] == 'S'){
                    return new Point(i,j);
                }
            }
        }
        //No S present in input
        return new Point(-1,-1);
    }

    private void addShapes() {
        shapes.put('|', List.of("n", "s"));
        shapes.put('-', List.of("e", "w"));
        shapes.put('L', List.of("n", "e"));
        shapes.put('J', List.of("n", "w"));
        shapes.put('7', List.of("s", "w"));
        shapes.put('F', List.of("s", "e"));
    }
}
