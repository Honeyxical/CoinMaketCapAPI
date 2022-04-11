package by.benikov.coinmarketcap.api.service.impl;

import by.benikov.coinmarketcap.api.entity.Coin;
import jakarta.ws.rs.DefaultValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ParseImpl {

    private static final String TAG_DATA_ROOT = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_SYMBOL = "symbol";
    private static final String TAG_SLUG = "slug";
    private static final String TAG_PLATFORM = "platform";
    private static final String TAG_QUOTE = "quote";
    private static final String TAG_USD = "USD";
    private static final String TAG_PRICE = "price";

    public static List<Coin> parseResponse(String response) {
        List<Coin> coinList = new ArrayList<>(); // ToDo maybe need to switch on linkedlist
        JSONParser parser = new JSONParser();
        JSONObject rootJSONObject = null;
        try {
            rootJSONObject = (JSONObject) parser.parse(response);
        } catch (ParseException e) {
            System.out.println("Error parse response \n" + e);
        }//ToDo add finally if exception catch app should not drop

        assert rootJSONObject != null;

//        responseValidate(rootJSONObject);

        JSONArray jsonCoinArray = (JSONArray) rootJSONObject.get(TAG_DATA_ROOT);
        for(Object jsonCoin: jsonCoinArray){
            coinList.add(createObject((JSONObject) jsonCoin));
        }
        return coinList;
    }

    private static void responseValidate(JSONObject rootJSONObject){
        JSONArray dataValue = (JSONArray) rootJSONObject.get("data");
        if(dataValue == null){
            JSONObject errorStatus = (JSONObject) rootJSONObject.get("status");
            throw new RuntimeException("Error code: " + errorStatus.get("error_code") + "" +
                    "\n Error message: " + errorStatus.get("error_message"));
        }
    }

    //Parse metadata

    public static Coin.Metadata parseMetadata(String response, String id, Coin.Metadata metadata){
        JSONParser parser = new JSONParser();
        JSONObject rootJSONObject = null;
        try {
            rootJSONObject = (JSONObject) parser.parse(response);
        } catch (ParseException e) {
            System.out.println("Error parse response \n" + e);
        }//ToDo add finally if exception catch app should not drop

        assert rootJSONObject != null;

//        responseValidate(rootJSONObject);

        JSONObject dataObject = (JSONObject) rootJSONObject.get("data");
        JSONObject idObject = (JSONObject) dataObject.get(id); // ToDo Validate entry id
        JSONObject urlObject = (JSONObject) idObject.get("urls"); // get urls
        setUrl(urlObject, metadata);
        metadata.setLogo((String) dataObject.get("logo"));
        metadata.setDescription((String) dataObject.get("description"));
        metadata.setDateAdded((String) dataObject.get("date_added"));
        metadata.setCategory((String) dataObject.get("category"));
        return metadata;
    }

    private static void setUrl(JSONObject urlObject, Coin.Metadata metadata){
        JSONArray websiteArray = (JSONArray) urlObject.get("website");
        JSONArray technicalDoc = (JSONArray) urlObject.get("technical_doc");
        JSONArray twitter = (JSONArray) urlObject.get("twitter");
        JSONArray reddit = (JSONArray) urlObject.get("reddit");
        JSONArray messageBoard = (JSONArray) urlObject.get("message_board");
        JSONArray announcement = (JSONArray) urlObject.get("announcement");
        JSONArray chat = (JSONArray) urlObject.get("chat");
        JSONArray explorer = (JSONArray) urlObject.get("explorer");
        JSONArray sourceCode = (JSONArray) urlObject.get("source_code");


        metadata.setWebsite(String.valueOf(websiteArray.stream().findFirst()));
        metadata.setTechnicalDoc(String.valueOf(technicalDoc.stream().findFirst()));
        metadata.setTwitter(String.valueOf(twitter.stream().findFirst()));
        metadata.setReddit(String.valueOf(reddit.stream().findFirst()));
        metadata.setMessageBoard(String.valueOf(messageBoard.stream().findFirst()));
        metadata.setAnnouncement(String.valueOf(announcement.stream().findFirst()));
        metadata.setChat(String.valueOf(chat.stream().findFirst()));
        metadata.setExplorer(String.valueOf(explorer.stream().findFirst()));
        metadata.setSourceCode(String.valueOf(sourceCode.stream().findFirst()));
    }

    //Parse coin

    private static Coin createObject( JSONObject jsonCoin) {
        Coin coin = new Coin();
        coin.setId((Long) jsonCoin.get(TAG_ID))
                .setName((String) jsonCoin.get(TAG_NAME))
                .setSymbol((String) jsonCoin.get(TAG_SYMBOL))
                .setSlug((String) jsonCoin.get(TAG_SLUG));
//                .setPlatform((String) jsonCoin.get(TAG_PLATFORM)); // ToDo  display token on witch it is based

        JSONObject quoteObject = (JSONObject) jsonCoin.get(TAG_QUOTE);
        JSONObject usdObject = (JSONObject) quoteObject.get(TAG_USD);
        try{
            coin.setPrice((Double) usdObject.get(TAG_PRICE));
        }catch (ClassCastException e){
            System.out.println(usdObject.get(TAG_PRICE));
            System.out.println("ebaiy long : ");
        }
        return coin;
    }

}
