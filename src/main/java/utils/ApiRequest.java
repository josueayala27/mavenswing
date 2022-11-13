package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author josueayala
 */
public class ApiRequest {
    public String GET(String URL) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL);
        request.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed request with error code" + response.getStatusLine().getStatusCode());
        }

        BufferedReader buffer = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String json = buffer.lines().collect(Collectors.joining());
        return json;
    }
}
