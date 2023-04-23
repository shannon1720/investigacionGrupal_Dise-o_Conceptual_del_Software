import domain.Estudiante;
import persitence.EstudianteRepository;

public class Main {
    public static void main(String[] args) throws Exception{
        Estudiante estudiante = new Estudiante("Jose","Suarez","Perez","Masculino","Por allá","correo@correo.com","12345678","0987654332",15);
        EstudianteRepository estudianteRepository = new EstudianteRepository();
        //estudianteRepository.upsert(estudiante);
        System.out.println(estudianteRepository.buscar(6L));

    }
}