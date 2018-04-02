package utility;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.ConnectException;

public class ConnectionHandle {

    public static String getContentFromUrl(String url) {

        String responseContent = null;
        try {
            responseContent = Jsoup
                    .connect(url)
                    .method(Connection.Method.GET)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .execute()
                    .body();
            System.out.println("GET ");
        } catch (IOException ex) {
            System.out.println("Cannot get...");
        }
        return responseContent;
    }

    public static int postContentToUrl(String jsonStudent, String url) {

        int responseContent = 0;
        try {
           Connection.Response rs = Jsoup
                    .connect(url)
                    .method(Connection.Method.POST)
                    .requestBody(jsonStudent)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();

            responseContent = rs.statusCode();
            System.out.println(rs.statusCode());
            System.out.println("POST ");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot post...");
        }
        return responseContent;
    }

    public static String putUpdateContenttoUrl(String jsonStudent, String url) {

        String responseContent = null;
        try {
            responseContent = Jsoup
                    .connect(url)
                    .method(Connection.Method.PUT)
                    .requestBody(jsonStudent)
                    .ignoreContentType(true)
                    .execute()
                    .body();
            System.out.println("PUT");
        } catch (IOException ex) {
            System.out.println("Cannot put...");
        }
        return responseContent;
    }

    public static String deleteContenttoUrl(String jsonStudent, String url) {

        String responseContent = null;
        try {
            responseContent = Jsoup
                    .connect(url)
                    .method(Connection.Method.DELETE)
                    .ignoreContentType(true)
                    .execute()
                    .body();
            System.out.println("Delete");
        } catch (IOException ex) {
            System.out.println("Cannot delete...");
        }
        return responseContent;
    }
}
