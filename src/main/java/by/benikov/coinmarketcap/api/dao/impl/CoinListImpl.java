package by.benikov.coinmarketcap.api.dao.impl;

import by.benikov.coinmarketcap.api.dao.DAOFactory;
import by.benikov.coinmarketcap.api.dao.CoinListInterface;
import by.benikov.coinmarketcap.api.entity.Coin;
import by.benikov.coinmarketcap.api.service.impl.ParseImpl;
import jakarta.ws.rs.DefaultValue;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class CoinListImpl implements CoinListInterface {
    private static final String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private static final List<NameValuePair> parameters = new ArrayList<>();

    @Override
    public List<Coin> getAllCoin() {
        parameters.clear();
        parameters.add(new BasicNameValuePair("convert", "USD"));
        return ParseImpl.parseResponse((DAOFactory.makeAPICall(uri, parameters)));
    }

    @Override
    @DefaultValue("all")
    public List<Coin> getAllByType(String cryptocurrencyType) {  // default: "all"/"coins"/"tokens"
        parameters.clear();
        parameters.add(new BasicNameValuePair("cryptocurrency_type", cryptocurrencyType));
        return ParseImpl.parseResponse((DAOFactory.makeAPICall(uri, parameters)));
    }

    @Override
    @DefaultValue("all")
    public List<Coin> getAllByTag(String tag) {  // default: "all" /"defi" "filesharing"
        parameters.clear();
        parameters.add(new BasicNameValuePair("tag", tag));
        return ParseImpl.parseResponse((DAOFactory.makeAPICall(uri, parameters)));
    }

    @Override
    public List<Coin> getCoinMetadata(int id) {
        String uri = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/info";
        List<Coin> coinList = new ArrayList<>();
        Coin coin = null;

        for(Coin coins: getAllCoin()){
            if(coins.getId() == id){
                coin = coins;
            }
        }
        coinList.add(coin);

        parameters.clear();
        assert coin != null;
        parameters.add((new BasicNameValuePair("id",coin.getId().toString())));

        Coin.Metadata coinMetadata;
        coinList.add(coinMetadata = ParseImpl.parseMetadata(DAOFactory.makeAPICall(uri,parameters),coin.getId().toString(),new Coin.Metadata()));

        return coinList;
    }

//    @Override
//    @DefaultValue("market_cap")
//    public List<Coin> getAllBySort(String sortType) {  // default: "market_cap" /"name" "symbol" "date_added" "market_cap" "market_cap_strict" "price" "circulating_supply" "total_supply" "max_supply" "num_market_pairs" "volume_24h" "percent_change_1h" "percent_change_24h" "percent_change_7d" "market_cap_by_total_supply_strict" "volume_7d" "volume_30d"
//        parameters.clear();
//        parameters.add(new BasicNameValuePair("sort",sortType));
//        return ParseImpl.parseResponse((DAOFactory.makeAPICall(uri,parameters)));
// }
}

