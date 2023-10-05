package com.viewnext.kidaprojects.apienemigos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viewnext.kidaprojects.apienemigos.model.Enemigo;

/**
 * La interfaz {@code EnemigoRepository} proporciona métodos para acceder y gestionar
 * enemigos en la base de datos.
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 5 de Octubre de 2023
 */
public interface EnemigoRepository extends JpaRepository<Enemigo, Integer>{

	 /**
     * Recupera una lista de enemigos cuyos nombres comienzan con el prefijo especificado.
     *
     * @param prefijo El prefijo que debe coincidir con el nombre de los enemigos.
     * @return Una lista de enemigos cuyos nombres coinciden con el prefijo.
     */
	@Query("SELECT e FROM Enemigo e WHERE e.nombre LIKE :prefijo%")
	List<Enemigo> findByNombrePrefijo(@Param("prefijo") String prefijo);
	
	/**
     * Recupera una lista de enemigos cuyo nivel es menor o igual al nivel especificado.
     *
     * @param nivel El nivel máximo de los enemigos a recuperar.
     * @return Una lista de enemigos cuyo nivel es menor o igual al nivel especificado.
     */
	@Query("SELECT e FROM Enemigo e WHERE e.nivel <= :nivel")
	List<Enemigo> findByNivel(@Param("nivel") int nivel);
}
