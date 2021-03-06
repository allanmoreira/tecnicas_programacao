package negocio;

public class ProductCode {
    private String prodCode;
    private DiscountCode discountCode;
    private String description;

    public ProductCode(String prodCode, DiscountCode discountCode, String description) {
        this.prodCode = prodCode;
        this.discountCode = discountCode;
        this.description = description;
    }
        
    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
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
    
    public String toString(){
        return getDiscountCode().getDiscountCode()  + " - " + 
                    getProdCode() + " - " + 
                    getDescription() + " - " + 
                    getDiscountCode().getRate();
    }
    
}