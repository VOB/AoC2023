import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    Day3 day3 = new Day3();

    @Test
    public void day3Part1() {
        System.out.println(day3.part1());
    }

    @Test
    public void day3Part1Test() {
        List<String> input = new ArrayList<>();
        input.add("467..114..");
        input.add("...*......");
        input.add("..35..633.");
        input.add("......#...");
        input.add("617*......");
        input.add(".....+.58.");
        input.add("..592.....");
        input.add("......755.");
        input.add("...$.*....");
        input.add(".664.598..");

        Day3 day3Test = new Day3(input);
        System.out.println(day3Test.part1());
    }

    @Test
    public void validateThatSurroundingSymbolsAreCorrect() {
        List<String> input = new ArrayList<>();
        input.add("abc");
        input.add("d9e");
        input.add("fgh");

        Day3 day3Test = new Day3(input);

        Assertions.assertEquals("bd", day3Test.getSurroundingSymbols(0, 0));
        Assertions.assertEquals("be", day3Test.getSurroundingSymbols(0, 2));
        Assertions.assertEquals("deabcfgh", day3Test.getSurroundingSymbols(1,1));
        Assertions.assertEquals("ge", day3Test.getSurroundingSymbols(2,2));
        Assertions.assertEquals("gd", day3Test.getSurroundingSymbols(2,0));
    }
}
