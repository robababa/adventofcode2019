package day04;

import java.util.ArrayList;
import java.util.List;

public class Day04 {
    public static void main(String[] args) {
        int start = 235741;
        int end = 706948;
        int matches = 0;

        for (int i = start; i <= end; i++) {
            if (isValidPassword(i)) {
                matches++;
            }
        }
        System.out.println("Part 1 answer: " + matches);

        matches = 0;

        for (int i = start; i <= end; i++) {
            if (isValidPassword(i) && isValidPart2Password(i)) {
                matches++;
            }
        }
        System.out.println("Part 1 answer: " + matches);
    }

    private static List<Integer> digitList(int candidate) {
        List<Integer> digits = new ArrayList<>();
        digits.add(candidate / 100000);
        digits.add(candidate / 10000 % 10);
        digits.add(candidate / 1000 % 10);
        digits.add(candidate / 100 % 10);
        digits.add(candidate / 10 % 10);
        digits.add(candidate % 10);
        return digits;
    }

    private static boolean isValidPassword(int candidate) {
        List<Integer> digits = digitList(candidate);

        if (digits.get(0) > digits.get(1) ||
                digits.get(1) > digits.get(2) ||
                digits.get(2) > digits.get(3) ||
                digits.get(3) > digits.get(4) ||
                digits.get(4) > digits.get(5)
        ) {
            return false;
        }

        if (digits.get(0) == digits.get(1) ||
                digits.get(1) == digits.get(2) ||
                digits.get(2) == digits.get(3) ||
                digits.get(3) == digits.get(4) ||
                digits.get(4) == digits.get(5)) {
            return true;
        }
        return false;
    }

    private static boolean isValidPart2Password(int candidate) {
        List<Integer> digits = digitList(candidate);
        int previousDigit = -1;
        int matchLength = 0;
        for (int i = 0; i < digits.size(); i++) {
            int d = digits.get(i);
            if (d == previousDigit) {
                matchLength++;
            } else {
                // if this terminates a 2-digit-length match, we're good!
                if (matchLength == 2) {
                    return true;
                }
                matchLength = 1;
                previousDigit = d;
            }
        }
        if (matchLength == 2) {
            return true;
        }
        return false;
    }
}
