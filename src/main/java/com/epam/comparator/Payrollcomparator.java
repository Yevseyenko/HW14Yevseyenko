package com.epam.comparator;

import com.epam.model.Tariff;

import java.util.Comparator;

public class Payrollcomparator implements Comparator<Tariff> {
    @Override
    public int compare(Tariff one, Tariff another) {
        double firstPayroll = one.getPayroll();
        double secondPayroll = another.getPayroll();
        if(firstPayroll>secondPayroll)
        return 1;
        if(firstPayroll==secondPayroll)
            return 0;
        else
            return 1;
    }
}
