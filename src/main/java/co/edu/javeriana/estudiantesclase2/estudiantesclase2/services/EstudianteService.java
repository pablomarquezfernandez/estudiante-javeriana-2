package co.edu.javeriana.estudiantesclase2.estudiantesclase2.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.estudiantesclase2.estudiantesclase2.ModelMapperConfiguration;
import co.edu.javeriana.estudiantesclase2.estudiantesclase2.dto.EstudianteDTO;
import co.edu.javeriana.estudiantesclase2.estudiantesclase2.entities.Estudiante;
import co.edu.javeriana.estudiantesclase2.estudiantesclase2.repositories.EstudianteRepository;

@Service
public class EstudianteService {

    
    EstudianteRepository estudianteRepository;
    ModelMapper modelMapper;;

    @Autowired
    EstudianteService(EstudianteRepository estudianteRepository,  ModelMapper modelMapperConfiguration){
        this.estudianteRepository = estudianteRepository;
        this.modelMapper = modelMapperConfiguration;
    }


    public EstudianteDTO get( Long id ){
        Optional<Estudiante>estudianteOpt = estudianteRepository.findById(id);
        EstudianteDTO estudianteDTO = null;
        if( estudianteOpt.isPresent() ){
            Estudiante estuduante = estudianteOpt.get();
            estudianteDTO = modelMapper.map( estuduante, EstudianteDTO.class);
        }
        return estudianteDTO;
    }

    public List<EstudianteDTO> get(  ){
        List<Estudiante> estudiantes =  (List<Estudiante>) estudianteRepository.findAll();

        List<EstudianteDTO> estudianteDTOs = estudiantes.stream()
                                                            .map(estudiante -> modelMapper.map(estudiante, EstudianteDTO.class))
                                                            .collect(Collectors.toList());
       
        return estudianteDTOs;
    }


    public EstudianteDTO save( EstudianteDTO estudianteDTO ){
        Estudiante estudiante =  modelMapper.map(estudianteDTO, Estudiante.class);
        estudiante = estudianteRepository.save(estudiante);
        estudianteDTO = modelMapper.map(estudiante, EstudianteDTO.class);

        return estudianteDTO;

    }


    public EstudianteDTO update( EstudianteDTO estudianteDTO ){
        Estudiante estudiante =  modelMapper.map(estudianteDTO, Estudiante.class);
        estudiante = estudianteRepository.save(estudiante);
        estudianteDTO = modelMapper.map(estudiante, EstudianteDTO.class);

        return estudianteDTO;

    }

    public void delete( Long id ){
        estudianteRepository.deleteById(id);
    }
}
