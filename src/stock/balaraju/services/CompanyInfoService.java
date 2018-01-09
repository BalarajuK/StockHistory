package stock.balaraju.services;


import com.login.LoginDao;
import com.test.Employee;
import org.hibernate.cfg.Configuration;
import stock.balaraju.model.Company;
import stock.balaraju.model.StockHistory;
import stock.balaraju.persistence.StockDataProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/search")
public class CompanyInfoService {

    /**
     * @GET
     * @Path("/{param}")
     * @Produces(MediaType.TEXT_HTML) public String getCompanyInfo(@PathParam("param") String symbol) {
     * StringBuilder html = new StringBuilder();
     * html.append("<html> " +
     * "<title>Select stock history data file " +"</title>");
     * HtmlHelper.getFormComponent(html, "symbol", "search/symbol");
     * HtmlHelper.getBody(html, symbol);
     * html.append("</html>");
     * <p>
     * <p>
     * return html.toString();
     * }
     **/

    @GET
    @Path("/symbolform")
    @Produces(MediaType.TEXT_HTML)
    public String getCompanyInfo() {
        StringBuilder html = new StringBuilder();
        html.append("\n<html> " +
                "\n<title>Select symbol to search " + "</title>");
        html.append("<br/><br/>");
        html.append(HtmlHelper.getFormComponent("Select Company Symbol ", "symbol", "symbol"));
        html.append(HtmlHelper.getSearchButtons(""));
        html.append("\n</html>");
        return html.toString();
    }

    @GET
    @Path("/stockform")
    @Produces(MediaType.TEXT_HTML)
    public String getStockInfo() {
        StringBuilder html = new StringBuilder();
        html.append("\n<html> " +
                "\n<title>Select symbol to search " + "</title>");
        html.append("<br/><br/>");
        html.append(HtmlHelper.getFormComponent("Select Stock Symbol ", "symbol", "stock"));
        html.append(HtmlHelper.getSearchButtons(""));
        html.append("\n</html>");


        return html.toString();
    }

    @GET
    @Path("/stock")
    @Produces(MediaType.TEXT_HTML)
    public Response getStockHistory(@FormParam("symbol") String symbolName) {

        StringBuilder html = new StringBuilder();
        html.append("\n<html> " +
                "\n<title>Stock History of  " + symbolName + "</title>");
        html.append("<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "    text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>");
        html.append("\n<body>");

        StringBuilder companiesStr = new StringBuilder();
        List<StockHistory> stockHistories = StockDataProvider.getStockHistory(new Configuration().configure().buildSessionFactory(), symbolName);


        companiesStr.append("<br/>Stock History of  " + symbolName + "<br/>");
        companiesStr.append("\n<table style=\"width:100%\">\n");

        companiesStr.append("<tr>\n");
        companiesStr.append("<th>").append("Date").append("</th>\n");
        companiesStr.append("<th>").append("Open ").append("</th>\n");
        companiesStr.append("<th>").append("Close").append("</th>\n");
        companiesStr.append("<th>").append("High").append("</th>\n");
        companiesStr.append("<th>").append("Low").append("</th>\n");
        companiesStr.append("<th>").append("Volume").append("</th>\n");

        companiesStr.append("</tr>\n");

        for (StockHistory stockHistory : stockHistories) {
            companiesStr.append("<tr>\n");
            companiesStr.append("<th>").append(stockHistory.getDate()).append("</th>\n");
            companiesStr.append("<th>").append(stockHistory.getOpenPrice()).append("</th>\n");
            companiesStr.append("<th>").append(stockHistory.getClosePrice()).append("</th>\n");
            companiesStr.append("<th>").append(stockHistory.getHighPrice()).append("</th>\n");
            companiesStr.append("<th>").append(stockHistory.getLowPrice()).append("</th>\n");
            companiesStr.append("<th>").append(stockHistory.getVolume()).append("</th>\n");
            companiesStr.append("</tr>\n");
        }
        companiesStr.append("</table>");

        html.append(HtmlHelper.getSearchButtons(companiesStr.toString()));
        html.append("\n</body>");
        html.append("\n</html>");


        return Response.status(200).entity(html.toString()).build();
    }

    @GET
    @Path("/symbol")
    @Produces(MediaType.TEXT_HTML)
    public Response getMsg(@FormParam("symbol") String symbolName) {

        StringBuilder html = new StringBuilder();
        html.append("\n<html> " +
                "\n<title>Company Information " + "</title>");
        html.append("\n<body>");

        StringBuilder companiesStr = new StringBuilder();
        List<Company> companies = StockDataProvider.getCompanies(new Configuration().configure().buildSessionFactory(), symbolName);


        companiesStr.append("<br/>Company Information <br/>");
        for (Company company : companies) {
            companiesStr.append("<br/>");
            companiesStr.append("\n").append(company.getName() + "  " + company.getSymbol() + "  " + company.getSector() + "  " + company.getIndustry() + "  " + company.getMarketCapital());
        }

        html.append(HtmlHelper.getSearchButtons(companiesStr.toString()));
        html.append("\n</body>");
        html.append("\n</html>");


        return Response.status(200).entity(html.toString()).build();
    }


}
