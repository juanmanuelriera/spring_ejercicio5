package com.ejercicio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejercicio.modelo.Curso;
import com.ejercicio.modelo.Formacion;

@Service
public class FormacionesServiceImpl implements FormacionesService {
	
	@Autowired
	RestTemplate template;
	
	private String url = "http://servicio-cursos/";
	
	@Override
	public List<Formacion> formaciones() {
		List<Formacion> formaciones = new ArrayList<>();
		try {
			Formacion formacion;
			List<Curso> cursos = Arrays.asList(template.getForObject(url + "cursos", Curso[].class));
			for (Curso curso : cursos) {
				formacion = new Formacion();
				formacion.setCurso(curso.getNombre());
				formacion.setAsignaturas((curso.getDuracion() >= 50?10:5));
				formacion.setPrecio(curso.getPrecio());
				formaciones.add(formacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formaciones;
	}

	@Override
	public List<Formacion> nuevoCurso(Formacion formacion) {
		try {
			Curso curso = new Curso();
			curso.setCodCurso(formacion.getCurso().substring(0, 3) + (formacion.getAsignaturas()*10));
			curso.setNombre(formacion.getCurso());
			curso.setDuracion(formacion.getAsignaturas()*10);
			curso.setPrecio(formacion.getPrecio());
			template.postForObject(url + "curso", curso, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formaciones();
	}

}
