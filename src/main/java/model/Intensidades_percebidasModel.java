package model;

public class Intensidades_percebidasModel {
    private int instante; // instante em segundos
    private float intensidade; // Intensidade em w/m²
    private int id_experimento;

    public Intensidades_percebidasModel(int instante, float intensidade, int id_experimento){
        this.instante = instante;
        this.intensidade = intensidade;
        this.id_experimento = id_experimento;
    }

    public int getInstante(){
        return this.instante;
    }
    public float getIntensidade(){
        return this.intensidade;
    }
    public int getId_experimento(){
        return this.id_experimento;
    }
}