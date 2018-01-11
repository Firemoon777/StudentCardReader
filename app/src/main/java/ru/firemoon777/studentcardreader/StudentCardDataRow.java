package ru.firemoon777.studentcardreader;

/**
 * Created by Dragon on 11.01.2018.
 */

public class StudentCardDataRow {
    String name;
    String releaseValue;
    String debugValue;
    String description;
    Boolean personal;

    public StudentCardDataRow(String name) {
        this.name = name;
        personal = false;
    }

    public StudentCardDataRow(String name, String releaseValue) {
        this(name);
        this.releaseValue = releaseValue;
    }

    public StudentCardDataRow(String name, String releaseValue, String debugValue) {
        this(name, releaseValue);
        this.debugValue = debugValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseValue() {
        return releaseValue;
    }

    public void setReleaseValue(String releaseValue) {
        this.releaseValue = releaseValue;
    }

    public String getDebugValue() {
        return debugValue;
    }

    public void setDebugValue(String debugValue) {
        this.debugValue = debugValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(Boolean personal) {
        this.personal = personal;
    }
}
