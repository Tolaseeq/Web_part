package com.gpch.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Integer id;
    @Column(name = "film_name")
    @Length(min = 3, message = "*Your film name must have at least 3 characters")
    private String filmName;
    @Column(name = "film_genre")
    private String filmGenre;
    @Column(name = "film_description")
    @Length(min = 5, message = "*Your description must have at least 5 characters")
    private String filmDescription;
    @Column(name = "critic_rate")
    private String criticRate;
}