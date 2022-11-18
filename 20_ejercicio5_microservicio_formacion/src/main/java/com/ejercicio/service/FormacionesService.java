package com.ejercicio.service;

import java.util.List;

import com.ejercicio.modelo.Formacion;

public interface FormacionesService {
	
	List<Formacion> formaciones();
	
	List<Formacion> nuevoCurso(Formacion formacion);
}
