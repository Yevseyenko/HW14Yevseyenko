package com.epam.model;

public class Callprices {
    public double insideCallPrice;
    public double outsideCallPrice;
    public double stationaryCallPrice;


    public double getInsideCallPrice() {
        return insideCallPrice;
    }

    public double getOutsideCallPrice() {
        return outsideCallPrice;
    }

    public double getStationaryCallPrice() {
        return stationaryCallPrice;
    }

    public void setInsideCallPrice(double insideCallPrice) {
        this.insideCallPrice = insideCallPrice;
    }

    public void setOutsideCallPrice(double outsideCallPrice) {
        this.outsideCallPrice = outsideCallPrice;
    }

    public void setStationaryCallPrice(double stationaryCallPrice) {
        this.stationaryCallPrice = stationaryCallPrice;
    }
    @Override
    public String toString() {
        return "CallPrices{" +
                "insideCallPrice=" + insideCallPrice +
                ", outsideCallPrice=" + outsideCallPrice +
                ", stationaryCallPrice=" + stationaryCallPrice +
                '}';
    }
}
