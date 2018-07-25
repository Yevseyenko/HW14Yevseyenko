package com.epam.model;

public class Parameters {
    public boolean hadFavouriteNumber;
    public double connectionPrice;
    public String tariffication;

    public double getConnectionPrice() {
        return connectionPrice;
    }

    public String getTariffication() {
        return tariffication;
    }

    public void setConnectionPrice(double connectionPrice) {
        this.connectionPrice = connectionPrice;
    }

    public void setTariffication(String tariffication) {
        this.tariffication = tariffication;
    }

    public void setHadFavouriteNumber(boolean hadFavouriteNumber) {
        this.hadFavouriteNumber = hadFavouriteNumber;
    }

    public boolean isFavouriteNumber() {
        return hadFavouriteNumber;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "hadFavouriteNumber=" + hadFavouriteNumber +
                ", getconnectionPrice=" + connectionPrice +
                ", tariffication='" + tariffication + '\'' +
                '}';
    }
}