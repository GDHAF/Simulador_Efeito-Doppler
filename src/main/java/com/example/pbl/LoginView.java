package com.example.pbl;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.UsuariosModel;

public class LoginView {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink linkCadastrar;

    @FXML
    protected void BtnLogin_OnClick(ActionEvent event) {

        LoginController logCont = new LoginController();
        UsuariosModel user = null;

        try {
            String login = txtUser.getText();
            String senha = txtSenha.getText();

            user = logCont.AutenticaLogin(login, senha);
            MainApplication.Mudar_Tela("experimento");
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Usuário não encontrado, tente novamente!", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    @FXML
    void LinkCadastrarOnClick(ActionEvent event) {
        MainApplication.Mudar_Tela("cadastrar");
    }

}
