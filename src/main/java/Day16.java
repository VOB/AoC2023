import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day16 {

    TextParser textParser = new TextParser();
    char[][] input;
    Set<Vector> energizedVectors = new HashSet<>();
    Point NORTH = new Point(-1,0);
    Point EAST = new Point(0,1);
    Point SOUTH = new Point(1,0);
    Point WEST = new Point(0,-1);

    public Day16 () {
        List<String> lines = textParser.lines("src/main/Day16");
        input = new char[lines.get(0).length()][lines.size()];
        for (int i=0; i<lines.size(); i++) {
            input[i] = lines.get(i).toCharArray();
        }
    }

    public Day16(String path) {
        List<String> lines = textParser.lines(path);
        input = new char[lines.get(0).length()][lines.size()];
        for (int i=0; i<lines.size(); i++) {
            input[i] = lines.get(i).toCharArray();
        }
    }

    public int part1() {

        traverseSquares(0, 0, EAST);

        List<Point> energizedLocations = new ArrayList<>();
        for (Vector vector : energizedVectors) {
            Point point = new Point(vector.x, vector.y);

            if (!energizedLocations.contains(point)) {
                energizedLocations.add(point);
            }
        }

        return energizedLocations.size();
    }

    public int part2() {
        return 0;
    }

    private void traverseSquares(int x, int y, Point direction) {
        if (x < 0 || y < 0 || x > input.length-1 || y > input[x].length-1 ) {
            return;
        }
        Vector currentSquare = new Vector(x, y, direction.x, direction.y);
        boolean containsCurrentSquare = energizedVectors.stream().anyMatch(location -> location.equals(currentSquare));
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

    private class Vector {

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

        public boolean equals(Vector vector) {
            return vector.x == this.x
                    && vector.y == this.y
                    && vector.xDirection == this.xDirection
                    && vector.yDirection == this.yDirection;
        }
    }
}
