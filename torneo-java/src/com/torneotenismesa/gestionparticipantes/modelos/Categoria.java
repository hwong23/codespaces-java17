package com.torneotenismesa.gestionparticipantes.modelos;

import com.torneotenismesa.gestionparticipantes.enums.Genero;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una categoría dentro de un torneo (ej. Sub-18 Masculino).
 */
public class Categoria {
    private final UUID idCategoria;
    private String nombreCategoria;
    private Integer restriccionEdadMin; // Opcional
    private Integer restriccionEdadMax; // Opcional
    private Genero restriccionGenero;   // Opcional

    public Categoria(String nombreCategoria) {
        this.idCategoria = UUID.randomUUID();
        this.nombreCategoria = Objects.requireNonNull(nombreCategoria, "El nombre de la categoría no puede ser nulo.");
    }

    // Getters y Setters
    public UUID getIdCategoria() {
        return idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getRestriccionEdadMin() {
        return restriccionEdadMin;
    }

    public void setRestriccionEdadMin(Integer restriccionEdadMin) {
        this.restriccionEdadMin = restriccionEdadMin;
    }

    public Integer getRestriccionEdadMax() {
        return restriccionEdadMax;
    }

    public void setRestriccionEdadMax(Integer restriccionEdadMax) {
        this.restriccionEdadMax = restriccionEdadMax;
    }

    public Genero getRestriccionGenero() {
        return restriccionGenero;
    }

    public void setRestriccionGenero(Genero restriccionGenero) {
        this.restriccionGenero = restriccionGenero;
    }

    /**
     * Verifica si un jugador cumple con los criterios de esta categoría.
     * @param jugador El jugador a verificar.
     * @return true si el jugador es elegible, false en caso contrario.
     */
    public boolean esJugadorElegible(Jugador jugador) {
        if (jugador == null) return false;

        // Verificar género
        if (this.restriccionGenero != null && this.restriccionGenero != Genero.MIXTO) {
            if (jugador.getGenero() != this.restriccionGenero) {
                return false;
            }
        }

        // Verificar edad (simplificado, se necesitaría calcular la edad del jugador en la fecha del torneo)
        if (jugador.getFechaNacimiento() != null) {
            // Lógica de cálculo de edad... (omitida por brevedad en el esqueleto)
            // Ejemplo conceptual:
            // int edad = calcularEdad(jugador.getFechaNacimiento(), torneo.getFechaInicio());
            // if (this.restriccionEdadMin != null && edad < this.restriccionEdadMin) return false;
            // if (this.restriccionEdadMax != null && edad > this.restriccionEdadMax) return false;
        }
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria.equals(categoria.idCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                '}';
    }
}