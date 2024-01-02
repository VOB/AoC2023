import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day13Test {

    Day13 day13 = new Day13();

    @Test
    public void Day13Part1() {
        Assertions.assertEquals(33975, day13.part1());
    }

    @Test
    public void Day13Part2() {
        System.out.println(day13.part2());
    }

    @Test
    public void Day13Part1Test() {
        Day13 day13Test = new Day13("src/main/Day13Test");
        Assertions.assertEquals(405, day13Test.part1());
    }

    @Test
    public void Day13Part2Test() {
        Day13 day13Test = new Day13("src/main/Day13Test");
        Assertions.assertEquals(400, day13Test.part2());
    }
}
