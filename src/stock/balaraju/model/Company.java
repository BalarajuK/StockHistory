package stock.balaraju.model;


public class Company {

    private int id;
    private String symbol;
    private String name;
    private double marketCapital;
    private String sector;
    private String Industry;

    public int getId() {
        return symbol == null ? 1: symbol.hashCode();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(double marketCapital) {
        this.marketCapital = marketCapital;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }
}
