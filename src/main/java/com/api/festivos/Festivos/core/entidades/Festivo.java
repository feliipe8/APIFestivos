package com.api.festivos.Festivos.core.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="festivo")
public class Festivo {

    @Id
    @Column
    private int id;


    @Column(length = 100,nullable=false)
    private String nombre;


    @Column(nullable=false)
    private int dia;


    @Column(nullable=false)
    private int mes;

    @Column(name="diaspascua",nullable=false)
    private int diasPascua;


    @ManyToOne
    @JoinColumn(name="idtipo",referencedColumnName = "id",nullable=false)
    private Tipo tipo;


    public Festivo() {
    }


    public Festivo(int id, String nombre, int dia, int mes, int diasPascua, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public int getDia() {
        return dia;
    }


    public int getMes() {
        return mes;
    }


    public int getDiasPascua() {
        return diasPascua;
    }


    public Tipo getTipo() {
        return tipo;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setDia(int dia) {
        this.dia = dia;
    }


    public void setMes(int mes) {
        this.mes = mes;
    }


    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }


    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
