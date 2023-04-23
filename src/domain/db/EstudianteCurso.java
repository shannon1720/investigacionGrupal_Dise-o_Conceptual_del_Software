package domain.db;

import java.math.BigDecimal;

public class EstudianteCurso {
    private Long idEstudianteCurso;
    private Long idEstudiante;
    private Long idCurso;
    private BigDecimal nota;

    public EstudianteCurso(Long idEstudianteCurso, Long idEstudiante, Long idCurso, BigDecimal nota) {
        this.idEstudianteCurso = idEstudianteCurso;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.nota = nota;
    }

    public EstudianteCurso(Long idEstudiante, Long idCurso) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
    }

    public Long getIdEstudianteCurso() {
        return idEstudianteCurso;
    }

    public void setIdEstudianteCurso(Long idEstudianteCurso) {
        this.idEstudianteCurso = idEstudianteCurso;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }
}
