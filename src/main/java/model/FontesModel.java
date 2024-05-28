package model;
public class FontesModel {
    private float frequencia;  // Frequência em HZ
    private float potencia;    // Potência em W
    private float velocidade;  // Velocidade em m/s

    public FontesModel (float frequencia, float potencia, float velocidade) {
        this.frequencia =frequencia;
        this.potencia = potencia;
        this.velocidade =velocidade;
    }

    public float getFrequencia(){
        return this.frequencia;
    }
    public float getPotencia(){
        return this.potencia;
    }
    public float getVelocidade() {
        return this.velocidade;
    }
}
