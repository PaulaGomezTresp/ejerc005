package es.santander.ascender.ejerc005.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Provincia {

    @Id
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
