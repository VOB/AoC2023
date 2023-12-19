import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    Day3 day3 = new Day3();

    @Test
    public void day3Part1() {
        Assertions.assertEquals(535235, day3.part1());
    }

    @Test
    public void day3Part1Test() {
        Day3 day3Test = new Day3("src/main/Day3Test");
        Assertions.assertEquals(4361, day3Test.part1());
    }

    @Test
    public void day3Part2() {
        System.out.println(day3.part2());
    }

    @Test
    public void day3Part2Test() {
        Day3 day3Test = new Day3("src/main/Day3Test");
        Assertions.assertEquals(467835, day3Test.part2());
    }
}
