import java.util.*;

public class Day15 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day15() {
        input = textParser.lines("src/main/Day15");
    }

    public Day15(String path) {
        input = textParser.lines(path);
    }

    public int part1() {
        String[] inputValues = input.get(0).split(",");
        int sumOfHashes = 0;
        for (String inputValue : inputValues) {
            sumOfHashes += hash(inputValue);
        }
        return sumOfHashes;
    }

    public int part2() {
        Map<Integer, List<Lens>> hashMap = new HashMap<>();
        String[] inputValues = input.get(0).split(",");

        for (String inputValue : inputValues) {
            String id = inputValue.split("-")[0].split("=")[0];
            int hash = hash(id);
            char operation;
            if (inputValue.contains("=")) {
                operation = '=';
            } else {
                operation = '-';
            }

            List<Lens> hashMapEntry = hashMap.get(hash);
            if (hashMapEntry != null) {
                for (int i=0; i<hashMapEntry.size(); i++) {
                    if (hashMapEntry.get(i).id.equals(id)) {
                        hashMapEntry.remove(i);
                        if (operation == '=') {
                            hashMapEntry.add(i, new Lens(id, Integer.parseInt(inputValue.substring(inputValue.length()-1))));
                        }
                    }
                }
            } else {
                hashMapEntry = new ArrayList<>();
            }
            if (operation == '=') {
                Lens newLens = new Lens(id, Integer.parseInt(inputValue.substring(inputValue.length()-1)));

                if (hashMapEntry.stream().noneMatch(x -> x.id.equals(id))) {
                    hashMapEntry.add(newLens);
                }
            }
            if (!hashMapEntry.isEmpty()) {
                hashMap.put(hash, hashMapEntry);
            }
        }

        int totalFocusingPower = 0;
        for (int i=0; i<256; i++) {
            List<Lens> lensList = hashMap.get(i);

            if (lensList != null && !lensList.isEmpty()) {
                for (int slot=0; slot < lensList.size(); slot++) {
                    totalFocusingPower += (i+1)*(slot+1)*(lensList.get(slot).focalLength);
                }
            }
        }

        return totalFocusingPower;
    }

    private int hash(String input) {
        int currentValue = 0;

        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            currentValue += c;
            currentValue *= 17;
            currentValue %= 256;
        }

        return currentValue;
    }

    private static class Lens {

        String id;
        int focalLength;

        public Lens(String id, int focalLength) {
            this.id = id;
            this.focalLength = focalLength;
        }


    }
}
