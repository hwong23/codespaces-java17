package com.torneotenismesa.gestionparticipantes.modelos;

import com.torneotenismesa.gestionparticipantes.enums.Genero;
// import com.torneotenismesa.gestionusuarios.modelos.Usuario; // Si se vincula a Usuario

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa a un jugador individual. Hereda de Participante.
 */
public class Jugador extends Participante {
    // idJugador podría ser el mismo que idParticipante, pero se mantiene por si hay lógica específica
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Integer ranking; // Opcional
    private String club;     // Opcional
    private Genero genero;
    // private Usuario cuentaUsuario; // Opcional, si un jugador puede tener una cuenta en el sistema

    public Jugador(String nombre, String apellido, Date fechaNacimiento, Genero genero) {
        super(); // Llama al constructor de Participante para inicializar idParticipante
        this.nombre = Objects.requireNonNull(nombre, "El nombre del jugador no puede ser nulo.");
        this.apellido = Objects.requireNonNull(apellido, "El apellido del jugador no puede ser nulo.");
        this.fechaNacimiento = fechaNacimiento;
        this.genero = Objects.requireNonNull(genero, "El género del jugador no puede ser nulo.");
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /*
    public Usuario getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(Usuario cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }
    */

    @Override
    public String getNombreDescriptivo() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idParticipante=" + idParticipante + // Heredado de Participante
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero=" + genero +
                '}';
    }
}