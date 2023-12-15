import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day11() {
        input = textParser.lines("src/main/Day11");
        expandGalaxy();
    }

    public Day11(String path) {
        input = textParser.lines(path);
        expandGalaxy();
    }

    private void expandGalaxy() {
        for (int i=input.size()-1; i>=0; i--) {
            if (!input.get(i).contains("#")) {
                input.add(i, input.get(i));
            }
        }
        for (int i=input.get(0).length()-1; i>=0; i--) {
            addColumnsOfSpace(i);
        }
    }

    private void addColumnsOfSpace(int column) {
        for (String s : input) {
            if (s.charAt(column) == '#') {
                return;
            }
        }
        List<String> newInput = new ArrayList<>();
        for (String s : input) {
            newInput.add(s.substring(0, column) + '.' + s.substring(column));
        }
        input = newInput;
    }

    public int part1() {
        List<Point> galaxyLocations = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            for (int j=0; j<input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '#') {
                    galaxyLocations.add(new Point(i,j));
                }
            }
        }

        int sumOfLengths = 0;
        for (int i=0; i<galaxyLocations.size(); i++) {
            Point current = galaxyLocations.get(i);
            for (int j=i+1; j<galaxyLocations.size(); j++) {
                Point other = galaxyLocations.get(j);
                sumOfLengths += distanceBetweenGalaxies(current, other);
            }
        }
        return sumOfLengths;
    }

    private int distanceBetweenGalaxies(Point current, Point other) {
        int distance = 0;
        if (current.x < other.x) {
            distance = other.x - current.x;
        } else {
            distance = current.x - other.x;
        }
        if (current.y < other.y) {
            distance += other.y - current.y;
        } else {
            distance += current.y - other.y;
        }
        return distance;
    }

    public int part2() {
        return 0;
    }
}
