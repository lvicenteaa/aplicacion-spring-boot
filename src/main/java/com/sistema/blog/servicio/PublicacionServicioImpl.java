package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repository.PublicacionRepositorio;

@Service
public class PublicacionServicioImpl implements PublicacionServicio {

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = this.mapearDTO(publicacionDTO);
		Publicacion nuevaPublicacion = this.publicacionRepositorio.save(publicacion);
		PublicacionDTO publicacionRespuesta = this.mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
		List<Publicacion> publicaciones = publicacionRepositorio.findAll();
		return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
	}

	// Convierte entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();
		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setContenido(publicacion.getContenido());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		return publicacionDTO;
	}

	// Convierte DTO a Entidad
		private Publicacion mapearDTO(PublicacionDTO publicacionDTO) {
			Publicacion publicacion = new Publicacion();
			publicacion.setTitulo(publicacionDTO.getTitulo());
			publicacion.setContenido(publicacionDTO.getContenido());
			publicacion.setDescripcion(publicacionDTO.getDescripcion());
			return publicacion;
		}

		@Override
		public PublicacionDTO obtenerPublicacionPorId(Long id) {
			Publicacion publicacion = this.publicacionRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
			return mapearDTO(publicacion);
		}

		@Override
		public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
			Publicacion publicacion = this.publicacionRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
			
			publicacion.setTitulo(publicacionDTO.getTitulo());
			publicacion.setDescripcion(publicacionDTO.getDescripcion());
			publicacion.setContenido(publicacionDTO.getContenido());
			
			Publicacion publicacionActualizada = this.publicacionRepositorio.save(publicacion);
			
			return mapearDTO(publicacionActualizada);
		}

		@Override
		public void eliminarPublicacion(Long id) {
			Publicacion publicacion = this.publicacionRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
			this.publicacionRepositorio.delete(publicacion);
		}
	
}
