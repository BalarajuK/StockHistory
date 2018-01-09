package stock.balaraju.persistence;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import stock.balaraju.model.Company;
import stock.balaraju.model.StockHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockDataProvider {

    public static List<Company> getCompanies(SessionFactory sessionFactory, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            return getCompaniesByHCQL(name, session, Company.class);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
           // session.getTransaction().commit();
            session.close();
        }
        return Collections.emptyList();
    }

    public static List<StockHistory> getStockHistory(SessionFactory sessionFactory, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            return getCompaniesByHCQL(name, session, StockHistory.class);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // session.getTransaction().commit();
            session.close();
        }
        return Collections.emptyList();
    }

    private static <T> List<T> getCompaniesByHCQL(String name, Session session, Class<T> objectType) {
        Criteria c = session.createCriteria(objectType);
        c.add(Restrictions.like("symbol", name));
        List list = c.list();
        List<T> employees = new ArrayList<>();
        for (Object company : list) {
            employees.add((T)company);
        }
        return employees;
    }
}
