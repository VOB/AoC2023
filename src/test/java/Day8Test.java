import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day8Test {

    Day8 day8 = new Day8();

    @Test
    public void Day8Part1() {
        Assertions.assertEquals(19783, day8.part1());
    }

    @Test
    public void Day8Part1Test() {
        Day8 day8Test = new Day8("src/main/Day8Test");
        Assertions.assertEquals(6, day8Test.part1());
    }

    @Test
    public void Day8Part2() {
        System.out.println(day8.part2());
    }
}
