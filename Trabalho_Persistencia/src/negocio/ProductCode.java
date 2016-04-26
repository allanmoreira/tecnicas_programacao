package negocio;

public class ProductCode {
    private char prodCode;
    private DiscountCode discountCode;
    private String description;

    public ProductCode(char prodCode, DiscountCode discountCode, String description) {
        this.prodCode = prodCode;
        this.discountCode = discountCode;
        this.description = description;
    }
    
    public char getProdCode() {
        return prodCode;
    }

    public void setProdCode(char prodCode) {
        this.prodCode = prodCode;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}