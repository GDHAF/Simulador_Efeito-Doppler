package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class OperacoesBD {
    // String de conexão jdbc: SGBD da sua escolha://localhost:1433; databaseName = nome do banco de dados que será usado
    private String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=pbldb;encrypt=true;trustServerCertificate=true";
    // Usuário do banco de dados pelo qual o programa irá entrar
    private String username = "sa";
    // Senha do usuário
    private String password = "123456";

    Connection conexao;
    public int AutenticacaoLogin(String login, String senha) throws SQLException {
        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement cs = conexao.prepareCall("{call SP_AUTENTICACAO_USUARIO(?,?,?,?)}");
            cs.setString(1,login);      // entrada de login
            cs.setString(2,senha);      // entrada de senha
            cs.registerOutParameter(3, Types.VARCHAR);  // saída de mensagem de erro
            cs.registerOutParameter(4, Types.INTEGER);  // saída do id do usuário
            cs.execute();
            String mensagemErro = cs.getString(3);

            if (mensagemErro != null) {
                conexao.close();
                throw new SQLException("Ocorreu um erro: " + mensagemErro);
            }
            int id_usuario = cs.getInt(4);
            conexao.close();
            return id_usuario;

        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }

    public int CadastrarUsuario(String login, String senha) throws SQLException {
        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement cs = conexao.prepareCall("{call SP_CRIACAO_USUARIO(?,?,?,?)}");
            cs.setString(1,login);      // entrada de login
            cs.setString(2,senha);      // entrada de senha
            cs.registerOutParameter(3, Types.INTEGER);  // saída do id do usuário
            cs.registerOutParameter(4, Types.VARCHAR);  // saída de mensagem de erro
            cs.execute();
            String mensagemErro = cs.getString(4);

            if (mensagemErro != null) {
                conexao.close();
                throw new SQLException("Ocorreu um erro: " + mensagemErro);
            }
            int id_usuario = cs.getInt(3);
            conexao.close();
            return id_usuario;

        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }

    public int RegistraExperimento(int id_usuario, int duracaoExperimento,
              float distanciaFonteXObservador_x,float distanciaFonteXObservador_y, float frequenciaFonte, float potenciaFonte, float velocidadeFonte) throws SQLException {

        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement cs = conexao.prepareCall("{call SP_REGISTRO_EXPERIMENTO(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,id_usuario);      // entrada de id_usuario
            cs.setInt(2,duracaoExperimento);      // entrada de duração do experimento
            cs.setFloat(3, distanciaFonteXObservador_x);  // entrada distância FonteXObservador em x
            cs.setFloat(4, distanciaFonteXObservador_y);  // entrada distância FonteXObservador em y
            cs.setFloat(5, frequenciaFonte);  // entrada frequencia da fonte
            cs.setFloat(6, potenciaFonte);  // entrada potencia da fonte
            cs.setFloat(7, velocidadeFonte);  // entrada velocidade da fonte
            cs.registerOutParameter(8, Types.INTEGER);  // saída id do experimento
            cs.registerOutParameter(9, Types.VARCHAR);  // saída erro
            cs.execute();

            String mensagemErro = cs.getString(9);
            if (mensagemErro != null) {
                conexao.close();
                throw new SQLException("Ocorreu um erro: " + mensagemErro);
            }
            int id_experimento = cs.getInt(8);
            conexao.close();
            return id_experimento;

        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }
    public void RegistraFrequencias(int id_experimento, float frequenciaAprox, float frequenciaAfast) throws SQLException {

        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement cs = conexao.prepareCall("{call SP_REGISTRO_FREQUENCIAS(?,?,?,?)}");
            cs.setInt(1,id_experimento);      // entrada de id do experimento
            cs.setFloat(2,frequenciaAprox);      // entrada de frequencia de aproximação
            cs.setFloat(3, frequenciaAfast);  // entrada de frequencia de afastamento
            cs.registerOutParameter(4, Types.VARCHAR);  // saída de erro
            cs.execute();

            String mensagemErro = cs.getString(4);

            if (mensagemErro != null) {
                conexao.close();
                throw new SQLException("Ocorreu um erro: " + mensagemErro);
            }
            conexao.close();
        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }
    public void RegistraIntensidade(int id_experimento, int instante,float intensidade ) throws SQLException {
        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement cs = conexao.prepareCall("{call SP_REGISTRO_INTENSIDADES (?,?,?,?)}");
            cs.setInt(1,id_experimento);      // entrada de id do experimento
            cs.setInt(2,instante);      // entrada de instante
            cs.setFloat(3, intensidade);  // entrada de intensidade
            cs.registerOutParameter(4, Types.VARCHAR);  // saída de erro
            cs.execute();

            String mensagemErro = cs.getString(4);

            if (mensagemErro != null) {
                conexao.close();
                throw new SQLException("Ocorreu um erro: " + mensagemErro);
            }
            conexao.close();
        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }
    public ArrayList<InformacoesExperimentos> ListaExperimentos(int id_usuario) throws SQLException {
        try {
            conexao = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement ps = conexao.prepareStatement("select * from dbo.LISTAR_EXPERIMENTOS_DO_USUARIO(?)");
            ResultSet rs = ps.executeQuery();
            ArrayList<InformacoesExperimentos> informacoes = new ArrayList<InformacoesExperimentos>();

            while(rs.next()) {
                ExperimentosModel experimento = new ExperimentosModel(rs.getInt("duracaoExpEmS"), id_usuario, rs.getInt("idExperimento"));
                DistanciasFonteXObservador distancias = new DistanciasFonteXObservador(rs.getFloat("distanciaEixoXemM"), rs.getFloat("distanciaEixoYemM"));
                FontesModel fonte = new FontesModel(rs.getFloat("freqFonteEmHz"), rs.getFloat("potenciaFonteEmW"), rs.getFloat("velocidadeFonteEmMps"));
                Frequencias_percebidasModel frequencias = new Frequencias_percebidasModel(rs.getFloat("freqAproximacaoEmHz"), rs.getFloat("freqAfastamentoEmHz"), rs.getInt("idExperimento"));
                String dataHora = rs.getString("dataHoraExp");
                InformacoesExperimentos informacao = new InformacoesExperimentos(experimento, distancias, fonte, frequencias, dataHora);
                informacoes.add(informacao);
            }
            conexao.close();
            return informacoes;
        }
        catch(Exception ex){
            conexao.close();
            throw new SQLException("Erro durante a conexão: " + ex.getMessage());
        }
        finally{
            try {
                conexao.close();
            }
            catch (Exception ex){
                throw new SQLException("Erro durante a conexão: " + ex.getMessage());
            }
        }
    }
}
