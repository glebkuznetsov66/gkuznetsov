package ru.job4j.threads.analizator;

/**
 * Class for count spaces.
 *
 * @author gkuznetsov.
 * @version 0.1.
 * @since 13.10.2017.
 */
public class SpacesCounter implements Runnable {
    /**
     * Text.
     */
    private String text;
    /**
     * spaces count.
     */
    private int spaces = 0;

    /**
     * Constructor.
     * @param text - text.
     */
    public SpacesCounter(String text) {
        this.text = text;
    }

    /**
     * Run method for start thread.
     */
    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == 32) {
                    spaces++;
                }
            }
            System.out.println(String.format("Count of spaces - %s", spaces));
        } else {
            System.err.println("System timeout from spaces counter");
           return;
        }

    }
}

