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
        System.out.println(day1.part2());

        Assertions.assertEquals(29, day1.extractDecodedNumberFromLine("two1nine"));
        Assertions.assertEquals(83, day1.extractDecodedNumberFromLine("eightwothree"));
        Assertions.assertEquals(13, day1.extractDecodedNumberFromLine("abcone2threexyz"));
        Assertions.assertEquals(24, day1.extractDecodedNumberFromLine("xtwone3four"));
        Assertions.assertEquals(42, day1.extractDecodedNumberFromLine("4nineeightseven2"));
        Assertions.assertEquals(14, day1.extractDecodedNumberFromLine("zoneight234"));
        Assertions.assertEquals(76, day1.extractDecodedNumberFromLine("7pqrstsixteen"));
    }
}
