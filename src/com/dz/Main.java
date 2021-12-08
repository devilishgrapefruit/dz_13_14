package com.dz;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    public static int click = 1; // номер нажатия
    final static String x = "X";
    final static String o = "O";
    public static int[][] table = new int[3][3];

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j]= - 1; // игровая таблица
                // изначально заполняем -1
            }
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tic-Tac-Toe");
        GridPane board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(board, 300, 300);
        stage.setScene(scene);
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                board.add(getButton(stage), row, column); // создаем кнопки
        stage.show();
    }
   public static boolean checkWin(int is_x) { // проверка, есть ли победитель
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == is_x && table[i][1] == is_x &&
                    table[i][2] == is_x) ||
                    (table[0][i] == is_x && table[1][i] == is_x &&
                            table[2][i] == is_x))
                return true;
        if ((table[0][0] == is_x && table[1][1] == is_x &&
                table[2][2] == is_x) ||
                (table[2][0] == is_x && table[1][1] == is_x &&
                        table[0][2] == is_x))
            return true;
        return false;
    }
    private static Button getButton(Stage stage) {
        Button b = new Button(" "); // создаем и стилизуем кнопку
        b.setPrefSize(100,100);
        b.setStyle("-fx-font-size: 2em; ");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int is_x = -1; // изначально ячейка пуста (-1)
                if (click % 2 != 0) { // номер нажатия нечетный
                    is_x = 1;  // заполняем ячейку X
                    b.setText(x);
                } else {
                    is_x = 0;  // заполняем ячейку O
                    b.setText(o);
                }
                int row = GridPane.getRowIndex(b); // получим номер строки и столбца
                int column = GridPane.getColumnIndex(b);
                table[row][column] = is_x;
                Text gameover = new Text("Ничья");
               if (checkWin(is_x)) {
                   if (is_x == 1)
                       gameover.setText(" Крестик выиграл");
                   else
                       gameover.setText("Нолик выиграл");
                   Winner(stage, gameover);
               }
               if (click == 9) // фиксируем последнее нажатие
                   Winner(stage, gameover);
                click++; // увеличиваем счетчик нажатий
            }
        });
        return b;
    }

    private static void Winner(Stage stage, Text gameover) {// вывод победителя
        gameover.setStyle("-fx-font-size: 2em; ");
        StackPane txt = new StackPane(gameover);
        Scene scene = new Scene(txt);
        stage.setScene(scene);
        stage.setTitle("Tic-Tac_Toe");
        stage.setWidth(300);
        stage.setHeight(300);
        stage.show();
    }
}