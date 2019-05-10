package com.course.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "names")
public class names {
    @Id
    private int id;
    private String name;

}
