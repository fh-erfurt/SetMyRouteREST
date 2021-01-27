package de.SetMyRoute.model;

/*
 *  Created on 22.01.2021 @ 17:50 by Adrian Petzold -
 */

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.awt.*;

/*
    This class represents a color with the addition of giving it a name / label. So human can read that color.
 */
@Entity
public class RouteColor extends DatabaseEntity
{

    // Color in RGB Format
    @NotNull
    private int color;

    // Human readable name of the color
    @NotNull
    private String name;

    public RouteColor()
    {

    }

    public RouteColor(Color color, String name)
    {
        this.color = color.getRGB();
        this.name = name;
    }

    public RouteColor(int color, String name)
    {
        this.color = color;
        this.name = name;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
