package by.benikov.coinmarketcap.api;

import by.benikov.coinmarketcap.api.entity.Coin;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private static void sout(List<Coin> coinList) {
        for (Object ob : coinList) {
            System.out.println(ob.toString());
        }
        System.out.println("\t END \n");
    }
}
