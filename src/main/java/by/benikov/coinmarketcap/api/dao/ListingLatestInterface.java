package by.benikov.coinmarketcap.api.dao;

import by.benikov.coinmarketcap.api.entity.Coin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ListingLatestInterface {
    public List<Coin> getAllCoin() throws IOException, URISyntaxException;
    public List<Coin> getAllByType(String cryptocurrencyType); // default: "all"/"coins"/"tokens"
    public List<Coin> getAllByTag(String tag); // default: "all"/"defi" "filesharing"
    public Coin getCoinDescription(int id);
    //ToDo get contract adress for coin
   // Drop many error public List<Coin> getAllBySort(String sortType); // default: "market_cap" /"name" "symbol" "date_added" "market_cap" "market_cap_strict" "price" "circulating_supply" "total_supply" "max_supply" "num_market_pairs" "volume_24h" "percent_change_1h" "percent_change_24h" "percent_change_7d" "market_cap_by_total_supply_strict" "volume_7d" "volume_30d"
}
