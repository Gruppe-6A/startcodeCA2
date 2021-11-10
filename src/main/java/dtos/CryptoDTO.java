package dtos;

public class CryptoDTO {
    private String urlB;
    private String valueB;
    private String urlE;
    private String valueE;
    private String urlD;
    private String valueD;
    private String urlL;
    private String valueL;
    private String urlR;
    private String valueR;
    public CryptoDTO(BitcoinDTO bDTO, EthereumDTO eDTO, DogeDTO dDTO, LitecoinDTO lDTO, RippleDTO rDTO)
    {
        urlB = bDTO.getUrl();
        valueB = bDTO.getprice();
        urlE = eDTO.getUrl();
        valueE = eDTO.getValue();
        urlD = dDTO.getUrl();
        valueD = dDTO.getValue();
        urlL = lDTO.getUrl();
        valueL = lDTO.getValue();
        urlR = rDTO.getUrl();
        valueR = rDTO.getValue();
    }

    public String getUrlB() {
        return urlB;
    }

    public void setUrlB(String urlB) {
        this.urlB = urlB;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

    public String getUrlE() {
        return urlE;
    }

    public void setUrlE(String urlE) {
        this.urlE = urlE;
    }

    public String getValueE() {
        return valueE;
    }

    public void setValueE(String valueE) {
        this.valueE = valueE;
    }

    public String getUrlD() {
        return urlD;
    }

    public void setUrlD(String urlD) {
        this.urlD = urlD;
    }

    public String getValueD() {
        return valueD;
    }

    public void setValueD(String valueD) {
        this.valueD = valueD;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public String getValueL() {
        return valueL;
    }

    public void setValueL(String valueL) {
        this.valueL = valueL;
    }

    public String getUrlR() {
        return urlR;
    }

    public void setUrlR(String urlR) {
        this.urlR = urlR;
    }

    public String getValueR() {
        return valueR;
    }

    public void setValueR(String valueR) {
        this.valueR = valueR;
    }
}
