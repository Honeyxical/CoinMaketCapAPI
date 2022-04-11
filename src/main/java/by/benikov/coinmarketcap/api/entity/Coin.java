package by.benikov.coinmarketcap.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private double price;
    private String tokenAddress;

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

    public String getTokenAddress() {
        return tokenAddress;
    }

    public Coin setTokenAddress() {
//        ListingLatestInterface ll = new ListingLatestImpl();
//        this.tokenAddress =
        return this;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", platform='";
    }

    @Data
    public static class Metadata extends Coin {
        private String website;
        private String technicalDoc;
        private String twitter;
        private String reddit;
        private String messageBoard;
        private String announcement;
        private String chat;
        private String explorer;
        private String sourceCode;
        private String logo;
        private String description;
        private String dateAdded;
        private String category;

        public Metadata() {
        }

        @Override
        public String toString() {
            return "website=" + website +
                    ", technicalDoc=" + technicalDoc +
                    ", twitter=" + twitter +
                    ", reddit=" + reddit +
                    ", messageBoard=" + messageBoard +
                    ", announcement=" + announcement +
                    ", chat=" + chat +
                    ", explorer=" + explorer +
                    ", sourceCode=" + sourceCode +
                    ", logo='" + logo + '\'' +
                    ", description='" + description + '\'' +
                    ", dateAdded='" + dateAdded + '\'' +
                    ", category='" + category + '\'';
        }
    }

}
