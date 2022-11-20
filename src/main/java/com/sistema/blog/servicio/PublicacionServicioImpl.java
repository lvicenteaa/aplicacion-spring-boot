package com.sistema.blog.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.repository.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setContenido(publicacionDTO.getContenido());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
		
		PublicacionDTO publicacionRespuesta = new PublicacionDTO();
		publicacionRespuesta.setId(nuevaPublicacion.getId());
		publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
		publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());
		publicacionRespuesta.setDescripcion(nuevaPublicacion.getDescripcion());
		
		return publicacionRespuesta;
	}

}
