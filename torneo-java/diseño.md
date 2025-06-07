## Diseño Conceptual
* cuatro (4) paquetes | o proyectos
    * TournamentManagement
    * ParticipantManagement
    * MatchResultsManagement
    * ResourceLogisticsManagement
    * UserAccessManagement

* Clases Principales y sus Relaciones Clave (multiplicidades aproximadas):
    * TournamentManagement::Torneo (1) --tiene--> (0..*) ParticipantManagement::Inscripcion
    * TournamentManagement::Torneo (1) --tiene--> (1) TournamentManagement::FormatoTorneo
    * TournamentManagement::Torneo (1) --organizado_por--> (1) UserAccessManagement::Usuario (con rol Organizador)
    * TournamentManagement::Torneo (1) --contiene--> (0..*) MatchResultsManagement::Ronda / MatchResultsManagement::Grupo

    * ParticipantManagement::Inscripcion (1) --para--> (1) ParticipantManagement::Participante
    * ParticipantManagement::Participante <|-- ParticipantManagement::Jugador (Herencia)
    * ParticipantManagement::Participante <|-- ParticipantManagement::Equipo (Herencia)
    * ParticipantManagement::Equipo (1) --compuesto_por--> (2..*) ParticipantManagement::Jugador

    * MatchResultsManagement::Partido (1) --disputado_por--> (2) ParticipantManagement::Participante (o Inscripcion)
    * MatchResultsManagement::Partido (1) --tiene_un--> (1) MatchResultsManagement::Resultado
    * MatchResultsManagement::Partido (0..1) --oficiado_por--> (1) UserAccessManagement::Usuario (con rol Árbitro)
    * MatchResultsManagement::Partido (0..1) --jugado_en--> (1) ResourceLogisticsManagement::MesaJuego
    * MatchResultsManagement::Ronda (1) --contiene--> (1..*) MatchResultsManagement::Partido
    * MatchResultsManagement::Grupo (1) --contiene--> (1..*) MatchResultsManagement::Partido
    * MatchResultsManagement::Grupo (1) --tiene_clasificacion--> (1) MatchResultsManagement::TablaClasificacionGrupo
    * MatchResultsManagement::TablaClasificacionGrupo (1) --contiene--> (1..*) MatchResultsManagement::EntradaClasificacion
    * MatchResultsManagement::EntradaClasificacion (1) --para_participante--> (1) ParticipantManagement::Participante (o Inscripcion)
    
    * UserAccessManagement::Usuario (1) --tiene_rol--> (1) UserAccessManagement::RolUsuario

