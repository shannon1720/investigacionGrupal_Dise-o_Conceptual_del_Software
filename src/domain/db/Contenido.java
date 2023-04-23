package domain.db;

import java.util.List;

public class Contenido {
    private Long idContenido;
    private String tema;
    private Integer semana;
    private Long idCurso;
    private List<Subcontenido> subcontenido;
    public Contenido(Long idContenido, String tema, Integer semana, Long idCurso) {
        this.idContenido = idContenido;
        this.tema = tema;
        this.semana = semana;
        this.idCurso = idCurso;
    }

    public Contenido(String tema, Integer semana, Long idCurso) {
        this.tema = tema;
        this.semana = semana;
        this.idCurso = idCurso;
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

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public List<Subcontenido> getSubcontenido() {
        return subcontenido;
    }

    public void setSubcontenido(List<Subcontenido> subcontenido) {
        this.subcontenido = subcontenido;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "idContenido=" + idContenido +
                ", tema='" + tema + '\'' +
                ", semana=" + semana +
                ", idCurso=" + idCurso +
                ", subcontenido=" + subcontenido +
                '}';
    }
}
