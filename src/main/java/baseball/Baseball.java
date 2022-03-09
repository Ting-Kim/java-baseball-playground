package baseball;

public class Baseball {

    private Counter counter;
    private Judge judge;

    public Baseball(Counter counter, Judge judge) {
        this.counter = counter;
        this.judge = judge;
    }

    void run() {
        // 게임 시작
    }

    public String judge(String expectNumbers) {
        return judge.check(counter.getNumbers(), expectNumbers);
    }

    public Counter getCounter() {
        return counter;
    }
}
