module com.example.java_test_auto_generator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.java_test_auto_generator to javafx.fxml;
    exports com.example.java_test_auto_generator;
}