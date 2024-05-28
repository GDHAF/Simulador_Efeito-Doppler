package model;

public class UsuariosModel {
    private static int id_usuario;
    private static String login;
    private static String senha;


    public UsuariosModel(int id_usuario, String login, String senha){
        UsuariosModel.id_usuario = id_usuario;
        UsuariosModel.login = login;
        UsuariosModel.senha = senha;
    }

    public static int getId_usuario(){
        return id_usuario;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }
}
