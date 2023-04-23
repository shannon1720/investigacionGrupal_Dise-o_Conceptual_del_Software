package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Curso {
    private Long idCurso;
    private String descripcion;
    private String objetivo;
    private LocalDate fechaApertura;
    private Integer cuatrimestre;
    private String modalidad;
    private BigDecimal costoCurso;
    private String moneda;
    private List<Contenido> contenidos;
    public Curso(Long idCurso, String descripcion, String objetivo, LocalDate fechaApertura, Integer cuatrimestre, String modalidad, BigDecimal costoCurso, String moneda) {
        this.idCurso = idCurso;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.fechaApertura = fechaApertura;
        this.cuatrimestre = cuatrimestre;
        this.modalidad = modalidad;
        this.costoCurso = costoCurso;
        this.moneda = moneda;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Integer getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Integer cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public BigDecimal getCostoCurso() {
        return costoCurso;
    }

    public void setCostoCurso(BigDecimal costoCurso) {
        this.costoCurso = costoCurso;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public List<Contenido> getContenidos() {
        return contenidos;
    }
}
