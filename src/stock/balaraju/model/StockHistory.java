package stock.balaraju.model;


import java.util.Date;

public class StockHistory {
    private String id;
    private String symbol;
    private Date date;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private double volume;

    public String getId() {
        return symbol+date.getTime();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowLow) {
        this.lowPrice = lowLow;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volum) {
        this.volume = volum;
    }
}
