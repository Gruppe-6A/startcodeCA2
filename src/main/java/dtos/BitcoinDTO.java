package dtos;

public class BitcoinDTO {
    private String url;
    private String price;

    public BitcoinDTO(String url, String price) {
        this.url = url;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }
}
