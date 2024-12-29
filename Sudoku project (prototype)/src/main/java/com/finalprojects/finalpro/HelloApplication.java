package com.finalprojects.finalpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PushbackInputStream;

public class HelloApplication extends Application {
    public final double width = getScreenDimensions()[0]/3;
    public final double height = getScreenDimensions()[1]/3;
    //Now all we need to do is set some global constants for our application above the start() function.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add("file:///C:/Users/50vjt/IdeaProjects/finalProjects/src/main/resources/CSS/styles.css");
        stage.setTitle("Sing In");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static double[] getScreenDimensions(){
        //This function will use a JavaFX module called Screen and access the bounds of the primary display
        //being used by the computer.
        Rectangle2D sc = Screen.getPrimary().getBounds();
        double[] dim = new double[2];
        dim[0] = sc.getWidth();
        dim[1] = sc.getHeight();
        return dim;
    }
}