package com.viewnext.kidaprojects.apienemigos.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La clase {@code Enemigo} representa un enemigo en el sistema. Contiene
 * información como el identificador del enemigo, el nombre, la descripción, el
 * nivel de dificultad, la recompensa, la fuerza, la defensa, la vida original, la vida actual, el estado
 * de vencido/no vencido y el estado activo/inactivo del enemigo.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 5 de Octubre de 2023
 */
@Entity
@Table(name = "enemigos")
public class Enemigo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnemigo;

	private String nombre;
	private String descripcion;
	private int nivel;
	private int recompensa;
	private int fuerza;
	private int defensa;
	private int vidaOriginal;
	private int vidaActual;
	private boolean vencido;
	private boolean activo;

	public Enemigo(String nombre, String descripcion, int nivel, int recompensa, int fuerza, int defensa,
			int vidaOriginal) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.recompensa = recompensa;
		this.fuerza = fuerza;
		this.defensa = defensa;
		this.vidaOriginal = vidaOriginal;
		this.vidaActual = vidaOriginal;
		this.vencido = false;
		this.activo = true;
	}

	public Enemigo() {
		super();
	}

	public int getIdEnemigo() {
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(int recompensa) {
		this.recompensa = recompensa;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getVidaOriginal() {
		return vidaOriginal;
	}

	public void setVidaOriginal(int vidaOriginal) {
		this.vidaOriginal = vidaOriginal;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public void setVidaActual(int vida) {
		this.vidaActual = vida;
	}

	public boolean isVencido() {
		return vencido;
	}

	public void setVencido(boolean vencido) {
		this.vencido = vencido;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEnemigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enemigo other = (Enemigo) obj;
		return idEnemigo == other.idEnemigo;
	}

	@Override
	public String toString() {
		return "Enemigo [idEnemigo=" + idEnemigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel="
				+ nivel + ", recompensa=" + recompensa + ", fuerza=" + fuerza + ", defensa=" + defensa
				+ ", vidaOriginal=" + vidaOriginal + ", vidaActual=" + vidaActual + ", vencido=" + vencido + ", activo="
				+ activo + "]";
	}

	

}
