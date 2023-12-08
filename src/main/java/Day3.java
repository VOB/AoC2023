import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {

    TextParser textParser = new TextParser();

    List<String> input;
    int inputWidth;
    int inputHeight;

    public Day3() {
        input = textParser.lines("src/main/Day3");
        inputWidth = input.get(0).length();
        inputHeight = input.size();
    }

    public Day3(List<String> input) {
        this.input = input;
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

    public int partNumber(int xCoord, int yCoord) {
        StringBuilder partNumberAsString = new StringBuilder();
        StringBuilder surroundingSymbols = new StringBuilder();

        int offset = 0;
        while (yCoord+offset < inputWidth && Character.isDigit(input.get(xCoord).charAt(yCoord+offset))) {
            partNumberAsString.append(input.get(xCoord).charAt(yCoord + offset));
            surroundingSymbols.append(getSurroundingSymbols(xCoord, yCoord + offset));
            offset++;
        }

        if (!hasSymbols(surroundingSymbols.toString())) {
            return 0;
        }

        return Integer.parseInt(partNumberAsString.toString());
    }

    public String getSurroundingSymbols(int xCoord, int yCoord) {
        StringBuilder surroundingSymbols = new StringBuilder();

        if (yCoord > 0) {
            if (!Character.isDigit(input.get(xCoord).charAt(yCoord-1))) {
                surroundingSymbols.append(input.get(xCoord).charAt(yCoord - 1));
            }
        }

        if (yCoord < inputWidth-1) {
            if (!Character.isDigit(input.get(xCoord).charAt(yCoord+1))) {
                surroundingSymbols.append(input.get(xCoord).charAt(yCoord + 1));
            }
        }

        if (xCoord > 0) {
            for (int offset=-1; offset < 2; offset++) {
                if (yCoord+offset >= 0 && yCoord+offset < inputWidth) {
                    if (!Character.isDigit(input.get(xCoord - 1).charAt(yCoord + offset))) {
                        surroundingSymbols.append(input.get(xCoord - 1).charAt(yCoord + offset));
                    }
                }
            }
        }

        if (xCoord < inputHeight-1) {
            for (int offset=-1; offset < 2; offset++) {
                if (yCoord+offset >= 0 && yCoord+offset < inputWidth) {
                    if (!Character.isDigit(input.get(xCoord + 1).charAt(yCoord + offset))) {
                        surroundingSymbols.append(input.get(xCoord + 1).charAt(yCoord + offset));
                    }
                }
            }
        }
        return surroundingSymbols.toString();
    }

    public boolean hasSymbols(String surroundingSymbols) {
        return !surroundingSymbols.replace(".", "").isEmpty();
    }
}
