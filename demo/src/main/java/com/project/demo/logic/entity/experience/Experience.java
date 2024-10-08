package com.project.demo.logic.entity.experience;

import com.project.demo.logic.entity.rol.RoleEnum;
import jakarta.persistence.*;

@Table(name = "experience")
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ExperienceEnum name;

    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExperienceEnum getName() {
        return name;
    }

    public void setName(ExperienceEnum name) {
        this.name = name;
    }
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Experience() {
    }
}
