package com.mj;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mjoshi on 4/19/2016.
 */

@Path("/stockservice")
public class StockService implements IStockService{
    private static ConcurrentHashMap<String, Stock> map = new ConcurrentHashMap<String, Stock>();

    static {
        Stock netflix = new Stock();
        netflix.setSymbol("NFLX");
        netflix.setFullName("Netflix");
        netflix.setPrice(123.45d);
        map.put("NFLX", netflix);

        Stock facebook = new Stock();
        facebook.setSymbol("FB");
        facebook.setFullName("Facebook");
        facebook.setPrice(99.01d);
        map.put("FB", facebook);

    }
    public StockService() {
    }

    @GET
    @Path("/stocks/{symbol}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStock(@PathParam("symbol") String symbol) {
        Stock stock = map.get(symbol);
        if(stock != null) {
            Gson g = new Gson();
            return g.toJson(stock);
        }else {
            return null;
        }
    }

    @GET
    @Path("/stocks/{symbolList}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStocks(@PathParam("symbolList") String symbolList) {
        StringTokenizer strTok = new StringTokenizer(symbolList,",");
        List<Stock> stocks = new ArrayList<Stock>();
        while(strTok.hasMoreTokens()) {
            String symbol = strTok.nextToken();
            for (String key : map.keySet()) {
                Stock s = map.get(key);
                if (s.getSymbol().equals(symbol)) {
                    stocks.add(s);
                }
            }
        }
        Gson g = new Gson();
        return g.toJson(stocks);
    }

    @POST
    @Path("/stocks")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStock(String request) {
        Gson g = new Gson();
        Stock newStock = g.fromJson(request, Stock.class);
        Stock s = map.get(newStock.getSymbol());
        if(s == null) {
            map.put(newStock.getSymbol(), newStock);
        }
    }

    @PUT
    @Path("/stocks/{symbol}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStock(String request) {
        Gson g = new Gson();
        Stock updateStock = g.fromJson(request, Stock.class);
        Stock s = map.get(updateStock.getSymbol());
        if(s != null) {
            if(updateStock.getPrice() != null) {
                s.setPrice(updateStock.getPrice());
            }
            if(updateStock.getFullName() != null) {
                s.setFullName(updateStock.getFullName());
            }
        }
    }

    @DELETE
    @Path("/stocks/{symbol}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void deleteStock(String symbol) {
        Stock s = map.get(symbol);
        map.remove(s.getSymbol());
    }
}
