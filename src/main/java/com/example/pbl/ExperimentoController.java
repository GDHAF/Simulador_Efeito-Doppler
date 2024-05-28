package com.example.pbl;

import controller.SessaoController;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import model.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

public class ExperimentoController {

    SessaoController sessao = new SessaoController();

    @FXML
    protected Button btnSimular;

    @FXML
    protected TextField txtFrequencia;
    @FXML
    protected TextField txtPotencia;
    @FXML
    protected TextField txtVelocidade;
    @FXML
    protected TextField txtDuracao;
    @FXML
    protected TextField txtDistancia_em_x;
    @FXML
    protected TextField txtDistancia_eixo_y;
    @FXML
    protected LineChart<?, ?> graphFrequencia;


    @FXML
    protected void BtnSimular_OnClick() throws SQLException, UnsupportedAudioFileException, IOException {
        try {

            float frequencia = Float.parseFloat(txtFrequencia.getText());
            float potencia = Float.parseFloat(txtPotencia.getText());
            float velocidade = Float.parseFloat(txtVelocidade.getText());


            FontesModel fonte = sessao.CriaFonte(frequencia, potencia, velocidade);


            int duracao = Integer.parseInt(txtDuracao.getText());
            float distanciax = Float.parseFloat(txtDistancia_em_x.getText());
            float distanciay = Float.parseFloat(txtDistancia_eixo_y.getText());

            DistanciasFonteXObservador distancias = new DistanciasFonteXObservador(distanciax, distanciay);
            ExperimentosModel experimento = sessao.CriaERegistraExperimento(fonte, UsuariosModel.getId_usuario(), duracao, distancias);

            InformacoesIntensidadesFrequencias info = sessao.GeraArquivoAudioERetornaFrequenciasIntensidade(fonte, experimento, distancias, "src/main/resources/archives");
            sessao.GuardaInformacoesIntensidadeFrequencia(info);

            graphFrequencia.getXAxis().setLabel("Instante");

            graphFrequencia.getYAxis().setLabel("Intensidade");

            XYChart.Series series = new XYChart.Series();

            for (Intensidades_percebidasModel i : info.getIntensidades()) {
                series.getData().add(new XYChart.Data(""+i.getInstante(), 1.0*i.getIntensidade()));
            }

            graphFrequencia.getData().addAll(series);
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

}
