package com.api.festivos.Festivos.aplicacion;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;

import com.api.festivos.Festivos.core.entidades.Festivo;
import com.api.festivos.Festivos.core.repositorios.IFestivoRepositorio;
import com.api.festivos.Festivos.core.servicios.IFestivoServicio;


@Service
public class FestivoServicio implements IFestivoServicio{


    private final IFestivoRepositorio festivosRepositorio;


    public FestivoServicio(IFestivoRepositorio festivosRepositorio){
        this.festivosRepositorio=festivosRepositorio;
    }


    @Override
    public String verificarFestivo(Integer año, Integer mes, Integer dia) {
        if(!validarFecha(dia, mes, año)){
            return "Fecha no válida";
        }
        List<Festivo> listaFestivos = obtenerFestivosPorAño(año);
        listaFestivos.forEach(festivo -> System.out.println(festivo.getNombre()+" -" +festivo.getDia()+"- "+ festivo.getMes()+"-"+año));
        for (Festivo festivo : listaFestivos) {
            if (festivo.getMes() == mes && festivo.getDia() == dia) {
                return "Es festivo";
            }
        }
        return "No es festivo";
    }




    public List<Festivo> obtenerFestivosPorAño(int año) {
        List<Festivo> listaFestivos = festivosRepositorio.findAll();
        List<Festivo> listaFestivosModificada = new ArrayList<>();
        if (listaFestivos != null) {
            Date fechaPascua = agregarDias(getDomingoRamos(año), 7);
            if (listaFestivos != null) {
                for (final Festivo festivo : listaFestivos) {
                    Calendar cal = Calendar.getInstance();
                    switch (festivo.getTipo().getId()) {
                        case 2:
                            Date nuevaFecha2 = siguienteLunes(new Date(año - 1900, festivo.getMes() - 1, festivo.getDia()));
                            cal.setTime(nuevaFecha2);
                            festivo.setDia(cal.get(Calendar.DAY_OF_MONTH));
                            festivo.setMes(cal.get(Calendar.MONTH) + 1);
                            break;
                        case 3:
                            Date nuevaFecha3 = agregarDias(fechaPascua, festivo.getDiasPascua());
                            cal.setTime(nuevaFecha3);
                            festivo.setDia(cal.get(Calendar.DAY_OF_MONTH));
                            festivo.setMes(cal.get(Calendar.MONTH) + 1);
                            break;
                        case 4:
                            Date nuevaFecha4 = siguienteLunes(agregarDias(fechaPascua, festivo.getDiasPascua()));
                            cal.setTime(nuevaFecha4);
                            festivo.setDia(cal.get(Calendar.DAY_OF_MONTH)+1);
                            festivo.setMes(cal.get(Calendar.MONTH) + 1);
                            break;
                    }
                    listaFestivosModificada.add(festivo);
                }
            }
        }
        return listaFestivosModificada;
    }



    public static Date getDomingoRamos(int año) {


        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;
        int dia = 15 + dias;
        int mes = 3;
        if (dia > 31) {
            dia = dia - 31;
            mes = 4;
        }
        return new Date(año , mes - 1, dia);
    }


    public static Date agregarDias(Date fecha, int dias) {


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }
    //
    public static Date siguienteLunes(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaSemana == Calendar.MONDAY) {
            return fecha;}
        else if (diaSemana > Calendar.MONDAY) {
            fecha = agregarDias(fecha, Calendar.SATURDAY - diaSemana + 2);
        } else {
            fecha = agregarDias(fecha, Calendar.MONDAY - diaSemana);
        }
            return fecha;
    }


    public static boolean validarFecha(int dia, int mes, int anio) {
        if (anio < 0 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
            return false;
        }
        if (mes == 2) {
            if (esBisiesto(anio)) {
                return (dia <= 29);
            } else {
                return (dia <= 28);
            }
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return (dia <= 30);
        }
        return true;
    }


    public static boolean esBisiesto(int anio) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anio);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }


}
