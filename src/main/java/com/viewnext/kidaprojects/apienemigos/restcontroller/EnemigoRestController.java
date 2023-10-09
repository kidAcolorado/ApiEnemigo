package com.viewnext.kidaprojects.apienemigos.restcontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.kidaprojects.apienemigos.model.Enemigo;
import com.viewnext.kidaprojects.apienemigos.service.EnemigoService;

import jakarta.persistence.EntityNotFoundException;

/**
 * El controlador {@code EnemigoRestController} maneja las solicitudes
 * relacionadas con la entidad "Enemigo" en el sistema. Proporciona endpoints
 * para recuperar, crear, actualizar y gestionar enemigos, así como para
 * reiniciar su estado.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 5 de Octubre de 2023
 */
@RestController
public class EnemigoRestController {

	@Autowired
	private EnemigoService service;

	private static final String ENEMIGO_NOT_FOUND = "Enemigo/s no encontrado";

	/**
	 * Maneja una solicitud GET para recuperar todos los enemigos en el sistema.
	 *
	 * @return ResponseEntity que contiene una lista de enemigos si se encuentran, o
	 *         un mensaje de error si no se encuentran enemigos.
	 */
	@GetMapping(value = "enemigo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAllEnemigos() {
		try {
			List<Enemigo> listaEnemigos = service.showAll();
			return ResponseEntity.ok(listaEnemigos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud GET para recuperar un enemigo por su ID.
	 *
	 * @param idEnemigo El ID del enemigo a recuperar.
	 * @return ResponseEntity que contiene el enemigo si se encuentra, o un mensaje
	 *         de error si no se encuentra el enemigo.
	 */
	@GetMapping(value = "enemigo/{idEnemigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showEnemigoById(@PathVariable("idEnemigo") int idEnemigo) {
		try {
			Enemigo enemigo = service.showById(idEnemigo);
			return ResponseEntity.ok(enemigo);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud GET para recuperar la recompensa de un enemigo por su
	 * ID.
	 *
	 * @param idEnemigo El ID del enemigo para el cual se desea obtener la
	 *                  recompensa.
	 * @return ResponseEntity que contiene la recompensa si se encuentra, o un
	 *         mensaje de error si no se encuentra el enemigo.
	 */
	@GetMapping(value = "enemigo/recompensa/{idEnemigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecompensaByIdEnemigo(@PathVariable("idEnemigo") int idEnemigo) {
		try {
			int recompensa = service.getRecomenpsa(idEnemigo);
			return ResponseEntity.ok(recompensa);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}

	}

	/**
	 * Maneja una solicitud GET para recuperar enemigos cuyos nombres coinciden con
	 * un prefijo dado.
	 *
	 * @param prefijo El prefijo del nombre del enemigo a buscar.
	 * @return ResponseEntity que contiene una lista de enemigos si se encuentran, o
	 *         un mensaje de error si no se encuentran enemigos.
	 */
	@GetMapping(value = "enemigo/nombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showEnemigosByNombreLike(@RequestParam("prefijo") String prefijo) {
		try {
			List<Enemigo> listaEnemigos = service.showByNombreLike(prefijo);
			return ResponseEntity.ok(listaEnemigos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud GET para recuperar enemigos cuyo nivel de dificultad sea
	 * igual o menor al nivel especificado.
	 *
	 * @param nivel El nivel de dificultad máximo deseado.
	 * @return ResponseEntity que contiene una lista de enemigos si se encuentran, o
	 *         un mensaje de error si no se encuentran enemigos.
	 */
	@GetMapping(value = "enemigo/nivel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showEnemigosByNivel(@RequestParam("nivel") int nivel) {

		try {
			List<Enemigo> listaEnemigos = service.showByNivel(nivel);
			return ResponseEntity.ok(listaEnemigos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud POST para crear un nuevo enemigo en el sistema.
	 *
	 * @param enemigo El enemigo que se va a crear.
	 * @return ResponseEntity que contiene el enemigo creado y la URI del nuevo
	 *         recurso.
	 */
	@PostMapping(value = "enemigo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Enemigo> createEnemigo(@RequestBody Enemigo enemigo) {
		Enemigo enemigoCreado = service.createEnemigo(enemigo);

		// Utiliza la ruta relativa al recurso
		URI location = URI.create("/enemigo/" + enemigoCreado.getIdEnemigo());

		// Devuelve una respuesta con el código 201 Created y la URI del nuevo recurso
		return ResponseEntity.created(location).body(enemigoCreado);

	}

	/**
	 * Maneja una solicitud PUT para actualizar la vida de un enemigo basado en su
	 * ID y la cantidad de daño recibido.
	 *
	 * @param idEnemigo El ID del enemigo a actualizar.
	 * @param damage    La cantidad de daño recibido por el enemigo.
	 * @return ResponseEntity que contiene el enemigo actualizado o un mensaje de
	 *         error si el enemigo no se encuentra.
	 */
	@PutMapping(value = "enemigo/{idEnemigo}/damage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateVidaEnemigo(@PathVariable("idEnemigo") int idEnemigo,
			@RequestParam("damage") int damage) {
		try {
			Enemigo enemigoActualizado = service.updateVida(idEnemigo, damage);
			return ResponseEntity.ok(enemigoActualizado);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud DELETE para establecer el estado de un enemigo como
	 * "Inactivo" basado en su ID.
	 *
	 * @param idEnemigo El ID del enemigo a desactivar.
	 * @return ResponseEntity que contiene el enemigo desactivado o un mensaje de
	 *         error si el enemigo no se encuentra.
	 */
	@DeleteMapping(value = "enemigo/{idEnemigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setEstadoInactivo(@PathVariable("idEnemigo") int idEnemigo) {
		try {
			Enemigo enemigoInactivo = service.setEstadoInactivo(idEnemigo);
			return ResponseEntity.ok(enemigoInactivo);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENEMIGO_NOT_FOUND);
		}
	}

	/**
	 * Maneja una solicitud POST para reiniciar el estado de todos los enemigos en
	 * el sistema.
	 *
	 * @return ResponseEntity que contiene un mensaje indicando que los enemigos han
	 *         sido reiniciados.
	 */
	@PostMapping(value = "enemigo/reinicio")
	public ResponseEntity<String> reiniciarEnemigos() {
		service.reiniciarEnemigos();
		return ResponseEntity.ok("Enemigos reiniciados");
	}
}
