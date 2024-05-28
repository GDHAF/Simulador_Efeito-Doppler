package model;

public class InformacoesExperimentos {
    private ExperimentosModel experimento;
    private DistanciasFonteXObservador distancias;
    private FontesModel fonte;
    private Frequencias_percebidasModel frequencias;
    private String dataHoraExp;

    public InformacoesExperimentos(ExperimentosModel experimento, DistanciasFonteXObservador distancias,
      FontesModel fonte, Frequencias_percebidasModel frequencias, String dataHoraExp){
        this.experimento = experimento;
        this.distancias = distancias;
        this.fonte = fonte;
        this.frequencias = frequencias;
        this.dataHoraExp = dataHoraExp;
    }

    public ExperimentosModel getExperimento() {
        return experimento;
    }

    public void setExperimento(ExperimentosModel experimento) {
        this.experimento = experimento;
    }

    public DistanciasFonteXObservador getDistancias() {
        return distancias;
    }

    public void setDistancias(DistanciasFonteXObservador distancias) {
        this.distancias = distancias;
    }

    public FontesModel getFonte() {
        return fonte;
    }

    public void setFonte(FontesModel fonte) {
        this.fonte = fonte;
    }

    public Frequencias_percebidasModel getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(Frequencias_percebidasModel frequencias) {
        this.frequencias = frequencias;
    }

    public String getDataHoraExp() {
        return dataHoraExp;
    }

    public void setDataHoraExp(String dataHoraExp) {
        this.dataHoraExp = dataHoraExp;
    }
}
