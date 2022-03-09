package baseball;

public class Main {
    public static void main(String[] args) {
        Counter counter = new RobotCounter();
        Judge judge = new Judge();
        Baseball baseball = new Baseball(counter, judge);
        // 프로그램 시작
        baseball.run();
    }
}
