module com.finalprojects.finalpro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.finalprojects.finalpro to javafx.fxml;
    exports com.finalprojects.finalpro;
}