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
    Byte station;
    Byte stationAdditional;
    Integer entrance;
    String metroTime;
    // Unknown
    String debug;

    // Sector 12
    Integer boardNumber;
    String route;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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

    public Byte getStation() {
        return station;
    }

    public void setStation(Byte station) {
        this.station = station;
    }

    public Integer getEntrance() {
        return entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    public Byte getStationAdditional() {
        return stationAdditional;
    }

    public void setStationAdditional(Byte stationAdditional) {
        this.stationAdditional = stationAdditional;
    }

    @Override
    public String toString() {
        return "StudentCardData{" +
                "cardType='" + cardType + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validUntil='" + validUntil + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", passport='" + passport + '\'' +
                ", station=" + station +
                ", stationAdditional=" + stationAdditional +
                ", entrance=" + entrance +
                ", metroTime='" + metroTime + '\'' +
                ", debug='" + debug + '\'' +
                ", boardNumber=" + boardNumber +
                ", route='" + route + '\'' +
                ", groundTime='" + groundTime + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
