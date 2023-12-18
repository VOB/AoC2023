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
                input.set(i, input.get(i).replaceAll("\\.", "x"));
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
            newInput.add(s.substring(0, column) + 'x' + s.substring(column+1));
        }
        input = newInput;
    }
    public long part1() {
        return getSumOfDistanceBetweenGalaxies(2);
    }

    public long part2() {
        return getSumOfDistanceBetweenGalaxies(1000000);
    }

    public long getSumOfDistanceBetweenGalaxies(long spaceModifier) {
        List<Point> galaxyLocations = addGalaxyLocations();

        long sumOfLengths = 0;
        for (int i=0; i<galaxyLocations.size(); i++) {
            Point current = galaxyLocations.get(i);
            for (int j=i+1; j<galaxyLocations.size(); j++) {
                Point other = galaxyLocations.get(j);
                sumOfLengths += distanceBetweenGalaxies(current, other, spaceModifier);
            }
        }
        return sumOfLengths;
    }

    private List<Point> addGalaxyLocations() {
        List<Point> galaxyLocations = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            for (int j=0; j<input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '#') {
                    galaxyLocations.add(new Point(i,j));
                }
            }
        }
        return galaxyLocations;
    }

    private long distanceBetweenGalaxies(Point current, Point other, long spaceModifier) {
        long distance = 0;
        int xDistance = Math.abs(current.x - other.x);
        int yDistance = Math.abs(current.y - other.y);

        int stepper = current.x;
        while (xDistance > 0) {
            if (input.get(stepper).charAt(current.y) == 'x') {
                distance += spaceModifier;
            } else {
                distance++;
            }
            if (stepper < other.x) {
                stepper++;
            } else if (stepper > other.y){
                stepper--;
            }
            xDistance--;
        }

        stepper = current.y;
        while (yDistance > 0) {
            if (input.get(current.x).charAt(stepper) == 'x') {
                distance += spaceModifier;
            } else {
                distance++;
            }
            if (stepper < other.y) {
                stepper++;
            } else if (stepper > other.y){
                stepper--;
            }
            yDistance--;
        }

        return distance;
    }
}
