package by.benikov.coinmarketcap.api.entity;

import lombok.Data;

@Data
public class Coin {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private Boolean isMineable;
    private double price;
    private String platform;

    public Coin() {
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", isMineable=" + isMineable +
                ", price=" + price +
                ", platform='" + platform + '\'' + "\n";
    }
}
