package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Provincia;

@SpringBootTest
public class ProvinciaRepositoryTest {

   
    @Autowired
    private ProvinciaRepository repository;

    private Provincia provinciaCreada;

    @BeforeEach
    public void setUp() {
        provinciaCreada = getProvinciaYAlmacena();
    }

    @Test
    public void testFindById() {
                Provincia provinciaEncontrada = repository
                                                .findById(provinciaCreada.getId())
                                                .get();

                assertEquals("Cantabria", provinciaEncontrada.getNombre()); 
                assertEquals(35l, provinciaEncontrada.getPais_id()); 
                
                
        }


        @Test
        public void testFind() {
    
            List<Provincia> provincias = repository
                                            .findAll();

            assertTrue(provincias.size() >= 1);
            }        

    private Provincia getProvinciaYAlmacena() {
        Provincia provincia= new Provincia();
        provincia.setNombre("Cantabria");
        provincia.setPais_id(35l);
        repository.save(provincia);
        return provincia;
    }

     @Test
    public void testCreate() {
                assertTrue(
                                repository
                                                .findById(provinciaCreada.getId())
                                                .isPresent());
        }

        @Test
        public void delete() {
                assertTrue(
                                repository
                                                .findById(provinciaCreada.getId())
                                                .isPresent());
                repository.deleteById(provinciaCreada.getId());
        }

        @Test
        public void update() {
                assertTrue(
                                repository
                                                .existsById(provinciaCreada.getId()));
                provinciaCreada.setNombre("Cataluña");
                repository.save(provinciaCreada);
                Optional<Provincia> updatedProvincia= repository.findById(provinciaCreada.getId());
                assertTrue(updatedProvincia.isPresent());
                assertTrue(updatedProvincia.get().getNombre() == "Cataluña");
        }


}
