package de.SetMyRoute.model.difficulty;

/**
 * Numbers can be signed and unsigned. But we needed to tell if a number is just a number or if it has any Signedness present for example {@link #NEGATIVE}
 */
public enum Signedness {

    NONE('\u0000'), POSITIVE('+'), NEGATIVE('-');

    /**
     * the suffix of the number
     */
    private final char suffix;

    Signedness(char suffix) {
        this.suffix = suffix;
    }

    public char getSuffix() {
        return suffix;
    }

    public static Signedness fromInt(int value){
        return values()[value];
    }
}
