import java.awt.*;
import java.util.*;
import java.util.List;

public class Day16 {

    TextParser textParser = new TextParser();
    char[][] input;
    Set<Vector> energizedVectors = new HashSet<>();
    Point NORTH = new Point(-1,0);
    Point EAST = new Point(0,1);
    Point SOUTH = new Point(1,0);
    Point WEST = new Point(0,-1);

    public Day16 () {
        input = textParser.linesAsCharArray("src/main/Day16");
    }

    public Day16(String path) {
        input = textParser.linesAsCharArray(path);
    }

    public int part1() {
        traverseSquares(0, 0, EAST);
        return getEnergizedLocationsCount();
    }

    public int part2() {

        int maxEnergized = 0;
        for (int i=0; i<input.length; i++) {
            traverseSquares(i, 0, EAST);
            if (maxEnergized < getEnergizedLocationsCount()) {
                maxEnergized = getEnergizedLocationsCount();
            }
            energizedVectors = new HashSet<>();
        }
        for (int i=0; i<input.length; i++) {
            traverseSquares(i, input.length-1, WEST);
            if (maxEnergized < getEnergizedLocationsCount()) {
                maxEnergized = getEnergizedLocationsCount();
            }
            energizedVectors = new HashSet<>();
        }
        for (int j=0; j<input[0].length; j++) {
            traverseSquares(0, j, SOUTH);
            if (maxEnergized < getEnergizedLocationsCount()) {
                maxEnergized = getEnergizedLocationsCount();
            }
            energizedVectors = new HashSet<>();
        }
        for (int j=0; j<input[0].length; j++) {
            traverseSquares(input[0].length-1, j, NORTH);
            if (maxEnergized < getEnergizedLocationsCount()) {
                maxEnergized = getEnergizedLocationsCount();
            }
            energizedVectors = new HashSet<>();
        }

        return maxEnergized;
    }

    private int getEnergizedLocationsCount() {
        List<Point> energizedLocations = new ArrayList<>();
        for (Vector vector : energizedVectors) {
            Point point = new Point(vector.x, vector.y);

            if (!energizedLocations.contains(point)) {
                energizedLocations.add(point);
            }
        }
        return energizedLocations.size();
    }

    private void traverseSquares(int x, int y, Point direction) {
        if (x < 0 || y < 0 || x > input.length-1 || y > input[x].length-1 ) {
            return;
        }
        Vector currentSquare = new Vector(x, y, direction.x, direction.y);
        boolean containsCurrentSquare = energizedVectors.contains(currentSquare);
        if (containsCurrentSquare) {
            return;
        } else {
            energizedVectors.add(currentSquare);
        }


        char square = input[x][y];
        switch (square) {
            case '\\' -> {
                if (direction.equals(NORTH)) {
                    traverseSquares(x+WEST.x, y+WEST.y, WEST);
                } else if (direction.equals(SOUTH)) {
                    traverseSquares(x+EAST.x, y+EAST.y, EAST);
                } else if (direction.equals(EAST)) {
                    traverseSquares(x+SOUTH.x,y+SOUTH.y, SOUTH);
                } else {
                    traverseSquares(x+NORTH.x, y+NORTH.y, NORTH);
                }
            }
            case '/' -> {
                if (direction.equals(NORTH)) {
                    traverseSquares(x+EAST.x, y+EAST.y, EAST);
                } else if (direction.equals(SOUTH)) {
                    traverseSquares(x+WEST.x, y+WEST.y, WEST);
                } else if (direction.equals(EAST)) {
                    traverseSquares(x+NORTH.x,y+NORTH.y,NORTH);
                } else {
                    traverseSquares(x+SOUTH.x, y+SOUTH.y, SOUTH);
                }
            }
            case '|' -> {
                if (direction.equals(NORTH) || direction.equals(SOUTH)) {
                    traverseSquares(x+direction.x, y+direction.y, direction);
                } else {
                    traverseSquares(x+NORTH.x, y+NORTH.y, NORTH);
                    traverseSquares(x+SOUTH.x, y+SOUTH.y, SOUTH);
                }
            }
            case '-' -> {
                if (direction.equals(WEST) || direction.equals(EAST)) {
                    traverseSquares(x+direction.x, y+direction.y, direction);
                } else {
                    traverseSquares(x+WEST.x, y+WEST.y, WEST);
                    traverseSquares(x+EAST.x, y+EAST.y, EAST);
                }
            }
            default -> traverseSquares(x+direction.x, y+direction.y, direction);
        }
    }

    private static class Vector {

        int x;
        int y;
        int xDirection;
        int yDirection;

        public Vector(int x, int y, int xDirection, int yDirection) {
            this.x = x;
            this.y = y;
            this.xDirection = xDirection;
            this.yDirection = yDirection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vector vector = (Vector) o;
            return x == vector.x && y == vector.y && xDirection == vector.xDirection && yDirection == vector.yDirection;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, xDirection, yDirection);
        }
    }
}
