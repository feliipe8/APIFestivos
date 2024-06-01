package com.api.festivos.Festivos.core.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.festivos.Festivos.core.entidades.Festivo;


@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {}