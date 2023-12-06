import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day1Test {

    Day1 day1 = new Day1();

    @Test
    public void day1Part1() {
        Assertions.assertEquals(56465, day1.part1());
    }

    @Test
    public void day1Part2() {
        Assertions.assertEquals(55902, day1.part2());
    }
}
