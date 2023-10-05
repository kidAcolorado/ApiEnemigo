package com.viewnext.kidaprojects.apienemigos.service;

import java.util.List;

import com.viewnext.kidaprojects.apienemigos.model.Enemigo;

/**
 * La interfaz {@code EnemigoService} proporciona métodos para gestionar enemigos en el sistema.
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 5 de Octubre de 2023
 */
public interface EnemigoService {

    /**
     * Recupera una lista de todos los enemigos en el sistema.
     *
     * @return Una lista de todos los enemigos.
     */
    List<Enemigo> showAll();

    /**
     * Recupera una lista de enemigos cuyos nombres coinciden con un prefijo dado.
     *
     * @param prefijo El prefijo del nombre de los enemigos a buscar.
     * @return Una lista de enemigos cuyos nombres coinciden con el prefijo.
     */
    List<Enemigo> showByNombreLike(String prefijo);

    /**
     * Recupera una lista de enemigos cuyo nivel de dificultad sea igual o menor al nivel especificado.
     *
     * @param nivel El nivel de dificultad máximo deseado.
     * @return Una lista de enemigos con niveles de dificultad iguales o menores al nivel especificado.
     */
    List<Enemigo> showByNivel(int nivel);

    /**
     * Recupera un enemigo por su identificador único.
     *
     * @param idEnemigo El identificador único del enemigo a recuperar.
     * @return El enemigo correspondiente al identificador dado.
     */
   Enemigo showById(int idEnemigo);

    /**
     * Obtiene la recompensa asociada a un enemigo específico identificado por su ID.
     *
     * @param idEnemigo El ID del enemigo del cual se desea obtener la recompensa.
     * @return La cantidad de recompensa asociada al enemigo especificado, o -1 si el enemigo no existe.
     */
    int getRecomenpsa(int idEnemigo);

    /**
     * Crea un nuevo enemigo en el sistema.
     *
     * @param enemigo El enemigo que se va a crear.
     * @return El enemigo creado con su identificador único.
     */
    Enemigo createEnemigo(Enemigo enemigo);

    /**
     * Actualiza la vida de un enemigo mediante su identificador único y la cantidad de daño recibido.
     *
     * @param idEnemigo El identificador único del enemigo a actualizar.
     * @param daño La cantidad de daño que el enemigo ha recibido.
     * @return El enemigo con la vida actualizada.
     */
    Enemigo updateVida(int idEnemigo, int damage);

    
    /**
     * Establece el estado de un enemigo como "Inactivo" mediante su identificador único.
     *
     * @param idEnemigo El identificador único del enemigo a marcar como "Inactivo".
     * @return El enemigo con el estado actualizado.
     */
    Enemigo setEstadoInactivo(int idEnemigo);

    /**
     * Reinicia el estado de todos los enemigos, estableciendo su estado como activos y no vencidos. Este método se utiliza
     * para reiniciar todos los enemigos en el sistema.
     */
    void reiniciarEnemigos();
}

