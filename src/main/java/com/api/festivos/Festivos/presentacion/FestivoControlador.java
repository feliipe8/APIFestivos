package com.api.festivos.Festivos.presentacion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.festivos.Festivos.core.servicios.IFestivoServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/festivos/")
public class FestivoControlador {

    private final IFestivoServicio festivoServicio;


    public FestivoControlador(IFestivoServicio iFestivoServicio){
        this.festivoServicio=iFestivoServicio;
    }


    @GetMapping("verificar/{año}/{mes}/{dia}")
    public ResponseEntity<Object> verificarVestivo(@PathVariable Integer año,@PathVariable Integer mes,@PathVariable Integer dia) {
        return ResponseEntity.ok(festivoServicio.verificarFestivo(año, mes, dia));
    }




}
