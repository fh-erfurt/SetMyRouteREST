package de.SetMyRoute.model;

import de.SetMyRoute.model.difficulty.Difficulty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * this class represents a route you can find in any climbing hall
 */
@Entity
public class Route extends DatabaseEntity
{

    /**
     * name of the route
     */
    @NotNull
    @Size(min = 3, max = 128)
    private String name;

    /**
     * name of the segment
     */
    @NotNull
    @Size(max = 64)
    private String segment;

    /**
     * difficulty ranging from 1 to 10
     */
    @NotNull
    @OneToOne
    private Difficulty difficulty;

    /**
     * the setter (builder) of the route
     */
    @NotNull
    private String setter;

    /**
     * the time the setter has finished screwing the route / wall
     */
    @NotNull
    private LocalDateTime builtOn;

    /**
     * a List of {@link Hold}'s which represents the entire route
     */
    @NotNull
    @ManyToOne
    private Hold holdSet;

    /**
     * a List of {@link User} written {@link Review}'s
     */
    @OneToMany
    private List<Review> reviews;

    /**
     * Colors.. right?
     *
     * @see Color
     */
    @NotNull
    @OneToOne
    @JoinColumn(name = "color_id", nullable = false)
    private RouteColor color;

    public Route()
    {
    }

    public Route(String name, String segment, Difficulty difficulty, String setter, LocalDateTime builtOn, Hold holdSet,
                 List<Review> reviews, RouteColor color)
    {
        this.name = name;
        this.segment = segment;
        this.difficulty = difficulty;
        this.setter = setter;
        this.builtOn = builtOn;
        this.holdSet = holdSet;
        this.reviews = reviews;
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

    public String getSegment()
    {
        return segment;
    }

    public void setSegment(String segment)
    {
        this.segment = segment;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getSetter()
    {
        return setter;
    }

    public void setSetter(String setter)
    {
        this.setter = setter;
    }

    public LocalDateTime getBuiltOn()
    {
        return builtOn;
    }

    public void setBuiltOn(LocalDateTime builtOn)
    {
        this.builtOn = builtOn;
    }

    public Hold getHoldSet()
    {
        return holdSet;
    }

    public void setHolds(Hold holdSet)
    {
        this.holdSet = holdSet;
    }

    public List<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }

    public RouteColor getColor()
    {
        return color;
    }

    public void setColor(RouteColor color)
    {
        this.color = color;
    }
}