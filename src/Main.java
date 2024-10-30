import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static int bound = 1_000_000_001;
    static boolean guessed = false;
    static Random random = new Random();
    static int numb = random.nextInt(0,bound);
    static int second = 1;
    static Timer timer = new Timer("Timer");

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int find;
                do {
                    find = random.nextInt(0, bound);
                } while (find != numb);
                System.out.println("Поток угадал число {" + numb + "}");
                guessed = true;
            }
        });
        thread.start();

        TimerTask task = new TimerTask() {
            public void run() {
                if (!guessed) {
                    System.out.println(second);
                    second++;
                }
                else {
                    System.out.println("Таймер остановлен");
                    timer.cancel();
                }
            }
        };

        timer.schedule(task, 0, 1000);
    }
}