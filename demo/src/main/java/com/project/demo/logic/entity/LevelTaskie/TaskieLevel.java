package com.project.demo.logic.entity.LevelTaskie;

import com.project.demo.logic.entity.cosmetic.Cosmetic;
import jakarta.persistence.*;

@Table(name = "taskieLvl")
@Entity
public class TaskieLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "cosmetic_id", referencedColumnName = "id")
    private Cosmetic cosmetic;


    private boolean hasEvolution;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelEnum name;
    public TaskieLevel() {
    }

    public LevelEnum getName() {
        return name;
    }

    public void setName(LevelEnum name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    public boolean isHasEvolution() {
        return hasEvolution;
    }

    public void setHasEvolution(boolean hasEvolution) {
        this.hasEvolution = hasEvolution;
    }
}
