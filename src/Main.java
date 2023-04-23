import domain.db.*;
import service.UniService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception{
        UniService globalService = new UniService();

        Estudiante estudiante = new Estudiante("Juan","Suarez","Perez","masculino","por allá","correo@gmail.com","12345678","98765432",20);
        Curso curso = new Curso("Disenno Conseptual","Desarrollar sistemas útiles para un determinado negocio", LocalDate.now(),2,"presencial", BigDecimal.valueOf(150000),"colones");

        Long estudianteId = globalService.getEstudianteRepository().upsert(estudiante);
        Long cursoId = globalService.getCursoRepository().upsert(curso);

        EstudianteCurso estudianteCurso = new EstudianteCurso(estudianteId,cursoId,BigDecimal.valueOf(95));
        globalService.getEstudianteCursoRepository().upsert(estudianteCurso);


        Contenido contenido = new Contenido("JDBC",13,cursoId);
        Long contenidoId = globalService.getContenidoRepository().upsert(contenido);

        Subcontenido subcontenido = new Subcontenido("Transacciones",contenidoId);
        globalService.getSubcontenidoRepository().upsert(subcontenido);

        globalService.getEstudiantesArmados().forEach(System.out::println);
    }
}