package study;

import java.util.Scanner;

public class Calculator {

    private String[] expression;

    public int calculate() {
        ExpressionReader reader = new ExpressionReader();
        reader.read();

        if (expression.length == 0 || expression.length % 2 == 0) {
            throw new IllegalArgumentException("Invalid expression.");
        }
        int result = 0;
        try {
            result = Integer.parseInt(expression[0]);
            int length = expression.length;
            for (int idx = 1; idx < length; idx+=2) {
                result = calculateOnce(result, idx);
            }
            return result;
        } catch (Exception e) {
            throw new NumberFormatException("Invalid expression: " + e);
        }
    }

    public int calculateOnce(int result, int idx) {
        if ("*".equals(expression[idx])) {
            result *= Integer.parseInt(expression[idx + 1]);
            return result;
        } else if ("+".equals(expression[idx])) {
            result += Integer.parseInt(expression[idx + 1]);
            return result;
        } else if ("-".equals(expression[idx])) {
            result -= Integer.parseInt(expression[idx + 1]);
            return result;
        } else if ("/".equals(expression[idx])) {
            result /= Integer.parseInt(expression[idx + 1]);
            return result;
        }

        throw new IllegalArgumentException("Invalid expression.");
    }

    class ExpressionReader {
        private Scanner scanner = new Scanner(System.in);

        private ExpressionReader() {
        }

        private void read() {
            expression = scanner.nextLine()
                                .split(" ");
        }
    }
}
