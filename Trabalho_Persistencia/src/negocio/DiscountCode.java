/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.math.BigDecimal;

/**
 *
 * @author 12111151
 */
public class DiscountCode {
    private String discountCode;
    private BigDecimal rate;

    public DiscountCode(String discountCode, BigDecimal rate) {
        this.discountCode = discountCode;
        this.rate = rate;
    }
    
    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    public String toString(){
        return getDiscountCode() + " - " +
                getRate();
    }
}
