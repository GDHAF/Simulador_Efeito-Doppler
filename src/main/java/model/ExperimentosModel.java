package model;

public class ExperimentosModel {
    private int duracao;  // Duração em segundos
    private float distanciaFonteXObservador;  // Distância entre a fonte e o observador em metros
    private int id_usuario;
    private int id_experimento;

    public ExperimentosModel(int duracao, int id_usuario, int id_experimento){
        this.duracao = duracao;
        this.id_usuario = id_usuario;
        this.id_experimento = id_experimento;
    }

    public int getDuracao(){
        return this.duracao;
    }
    public float getDistanciaFonteXObservador(){
        return this.distanciaFonteXObservador;
    }
    public int getId_usuario(){
        return this.id_usuario;
    }
    public int getId_experimento(){
        return this.id_experimento;
    }
}
