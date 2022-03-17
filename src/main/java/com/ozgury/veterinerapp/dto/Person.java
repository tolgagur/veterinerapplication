package com.ozgury.veterinerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long personId;

    @Field(index = Index.YES, analyze = Analyze.NO)
    private String nameSurname;

    private String contactinformation;

    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals = new ArrayList<>();

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", nameSurname='" + nameSurname + '\'' +
                ", contactinformation='" + contactinformation + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", animals=" + animals +
                '}';
    }
}
