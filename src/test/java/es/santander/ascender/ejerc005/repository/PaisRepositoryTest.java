package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Pais;

@SpringBootTest
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository repository;

     @Test
        public void testCreate() {
                Pais pais = new Pais();
                pais.setNombre("España");
                pais.setDescripcion("Pais muy bonito");
                pais.setContinente("Europa");
                         

                repository.save(pais);
                assertTrue(
                                repository
                                                .findById(pais.getId())
                                                .isPresent());
        }

        @Test
        public void delete() {
            Pais pais = new Pais();
            pais.setNombre("España");
            pais.setDescripcion("Pais muy bonito");
            pais.setContinente("Europa");
                   

            repository.save(pais);
                assertTrue(
                                repository
                                                .findById(pais.getId())
                                                .isPresent());
                repository.deleteById(pais.getId());
        }

        @Test
        public void update() {
            Pais pais = new Pais();
            pais.setNombre("España");
            pais.setDescripcion("Pais muy bonito");
            pais.setContinente("Europa");
                     

            repository.save(pais);
                assertTrue(
                                repository
                                                .existsById(pais.getId()));
                pais.setNombre("Francia");
                repository.save(pais);
                Optional<Pais> updatedPais= repository.findById(pais.getId());
                assertTrue(updatedPais.isPresent());
                assertTrue(updatedPais.get().getNombre() == "Francia");
        }



}
