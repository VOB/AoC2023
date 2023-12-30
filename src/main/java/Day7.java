import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day7 {

    TextParser textParser = new TextParser();
    List<String> input;
    List<Hand> hands = new ArrayList<>();

    public Day7() {
        this("src/main/Day7");
    }

    public Day7(String path) {
        input = textParser.lines(path);
        for (String line : input) {
            String cards = line.split(" ")[0];
            int bet = Integer.parseInt(line.split(" ")[1]);
            hands.add(new Hand(cards, bet, getHandType(cards)));
        }
    }

    public int part1() {
        LinkedList<Hand> sortedHandList = new LinkedList<>();

        for (Hand hand : hands) {
            insertHandInSortedList(hand, sortedHandList);
        }

        int totalWinnings = 0;
        for (int i=0; i<sortedHandList.size(); i++) {
            totalWinnings += sortedHandList.get(i).bet*(i+1);
        }
        return totalWinnings;
    }

    public int part2() {
        return 0;
    }

    private void insertHandInSortedList(Hand hand, LinkedList<Hand> sortedList) {

        for (int i=0; i<sortedList.size(); i++) {
            Hand sortedHand = sortedList.get(i);

            if (handComparator(hand, sortedHand) < 1) {
                sortedList.add(i, hand);
                return;
            }
        }
        //sortedList is empty
        sortedList.add(hand);
    }

    private LinkedList<String> insertHandInSortedList(String line, LinkedList<String> sortedList) {
        String newHand = line.split(" ")[0];

        for (int i=0; i<sortedList.size(); i++) {
            String sortedHand = sortedList.get(i).split(" ")[0];

            if (handComparator(newHand, sortedHand) < 1) {
                sortedList.add(i, line);
                return sortedList;
            }
        }

        //sortedList is empty
        sortedList.add(line);
        return sortedList;
    }

    //returns 1 if hand1 > hand2, -1 if hand2 > hand1, else 0
    public int handComparator(String hand1, String hand2) {

        if (getHandType(hand1) < getHandType(hand2)) {
            return -1;
        }
        if (getHandType(hand1) > getHandType(hand2)) {
            return 1;
        }

        for (int i=0; i < 5; i++) {
            if (cardStrength(hand1.charAt(i)) < cardStrength(hand2.charAt(i))) {
                return -1;
            } else if (cardStrength(hand1.charAt(i)) > cardStrength(hand2.charAt(i))) {
                return 1;
            }
        }
        return 0;
    }

    public int handComparator(Hand hand1, Hand hand2) {
        if (hand1.handType < hand2.handType) {
            return -1;
        }
        if (hand1.handType > hand2.handType) {
            return 1;
        }

        for (int i=0; i<5; i++) {
            if (cardStrength(hand1.cards.get(i)) < cardStrength(hand2.cards.get(i))) {
                return -1;
            } else if (cardStrength(hand1.cards.get(i)) > cardStrength(hand2.cards.get(i))) {
                return 1;
            }
        }
        return 0;
    }

    //High card returns 1, Five of a kind returns 7.
    public int getHandType(String hand) {

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

    private static class Hand {
        List<Character> cards;
        int bet;
        int handType;

        public Hand (String cards, int bet, int handType) {
            this.cards = new ArrayList<>();
            for (Character c: cards.toCharArray()) {
                this.cards.add(c);
            }
            this.bet = bet;
            this.handType = handType;
        }

    }
}
