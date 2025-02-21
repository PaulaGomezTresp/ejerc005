package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Documento;

@SpringBootTest
public class DocumentoRepositoryTest {

    @Autowired
    private DocumentoRepository repository;

    private Documento documentoCreado;

    @BeforeEach
    public void setUp() {
        documentoCreado = getDocumentoYAlmacena();
    }

    @Test
    public void testFindById() {
                Documento documentoEncontrado = repository
                                                .findById(documentoCreado.getId())
                                                .get();

                assertEquals("Doc001", documentoEncontrado.getNombre()); 
                assertEquals(".pdf", documentoEncontrado.getExtension()); 
                assertEquals(1l, documentoEncontrado.getPersona_id()); 
                assertEquals(false, documentoEncontrado.isBorrado());
                
        }


        @Test
        public void testFind() {
    
            List<Documento> documentos = repository
                                            .findAll();

            assertTrue(documentos.size() >= 1);
            }        

    private Documento getDocumentoYAlmacena() {
        Documento documento= new Documento();
        documento.setNombre("Doc001");
        documento.setExtension(".pdf");
        documento.setPersona_id(1l);
        documento.setBorrado(false);
        repository.save(documento);
        return documento;
    }

     @Test
    public void testCreate() {
                assertTrue(
                                repository
                                                .findById(documentoCreado.getId())
                                                .isPresent());
        }

        @Test
        public void delete() {
                assertTrue(
                                repository
                                                .findById(documentoCreado.getId())
                                                .isPresent());
                repository.deleteById(documentoCreado.getId());
        }

        @Test
        public void update() {
                assertTrue(
                                repository
                                                .existsById(documentoCreado.getId()));
                documentoCreado.setNombre("Doc002");
                repository.save(documentoCreado);
                Optional<Documento> updatedDocumento= repository.findById(documentoCreado.getId());
                assertTrue(updatedDocumento.isPresent());
                assertTrue(updatedDocumento.get().getNombre() == "Doc002");
        }



}
