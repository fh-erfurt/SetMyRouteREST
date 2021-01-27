package de.SetMyRoute.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class DatabaseEntity
{
    @ApiModelProperty(notes = "The database generated ID.")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
