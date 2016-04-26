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
class DiscountCode {
    private char discountCode;
    private BigDecimal rate;

    public DiscountCode(char discountCode, BigDecimal rate) {
        this.discountCode = discountCode;
        this.rate = rate;
    }
    
    public char getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    
}
