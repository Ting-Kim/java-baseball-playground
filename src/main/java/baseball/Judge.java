package baseball;

public class Judge {

    public String check(String counterNumbers, String expectNumbers) {

        if (expectNumbers.length() != 3 || !expectNumbers.matches("^[1-9]*$")
            || isDuplicated(expectNumbers)) {
            throw new IllegalArgumentException("Invalid numbers: length(" +
                    expectNumbers.length() + ") numbers(" + expectNumbers + ")");
        }

        // (Strike, Ball, isStrike(1=true, 0=false))
        int[] countOfStrikeAndBall = {0, 0, 0};

        for (int idx = 0; idx < 3; idx++) {
            countStrikes(counterNumbers, expectNumbers, idx, countOfStrikeAndBall);
            countBalls(counterNumbers, expectNumbers, idx, countOfStrikeAndBall);
        }

        return decideHint(countOfStrikeAndBall[0], countOfStrikeAndBall[1]);
    }

    private String decideHint(int strikes, int balls) {
        String hint = "";
        if (strikes == 0 && balls == 0) {
            return "낫싱";
        }
        if (balls > 0) {
            hint = balls + "볼";
        }
        if (strikes == 0) {
            return hint;
        }
        if (balls == 0) {
            return strikes + "스트라이크";
        }

        hint = hint + " " + strikes + "스트라이크";
        return hint;
    }

    private boolean isDuplicated(String expectedNumbers) {
        if (expectedNumbers.charAt(0) == expectedNumbers.charAt(1)) return true;
        if (expectedNumbers.charAt(0) == expectedNumbers.charAt(2)) return true;
        if (expectedNumbers.charAt(1) == expectedNumbers.charAt(2)) return true;
        return false;
    }

    private void countStrikes(String counterNumbers, String expectNumbers, int idx, int[] countOfStrikeAndBall) {
        if (counterNumbers.charAt(idx) == expectNumbers.charAt(idx)) {
            countOfStrikeAndBall[0]++;
            // isStrike = true
            countOfStrikeAndBall[2] = 1;
            return;
        }
        // isStrike = false
        countOfStrikeAndBall[2] = 0;
    }

    private void countBalls(String counterNumbers, String expectNumbers, int idx, int[] countOfStrikeAndBall) {
        // Strike한 경우 pass
        if (countOfStrikeAndBall[2] == 1) return;
        if (counterNumbers.indexOf(expectNumbers.charAt(idx)) != -1) {
            countOfStrikeAndBall[1]++;
        }
    }
}
