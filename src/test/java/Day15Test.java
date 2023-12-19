import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day15Test {

    Day15 day15 = new Day15();

    @Test
    public void Day15Part1() {
        Assertions.assertEquals(511343, day15.part1());
    }

    @Test
    public void Day15Part2() {
        Assertions.assertEquals(294474, day15.part2());
    }

    @Test
    public void Day15Part1Test() {
        Day15 day15Test = new Day15("src/main/Day15Test");
        Assertions.assertEquals(1320, day15Test.part1());
    }

    @Test
    public void Day15Part2Test() {
        Day15 day15Test = new Day15("src/main/Day15Test");
        Assertions.assertEquals(145, day15Test.part2());
    }
}
