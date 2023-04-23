package service;

import domain.db.*;
import domain.json.EstudianteJson;
import persitence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UniService {
    private EstudianteRepository estudianteRepository;
    private CursoRepository cursoRepository;
    private ContenidoRepository contenidoRepository;
    private SubcontenidoRepository subcontenidoRepository;
    private EstudianteCursoRepository estudianteCursoRepository;
    public UniService(){
        estudianteRepository = new EstudianteRepository();
        cursoRepository = new CursoRepository();
        contenidoRepository = new ContenidoRepository();
        subcontenidoRepository = new SubcontenidoRepository();
        estudianteCursoRepository = new EstudianteCursoRepository();
    }

    public List<EstudianteJson> getEstudiantesArmados() throws Exception {
        List<EstudianteJson> estudianteJsonList = new ArrayList<>();
        Collection<Estudiante> estudiantes = estudianteRepository.listar();
        for (Estudiante estudiante: estudiantes) {
            estudianteJsonList.add(construirEstudiante(estudiante));
        }
        return estudianteJsonList;
    }

    public List<Curso> getCursosArmados() throws Exception {
        List<Curso> cursos = cursoRepository.listar();
        for (Curso curso:cursos) {
            curso.setContenidos(getContenidos(curso.getIdCurso()));
        }
        return cursos;
    }

    private EstudianteJson construirEstudiante(Estudiante estudiante) throws Exception {
        EstudianteJson estudianteJson = convertToEstudianteJson(estudiante);
        List<EstudianteCurso> cursosPorEstudiante = estudianteCursoRepository.buscarByIdEstudiante(estudiante.getEstudianteId());
        for (EstudianteCurso relacion: cursosPorEstudiante) {
            Curso curso = construirCurso(relacion.getIdCurso());
            estudianteJson.getCursos().add(curso);
            estudianteJson.getNotas().put(curso.getDescripcion(),relacion.getNota());
        }
        return estudianteJson;
    }
    private Curso construirCurso(Long idCurso) throws Exception {
        Curso curso = cursoRepository.buscar(idCurso);
        curso.setContenidos(getContenidos(idCurso));
        return curso;
    }

    private List<Contenido> getContenidos(Long idCurso) throws Exception {
        List<Contenido> contenidosPorCurso = contenidoRepository.buscarPorCurso(idCurso);
        for (Contenido contenido:contenidosPorCurso) {
            contenido.setSubcontenido(subcontenidoRepository.buscarPorIdContenido(contenido.getIdContenido()));
        }
        return contenidosPorCurso;
    }

    private EstudianteJson convertToEstudianteJson(Estudiante estudiante){
        return new EstudianteJson(estudiante.getEstudianteId(),estudiante.getNombre(),estudiante.getPrimerApellido(),estudiante.getSegundoApellido(),estudiante.getGenero(),estudiante.getDireccion(),estudiante.getCorreoElectronico(),estudiante.getTelefonoCasa(),estudiante.getTelefonoCelular(),estudiante.getEdad());
    }

    public EstudianteRepository getEstudianteRepository() {
        return estudianteRepository;
    }

    public CursoRepository getCursoRepository() {
        return cursoRepository;
    }

    public ContenidoRepository getContenidoRepository() {
        return contenidoRepository;
    }

    public SubcontenidoRepository getSubcontenidoRepository() {
        return subcontenidoRepository;
    }

    public EstudianteCursoRepository getEstudianteCursoRepository() {
        return estudianteCursoRepository;
    }
}
