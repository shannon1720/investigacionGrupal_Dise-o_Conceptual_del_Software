package service;

import domain.db.*;
import domain.json.EstudianteBusinessObject;
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

    public List<EstudianteBusinessObject> getEstudiantes() throws Exception {
        List<EstudianteBusinessObject> estudianteBusinessObjectList = new ArrayList<>();
        Collection<Estudiante> estudiantes = estudianteRepository.listar();
        for (Estudiante estudiante: estudiantes) {
            estudianteBusinessObjectList.add(construirEstudiante(estudiante));
        }
        return estudianteBusinessObjectList;
    }

    public EstudianteBusinessObject getEstudiante(Long estudianteId) throws Exception {
        return construirEstudiante(estudianteRepository.buscar(estudianteId));
    }

    public List<Curso> getCursos() throws Exception {
        List<Curso> cursos = cursoRepository.listar();
        for (Curso curso:cursos) {
            curso.setContenidos(getContenidos(curso.getIdCurso()));
        }
        return cursos;
    }

    private EstudianteBusinessObject construirEstudiante(Estudiante estudiante) throws Exception {
        EstudianteBusinessObject estudianteBusinessObject = convertToEstudianteJson(estudiante);
        List<EstudianteCurso> cursosPorEstudiante = estudianteCursoRepository.buscarByIdEstudiante(estudiante.getEstudianteId());
        for (EstudianteCurso relacion: cursosPorEstudiante) {
            Curso curso = construirCurso(relacion.getIdCurso());
            estudianteBusinessObject.getCursos().add(curso);
            estudianteBusinessObject.getNotas().put(curso.getDescripcion(),relacion.getNota());
        }
        return estudianteBusinessObject;
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

    private EstudianteBusinessObject convertToEstudianteJson(Estudiante estudiante){
        return new EstudianteBusinessObject(estudiante.getEstudianteId(),estudiante.getNombre(),estudiante.getPrimerApellido(),estudiante.getSegundoApellido(),estudiante.getGenero(),estudiante.getDireccion(),estudiante.getCorreoElectronico(),estudiante.getTelefonoCasa(),estudiante.getTelefonoCelular(),estudiante.getEdad());
    }

    public Long salvarEstudiante(Estudiante estudiante) throws Exception {
        return estudianteRepository.upsert(estudiante);
    }

    public void eliminarEstudiante(Long estudianteId) throws Exception {
        estudianteRepository.eliminar(estudianteId);
    }

    public Long salvarCurso(Curso curso) throws Exception {
        return cursoRepository.upsert(curso);
    }
    public Curso getCurso(Long cursoId) throws Exception {
        return cursoRepository.buscar(cursoId);
    }

    public void eliminarCurso(Long cursoId) throws Exception {
        cursoRepository.eliminar(cursoId);
    }

    public Long salvarContenido(Contenido contenido) throws Exception {
        return contenidoRepository.upsert(contenido);
    }

    public void eliminarContenido(Long contenidoId) throws Exception {
        contenidoRepository.eliminar(contenidoId);
    }

    public Long salvarSubcontenido(Subcontenido subcontenido) throws Exception {
        return subcontenidoRepository.upsert(subcontenido);
    }
    public void eliminarSubContenido(Long subcontenidoId) throws Exception {
        subcontenidoRepository.eliminar(subcontenidoId);
    }

    public Long actualizarCursoDeEstudiante(EstudianteCurso estudianteCurso) throws Exception {
        return estudianteCursoRepository.upsert(estudianteCurso);
    }

    public EstudianteCurso obtenerCursoDeEstudiante(Long estudianteCursoId) throws Exception {
        return estudianteCursoRepository.buscar(estudianteCursoId);
    }
}
