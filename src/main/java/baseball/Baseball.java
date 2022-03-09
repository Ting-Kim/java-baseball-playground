package baseball;

import java.util.Scanner;

public class Baseball {

    private Counter counter;
    private Judge judge;
    private Status status = Status.IN_PROCESS;

    public Baseball(Counter counter, Judge judge) {
        this.counter = counter;
        this.judge = judge;
    }

    void run() {
        // 게임 시작
        String result;
        Reader reader = new Reader();

        while (Status.IN_PROCESS.equals(status)) {
            result = process(reader);
            System.out.println(result);
            isContinue(reader, result);
        }

        System.out.println("게임이 종료되었습니다.");
    }

    private String process(Reader reader) {
        String input;
        System.out.print("숫자를 입력해 주세요 : ");

        try {
            input = reader.scanner.nextLine();
            return judge.check(counter.numbers, input);
        } catch (Exception e) {
            return "잘못된 값을 입력하셨습니다.";
        }
    }

    private void isContinue(Reader reader, String result) {

        String restartOrEnd = null;
        if (result.equals("3스트라이크")) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            restartOrEnd = reader.scanner.nextLine();
        }
        if ("1".equals(restartOrEnd)) {
            counter.regenerate();
        }
        if ("2".equals(restartOrEnd)) {
            status = Status.END;
        }
    }

    public String judge(String expectNumbers) {
        return judge.check(counter.getNumbers(), expectNumbers);
    }

    public Counter getCounter() {
        return counter;
    }

    class Reader {
        private Scanner scanner = new Scanner(System.in);

        private String nextLine() {
            return scanner.nextLine();
        }
    }
}
