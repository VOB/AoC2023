import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day18 {

    TextParser textParser = new TextParser();
    Set<Edge> edgeSet;

    Point U = new Point(-1,0);
    Point R = new Point(0,1);
    Point D = new Point(1,0);
    Point L = new Point(0,-1);

    public Day18() {
        edgeSet = generateTrench(textParser.lines("src/main/Day18"));
    }

    public Day18(String path) {
        edgeSet = generateTrench(textParser.lines(path));
    }

    public int part1() {
        Set<Point> lagoonCoords = edgeSet.stream().map(x->x.location).collect(Collectors.toSet());
        for (Edge edge : edgeSet) {
            switch (edge.direction) {
                case "U" -> fillLagoon(edge.location, R, lagoonCoords);
                case "R" -> fillLagoon(edge.location, D, lagoonCoords);
                case "D" -> fillLagoon(edge.location, L, lagoonCoords);
                case "L" -> fillLagoon(edge.location, U, lagoonCoords);
            }
        }
        return lagoonCoords.size();
    }

    public void fillLagoon(Point point, Point direction, Set<Point> lagoon) {
        Point maybeLagoonCoord = new Point(point.x+direction.x, point.y+direction.y);
        lagoon.add(maybeLagoonCoord);
        if (!edgeSet.contains(new Edge(maybeLagoonCoord))) {
            fillLagoon(maybeLagoonCoord, direction, lagoon);
        }
    }

    public int part2() {
        return 0;
    }

    public Set<Edge> generateTrench(List<String> lines) {
        Set<Edge> edges = new HashSet<>();
        Point currentLocation = new Point(0,0);

        for (String line : lines) {
            String direction = line.split(" ")[0];
            int steps = Integer.parseInt(line.split(" ")[1]);
            String hexColor = line.split(" ")[2].replaceAll("[#()]","");
            for (int i=0; i<steps; i++) {
                switch (direction) {
                    case "U" -> currentLocation.setLocation(currentLocation.x - 1, currentLocation.y);
                    case "R" -> currentLocation.setLocation(currentLocation.x, currentLocation.y + 1);
                    case "D" -> currentLocation.setLocation(currentLocation.x + 1, currentLocation.y);
                    case "L" -> currentLocation.setLocation(currentLocation.x, currentLocation.y - 1);
                }
                Edge newEdge = new Edge(new Point(currentLocation), direction, hexColor);
                edges.add(newEdge);
            }
        }


        return edges;
    }

    public static class Edge {
        Point location;
        String direction;
        String hexColor;

        public Edge(Point location, String direction, String hexColor) {
            this.location = location;
            this.direction = direction;
            this.hexColor = hexColor;
        }

        public Edge(Point location) {
            this.location = location;
            direction = "";
            hexColor = "";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(location, edge.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location);
        }
    }
}
