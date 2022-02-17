package com.jpa.study.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Setter
@Getter
@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;
}
