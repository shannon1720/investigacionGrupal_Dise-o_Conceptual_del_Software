package domain.json;

import domain.db.Curso;
import domain.db.Estudiante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstudianteBusinessObject extends Estudiante {
    private List<Curso> cursos;
    private Map<String, BigDecimal> notas;
    public EstudianteBusinessObject(Long estudianteId, String nombre, String primerApellido, String segundoApellido, String genero, String direccion, String correoElectronico, String telefonoCasa, String telefonoCelular, Integer edad) {
        super(estudianteId, nombre, primerApellido, segundoApellido, genero, direccion, correoElectronico, telefonoCasa, telefonoCelular, edad);
        cursos = new ArrayList<>();
        notas = new HashMap<>();
    }

    public EstudianteBusinessObject(String nombre, String primerApellido, String segundoApellido, String genero, String direccion, String correoElectronico, String telefonoCasa, String telefonoCelular, Integer edad) {
        super(nombre, primerApellido, segundoApellido, genero, direccion, correoElectronico, telefonoCasa, telefonoCelular, edad);
        cursos = new ArrayList<>();
        notas = new HashMap<>();
    }
    public List<Curso> getCursos() {
        return cursos;
    }
    public Map<String, BigDecimal> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return "EstudianteJson{" +
                "estudianteId=" + estudianteId +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", genero='" + genero + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefonoCasa='" + telefonoCasa + '\'' +
                ", telefonoCelular='" + telefonoCelular + '\'' +
                ", edad=" + edad +
                ", cursos=" + cursos +
                ", notas=" + notas +
                '}';
    }
}
