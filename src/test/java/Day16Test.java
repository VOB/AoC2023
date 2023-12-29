import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day16Test {

    Day16 day16 = new Day16();

    @Test
    public void Day16Part1() {
        Assertions.assertEquals(7199, day16.part1());
    }

    @Test
    public void Day16Part2() {
        Assertions.assertEquals(7438, day16.part2());
    }

    @Test
    public void Day16Part1Test() {
        Day16 day16Test = new Day16("src/main/Day16Test");
        Assertions.assertEquals(46, day16Test.part1());
    }

    @Test
    public void Day16Part2Test() {
        Day16 day16Test = new Day16("src/main/Day16Test");
        Assertions.assertEquals(51, day16Test.part2());
    }
}
