package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.repository.PersonaRepository;
import es.santander.ascender.ejerc005.model.Persona;

@Service
@Transactional
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public Persona create (Persona persona){
        if (persona.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro persona utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 persona.getId());
        }
        return repository.save(persona);
    }

    @Transactional (readOnly = true)
    public Persona read(Long id){
        return repository.findById(id).orElse(null);
    }

    @Transactional (readOnly = true)
    public List<Persona> read(){
        return repository.findAll();
    }

    public Persona update(Persona persona){
        if (persona.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro persona utilizando la modificación",
                                                 CRUDOperation.UPDATE, 
                                                 persona.getId());
        }
        return repository.save(persona);
    }

    public void delete(Long id){
        repository.deleteById(id);
        return;
    }
}
