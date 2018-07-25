package com.epam.comparator;

import com.epam.model.Tariff;

import java.util.Comparator;

public class SMScomparator implements Comparator<Tariff> {
    @Override
    public int compare(Tariff one, Tariff another) {
        double smsPriceFirst = one.getPriceSMS();
        double smsPriceAnother = another.getPriceSMS();
        if (smsPriceFirst > smsPriceAnother)
            return 1;
        if (smsPriceFirst == smsPriceAnother)
            return 0;
        else
            return 1;
    }
}
