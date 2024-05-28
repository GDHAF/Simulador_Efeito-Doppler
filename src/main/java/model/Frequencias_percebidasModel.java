package model;

public class Frequencias_percebidasModel {
    private float frequenciaAproximacao;
    private float frequencaiAfastamento;
    private int id_experimento;

    public Frequencias_percebidasModel(float frequenciaAproximacao, float frequenciaAfastamento, int id_experimento){
        this.frequenciaAproximacao = frequenciaAproximacao;
        this.frequencaiAfastamento = frequenciaAfastamento;
        this.id_experimento = id_experimento;
    }

    public float getFrequenciaAproximacao(){
        return this.frequenciaAproximacao;
    }
    public float getFrequencaiAfastamento(){
        return this.frequencaiAfastamento;
    }
    public int getId_experimento(){
        return this.id_experimento;
    }
}
