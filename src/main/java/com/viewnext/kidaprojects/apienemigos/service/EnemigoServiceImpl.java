package com.viewnext.kidaprojects.apienemigos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.kidaprojects.apienemigos.model.Enemigo;
import com.viewnext.kidaprojects.apienemigos.repository.EnemigoRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * La clase {@code EnemigoServiceImpl} implementa la interfaz
 * {@code EnemigoService} y proporciona servicios relacionados con la gestión de
 * enemigos en el sistema.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 5 de Octubre de 2023
 */
@Service
public class EnemigoServiceImpl implements EnemigoService {

	@Autowired
	private EnemigoRepository enemigoRepository;

	/**
	 * Recupera una lista de todos los enemigos en el sistema.
	 *
	 * @return Una lista de todos los enemigos.
	 * @throws EntityNotFoundException Si no se encuentran enemigos en el sistema.
	 */
	@Override
	public List<Enemigo> showAll() throws EntityNotFoundException {
		List<Enemigo> listaEnemigos = enemigoRepository.findAll();

		if (listaEnemigos.isEmpty()) {
			throw new EntityNotFoundException();
		}

		return listaEnemigos;
	}

	/**
	 * Recupera una lista de enemigos cuyos nombres coinciden con un prefijo dado.
	 *
	 * @param prefijo El prefijo del nombre de los enemigos a buscar.
	 * @return Una lista de enemigos cuyos nombres coinciden con el prefijo.
	 * @throws EntityNotFoundException Si no se encuentran enemigos con el prefijo
	 *                                 especificado.
	 */
	@Override
	public List<Enemigo> showByNombreLike(String prefijo) throws EntityNotFoundException {
		List<Enemigo> listaEnemigos = enemigoRepository.findByNombrePrefijo(prefijo);
		if (listaEnemigos.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaEnemigos;
	}

	/**
	 * Recupera una lista de enemigos cuyo nivel de dificultad sea igual o menor al
	 * nivel especificado.
	 *
	 * @param nivel El nivel de dificultad máximo deseado.
	 * @return Una lista de enemigos con niveles de dificultad iguales o menores al
	 *         nivel especificado.
	 * @throws EntityNotFoundException Si no se encuentran enemigos con el nivel
	 *                                 especificado.
	 */
	@Override
	public List<Enemigo> showByNivel(int nivel) throws EntityNotFoundException {
		List<Enemigo> listaEnemigos = enemigoRepository.findByNivel(nivel);

		if (listaEnemigos.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaEnemigos;
	}

	/**
	 * Recupera un enemigo por su identificador único.
	 *
	 * @param idEnemigo El identificador único del enemigo a recuperar.
	 * @return El enemigo correspondiente al identificador dado.
	 * @throws EntityNotFoundException Si no se encuentra un enemigo con el
	 *                                 identificador especificado.
	 */
	@Override
	public Enemigo showById(int idEnemigo) throws EntityNotFoundException {
		Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(idEnemigo);

		if (optionalEnemigo.isEmpty()) {
			throw new EntityNotFoundException();
		}

		return optionalEnemigo.get();
	}

	/**
	 * Obtiene la recompensa asociada a un enemigo específico identificado por su
	 * ID.
	 *
	 * @param idEnemigo El ID del enemigo del cual se desea obtener la recompensa.
	 * @return La cantidad de recompensa asociada al enemigo especificado, o -1 si
	 *         el enemigo no existe.
	 * @throws EntityNotFoundException Si no se encuentra un enemigo con el ID
	 *                                 especificado.
	 */
	@Override
	public int getRecomenpsa(int idEnemigo) throws EntityNotFoundException {
		Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(idEnemigo);

		if (optionalEnemigo.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Enemigo enemigo = optionalEnemigo.get();

		return enemigo.getRecompensa();
	}

	/**
	 * Crea un nuevo enemigo en el sistema.
	 *
	 * @param enemigo El enemigo que se va a crear.
	 * @return El enemigo creado con su identificador único.
	 */
	@Override
	public Enemigo createEnemigo(Enemigo enemigo) {

		return enemigoRepository.save(enemigo);
	}

	/**
	 * Actualiza la vida de un enemigo mediante su identificador único y la cantidad
	 * de daño recibido.
	 *
	 * @param idEnemigo El identificador único del enemigo a actualizar.
	 * @param damage    La cantidad de daño que el enemigo ha recibido.
	 * @return El enemigo con la vida actualizada.
	 * @throws EntityNotFoundException Si no se encuentra un enemigo con el ID
	 *                                 especificado.
	 */
	@Override
	public Enemigo updateVida(int idEnemigo, int damage) throws EntityNotFoundException {
		Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(idEnemigo);

		if (optionalEnemigo.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Enemigo enemigo = optionalEnemigo.get();

		int vidaActual = enemigo.getVidaActual();
		
		enemigo.setVidaActual(vidaActual - damage);

		if (vidaActual <= 0) {
			enemigo.setVencido(true);
		}

		return enemigoRepository.save(enemigo);
	}

	/**
	 * Establece el estado de un enemigo como "Inactivo" mediante su identificador
	 * único.
	 *
	 * @param idEnemigo El identificador único del enemigo a marcar como "Inactivo".
	 * @return El enemigo con el estado actualizado.
	 * @throws EntityNotFoundException Si no se encuentra un enemigo con el ID
	 *                                 especificado.
	 */
	@Override
	public Enemigo setEstadoInactivo(int idEnemigo) throws EntityNotFoundException {
		Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(idEnemigo);

		if (optionalEnemigo.isEmpty()) {
			throw new EntityNotFoundException();
		}

		Enemigo enemigo = optionalEnemigo.get();

		enemigo.setActivo(false);

		return enemigoRepository.save(enemigo);
	}

	/**
	 * Reinicia el estado de todos los enemigos, estableciendo su estado como
	 * activos y no vencidos. Este método se utiliza para reiniciar todos los
	 * enemigos en el sistema.
	 */
	@Override
	public void reiniciarEnemigos() {
		List<Enemigo> listaEnemigos = enemigoRepository.findAll();

		for (Enemigo e : listaEnemigos) {
			e.setActivo(true);
			e.setVencido(false);
			e.setVidaActual(e.getVidaOriginal());

		}

		enemigoRepository.saveAll(listaEnemigos);
	}

}
