package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entidades.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
