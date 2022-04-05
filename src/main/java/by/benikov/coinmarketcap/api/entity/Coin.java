package by.benikov.coinmarketcap.api.entity;

import lombok.Data;

@Data
public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private double price;
    private String platform;

    public Coin() {
    }

    public Coin setId(Long id) {
        this.id = id;
        return this;
    }

    public Coin setName(String name) {
        this.name = name;
        return this;
    }

    public Coin setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Coin setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Coin setPrice(double price) {
        this.price = price;
        return this;
    }

    public Coin setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", platform='" + platform + '\'';
    }
}
