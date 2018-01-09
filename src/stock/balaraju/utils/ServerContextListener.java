package stock.balaraju.utils;


import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import stock.balaraju.model.Company;
import stock.balaraju.model.StockHistory;
import stock.balaraju.persistence.StockInfoPersist;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Consumer;

public class ServerContextListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent contextEvent) {        //loadData();

        //loadDataFromDisk();

    }

    private void loadDataFromDisk() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        StockInfoPersist persist = new StockInfoPersist();
        try {
            loadDataFromFile(new File("D:\\temp\\DE\\companyInfo.csv.csv"), data -> persist.updateCompany(new Company(), sessionFactory, data));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            loadDataFromFile(new File("D:\\temp\\DE\\stockPrices.csv"), data -> persist.updateStockHistory(new StockHistory(), sessionFactory, data));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadDataFromFile(File file, Consumer<String> handleData) throws IOException {

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

//    private boolean loadData() {
//        // Make sure that this directory exists
//        String dirName = "C:\\";
//
//        boolean downloaded = false;
//        try {
//
//            System.out.println("Downloading ...");
//            saveFileFromUrlWithJavaIO(dirName + "\\stockInfo.csv", "http://hck.re/CPKVPp");
//
//
//            downloaded = true;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        return downloaded;
//
//    }
//
//    // Using Java IO
//    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
//            throws IOException {
//        BufferedInputStream in = null;
//        FileOutputStream fout = null;
//        try {
//            in = new BufferedInputStream(new URL(fileUrl).openStream());
//            //fout = new FileOutputStream(fileName);
//
////            byte data[] = new byte[1024];
////            int count;
////            while ((count = in.read(data, 0, 1024)) != -1) {
////                fout.write(data, 0, count);
////            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } finally {
//            if (in != null)
//                in.close();
//            if (fout != null)
//                fout.close();
//        }
//    }

//    public static void saveFileFromUrlWithCommonsIO(String fileName,
//                                                    String fileUrl) throws MalformedURLException, IOException {
//        FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
//    }


    public void contextDestroyed(ServletContextEvent contextEvent) {

    }

}
