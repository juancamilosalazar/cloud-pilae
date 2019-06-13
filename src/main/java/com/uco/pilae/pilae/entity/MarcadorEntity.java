package com.uco.pilae.pilae.entity;


import javax.persistence.*;

@Table(name = "marcador_tbl")
@Entity
public class MarcadorEntity {

    @Id
    @Column(name = "id_marcador",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "equipo_ganador")
    private String equipoGanador;
    @Column(name = "marcador_equipo_local")
    private int equipoLocalMrc;
    @Column(name = "marcador_equipo_visitante")
    private int equipoVisitanteMrc;

    @JoinColumn(name = "id_partido", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private PartidoEntity fkPartido;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(String equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public int getEquipoLocalMrc() {
        return equipoLocalMrc;
    }

    public void setEquipoLocalMrc(int equipoLocalMrc) {
        this.equipoLocalMrc = equipoLocalMrc;
    }

    public int getEquipoVisitanteMrc() {
        return equipoVisitanteMrc;
    }

    public void setEquipoVisitanteMrc(int equipoVisitanteMrc) {
        this.equipoVisitanteMrc = equipoVisitanteMrc;
    }

    public PartidoEntity getFkPartido() {
        return fkPartido;
    }

    public void setFkPartido(PartidoEntity fkPartido) {
        this.fkPartido = fkPartido;
    }
}
