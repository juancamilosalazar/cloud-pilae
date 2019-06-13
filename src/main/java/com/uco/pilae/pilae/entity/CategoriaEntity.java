package com.uco.pilae.pilae.entity;



import javax.persistence.*;

@Entity
@Table(name = "categoria_tbl")
public class CategoriaEntity {
    @Id
    @Column(name = "id_categoria",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="nombre")
    private String name;
    @Column(name = "edad_min")
    private int edadMin;
    @Column(name = "edad_max")
    private int edadMax;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }
}
