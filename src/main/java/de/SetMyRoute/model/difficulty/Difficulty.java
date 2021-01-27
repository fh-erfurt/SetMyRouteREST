package de.SetMyRoute.model.difficulty;

import de.SetMyRoute.model.DatabaseEntity;

import javax.persistence.Entity;

/**
 * this class represents a Difficulty Level that can range from 3- to 15+
 * examples are: 3- 3 3+ , 13- 13 13+
 */
@Entity
public class Difficulty extends DatabaseEntity
{

    /**
     * the values of the difficulty
     */
    private byte value;

    /**
     * the {@link Signedness} of the value
     */
    private Signedness signedness;

    public Difficulty(){

    }

    public Difficulty(byte value, Signedness signedness) {
        this.value = value;
        this.signedness = signedness;
    }

    public byte getValue() {
        return value;
    }

    public Signedness getSignedness() {
        return signedness;
    }

    public void setSignedness(Signedness signedness) {
        this.signedness = signedness;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public String getDisplayableNumber() {
        return String.valueOf(value) + this.signedness.getSuffix();
    }

    public static Signedness fromInt(int value){
        return values()[value];
    }

}
