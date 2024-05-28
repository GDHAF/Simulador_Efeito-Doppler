package com.example.pbl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage stage;
    private static Scene login_scene;
    private static Scene experimento_scene;
    private static Scene cadastrar_scene;

    @Override
    public void start(Stage primary_stage) throws IOException {

        stage = primary_stage;
        primary_stage.setTitle("Simulação - Efeito Doppler");

        Parent fxml_login = FXMLLoader.load(getClass().getResource("login_view.fxml"));
        login_scene = new Scene(fxml_login, 640, 480);

        Parent fxml_experimento = FXMLLoader.load(getClass().getResource("experimento_view.fxml"));
        experimento_scene = new Scene(fxml_experimento, 640, 480);

        Parent fxml_cadastrar = FXMLLoader.load(getClass().getResource("logup_view.fxml"));
        cadastrar_scene = new Scene(fxml_cadastrar, 640, 480);

        primary_stage.setScene(login_scene);

        primary_stage.show();
    }

    public static void Mudar_Tela(String scr){
        switch (scr){
            case "login":
                stage.setScene(login_scene);
                break;
            case "experimento":
                stage.setScene(experimento_scene);
                break;
            case "cadastrar":
                stage.setScene(cadastrar_scene);
                break;
        }

    }
    public static void main(String[] args) {
        launch();
    }
}