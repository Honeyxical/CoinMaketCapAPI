package by.benikov.coinmarketcap.api.service;

import by.benikov.coinmarketcap.api.entity.Coin;

import java.util.List;

public interface ParseInterface {
    public List<Coin> parseResponse(String response);
}
