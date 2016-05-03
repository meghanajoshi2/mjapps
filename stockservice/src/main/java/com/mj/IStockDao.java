package com.mj;

import java.util.List;

/**
 * Created by mjoshi on 4/29/2016.
 */
public interface IStockDao {
    void insertStock(Stock s);
    void updateStock(Stock s);
    void deleteStock(Stock s);

    /**
     * retrieve all stocks
     * @return
     */
    List<Stock> getStock(String symbol, String name);
    Stock getStock(String symbol);
    List<Stock> getAllStocks();

}
