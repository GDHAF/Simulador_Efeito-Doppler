package com.example.pbl;

import dao.OperacoesBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class LogUpController {

    @FXML
    private Button btnLogon;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtSenhaConfirmacao;

    @FXML
    private TextField txtUser;

    @FXML
    void BtnLogon_OnClick(ActionEvent event) throws SQLException {
        try {
            if (txtSenha.getText().equals(txtSenhaConfirmacao.getText())) {
                OperacoesBD bd = new OperacoesBD();
                int user = -1;
                user = bd.CadastrarUsuario(txtUser.getText(), txtSenha.getText());
                if (user == -1){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "O Usuário já existe. \nTente outro nome.", ButtonType.CLOSE);
                    alert.showAndWait();
                }

                MainApplication.Mudar_Tela("login");

            }
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Valores inserido incorretamente.\nTente Novamente.", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

}
