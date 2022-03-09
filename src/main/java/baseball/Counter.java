package baseball;

import java.util.Random;

public abstract class Counter {

    String numbers;
    final int MAX_VALUE = 9;
    final int MIN_VALUE = 1;

    String getNumbers() {
        return numbers;
    }

    void regenerate() {
        numbers = "";

        String nextNumber;
        while (numbers.length() < 3) {
            nextNumber = String.valueOf(new Random().nextInt(MAX_VALUE) + 1);
            appendNumber(nextNumber);
        }
    }

    private void appendNumber(String nextNumber) {
        if (numbers.contains(nextNumber)) return;
        numbers += nextNumber;
    }
}
