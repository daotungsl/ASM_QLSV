package utilityImage;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PostImage {

    public String loadUploadUrl(){
        String uploadUrl = null;
        try {
            uploadUrl = Jsoup.connect("https://too-late-to-apologize.appspot.com/get-upload-url").method(Connection.Method.GET).ignoreContentType(true).execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }

    public String uploadImage(File uploadFile1, String uploadUrl){
        String charset = "UTF-8";
        try {
            MultipartUtility multipart = new MultipartUtility(uploadUrl, charset);

            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFilePart("myFile", uploadFile1);

            List<String> response = multipart.finish();

            System.out.println("SERVER REPLIED:");

            String imageUrl = "";
            for (String line : response) {
                System.out.println(line);
                if(line.length() > 0){
                    imageUrl = line;
                }
            }
            System.out.println(imageUrl);
//            displayImageFromUrl(imageUrl + "=s200");
            return imageUrl;
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
