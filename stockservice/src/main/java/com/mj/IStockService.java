package com.mj;


import java.util.List;

/**
 * Created by mjoshi on 4/19/2016.
 */
public interface IStockService {
    public String getStock(String symbol);

    /**
     *
     * @param symbolList comma separated list of symbols
     * @return
     */
    public String getStocks(String symbolList);

    /**
     * json that maps to a Stock object
     * @param request
     */
    public void addStock(String request);
    /**
     * json that maps to a Stock object
     * @param request
     */
    public void updateStock(String request);
    public void deleteStock(String symbol);

}
