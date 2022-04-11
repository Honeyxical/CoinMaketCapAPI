package by.benikov.coinmarketcap.api;

import by.benikov.coinmarketcap.api.dao.CoinListInterface;
import by.benikov.coinmarketcap.api.dao.DAOFactory;
import by.benikov.coinmarketcap.api.dao.impl.CoinListImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        
        String[] a = new String[0];
        System.out.println(Arrays.stream(a).findFirst());
        
        DAOFactory.setApiKey("fdcbd548-fa0f-48fa-b766-2cd8d46fcdcd");
        CoinListInterface coinListInterface = new CoinListImpl();
        System.out.println(coinListInterface.getCoinMetadata(1));
    }
}
