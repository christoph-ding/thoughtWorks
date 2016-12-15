package thoughtworkschallenge;

import java.math.BigDecimal;

public class ParsedItems {
    final Float numberBought;
    final String itemBought;
    final BigDecimal price;

    public ParsedItems(Float numberBought, String itemBought, BigDecimal price) {
        this.numberBought = numberBought;
        this.itemBought = itemBought;
        this.price = price;
    }    
}
