package stock.balaraju.services;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


@Path("/loadstock")
public class LoadStockHistory {
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Loading data... : " + msg;
        return Response.status(200).entity(output).build();
    }

//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition fileDetail) {
//        String fileLocation = "e://" + fileDetail.getFileName();
//        //saving file
//        try {
//            FileOutputStream out = new FileOutputStream(new File(fileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            out = new FileOutputStream(new File(fileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {e.printStackTrace();}
//        String output = "File successfully uploaded to : " + fileLocation;
//        return Response.status(200).entity(output).build();
//    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        StringBuilder html = new StringBuilder();
        html.append("<html> <title>Select stock history data file </title>");
        html.append("<body>");
        html.append(HtmlHelper.getFormComponent( "Select Company Symbol ", "file", "search/symbol"));
        html.append("</body>");
        html.append("/html");


        return html.toString();
    }



}
