package com.uco.pilae.pilae.entity;


import javax.persistence.*;

@Entity
@Table(name = "tabla_posiciones_tbl")
public class PosicionEntity {
    @Id
    @Column(name = "id_posiciones")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "partidos_jugados")
    private int partidosJugados;
    @Column(name = "partidos_ganados")
    private int partidosGanados;
    @Column(name = "partidos_perdidos")
    private int partidosPerdidos;
    @Column(name = "partidos_empatados")
    private int partidosEmpatados;
    @Column(name = "goles_favor")
    private int golesFavor;
    @Column(name = "goles_contra")
    private int golesContra;
    @Column(name = "goles_diferencia")
    private int golesDiferencia;
    @Column(name = "puntos")
    private int puntos;

    @JoinColumn(name = "id_equipo", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private EquipoEntity fkEquipo;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private TorneoEntity fkTorneo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getGolesDiferencia() {
        return golesDiferencia;
    }

    public void setGolesDiferencia(int golesDiferencia) {
        this.golesDiferencia = golesDiferencia;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public EquipoEntity getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(EquipoEntity fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    public TorneoEntity getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoEntity fkTorneo) {
        this.fkTorneo = fkTorneo;
    }
}
