package com.example.java_test_auto_generator;

import com.example.java_test_auto_generator.utility.ProcessResultReader;
import com.example.java_test_auto_generator.utility.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HelloController {

    static String INPUT_CODE_FILE = "build\\Main.java";

    @FXML
    private TextArea txtAreaInput;

    @FXML
    private TextArea txtAreaOutput;

    @FXML
    private Button btnCompile;

    @FXML
    public void onCompile(ActionEvent event) {
        try {
            loadInputCodeToFile();
            boolean isBuildSuccess = buildFile();


            String query = "dir";
//            runCommand(query);
            Result res = executeCommandLine("cmd /c dir", 4000);
            System.out.printf("Process execute completely with exit code %d, runtime %d ms\n", res.exitCode, res.timeExecuted);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void runCommand(String command) throws IOException, InterruptedException {
        final Process p = Runtime.getRuntime().exec(String.format("cmd /c %s", command));

        final ProcessResultReader stderr = new ProcessResultReader(p.getErrorStream(), "STDERR");
        final ProcessResultReader stdout = new ProcessResultReader(p.getInputStream(), "STDOUT");

        stderr.start();
        stdout.start();

        final int exitValue = p.waitFor();
        if (exitValue == 0) {
            txtAreaOutput.appendText(stdout.toString());
        } else {
            txtAreaOutput.appendText(stderr.toString());
        }
    }

    private boolean buildFile() {
        return false;
    }

    public Result executeCommandLine(final String commandLine,
                                            final long timeout)
            throws IOException, InterruptedException, TimeoutException {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec(commandLine);

        final ProcessResultReader stderr = new ProcessResultReader(p.getErrorStream(), "STDERR");
        final ProcessResultReader stdout = new ProcessResultReader(p.getInputStream(), "STDOUT");
        stderr.start();
        stdout.start();

        Worker worker = new Worker(p);
        worker.start();
        long from = System.currentTimeMillis();
        long to = from;

        System.out.println(from);
        try {
            worker.join(timeout);
            to = System.currentTimeMillis();
            System.out.println(to);
            Integer exitCode = worker.getExitCode();
            if (exitCode == null || !exitCode.equals(0)) {
                txtAreaOutput.appendText(stderr.toString());
                throw new TimeoutException();
            }
            else {
                txtAreaOutput.appendText(stdout.toString());
            }
        } catch (InterruptedException ex) {
            worker.interrupt();
            Thread.currentThread().interrupt();
            throw ex;
        } finally {
            p.destroyForcibly();
        }
        return new Result(worker.getExitCode(), to - from);
    }


    private void loadInputCodeToFile() throws IOException {
        String txtInput = txtAreaInput.getText();
        File file = new File(INPUT_CODE_FILE);

        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(txtInput);
        bufferedWriter.close();

        System.out.println("File has been written in " + INPUT_CODE_FILE);
    }

    public static class Result {
        Integer exitCode;
        long timeExecuted;

        public Result(int exitCode, long timeExecuted) {
            this.exitCode = exitCode;
            this.timeExecuted = timeExecuted;
        }
    }

}