package com.ozgury.veterinerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @Field(index = Index.YES, analyze = Analyze.NO)
    private String name;

    private String species;

    private String genus;

    private String age;

    private String description;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;

    @Override
    public String toString() {
        return "" +
                "İsim = " + name + '\'' +
                ", Tür = " + species + '\'' +
                ", Cins = " + genus + '\'' +
                ", Cins = " + age + '\'' +
                ", Cins = " + id + '\'';
    }
}

