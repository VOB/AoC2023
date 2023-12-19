import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {

    TextParser textParser = new TextParser();

    List<String> input;
    int inputWidth;
    int inputHeight;
    Map<Point, List<Integer>> gears = new HashMap<>();

    public Day3() {
        input = textParser.lines("src/main/Day3");
        inputWidth = input.get(0).length();
        inputHeight = input.size();
    }

    public Day3(String path) {
        this.input = textParser.lines(path);
        inputWidth = input.get(0).length();
        inputHeight = input.size();
    }

    public int part1() {
        int sum = 0;

        for (int xCoord = 0; xCoord < inputHeight; xCoord++) {
            String line = input.get(xCoord);
            for (int yCoord = 0; yCoord < line.length(); yCoord++) {
                if (yCoord != 0 && Character.isDigit(line.charAt(yCoord-1))) {
                    //do nothing.
                }
                else if (Character.isDigit(line.charAt(yCoord))) {
                    sum += partNumber(xCoord, yCoord);
                }
            }
        }
        return sum;
    }

    public int part2() {
        int sum = 0;

        for (int xCoord = 0; xCoord < inputHeight; xCoord++) {
            String line = input.get(xCoord);
            for (int yCoord = 0; yCoord < line.length(); yCoord++) {
                if (yCoord != 0 && Character.isDigit(line.charAt(yCoord-1))) {
                    //do nothing.
                }
                else if (Character.isDigit(line.charAt(yCoord))) {
                    partNumber(xCoord, yCoord);
                }
            }
        }

        for (List<Integer> partsWithGears : gears.values()) {
            if (partsWithGears.size() == 2) {
                sum += (partsWithGears.get(0) * partsWithGears.get(1));
            }
        }
        return sum;
    }


    public int partNumber(int xCoord, int yCoord) {
        StringBuilder partNumberAsString = new StringBuilder();
        List<Point> numberCoordinates = new ArrayList<>();

        int offset = 0;
        while (yCoord+offset < inputWidth && Character.isDigit(input.get(xCoord).charAt(yCoord+offset))) {
            partNumberAsString.append(input.get(xCoord).charAt(yCoord + offset));
            numberCoordinates.add(new Point(xCoord, yCoord+offset));
            offset++;
        }

        int partNumber = Integer.parseInt(partNumberAsString.toString());
        String surroundingSymbols = getSurroundingSymbols(numberCoordinates, partNumber);
        if (!hasSymbols(surroundingSymbols)) {
            return 0;
        }

        return partNumber;
    }

    public String getSurroundingSymbols(List<Point> numberCoordinates, int partNumber) {
        int xCoord = numberCoordinates.get(0).x;
        int yCoordMin = numberCoordinates.get(0).y;
        int yCoordMax = numberCoordinates.get(numberCoordinates.size()-1).y;
        StringBuilder surroundingSymbols = new StringBuilder();

        char symbol;
        if (yCoordMin > 0) {
            symbol = input.get(xCoord).charAt(yCoordMin-1);
            if (symbol == '*') {
                List<Integer> partNumbers = gears.get(new Point(xCoord, yCoordMin-1));
                if (partNumbers == null) {
                    partNumbers = new ArrayList<>();
                }
                partNumbers.add(partNumber);
                gears.put(new Point(xCoord,yCoordMin-1), partNumbers);
            }

            surroundingSymbols.append(symbol);
        }

        if (yCoordMax < inputWidth-1) {
            symbol = input.get(xCoord).charAt(yCoordMax+1);
            if (symbol == '*') {
                List<Integer> partNumbers = gears.get(new Point(xCoord, yCoordMax+1));
                if (partNumbers == null) {
                    partNumbers = new ArrayList<>();
                }
                partNumbers.add(partNumber);
                gears.put(new Point(xCoord,yCoordMax+1), partNumbers);
            }

            surroundingSymbols.append(symbol);
        }

        //Line above xCoord
        if (xCoord > 0) {
            for (int yCoord=yCoordMin-1; yCoord < yCoordMax+2; yCoord++) {
                if (yCoord >= 0 && yCoord < inputWidth) {
                    symbol = input.get(xCoord-1).charAt(yCoord);
                    if (symbol == '*') {
                        List<Integer> partNumbers = gears.get(new Point(xCoord-1, yCoord));
                        if (partNumbers == null) {
                            partNumbers = new ArrayList<>();
                        }
                        partNumbers.add(partNumber);
                        gears.put(new Point(xCoord-1,yCoord), partNumbers);
                    }
                    surroundingSymbols.append(symbol);
                }
            }
        }

        //Line below xCoord
        if (xCoord < inputHeight-1) {
            for (int yCoord=yCoordMin-1; yCoord < yCoordMax+2; yCoord++) {
                if (yCoord >= 0 && yCoord < inputWidth) {
                    symbol = input.get(xCoord+1).charAt(yCoord);
                    if (symbol == '*') {
                        List<Integer> partNumbers = gears.get(new Point(xCoord+1, yCoord));
                        if (partNumbers == null) {
                            partNumbers = new ArrayList<>();
                        }
                        partNumbers.add(partNumber);
                        gears.put(new Point(xCoord+1,yCoord), partNumbers);
                    }
                    surroundingSymbols.append(input.get(xCoord+1).charAt(yCoord));
                }
            }
        }

        return surroundingSymbols.toString();
    }

    public boolean hasSymbols(String surroundingSymbols) {
        return !surroundingSymbols.replace(".", "").isEmpty();
    }
}
