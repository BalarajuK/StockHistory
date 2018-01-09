package stock.balaraju.services;


public class HtmlHelper {

    public static String getSearchButtons(String data) {
        return "\n<body>" +
                data +

                "<br/><br/><br/><br/>" +
                "<a href=\"symbolform\">Search Company Info</a>|\n" +
                "    <a href=\"stockform\">Search stock history</a>\n" +
                "</body>";
    }

    public static String getFormComponent(String text, String name, String action) {
        StringBuilder html = new StringBuilder();
        html.append("\n     <form action=\"").append(action).append("\" method=\"get\" >");
        html.append("\n         ").append(text).append(": <input type=\"text\" name=\"").append(name).append("\" />");
        html.append("<br/><br/>");
        html.append("\n         <input type=\"submit\" value=\"Search\" />");
        html.append("\n     </form>");
        return html.toString();

//        <form action="rest/product/add" method="post">
//                Enter Id:<input type="text" name="id"/><br/><br/>
//                Enter Name:<input type="text" name="name"/><br/><br/>
//                Enter Price:<input type="text" name="price"/><br/><br/>
//        <input type="submit" value="Add Product"/>
//        </form>
    }


}
