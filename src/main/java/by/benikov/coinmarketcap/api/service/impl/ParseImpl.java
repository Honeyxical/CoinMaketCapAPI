package by.benikov.coinmarketcap.api.service.impl;

import by.benikov.coinmarketcap.api.entity.Coin;
import by.benikov.coinmarketcap.api.service.ParseInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ParseImpl implements ParseInterface {

    private static final String TAG_DATA_ROOT = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_SYMBOL = "symbol";
    private static final String TAG_SLUG = "slug";
    private static final String TAG_PLATFORM = "platform";
    private static final String TAG_QUOTE = "quote";
    private static final String TAG_USD = "usd";
    private static final String TAG_PRICE = "price";

    @Override
    public List<Coin> parseResponse(String response) {
        List<Coin> coinList = new ArrayList<>(); // ToDo maybe need to switch on linkedlist
        JSONParser parser = new JSONParser();
        JSONObject rootJSONObject = null;
        try {
            rootJSONObject = (JSONObject) parser.parse(response);
        } catch (ParseException e) {
            System.out.println("Error parse response \n" + e);
        }//ToDo add finally if exception catch app should not drop

        assert rootJSONObject != null;
        JSONArray jsonCoinArray = (JSONArray) rootJSONObject.get(TAG_DATA_ROOT);
        Coin coin = new Coin();
        for(Object jsonCoin: jsonCoinArray){
            createObject(coin, (JSONObject) jsonCoin);
            coinList.add(coin);
        }
        return coinList;
    }

    public static void createObject(Coin coin, JSONObject jsonCoin) {
        coin.setId((Long) jsonCoin.get(TAG_ID))
                .setName((String) jsonCoin.get(TAG_NAME))
                .setSymbol((String) jsonCoin.get(TAG_SYMBOL))
                .setSlug((String) jsonCoin.get(TAG_SLUG))
                .setPlatform((String) jsonCoin.get(TAG_PLATFORM));

        JSONObject quoteObject = (JSONObject) jsonCoin.get(TAG_QUOTE);
        JSONObject usdObject = (JSONObject) quoteObject.get(TAG_USD);
        coin.setPrice((Double) usdObject.get(TAG_PRICE));
    }
}
