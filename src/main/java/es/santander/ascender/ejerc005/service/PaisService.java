package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc005.model.Pais;
import es.santander.ascender.ejerc005.repository.PaisRepository;

@Service
@Transactional
public class PaisService {

    @Autowired
    private PaisRepository repository;

     public Pais create(Pais pais){
         if (pais.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro pais utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 pais.getId());
        }
        return repository.save(pais);
    }

    @Transactional (readOnly = true)
    public Pais read(Long id){
        return repository.findById(id).orElse(null);
    }

    @Transactional (readOnly = true)
    public List<Pais> read(){
        return repository.findAll();
    }

    public Pais update(Pais pais){
        if (pais.getId() == null){
            throw new CrudSecurityException("Han tratado de crear un registro pais utilizando la modificación",
                                                CRUDOperation.UPDATE, 
                                                pais.getId());
        }
        return repository.save(pais);
    }

    public void delete (Long id){
        repository.deleteById(id);
        return;
    }


}
