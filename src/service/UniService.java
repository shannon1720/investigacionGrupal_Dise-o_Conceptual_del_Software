package service;

import domain.Estudiante;
import persitence.EstudianteRepository;

import java.util.List;

public class UniService {
    private EstudianteRepository estudianteRepository;
    public UniService(){
        estudianteRepository = new EstudianteRepository();
    }
}
