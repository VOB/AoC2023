import java.util.List;

public class Day7 {

    TextParser textParser = new TextParser();
    List<String> input;

    public Day7() {
        input = textParser.lines("src/main/Day7");
    }

    public int part1() {

        return 0;
    }

    public int part2() {
        return 0;
    }

    //High card returns 1, Five of a kind returns 7.
    public int handType(String hand) {

        int countCards = countCardsInHand(hand);
        if (countCards == 5) {
            return 7; //five of a kind
        }
        if (countCards == 4) {
            return 6; //four of a kind
        }
        if (countCards == 3) {
            String subHand = getSubHand(hand);
            int subHandCount = countCardsInHand(subHand);
            if (subHandCount == 2) {
                return 5; //full house tree of a kind and pair
            } else {
                return 4; //three of a kind
            }
        }
        if (countCards == 2) {
            String subHand = getSubHand(hand);
            int subHandCount = countCardsInHand(subHand);
            if (subHandCount == 3) {
                return 5; //full house two pair and tree of a kind
            } else if (subHandCount == 2){
                return 3; //two pair
            } else {
                String innerSubHand = getSubHand(subHand);
                int innerSubHandCount = countCardsInHand(innerSubHand);
                if (innerSubHandCount == 2) {
                    return 3; //two pair
                } else {
                    return 2; //one pair
                }
            }
        }
        if (countCards == 1) {
            String subHand = getSubHand(hand);
            int subHandCount = countCardsInHand(subHand);
            if (subHandCount == 4) {
                return 6; //four of a kind
            } else if (subHandCount == 3) {
                return 4; //three of a kind
            } else if (subHandCount == 2) {
                //possible: one pair, two pair
                String innerSubHand = getSubHand(subHand);
                int innerSubHandCount = countCardsInHand(innerSubHand);
                if (innerSubHandCount == 2) {
                    return 3; //two pair
                } else {
                    return 2; //one pair
                }
            } else {
                //possible: three of a kind, one pair, high card
                subHand = getSubHand(subHand);
                subHandCount = countCardsInHand(subHand);
                if (subHandCount == 3) {
                    return 4; //three of a kind
                } else if (subHandCount == 2) {
                    return 2; //one pair
                } else {
                    subHand = getSubHand(subHand);
                    subHandCount = countCardsInHand(subHand);
                    if (subHandCount == 2) {
                        return 2; //one pair
                    } else {
                        return 1; //one of a kind
                    }
                }
            }
        }

        return 0;
    }

    private String getSubHand(String hand) {
        return hand.replaceAll(String.valueOf(hand.charAt(0)), "");
    }

    private int countCardsInHand(String subHand) {
        return (int) subHand.chars().filter(ch -> ch == subHand.charAt(0)).count();
    }

    private int cardStrength(char card) {
        if (Character.isDigit(card)) {
            return Integer.parseInt("" + card);
        }

        return switch (card) {
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> 0;
        };
    }
}
