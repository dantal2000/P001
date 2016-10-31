package GUI001_f;

import GUI001_f.ErrorWindow001.CallError;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Connector_2 {
    private URL url;
    private String Cookie;

    Connector_2(String url, String cookie) throws MalformedURLException {
        this.url = new URL(url);
        Cookie = cookie;
    }

    String getResult() {
        String result = "";

        /*HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(String.valueOf(url));

        getRequest.addHeader("Cookie", Cookie);*/

        try {
            //HttpResponse response = client.execute(getRequest);
            HttpResponse response = getResponce();
            String charset = "UTF-8";

            if (response.containsHeader("Content-Type")) {
                String contentType = response.getFirstHeader("Content-Type").getValue();
                Pattern pattern = Pattern.compile("charset=(\\S*);?");
                java.util.regex.Matcher matcher = pattern.matcher(contentType);
                while (matcher.find()) charset = matcher.group().replaceAll("charset=", "").replaceAll(";", "");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset))) {
                for (String line = reader.readLine(); line != null; line = reader.readLine())
                    result += line + "\n";
            }
        } catch (IOException e) {
            //e.printStackTrace();
            CallError.Call(e.getMessage());
        }
        return result;
    }

    Map<String, String> getResponseParams() {
        Map<String, String> map = new HashMap<>();

        try {
            HttpResponse response = getResponce();
            for (Header header : response.getAllHeaders()) map.put(header.getName(), header.getValue());
        } catch (IOException e) {
            CallError.Call(e.getMessage());
        }

        return map;
    }

    private HttpResponse getResponce() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(String.valueOf(url));
        getRequest.addHeader("Cookie", Cookie);
        return client.execute(getRequest);
    }

    String recieveCookies() {
        String cookies = "";
        try {
            HttpResponse response = getResponce();
            if (response.containsHeader("Set-Cookie"))
                cookies = response.getFirstHeader("Set-Cookie").getValue();
        } catch (IOException e) {
            CallError.Call(e.getMessage());
        }
        return cookies;
    }
}
