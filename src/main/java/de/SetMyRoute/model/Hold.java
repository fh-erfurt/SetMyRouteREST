package de.SetMyRoute.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Hold extends DatabaseEntity {


    /**
     * the manufacturer
     */
    @NotNull
    @Size(min = 3, max = 256)
    private String manufacturer;

    /**
     * the type
     */
    @NotNull
    @Size(min = 3, max = 128)
    private String type;


    public Hold() {
    }

    public Hold(String manufacturer, String type) {
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}