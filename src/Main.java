import domain.db.*;
import domain.json.EstudianteBusinessObject;
import service.UniService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception{
        UniService globalService = new UniService();

        Long estudianteId = globalService.getEstudianteRepository().upsert(estudiante);
        Long cursoId = globalService.getCursoRepository().upsert(curso);

        EstudianteCurso estudianteCurso = new EstudianteCurso(estudianteId,cursoId,BigDecimal.valueOf(95));
        globalService.getEstudianteCursoRepository().upsert(estudianteCurso);


        Contenido contenido = new Contenido("JDBC",13,cursoId);
        Long contenidoId = globalService.getContenidoRepository().upsert(contenido);

        Subcontenido subcontenido = new Subcontenido("Transacciones",contenidoId);
        globalService.getSubcontenidoRepository().upsert(subcontenido);

        globalService.getEstudiantes().forEach(System.out::println);

        System.out.println("\n \033[0;37m*********SECCIÓN ESTUDIANTES***************\033[0;37m");

        System.out.println("\n \033[0;32mCreando estudiante...\033[0;32m");

        Estudiante estudiante1 = new Estudiante("Juan","Suarez","Perez","masculino","por allá","correo@gmail.com","12345678","98765432",20);
        Long estudianteId1 = globalService.salvarEstudiante(estudiante1);

        System.out.println("\n \033[0;32mCreando estudiante...\033[0;32m");

        Estudiante estudiante2 = new Estudiante("Jose","Perez","Mora","masculino","por allá","correo@gmail.com","12345678","98765432",20);
        Long estudianteId2 = globalService.salvarEstudiante(estudiante2);

        System.out.println("\n \033[0;32mCreando estudiante...\033[0;32m");

        Estudiante estudiante3 = new Estudiante("Mario","Vargas","Perez","masculino","por allá","correo@gmail.com","12345678","98765432",20);
        Long estudianteId3 = globalService.salvarEstudiante(estudiante3);

        System.out.println("\n \033[0;32mCreando estudiante...\033[0;32m");

        Estudiante estudiante4 = new Estudiante("María","Vargas","Perez","femenino","por allá","correo@gmail.com","12345678","98765432",20);
        Long estudianteId4 = globalService.salvarEstudiante(estudiante4);

        System.out.println("\n \033[0;33mListar todos los estudiantes creados...\033[0;33m");

        globalService.getEstudiantes().forEach(System.out::println);

        System.out.println("\n \033[0;35mActualizando estudiante...\033[0;35m");

        EstudianteBusinessObject estudianteParaModificar = globalService.getEstudiante(estudianteId1);
        estudianteParaModificar.setEdad(15);
        estudianteParaModificar.setNombre("Juanchito");
        globalService.salvarEstudiante(estudianteParaModificar);

        System.out.println("\n \033[0;33mListar todos los estudiantes después de la actualización de un estudiante\033[0;33m");

        globalService.getEstudiantes().forEach(System.out::println);

        System.out.println("\n \033[0;31mEliminando estudiante... \033[0;31m");

        globalService.eliminarEstudiante(estudianteId3);

        System.out.println("\n \033[0;33mListar todos los estudiantes después de eliminar un estudiante\033[0;33m");

        globalService.getEstudiantes().forEach(System.out::println);

        System.out.println("\n \033[0;37m*********SECCIÓN CURSOS***************\033[0;37m");

        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");

        Curso curso1 = new Curso("Disenno Conseptual","Desarrollar sistemas útiles para un determinado negocio", LocalDate.now(),2,"presencial", BigDecimal.valueOf(150000),"colones");

        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");
        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");
        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");
        System.out.println("\n \033[0;33mListar todos los cursos creados...\033[0;33m");

        System.out.println("\n \033[0;35mActualizando curso...\033[0;35m");
        System.out.println("\n \033[0;33mListar todos los cursos después de la actualización de un curso\033[0;33m");

        System.out.println("\n \033[0;31mEliminando curso... \033[0;31m");
        System.out.println("\n \033[0;33mListar todos los cursos después de eliminar un curso\033[0;33m");

        System.out.println("\n \033[0;37m*********SECCIÓN CONTENIDO***************\033[0;37m");
        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");
        System.out.println("\n \033[0;33mListar todos los contenidos creados...\033[0;33m");

        System.out.println("\n \033[0;35mActualizando contenido...\033[0;35m");
        System.out.println("\n \033[0;33mListar todos los contenidos después de la actualización de un contenido\033[0;33m");

        System.out.println("\n \033[0;31mEliminando contenido... \033[0;31m");
        System.out.println("\n \033[0;33mListar todos los contenidos después de eliminar un contenido\033[0;33m");

        System.out.println("\n \033[0;37m*********SECCIÓN SUBCONTENIDO***************\033[0;37m");
        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");
        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");
        System.out.println("\n \033[0;33mListar todos los subcontenidos creados...\033[0;33m");

        System.out.println("\n \033[0;35mActualizando subcontenido...\033[0;35m");
        System.out.println("\n \033[0;33mListar todos los subcontenidos después de la actualización de un subcontenido\033[0;33m");

        System.out.println("\n \033[0;31mEliminando subcontenido... \033[0;31m");
        System.out.println("\n \033[0;33mListar todos los subcontenidos después de eliminar un subcontenido\033[0;33m");
    }
}