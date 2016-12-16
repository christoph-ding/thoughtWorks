package thoughtworkschallenge;

import java.math.BigDecimal;

public class ParsedItems {
    final int numberBought;
    final String itemBought;
    final BigDecimal price;

    public ParsedItems(int numberBought, String itemBought, BigDecimal price) {
        this.numberBought = numberBought;
        this.itemBought = itemBought;
        this.price = price;
    }    
}
