import domain.db.*;
import domain.json.EstudianteBusinessObject;
import service.UniService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception{
        UniService globalService = new UniService();

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

        Curso curso1 = new Curso("Programación Orientada a Objetos","Desarrollar software con el paradigma de objetos", LocalDate.now(),2,"presencial", BigDecimal.valueOf(150000),"colones");
        Long cursoId1 = globalService.salvarCurso(curso1);

        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");

        Curso curso2 = new Curso("Programación con Patrones","Desarrollar sistemas utilizando patrones de diseño", LocalDate.now(),2,"virtual", BigDecimal.valueOf(200000),"colones");
        Long cursoId2 = globalService.salvarCurso(curso2);

        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");

        Curso curso3 = new Curso("Fundamentos de Bases de Datos","Fundamentos de SQL", LocalDate.now(),2,"presencial", BigDecimal.valueOf(200000),"colones");
        Long cursoId3 = globalService.salvarCurso(curso3);

        System.out.println("\n \033[0;32mCreando curso...\033[0;32m");

        Curso curso4 = new Curso("Proyecto Ingeniería de Software","Desarrollo de una aplicación full stack en grupo", LocalDate.now(),2,"virtual", BigDecimal.valueOf(200000),"colones");
        Long cursoId4 = globalService.salvarCurso(curso4);

        System.out.println("\n \033[0;33mListar todos los cursos creados...\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;35mActualizando curso...\033[0;35m");

        Curso cursoAModificar = globalService.getCurso(cursoId4);
        cursoAModificar.setDescripcion("Proyecto Ingeniería II");
        cursoAModificar.setModalidad("presencial");
        globalService.salvarCurso(cursoAModificar);

        System.out.println("\n \033[0;33mListar todos los cursos después de la actualización de un curso\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;31mEliminando curso... \033[0;31m");

        globalService.eliminarCurso(cursoId1);

        System.out.println("\n \033[0;33mListar todos los cursos después de eliminar un curso\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;37m*********SECCIÓN CONTENIDO***************\033[0;37m");
        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");

        Contenido contenido1 = new Contenido("Patrones",4,cursoId2);
        Long contenidoId1 = globalService.salvarContenido(contenido1);

        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");

        Contenido contenido2 = new Contenido("Queries",4,cursoId3);
        Long contenidoId2 = globalService.salvarContenido(contenido2);

        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");

        Contenido contenido3 = new Contenido("Importancia del buen diseño",1,cursoId2);
        Long contenidoId3 = globalService.salvarContenido(contenido3);

        System.out.println("\n \033[0;32mCreando contenido...\033[0;32m");

        Contenido contenido4 = new Contenido("Metodologías Ágiles",5,cursoId4);
        Long contenidoId4 = globalService.salvarContenido(contenido3);

        System.out.println("\n \033[0;33mListar todos los cursos ahora con contenidos creados...\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;35mActualizando contenido...\033[0;35m");

        Curso cursoConContenidos = globalService.getCurso(cursoId2);
        Contenido contenidoAModificar = cursoConContenidos.getContenidos().get(1);
        contenidoAModificar.setTema("Scrum");
        contenidoAModificar.setSemana(2);
        globalService.salvarContenido(contenidoAModificar);

        System.out.println("\n \033[0;33mListar todos los cursos después de la actualización de un contenido\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;31mEliminando contenido... \033[0;31m");

        globalService.eliminarContenido(contenidoId3);

        System.out.println("\n \033[0;33mListar todos los cursos después de eliminar un contenido\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;37m*********SECCIÓN SUBCONTENIDO***************\033[0;37m");
        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");

        Subcontenido subcontenido1 = new Subcontenido("Observer",contenidoId1);
        Long subcontenidoId1 = globalService.salvarSubcontenido(subcontenido1);

        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");

        Subcontenido subcontenido2 = new Subcontenido("Builder",contenidoId1);
        Long subcontenidoId2 = globalService.salvarSubcontenido(subcontenido2);

        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");

        Subcontenido subcontenido3 = new Subcontenido("Insert",contenidoId2);
        Long subcontenidoId3 = globalService.salvarSubcontenido(subcontenido3);

        System.out.println("\n \033[0;32mCreando subcontenido...\033[0;32m");

        Subcontenido subcontenido4 = new Subcontenido("Update",contenidoId2);
        Long subcontenidoId4 = globalService.salvarSubcontenido(subcontenido4);

        System.out.println("\n \033[0;33mListar todos los cursos con contenidos y subcontenidos creados...\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;35mActualizando subcontenido...\033[0;35m");

        Curso cursoConSubcontenidos = globalService.getCurso(cursoId3);
        Subcontenido subcontenidoAModificar = cursoConSubcontenidos.getContenidos().get(0).getSubcontenido().get(1);
        subcontenidoAModificar.setDescripcion("Subqueries");
        globalService.salvarSubcontenido(subcontenidoAModificar);

        System.out.println("\n \033[0;33mListar todos los cursos después de la actualización de un subcontenido\033[0;33m");

        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;31mEliminando subcontenido... \033[0;31m");

        globalService.eliminarSubContenido(subcontenidoId3);

        System.out.println("\n \033[0;33mListar todos los subcontenidos después de eliminar un subcontenido\033[0;33m");
        globalService.getCursos().forEach(System.out::println);

        System.out.println("\n \033[0;37m*********SECCIÓN MATRÍCULAS***************\033[0;37m");
        System.out.println("\n \033[0;33mMatriculando estudiantes\033[0;33m");
        EstudianteCurso matricula1 = new EstudianteCurso(estudianteId1,cursoId2);
        Long matriculaId1 = globalService.actualizarCursoDeEstudiante(matricula1);

        EstudianteCurso matricula2 = new EstudianteCurso(estudianteId2,cursoId3);
        Long matriculaId2 = globalService.actualizarCursoDeEstudiante(matricula2);

        EstudianteCurso matricula3 = new EstudianteCurso(estudianteId2,cursoId4);
        Long matriculaId3 =globalService.actualizarCursoDeEstudiante(matricula3);


        EstudianteCurso matricula4 = new EstudianteCurso(estudianteId4,cursoId4);
        Long matriculaId4 = globalService.actualizarCursoDeEstudiante(matricula4);

        System.out.println("\n \033[0;33mPoniendo Notas\033[0;33m");
        EstudianteCurso cursoAPonerNota1= globalService.obtenerCursoDeEstudiante(matriculaId1);
        cursoAPonerNota1.setNota(BigDecimal.valueOf(100));
        globalService.actualizarCursoDeEstudiante(cursoAPonerNota1);

        EstudianteCurso cursoAPonerNota2= globalService.obtenerCursoDeEstudiante(matriculaId2);
        cursoAPonerNota2.setNota(BigDecimal.valueOf(95.5));
        globalService.actualizarCursoDeEstudiante(cursoAPonerNota2);

        EstudianteCurso cursoAPonerNota3= globalService.obtenerCursoDeEstudiante(matriculaId3);
        cursoAPonerNota3.setNota(BigDecimal.valueOf(85.7));
        globalService.actualizarCursoDeEstudiante(cursoAPonerNota3);

        EstudianteCurso cursoAPonerNota4= globalService.obtenerCursoDeEstudiante(matriculaId4);
        cursoAPonerNota4.setNota(BigDecimal.valueOf(100));
        globalService.actualizarCursoDeEstudiante(cursoAPonerNota4);


        System.out.println("\n \033[0;37m*********RESULTADO***************\033[0;37m");
        globalService.getEstudiantes().forEach(estudianteBusinessObject -> System.out.println(estudianteBusinessObject.enhancedToString()));
    }
}