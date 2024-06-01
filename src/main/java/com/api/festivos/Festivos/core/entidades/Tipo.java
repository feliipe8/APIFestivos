package com.api.festivos.Festivos.core.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tipo")
public class Tipo {


    @Id
    @Column
    private int id;


    @Column(length = 100,nullable=false)
    private String tipo;


    public Tipo() {
    }


    public Tipo(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }


    public String getTipo() {
        return tipo;
    }


}


