package com.finalprojects.finalpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class HelloController {

    @FXML
    private TextField us;

    @FXML
    private PasswordField ps;

    @FXML
    private Button submit;

    @FXML
    private TextArea textArea = new TextArea();

    @FXML
    private static Sudoku sudoku = new Sudoku();

    @FXML
    private Label gametitle;

    //Make a 2Darray S0~S8
    public static int[][] S0 = sudoku.getBoard();
    public static int[][] X1 = {{0,0,1},{1,0,0},{0,1,0}};
    public static int[][] X2 = {{0,1,0},{0,0,1},{1,0,0}};
    public static int[][] S1 = Sudoku.solution(X2,S0);
    public static int[][] S2 = Sudoku.solution(X1,S0);
    public static int[][] S3 = Sudoku.solution(S0,X1);
    public static int[][] S4 = Sudoku.solution(Sudoku.solution(X2,S0),X1);
    public static int[][] S5 = Sudoku.solution(Sudoku.solution(X1,S0),X1);
    public static int[][] S6 = Sudoku.solution(S0,X2);
    public static int[][] S7 = Sudoku.solution(Sudoku.solution(X2,S0),X2);
    public static int[][] S8 = Sudoku.solution(Sudoku.solution(X1,S0),X2);

//  @FXML
//    public void login() {
////        System.out.print("TEST!");
//        String username = us.getText();
//        String password = ps.getText();
//        System.out.println(username + " ---> " + password);
//
//    }
    //Get screen size
    public final double width = getScreenDimensions()[0]/3;
    public final double height = getScreenDimensions()[1]/3;
    //Copy of the board
    public static int[][] realBoard = makeBoard();
    public static int[][] copyBoard = deepCopy(realBoard);

//    public static void main(String[] args) {
//        print2DIntArray(realBoard);
//        print2DIntArray(copyBoard);
//
//    }

    @FXML
    private void login() throws IOException {
        //sub screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sudoku.fxml"));
        loader.setController(this); //connect the controller
        Group root = new Group();
        Scene newScene = new Scene(root, width, height);
        newScene.getStylesheets().add("file:///C:/Users/50vjt/IdeaProjects/finalProjects/src/main/resources/CSS/styles1.css"); //css
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.setTitle("Sudoku Game");
        stage.setScene(newScene);
        stage.show();

        Text sudokuText = new Text("Sudoku Game"); //Text
        sudokuText.setFont(Font.font("Veranda", 50));
        sudokuText.setX(width); //text box size
        sudokuText.setY(height/3);
        root.getChildren().add(sudokuText);

        Text sudokuex = new Text("Please click enter"); //Text: explain
        sudokuex.setFont(Font.font("Veranda", 20));
        sudokuex.setX(width);
        sudokuex.setY(height/3 + 20);
        root.getChildren().add(sudokuex);

        TextField[][] player = new TextField[9][9]; //print copyBoard (including 0)
        int[][] copyBoard1 = printBoardToTextArea(copyBoard);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                player[i][j] = field;
                if (copyBoard1[i][j] != 0) {
                    field.setText(Integer.toString(copyBoard1[i][j]));
                    field.setDisable(true);
                }
                field.setLayoutX(i * 60 + width);
                field.setLayoutY(j * 60 + height/2);
                field.setFont(Font.font("Veranda", 25));
                field.setPrefColumnCount(1);
                int index = i;
                int index1 = j;
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent action) { //clicking event
                        if(Objects.equals(field.getText(), Integer.toString(realBoard[index][index1]))){
                            System.out.println("Correct");
                            field.setDisable(true);
                            copyBoard1[index][index1] = realBoard[index][index1]; //compared the Board
                            if(Arrays.deepEquals(copyBoard1, realBoard)){ //if real and copy are exactly same
                                Text Cong = new Text("Congratulation!!!!!"); // game is ended
                                Cong.setFont(Font.font("Veranda", 200));
                                Cong.setX(0);
                                Cong.setY(height);
                                root.getChildren().add(Cong);
                            }
                        } else{
                            System.out.println("Wrong");
                        }
                    }
                };
                field.setOnAction(event);
                root.getChildren().add(field);
            }
//        printBoardToTextArea(copyBoard);
        }
    }

    public static double[] getScreenDimensions(){ //screen size
        Rectangle2D scr = Screen.getPrimary().getBounds();
        double[] dim = new double[2];
        dim[0] = scr.getWidth();
        dim[1] = scr.getHeight();


        return dim;
    }

    @FXML
    private int[][] printBoardToTextArea(int[][] copyBoard) {
        Random rand = new Random();

        int a = rand.nextInt(40,60); //remove, they need hint at least 17.
//        int a = 1;
        for(int i = 0; i<a; i++){
            int b = rand.nextInt(9);
            int c = rand.nextInt(9);
            copyBoard[b][c] = 0;
        }

//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                sb.append(copyBoard[i][j]).append(" | ");
//            }
//            sb.append("\n");
//        }

        return copyBoard;
    }

    private static int[][] makeBoard() { //make a random perfect sudoku board
        StringBuilder sb = new StringBuilder();


        int[][] combinedBoard = new int[9][9];


        int[][][] boards = {S0, S1, S2, S3, S4, S5, S6, S7, S8}; //3D array
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int setX = i * 3;  // 0, 3, 6
                int setY = j * 3;  // 0, 3, 6
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        combinedBoard[setX + k][setY + l] = boards[index][k][l]; //9x9 2D array
                    }
                }
                index++;
            }
        }
    return combinedBoard;
    }

    public static int[][] deepCopy(int[][] array1){ //array deep copy cord
        int[][] copy = new int[array1.length][];
        for (int i =0; i< array1.length;i++) {
            copy[i] = array1[i].clone();
        }
        return copy;
    }

    public static void print2DIntArray(int[][] arr){
        System.out.println("***** PRINTING 2D INT ARRAY *****\n");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
