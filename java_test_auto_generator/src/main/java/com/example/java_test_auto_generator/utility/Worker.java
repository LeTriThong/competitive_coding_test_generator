package com.example.java_test_auto_generator.utility;

public class Worker extends Thread {
    private final Process process;
    private Integer exitCode = null;

    public Worker(Process process) {
        this.process = process;
    }

    public void run() {
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException ignore) {

        }
    }

    public Integer getExitCode() {
        return exitCode;
    }
}
