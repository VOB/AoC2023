import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day5Test {

    Day5 day5 = new Day5();

    @Test
    public void day5Part1() {
        Assertions.assertEquals(525792406, day5.part1());
    }


    @Test
    public void day5Part2() {
        Assertions.assertEquals(79004094, day5.part2());
    }

    @Test
    public void day5Part1Test() {
        Day5 day5Test = new Day5("src/main/Day5Test");
        Assertions.assertEquals(35, day5Test.part1());
    }

    @Test
    public void day5Part2Test() {
        Day5 day5Test = new Day5("src/main/Day5Test");
        Assertions.assertEquals(46, day5Test.part2());
    }
}
