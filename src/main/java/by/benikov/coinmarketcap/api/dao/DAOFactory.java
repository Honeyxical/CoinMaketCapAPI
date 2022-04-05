package by.benikov.coinmarketcap.api.dao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class DAOFactory {
    private static String apiKey; // ToDo hide apiKey

    public static String makeAPICall(String uri, List<NameValuePair> parameters){
        String response_content = "";

        URIBuilder query = null;
        try {
            query = new URIBuilder(uri);
        } catch (URISyntaxException e) {
            System.out.println("Error with URI link \n" + e);
        }
        assert query != null;
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = null;
        try {
            request = new HttpGet(query.build());
        } catch (URISyntaxException e) {
            System.out.println("Error with query \n" + e);
        }

        assert request != null;
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            System.out.println("Error execute request \n" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response_content;
    }

    public static void setApiKey(String api){
        //ToDo validate api key
        apiKey = api;
    }
}
