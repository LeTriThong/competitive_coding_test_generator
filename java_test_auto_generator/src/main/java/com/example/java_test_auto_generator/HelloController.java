package com.example.java_test_auto_generator;

import com.example.java_test_auto_generator.utility.ProcessResultReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController{
    @FXML
    private TextArea txtAreaInput;

    @FXML
    private TextArea txtAreaOutput;

    @FXML
    private Button btnCompile;

    @FXML
    public void test(ActionEvent event) throws IOException {

        try
        {
            String query = "dir";
            final Process p = Runtime.getRuntime().exec(String.format("cmd /c %s", query));
            final ProcessResultReader stderr = new ProcessResultReader(p.getErrorStream(), "STDERR");
            final ProcessResultReader stdout = new ProcessResultReader(p.getInputStream(), "STDOUT");

            stderr.start();
            stdout.start();
            final int exitValue = p.waitFor();
            if (exitValue == 0)
            {
                System.out.print(stdout);
                txtAreaOutput.setText(stdout.toString());
            }
            else
            {
                System.err.print(stderr);
                txtAreaOutput.setText(stderr.toString());
            }
        }
        catch (final IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }


    }

}