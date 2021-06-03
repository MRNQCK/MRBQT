package at.shadowbot.utils;

public class Multithreading {
    public static void runAsync(Runnable runnable) {
        (new Thread(runnable)).start();
    }
}
