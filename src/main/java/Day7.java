import java.util.ArrayList;
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
    }

    public int part1() {
        LinkedList<Hand> sortedHandList = new LinkedList<>();

        for (String line : input) {
            String cards = line.split(" ")[0];
            int bet = Integer.parseInt(line.split(" ")[1]);
            hands.add(new Hand(cards, bet, getHandType(cards)));
        }

        for (Hand hand : hands) {
            insertHandInSortedList(hand, sortedHandList, false);
        }

        int totalWinnings = 0;
        for (int i=0; i<sortedHandList.size(); i++) {
            totalWinnings += sortedHandList.get(i).bet*(i+1);
        }
        return totalWinnings;
    }

    public int part2() {
        LinkedList<Hand> sortedHandList = new LinkedList<>();

        for (String line : input) {
            String cards = line.split(" ")[0];
            int bet = Integer.parseInt(line.split(" ")[1]);
            hands.add(new Hand(cards, bet, getHandTypeWithJokers(cards)));
        }

        for (Hand hand : hands) {
            insertHandInSortedList(hand, sortedHandList, true);
        }

        int totalWinnings = 0;
        for (int i=0; i<sortedHandList.size(); i++) {
            totalWinnings += sortedHandList.get(i).bet*(i+1);
        }
        return totalWinnings;
    }

    private void insertHandInSortedList(Hand hand, LinkedList<Hand> sortedList, boolean hasJokers) {

        for (int i=0; i<sortedList.size(); i++) {
            Hand sortedHand = sortedList.get(i);

            if (handComparator(hand, sortedHand, hasJokers) < 1) {
                sortedList.add(i, hand);
                return;
            }
        }
        //sortedList is empty
        sortedList.add(hand);
    }

    public int handComparator(Hand hand1, Hand hand2, boolean hasJokers) {
        if (hand1.handType < hand2.handType) {
            return -1;
        }
        if (hand1.handType > hand2.handType) {
            return 1;
        }

        for (int i=0; i<5; i++) {
            if (cardStrength(hand1.cards.get(i), hasJokers) < cardStrength(hand2.cards.get(i), hasJokers)) {
                return -1;
            } else if (cardStrength(hand1.cards.get(i), hasJokers) > cardStrength(hand2.cards.get(i), hasJokers)) {
                return 1;
            }
        }
        return 0;
    }

    private int getHandTypeWithJokers(String hand) {
        return 0;
    }

    private int getHandType(String hand) {
        if (checkIfFiveOfAKind(hand)) {
            return 7;
        } else if (checkIfFourOfAKind(hand)) {
            return 6;
        } else if (checkIfFullHouse(hand)) {
            return 5;
        } else if (checkIfThreeOfAKind(hand)) {
            return 4;
        } else if (checkIfTwoPair(hand)) {
            return 3;
        } else if (checkIfOnePair(hand)) {
            return 2;
        } else {
            return 1;
        }
    }

    private boolean checkIfFiveOfAKind(String hand) {
        if (countCardsInHand(hand) == 5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfFourOfAKind(String hand) {
        if (countCardsInHand(hand) == 4) {
            return true;
        } else if (countCardsInHand(hand.substring(1)) == 4) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfFullHouse(String hand) {
        String subHand;
        if (countCardsInHand(hand) == 2) {
            subHand = getSubHand(hand);
            if (countCardsInHand(subHand) == 3) {
                return true;
            }
        }

        if (countCardsInHand(hand) == 3) {
            subHand = getSubHand(hand);
            if (countCardsInHand(subHand) == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfThreeOfAKind(String hand) {
        String subHand = hand;
        while (subHand.length() > 2) {
            if (countCardsInHand(subHand) == 3) {
                return true;
            }
            subHand = subHand.substring(1);
        }
        return false;
    }

    private boolean checkIfTwoPair(String hand) {
        String subHand = hand;

        if (countCardsInHand(subHand) == 2) {
            subHand = getSubHand(subHand);
            if (countCardsInHand(subHand) == 2) {
                return true;
            }
            subHand = getSubHand(subHand);
            return countCardsInHand(subHand) == 2;
        }

        subHand = getSubHand(subHand);
        if (countCardsInHand(subHand) == 2) {
            subHand = getSubHand(subHand);
            return countCardsInHand(subHand) == 2;
        }

        return false;
    }

    private boolean checkIfOnePair(String hand) {
        String subHand = hand;
        while (subHand.length() > 1) {
            if (countCardsInHand(subHand) == 2) {
                return true;
            }
            subHand = subHand.substring(1);
        }
        return false;
    }

    private String getSubHand(String hand) {
        return hand.replaceAll(Character.toString(hand.charAt(0)), "");
    }

    private int countCardsInHand(String subHand) {
        return (int) subHand.chars().filter(ch -> ch == subHand.charAt(0)).count();
    }

    private int cardStrength(char card, boolean hasJokers) {
        if (hasJokers) {
            return switch (card) {
                case 'T' -> 10;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                case 'J' -> 1;
                default -> Character.getNumericValue(card);
            };
        } else {
            return switch (card) {
                case 'T' -> 10;
                case 'J' -> 11;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                default -> Character.getNumericValue(card);
            };
        }
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
