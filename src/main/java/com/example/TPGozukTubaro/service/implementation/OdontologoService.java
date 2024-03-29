package com.example.TPGozukTubaro.service.implementation;


import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.exceptions.ResourceNotFoundException;
import com.example.TPGozukTubaro.repository.IOdontologoRepository;
import com.example.TPGozukTubaro.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Guardando el odontologo de la matricula" + odontologo.getMatricula());
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        }else {
            String message = "No se encontro el odontologo con el ID" + id;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

   // @Override
  //  public void actualizar(Odontologo odontologo) {
    //    odontologoRepository.save(odontologo);
//    }

    @Override
    public void actualizar(Odontologo odontologo){
        LOGGER.info("Actualizando odontologo: "+odontologo.getMatricula());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoRepository.save(odontologo);
            LOGGER.info("Odontologo actualizado!");
        }else {
            String message = "No se encontro el odontologo a actualizar";
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    //@Override
    //public void eliminar(Long id) {
    //      odontologoRepository.deleteById(id);
    //    }

    @Override
    public void eliminar(Long id) {
        LOGGER.info("Eliminando odontologo del ID:" +id );
        Optional<Odontologo> odontologoABorrar = odontologoRepository.findById(id);
        if (odontologoABorrar.isPresent()) {
            odontologoRepository.deleteById(id);
            LOGGER.info("Se elimino el odontolgo!");
        }else{
            String message = "No se encontro el odontologo con ID "+id;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando los odontologos");
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> findByMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }

    //agregados metos de hql
    @Override
    public List<Odontologo> listOrderByMatricula() {
       LOGGER.info("Muestro Odontologos por matricula ASC");
       return odontologoRepository.listOrderByMatricula();
    }
    @Override
    public List<Odontologo> listOrderByNombre() {
        LOGGER.info("Muestro Odonotologos por nombre ASC");
        return odontologoRepository.listOrderByNombre();
    }

    @Override
    public List<Odontologo> listOrderByApellido() {
        LOGGER.info("Muestro Odontologos por apellido ASC");
        return odontologoRepository.listOrderByApellido();
    }



}