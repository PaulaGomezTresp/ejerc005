package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Pais;

@SpringBootTest
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository repository;

    private Pais paisCreado;

    @BeforeEach
    public void setUp() {
        paisCreado = getPaisYAlmacena();
    }

    @Test
    public void testFindById() {
                Pais paisEncontrado = repository
                                                .findById(paisCreado.getId())
                                                .get();

                assertEquals("España", paisEncontrado.getNombre()); 
                assertEquals("Es un país muy bonito", paisEncontrado.getDescripcion()); 
                assertEquals("Europa", paisEncontrado.getContinente()); 
                
        }


        @Test
        public void testFind() {
    
            List<Pais> paises = repository
                                            .findAll();

            assertTrue(paises.size() >= 1);
            }        

    private Pais getPaisYAlmacena() {
        Pais pais= new Pais();
        pais.setNombre("España");
        pais.setDescripcion("Es un país muy bonito");
        pais.setContinente("Europa");
        repository.save(pais);
        return pais;
    }

     @Test
    public void testCreate() {
                assertTrue(
                                repository
                                                .findById(paisCreado.getId())
                                                .isPresent());
        }

        @Test
        public void delete() {
                assertTrue(
                                repository
                                                .findById(paisCreado.getId())
                                                .isPresent());
                repository.deleteById(paisCreado.getId());
        }

        @Test
        public void update() {
                assertTrue(
                                repository
                                                .existsById(paisCreado.getId()));
                paisCreado.setNombre("Francia");
                repository.save(paisCreado);
                Optional<Pais> updatedPais= repository.findById(paisCreado.getId());
                assertTrue(updatedPais.isPresent());
                assertTrue(updatedPais.get().getNombre() == "Francia");
        }



}
