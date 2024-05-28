package controller;

import dao.OperacoesBD;
import model.UsuariosModel;

import java.sql.SQLException;

public class LoginController {
    OperacoesBD opBD = new OperacoesBD();

    public UsuariosModel AutenticaLogin(String login, String senha) throws SQLException {
         UsuariosModel user = new UsuariosModel (opBD.AutenticacaoLogin(login,senha), login, senha);
         return user;
    }
}
