import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day9Test {

    Day9 day9 = new Day9();

    @Test
    public void Day9Part1() {
        System.out.println(day9.part1());
    }

    @Test
    public void Day9Part1Test() {
        Day9 day9Test = new Day9("src/main/Day9Test");
        Assertions.assertEquals(114, day9Test.part1());
    }

    @Test
    public void extrapolateHistoryTest() {
        Assertions.assertEquals(18, day9.extrapolateHistory("0 3 6 9 12 15"));
        Assertions.assertEquals(28, day9.extrapolateHistory("1 3 6 10 15 21"));
    }

    @Test
    public void Day9Part2() {
        System.out.println(day9.part2());
    }
}
