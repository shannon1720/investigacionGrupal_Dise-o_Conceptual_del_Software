package domain;

public class Subcontenido {
    private Long idSubcontenido;
    private String descripcion;
    public Subcontenido(Long idSubcontenido, String descripcion) {
        this.idSubcontenido = idSubcontenido;
        this.descripcion = descripcion;
    }

    public Long getIdSubcontenido() {
        return idSubcontenido;
    }

    public void setIdSubcontenido(Long idSubcontenido) {
        this.idSubcontenido = idSubcontenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
