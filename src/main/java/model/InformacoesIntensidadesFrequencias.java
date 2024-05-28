package model;

import java.util.ArrayList;

public class InformacoesIntensidadesFrequencias {
    private Frequencias_percebidasModel frequenciasPercebidas;
    private ArrayList<Intensidades_percebidasModel> intensidades;

    public InformacoesIntensidadesFrequencias(Frequencias_percebidasModel frequenciasPercebidas, ArrayList<Intensidades_percebidasModel> intensidades){
        this.frequenciasPercebidas = frequenciasPercebidas;
        this.intensidades = intensidades;
    }

    public Frequencias_percebidasModel getFrequenciasPercebidas() {
        return this.frequenciasPercebidas;
    }

    public ArrayList<Intensidades_percebidasModel> getIntensidades() {
        return intensidades;
    }
}
