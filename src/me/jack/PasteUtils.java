package me.jack;

import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class PasteUtils {

    private static String URL = "https://hastebin.com/";

    public static String post(String data) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URL + "documents");

        try {
            post.setEntity(new StringEntity(data));

            HttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            return URL + new JsonParser().parse(result).getAsJsonObject().get("key").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Could not paste...!";
    }

}