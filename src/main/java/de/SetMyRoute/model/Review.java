package de.SetMyRoute.model;
import de.SetMyRoute.model.difficulty.Difficulty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * this class represents a feedback from a user.
 * It holds information about how the user has experienced the actual climb.
 */

@Entity
public class Review extends DatabaseEntity{


    /**
     * the author / submitter of the Review
     */
    @ManyToOne
    private User author;

    /**
     * the headline
     */
    @NotNull
    @Size(min=3, max = 128)
    private String headline;

    /**
     * the suggested difficulty measured by the user's personal opinion
     */
    // TODO clazz -3 to 15+
    @NotNull
    @OneToOne
    private Difficulty userDifficulty;

    /**
     * a flag to determinate if the user liked climbing the route
     */
    @NotNull
    @Column(name = "isLiked")
    private boolean like;

    /**
     * a flag to determinate if the user needed a strong arm in order to climb the route
     */
    @NotNull
    private boolean strongArm;

    /**
     * a flag to determinate if the user needed a long arm in order to climb the route
     */
    @NotNull
    private boolean longArm;

    /**
     * the message, may be a detailed text about what's good / what's bad
     */
    @NotNull
    @Size(max = 1024)
    private String message;

    public Review(){
    }

    public Review(User author, String headline, Difficulty userDifficulty, boolean like, boolean strongArm, boolean longArm,
                  String message) {
        this.author = author;
        this.headline = headline;
        this.userDifficulty = userDifficulty;
        this.like = like;
        this.strongArm = strongArm;
        this.longArm = longArm;
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public String getHeadline() {
        return headline;
    }

    public boolean isLike() {
        return like;
    }

    public boolean isStrongArm() {
        return strongArm;
    }

    public boolean isLongArm() {
        return longArm;
    }

    public String getMessage() {
        return message;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void setStrongArm(boolean strongArm) {
        this.strongArm = strongArm;
    }

    public void setLongArm(boolean longArm) {
        this.longArm = longArm;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Difficulty getUserDifficulty()
    {
        return userDifficulty;
    }

    public void setUserDifficulty(Difficulty userDifficulty)
    {
        this.userDifficulty = userDifficulty;
    }
}
