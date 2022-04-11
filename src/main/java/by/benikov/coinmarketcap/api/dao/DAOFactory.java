package by.benikov.coinmarketcap.api.dao;

import jakarta.ws.rs.DefaultValue;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class DAOFactory {
    private static String apiKey; // ToDo hide apiKey

    public static String makeAPICall(String uri, List<NameValuePair> parameters){
        String responseContent = "";

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

        System.out.println(query + "\n" + request);

        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            System.out.println("Error execute request \n" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseContent;
    }

    @DefaultValue("fdcbd548-fa0f-48fa-b766-2cd8d46fcdcd")
    public static void setApiKey(String apiKey){
        //ToDo validate api key
        DAOFactory.apiKey = apiKey;
    }
}
