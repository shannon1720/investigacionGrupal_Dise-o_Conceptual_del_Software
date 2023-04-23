package domain.db;

public class Subcontenido {
    private Long idSubcontenido;
    private String descripcion;
    private Long idContenido;
    public Subcontenido(Long idSubcontenido, String descripcion, Long idContenido) {
        this.idSubcontenido = idSubcontenido;
        this.descripcion = descripcion;
        this.idContenido = idContenido;
    }

    public Subcontenido(String descripcion, Long idContenido) {
        this.descripcion = descripcion;
        this.idContenido = idContenido;
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

    public Long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Long idContenido) {
        this.idContenido = idContenido;
    }

    @Override
    public String toString() {
        return "Subcontenido{" +
                "idSubcontenido=" + idSubcontenido +
                ", descripcion='" + descripcion + '\'' +
                ", idContenido=" + idContenido +
                '}';
    }
}
