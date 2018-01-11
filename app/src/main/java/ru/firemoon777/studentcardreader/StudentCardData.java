package ru.firemoon777.studentcardreader;

/**
 * Created by Dragon on 06.01.2018.
 */

public class StudentCardData {
    // Sector 8
    String cardType;
    String validFrom;
    String validUntil;
    String updateTime;
    String passport;
    String station;
    Integer entrance;
    String metroTime;
    // Unknown
    String debug;

    // Sector 12
    Integer boardNumber;
    String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getEntrance() {
        return entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "StudentCardData{" +
                "cardType='" + cardType + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validUntil='" + validUntil + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", metroTime='" + metroTime + '\'' +
                ", debug='" + debug + '\'' +
                ", boardNumber=" + boardNumber +
                ", type=" + type +
                ", groundTime='" + groundTime + '\'' +
                '}';
    }
}
