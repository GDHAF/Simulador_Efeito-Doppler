
package util;

import model.Frequencias_percebidasModel;
import model.InformacoesIntensidadesFrequencias;
import model.Intensidades_percebidasModel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OperacoesSom {
    final float velocidadeSom = 340;  // velocidade do som de 340 m/s nesse caso
    final float intensidadeMinima = (float)(Biblioteca_Matematica.exp(10.0,-12)); // intensidade de som mínima que um ser humano é capaz de ouvir

    private boolean CriaArquivoAudio(int taxaAmostragem, byte[] dadosAudio, int numAmostras, String caminho)
            throws IOException, UnsupportedAudioFileException
    {
        try {
            String caminhoAudio = caminho + "\\audio.wav";


            AudioFormat formato = new AudioFormat(taxaAmostragem, 16, 1, true, false);
            AudioInputStream entradaAudio = new AudioInputStream(
                    new java.io.ByteArrayInputStream(dadosAudio), formato, numAmostras);
            AudioSystem.write(entradaAudio, AudioFileFormat.Type.WAVE, new File(caminhoAudio));
            return true;
        }
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }

    public InformacoesIntensidadesFrequencias CalculaDadosFrequenciaIntensidadeAudio(float distanciaFonteXObservador_x, float distanciaFonteXObservador_y, int duracaoSegundos,
        float velocidadeFonte, float frequencia, float potenciaFonte, int id_experimento, String caminhoAudio) throws UnsupportedAudioFileException, IOException {
        final int taxaAmostragem = 48000; // Frequência de amostragem (Hz);
        int numAmostras = taxaAmostragem * duracaoSegundos;
        byte[] dadosAudio = new byte[2 * numAmostras]; // 2 bytes por amostra (formato de áudio PCM de 16 bits)
        float distanciaFonteObservador_x = distanciaFonteXObservador_x;
        float distanciaFonteObservador = 0;
        float frequenciaAprox = CalculaFrequenciaAparenteAprox(frequencia, velocidadeFonte);
        float frequenciaAfast = CalculaFrequenciaAparenteAfast(frequencia, velocidadeFonte);
        float amplitude = 0;
        float intensidade = 0;
        float intensidadeSonoraMaxima = CalculaIntensidadeSonoraMaxima(potenciaFonte);
        double valor = 0;
        ArrayList<Intensidades_percebidasModel> intensidades = new ArrayList<Intensidades_percebidasModel>();
        Frequencias_percebidasModel frequenciasPercebidas = new Frequencias_percebidasModel(frequenciaAprox, frequenciaAfast, id_experimento);

        for (int i = 0; i < numAmostras; i++) {

            double tempo = i / (double) taxaAmostragem;
            distanciaFonteObservador_x = (float) (distanciaFonteXObservador_x - (velocidadeFonte * tempo));
            distanciaFonteObservador = (float) Math.sqrt((long)(Biblioteca_Matematica.exp(distanciaFonteObservador_x,2) + Biblioteca_Matematica.exp(distanciaFonteXObservador_y,2)));

            if (distanciaFonteObservador > 0) {
                intensidade = CalculaIntensidade(Biblioteca_Matematica.abs((long)distanciaFonteObservador), potenciaFonte);
                amplitude = ConverteEscalaDecibelsEmPorcentagem(intensidadeSonoraMaxima, CalculaIntensidadeSonora(intensidade));
                if(amplitude < 0) amplitude = 0;
                valor = amplitude * Biblioteca_Matematica.sen(2 * Biblioteca_Matematica.PI * frequenciaAprox * tempo);
            } else if (distanciaFonteObservador == 0) {
                intensidade = CalculaIntensidade(distanciaFonteXObservador_y, potenciaFonte);
                amplitude = (float) 1;
                valor = amplitude * Biblioteca_Matematica.sen(2 * Biblioteca_Matematica.PI * frequencia * tempo);
            } else {
                intensidade = CalculaIntensidade(Math.abs(distanciaFonteObservador), potenciaFonte);
                amplitude = ConverteEscalaDecibelsEmPorcentagem(intensidadeSonoraMaxima, CalculaIntensidadeSonora(intensidade));
                if(amplitude < 0) amplitude = 0;
                valor = amplitude * Biblioteca_Matematica.sen(2 * Biblioteca_Matematica.PI * frequenciaAfast * tempo);
            }
            if ((tempo * 10) % 10 == 0) {
                intensidades.add(new Intensidades_percebidasModel((int) tempo, intensidade, id_experimento));
            }

            short amostra = (short) (valor * Short.MAX_VALUE);
            dadosAudio[2 * i] = (byte) (amostra & 0xFF);
            dadosAudio[2 * i + 1] = (byte) ((amostra >> 8) & 0xFF);
        }
        if(CriaArquivoAudio(taxaAmostragem,dadosAudio,numAmostras,caminhoAudio) == false) throw new UnsupportedAudioFileException("Impossível criar arquivo de áudio");
        InformacoesIntensidadesFrequencias informacoes = new InformacoesIntensidadesFrequencias(frequenciasPercebidas, intensidades);
        return informacoes;

    }


    private float CalculaFrequenciaAparenteAprox(float frequenciaOnda, float velocidadeFonte) {
     if (velocidadeSom == velocidadeFonte) throw new ArithmeticException("O a velocidade da fonte não pode ser igual a do som");
     return velocidadeSom/((velocidadeSom - velocidadeFonte)/frequenciaOnda);
    }
    private float CalculaFrequenciaAparenteAfast(float frequenciaOnda, float velocidadeFonte) {
        return velocidadeSom/((velocidadeSom + velocidadeFonte)/frequenciaOnda);
    }
    private float CalculaIntensidade(float distanciaFonteXObservador, float potenciaFonte){
        if(distanciaFonteXObservador == 0) throw new ArithmeticException("A distância entre a fonte e o observador não podem ser igual a 0");
        if(potenciaFonte == 0) throw new ArithmeticException("A a potência da fonte não pode ser igual a 0");
        return (float)(potenciaFonte/(4*Biblioteca_Matematica.PI*(Biblioteca_Matematica.exp(distanciaFonteXObservador,2))));
    }
    private float CalculaIntensidadeSonora(float intensidade){
        return (float)(10*Math.log(intensidade/intensidadeMinima));
    }
    private float CalculaIntensidadeSonoraMaxima(float potenciaFonte){
        return CalculaIntensidadeSonora(CalculaIntensidade((float)0.000001,potenciaFonte));
    }
    private float ConverteEscalaDecibelsEmPorcentagem(float intensidadeSonoraMaxima, float intensidadeAtual){
        return (float)(intensidadeAtual/intensidadeSonoraMaxima);
    }
}