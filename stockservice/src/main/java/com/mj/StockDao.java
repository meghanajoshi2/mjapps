package com.mj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjoshi on 4/29/2016.
 */
public class StockDao implements IStockDao{
    private Connection connection;
    private static StockDao instance = null;
    private StockDao() {
    }

    public static IStockDao getInstance() {
        if(instance == null) {
            instance = new StockDao();
        }
        return  instance;
    }

    public void insertStock(Stock s) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String insertSql = "INSERT INTO stock (symbol, price, name) VALUES(?,?,?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, s.getSymbol());
            insertStmt.setBigDecimal(2, s.getPrice());
            insertStmt.setString(3, s.getFullName());
            insertStmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateStock(Stock s) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String updateSql = "UPDATE stock SET symbol = ?, price = ?, name = ? WHERE s.symbol = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, s.getSymbol());
            updateStmt.setBigDecimal(2, s.getPrice());
            updateStmt.setString(3, s.getFullName());
            updateStmt.setString(4, s.getSymbol());
            updateStmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void deleteStock(Stock s){
        try {
            Connection conn = ConnectionFactory.getConnection();
            String updateSql = "DELETE FROM stock WHERE s.symbol = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(updateSql);
            deleteStmt.setString(1, s.getSymbol());
            deleteStmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Stock> getStock(String symbol, String name) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String selectSql = "SELECT symbol, name, price FROM stock WHERE ";
            if(symbol == null && name == null) {
                throw new IllegalArgumentException("Insufficient search criteria");
            }
            if(symbol != null) {
                selectSql = selectSql + " symbol = ?";
                if(name != null) {
                    selectSql = selectSql + " AND name ilike '%" + name + "%'";
                }
            }else {
                selectSql = selectSql + " AND name ilike '%" + name + "%'";
            }

            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            if(symbol != null) {
                selectStmt.setString(1, symbol);
                if(name != null) {
                    selectStmt.setString(2, name);
                }
            } else {
                selectStmt.setString(1, name);
            }
            ResultSet res  = selectStmt.executeQuery();
            List<Stock> stocks = new ArrayList<Stock>();
            while(res.next()) {
                Stock s = new Stock();
                s.setSymbol(res.getString(1));
                s.setFullName(res.getString(2));
                s.setPrice(res.getBigDecimal(3));
                stocks.add(s);
            }
            return stocks;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Stock> getAllStocks() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String selectSql = "SELECT symbol, name, price FROM stock";
            PreparedStatement deleteStmt = conn.prepareStatement(selectSql);
            ResultSet res  = deleteStmt.executeQuery();
            List<Stock> stocks = new ArrayList<Stock>();
            while(res.next()) {
                Stock s = new Stock();
                s.setSymbol(res.getString(1));
                s.setFullName(res.getString(2));
                s.setPrice(res.getBigDecimal(3));
                stocks.add(s);
            }
            return stocks;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Stock getStock(String symbol) {
        List<Stock> stocks = this.getStock(symbol, null);
        if(stocks.size() > 0) return stocks.get(0);
        return null;
    }

    static class ConnectionFactory {
        static {
            try {
                Class.forName("org.postgresql.Driver");
            }catch(ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
        }
    }
}
