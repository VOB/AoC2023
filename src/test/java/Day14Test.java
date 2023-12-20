import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day14Test {

    Day14 day14 = new Day14();

    @Test
    public void Day14Part1() {
        Assertions.assertEquals(109345, day14.part1());
    }

    @Test
    public void Day14Part2() {
        Assertions.assertEquals(112452, day14.part2());
    }

    @Test
    public void Day14Part1Test() {
        Day14 day14Test = new Day14("src/main/Day14Test");
        Assertions.assertEquals(136, day14Test.part1());
    }

    @Test
    public void Day14Part2Test() {
        Day14 day14Test = new Day14("src/main/Day14Test");
        Assertions.assertEquals(64, day14Test.part2());
    }
}
