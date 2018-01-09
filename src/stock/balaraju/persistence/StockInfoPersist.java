package stock.balaraju.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import stock.balaraju.model.Company;
import stock.balaraju.model.StockHistory;

import java.util.Date;

public class StockInfoPersist {
    public void updateCompany(Company company, SessionFactory sessionFactory, String info) {

        Session session = null;
        try {
            if (info.startsWith("Symbol,Name,MarketCap,Sector,Industry")) {
                return;
            }
            String[] data = info.split(",");
            company.setSymbol(data[0]);
            int i = 1;
            if (data[i].startsWith("\"")) {
                String firstName = data[i].substring(1, data[i].length());
                i++;
                String secondName = data[i].substring(0, data[i++].length() - 1);
                company.setName(firstName + " " + secondName);
            } else {
                company.setName(data[i++]);
            }

            company.setMarketCapital(Double.valueOf(data[i++]));
            company.setSector(data[i++]);
            company.setIndustry(data[i++]);

            session = sessionFactory.openSession();
            session.beginTransaction();

            session.saveOrUpdate(company);

            session.getTransaction().commit();
            session.close();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(session !=null && session.isOpen()){
                session.close();
            }
        }

    }

    public void updateStockHistory(StockHistory stockHistory, SessionFactory sessionFactory, String stcokInfo) {

        //date,symbol,open,close,low,high,volume
        //2016-01-05 00:00:00,WLTW,123.43,125.839996,122.309998,126.25,2163600.0
        if(stcokInfo.startsWith("date,symbol,open,close,low,high,volume")){
            return;
        }
        Session session = null;

        try {
            String[] data = stcokInfo.split(",");
            Date date = convertToDate(data[0]);
            if (date == null) {
                return;
            }
            stockHistory.setDate(date);
            stockHistory.setSymbol(data[1]);
            stockHistory.setOpenPrice(Double.valueOf(data[2]));
            stockHistory.setClosePrice(Double.valueOf(data[2]));
            stockHistory.setLowPrice(Double.valueOf(data[2]));
            stockHistory.setHighPrice(Double.valueOf(data[2]));
            stockHistory.setVolume(Double.valueOf(data[2]));


            session = sessionFactory.openSession();
            session.beginTransaction();

            session.saveOrUpdate(stockHistory);

            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(session !=null && session.isOpen()){
                session.close();
            }
        }
    }

    private Date convertToDate(String dateStr) {
        try {
            String[] dateStr2 = dateStr.split(" ");
            String[] datePart = dateStr2[0].split("-");
            String[] timePart= null;
            if(dateStr2.length >= 2){
                timePart = dateStr2[1].split(":");
            }


            return new Date(Integer.valueOf(datePart[0])
                    , Integer.valueOf(datePart[1]),
                    Integer.valueOf(datePart[2]),
                    timePart == null ? 0:Integer.valueOf(timePart[0]),
                    timePart == null ? 0:Integer.valueOf(timePart[1]),
                    timePart == null ? 0:Integer.valueOf(timePart[2]));
        }
        catch (Exception ex){
            return null;
        }
    }
}
