import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class Day10Test {

    Day10 day10 = new Day10();

    @Test
    public void Day10Part1() {
        Assertions.assertEquals(6815, day10.part1());
    }

    @Test
    public void Day10Part2() {
        Assertions.assertEquals(269, day10.part2());
    }

    @Test
    public void Day10Part1Test() {
        Day10 day10Test = new Day10("src/main/Day10Test");
        Assertions.assertEquals(4, day10Test.part1());
    }

    @Test
    public void verifyTraversal() {
        Day10 day10Test = new Day10("src/main/Day10Test");
        String path = "S|L-J|7-S";
        StringBuilder actualPath = new StringBuilder();
        List<Point> points = day10Test.collectPositions(day10Test.input);
        for(Point point : points) {
            actualPath.append(day10Test.input[point.x][point.y]);
        }
        Assertions.assertEquals(path, actualPath.toString());
    }

    @Test
    public void Day10Part2Test() {
        Day10 day10Test = new Day10("src/main/Day10Test");
        Assertions.assertEquals(1, day10Test.part2());
    }
}
