package com.mj;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by mjoshi on 4/19/2016.
 */

/**
 * incoming/outgoing json parameters map to this class
 * @param request
 */

public class Stock {
   private BigDecimal price;
   private String symbol;
   private String fullName;

    public Stock() {
    }

    public void setPrice(BigDecimal inPrice) {
        price = inPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setSymbol(String inSymbol) {
        symbol = inSymbol;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setFullName(String inFullName) {
        fullName = inFullName;
    }
    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Name:" + getFullName());
        bldr.append("Price:" + getPrice());
        bldr.append("Symbol:" + getSymbol());
        return bldr.toString();
    }
}
