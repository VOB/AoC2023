import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

    TextParser textParser = new TextParser();
    List<String> input;
    Point north = new Point(-1, 0);
    Point south = new Point(1, 0);
    Point east = new Point(0, 1);
    Point west = new Point(0, -1);
    public Day14() {
        this.input = textParser.lines("src/main/Day14");
    }

    public Day14(String path) {
        this.input = textParser.lines(path);
    }

    public int part1() {
        slideAllRocksNorth();
        return loadOnNorthWall();
    }

    public int part2() {
        List<List<String>> dishList = new ArrayList<>();
        dishList.add(new ArrayList<>(input));

        for (int i=0; i<160; i++) {
            slideAllRocksNorth();
            slideAllRocksWest();
            slideAllRocksSouth();
            slideAllRocksEast();

            if (dishList.contains(input)) {
                int index = dishList.indexOf(input);
                int actualMaxMod = (i-index) % (1000000000-index);
                if (actualMaxMod == 0) {
                    return loadOnNorthWall();
                } else {
                    dishList.add(input);
                }
            } else {
                dishList.add(new ArrayList<>(input));
            }
        }

        return loadOnNorthWall();
    }

    private void slideAllRocksNorth() {
        for (int i=1; i<input.size(); i++) {
            for (int j=0; j<input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'O' ) {
                    slideRock(i, j, north);
                }
            }
        }
    }

    private void slideAllRocksSouth() {
        for (int i=input.size()-2; i>=0; i--) {
            for (int j=0; j<input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'O' ) {
                    slideRock(i, j, south);
                }
            }
        }
    }
    private void slideAllRocksEast() {
        for (int i=0; i<input.size(); i++) {
            for (int j=input.get(i).length()-2; j>=0; j--) {
                if (input.get(i).charAt(j) == 'O' ) {
                    slideRock(i, j, east);
                }
            }
        }
    }
    private void slideAllRocksWest() {
        for (int i=0; i<input.size(); i++) {
            for (int j=1; j<input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'O' ) {
                    slideRock(i, j, west);
                }
            }
        }
    }
    private void slideRock(int i, int j, Point direction) {
        boolean nextSpace = input.get(i+direction.x).charAt(j+direction.y) == '.';
        if (nextSpace) {
            input.set(i+direction.x, input.get(i+direction.x).substring(0, j+direction.y) + 'O' + input.get(i+direction.x).substring(j+direction.y+1));
            input.set(i, input.get(i).substring(0, j) + '.' + input.get(i).substring(j+1));
        }
        if (nextSpace &&
                (direction.equals(north) && i > 1
                || direction.equals(east) && j < input.get(0).length()-2
                || direction.equals(south) && i < input.size()-2
                || direction.equals(west) && j > 1)) {
            slideRock(i+direction.x, j+direction.y, direction);
        }
    }

    private int loadOnNorthWall() {
        int totalLoad = 0;
        for (int i=0; i< input.size(); i++) {
            int rockCount = (int) input.get(i).chars().filter(ch -> ch == 'O').count();
            totalLoad += rockCount * (input.size()-i);
        }
        return totalLoad;
    }
}
