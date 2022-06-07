module pl.edu.pw.ee.jimp.Application {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.edu.pw.ee.jimp.Application to javafx.fxml;
    exports pl.edu.pw.ee.jimp.Application;
}
