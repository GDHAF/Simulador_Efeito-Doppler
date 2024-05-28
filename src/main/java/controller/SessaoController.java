package controller;

import dao.OperacoesBD;
import model.DistanciasFonteXObservador;
import model.ExperimentosModel;
import model.FontesModel;
import model.InformacoesIntensidadesFrequencias;
import util.OperacoesSom;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

public class SessaoController {
    OperacoesBD opBD = new OperacoesBD();
    OperacoesSom opSom = new OperacoesSom();

    public FontesModel CriaFonte(float frequencia, float potencia, float velocidade){
        FontesModel fonte = new FontesModel(frequencia,potencia,velocidade);
        return fonte;
    }

    public ExperimentosModel CriaERegistraExperimento(FontesModel fonte, int id_usuario, int duracao, DistanciasFonteXObservador distancias)
        throws SQLException {
        ExperimentosModel experimento = new ExperimentosModel(duracao,id_usuario,
                opBD.RegistraExperimento(id_usuario,duracao, distancias.getDistanciaFonteXObservador_x(),
                        distancias.getDistanciaFonteXObservador_y(),fonte.getFrequencia(),fonte.getPotencia(),fonte.getVelocidade()));
        return experimento;
    }
    public InformacoesIntensidadesFrequencias GeraArquivoAudioERetornaFrequenciasIntensidade(FontesModel fonte, ExperimentosModel experimento,
                                          DistanciasFonteXObservador distancias, String caminho) throws UnsupportedAudioFileException, IOException {
        InformacoesIntensidadesFrequencias informacoes = opSom.CalculaDadosFrequenciaIntensidadeAudio(distancias.getDistanciaFonteXObservador_x(),
        distancias.getDistanciaFonteXObservador_y(), experimento.getDuracao(), fonte.getVelocidade(),fonte.getFrequencia(),
        fonte.getPotencia(), experimento.getId_experimento(), caminho);
        return informacoes;
    }
    public void GuardaInformacoesIntensidadeFrequencia(InformacoesIntensidadesFrequencias informacoes) throws SQLException {
        try {
            opBD.RegistraFrequencias(informacoes.getFrequenciasPercebidas().getId_experimento(),
                    informacoes.getFrequenciasPercebidas().getFrequenciaAproximacao(),informacoes.getFrequenciasPercebidas().getFrequencaiAfastamento());
            for(int i = 0; i < informacoes.getIntensidades().size(); i++){
                opBD.RegistraIntensidade(informacoes.getIntensidades().get(i).getId_experimento(), informacoes.getIntensidades().get(i).getInstante(),
                        informacoes.getIntensidades().get(i).getIntensidade());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
