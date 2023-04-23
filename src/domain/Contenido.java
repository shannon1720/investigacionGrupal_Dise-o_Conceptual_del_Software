package domain;

import java.util.List;

public class Contenido {
    private Long idContenido;
    private String tema;
    private Integer semana;
    private List<Subcontenido> subcontenido;
    public Contenido(Long idContenido, String tema, Integer semana) {
        this.idContenido = idContenido;
        this.tema = tema;
        this.semana = semana;
    }

    public Long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Long idContenido) {
        this.idContenido = idContenido;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Integer getSemana() {
        return semana;
    }

    public void setSemana(Integer semana) {
        this.semana = semana;
    }

    public List<Subcontenido> getSubcontenido() {
        return subcontenido;
    }
}
