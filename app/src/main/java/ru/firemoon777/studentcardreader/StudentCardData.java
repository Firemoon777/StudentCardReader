package ru.firemoon777.studentcardreader;

/**
 * Created by Dragon on 06.01.2018.
 */

public class StudentCardData {
    // Sector 8
    String validUntil;
    String validFrom;
    String passport;
    String metroTime;
    // Unknown
    String debug;

    // Sector 12
    Integer boardNumber;
    Integer type;
    String groundTime;

    // Sector 13
    String surname;

    // Sector 14
    String firstName;

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getMetroTime() {
        return metroTime;
    }

    public void setMetroTime(String metroTime) {
        this.metroTime = metroTime;
    }

    public Integer getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(Integer boardNumber) {
        this.boardNumber = boardNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(String groundTime) {
        this.groundTime = groundTime;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "StudentCardData{" +
                "validUntil='" + validUntil + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", passport='" + passport + '\'' +
                ", metroTime='" + metroTime + '\'' +
                ", boardNumber=" + boardNumber +
                ", type=" + type +
                ", groundTime='" + groundTime + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
