package stock.balaraju.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import stock.balaraju.model.Company;
import stock.balaraju.model.StockHistory;
import stock.balaraju.persistence.StockInfoPersist;

import java.io.*;
import java.util.function.Consumer;

public class TestPersister {

    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        StockInfoPersist persist = new StockInfoPersist();
        //persist.updateEmployee(new Company(), sessionFactory, "PIH,\"1347 Property Insurance Holdings, Inc.\",42903835.2,Finance,Property-Casualty Insurers");

       // persist.updateStockHistory(new StockHistory(), sessionFactory, "2016-01-05 00:00:00,WLTW,123.43,125.839996,122.309998,126.25,2163600.0");

//        try {
//            loadDataFromFile(new File("D:\\temp\\DE\\companyInfo.csv"), data->persist.updateCompany(new Company(), sessionFactory, data));
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }

        try {
            loadDataFromFile(new File("D:\\temp\\DE\\prices763fefc.csv"), data->persist.updateStockHistory(new StockHistory(), sessionFactory, data));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void loadDataFromFile(File file, Consumer<String> handleData) throws IOException {

        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                handleData.accept(line);
                System.out.println(line);
            }
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }


    }
}
