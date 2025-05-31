package com.torneotenismesa.gestiontorneos.modelos;

import java.util.List;
import java.util.UUID;

// --- Importaciones de clases de OTROS SUBSISTEMAS (conceptuales por ahora) ---
// import com.torneotenismesa.gestionparticipantes.modelos.ParticipanteInscrito;
// import com.torneotenismesa.gestionpartidos.modelos.Cuadro; // O similar para la estructura de partidos
// import com.torneotenismesa.gestionpartidos.modelos.FaseTorneo; // Abstracción de Ronda o Grupo

/**
 * Interfaz que define el contrato para los diferentes formatos de torneo.
 * Un formato determina cómo se estructura la competición (ej. Eliminatoria Directa, Round Robin).
 * Sus implementaciones concretas se encargarán de la lógica de generación de cuadros
 * y determinación de las siguientes fases.
 */
public interface FormatoTorneo {

    /**
     * Obtiene el identificador único del formato.
     * @return UUID del formato.
     */
    UUID getIdFormato();

    /**
     * Obtiene el nombre descriptivo del formato.
     * @return Nombre del formato (Ej: "Eliminatoria Directa").
     */
    String getNombreFormato();

    /**
     * Obtiene una descripción más detallada del formato.
     * @return Descripción del formato.
     */
    String getDescripcion();

    /**
     * Método conceptual para generar la estructura inicial de partidos (cuadro/grupos)
     * para un torneo y una lista de participantes.
     * La implementación real dependerá de las entidades de Partidos y Participantes.
     * @param torneo El torneo para el cual generar el cuadro.
     * @param participantesInscritos Lista de participantes inscritos.
     * @return Una representación de la estructura de partidos (Ej: Cuadro, List<Grupo>).
     *         (Tipo de retorno es conceptual y dependerá de las clases del subsistema de Partidos)
     */
    // Object generarCuadro(Torneo torneo, List<ParticipanteInscrito> participantesInscritos);
    // Por ejemplo, podría ser:
    // com.torneotenismesa.gestionpartidos.modelos.Cuadro generarCuadro(
    //      Torneo torneo,
    //      List<com.torneotenismesa.gestionparticipantes.modelos.ParticipanteInscrito> participantesInscritos
    // );


    /**
     * Método conceptual para determinar la siguiente fase o ronda del torneo.
     * La implementación real dependerá de las entidades de Partidos y Resultados.
     * @param torneo El torneo en curso.
     * @param faseActual La fase/ronda/grupo actual que ha concluido.
     * @return La siguiente fase del torneo.
     *         (Tipo de retorno es conceptual y dependerá de las clases del subsistema de Partidos)
     */
    // Object determinarSiguienteFase(Torneo torneo, Object faseActual);
    // Por ejemplo, podría ser:
    // com.torneotenismesa.gestionpartidos.modelos.FaseTorneo determinarSiguienteFase(
    //      Torneo torneo,
    //      com.torneotenismesa.gestionpartidos.modelos.FaseTorneo faseActual
    // );
}