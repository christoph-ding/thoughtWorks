/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thoughtworkschallenge;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalesTaxCalculator {
    
    public static void main(String[] args) {
        // variable declarations
        String fileName;
        FileReader fileReader;
        BufferedReader bufferedReader;
        BigDecimal totalTax;
        BigDecimal totalSpent;
        String currentLine;
        String salesTaxMessage;
        String totalSpentMessage;
        
        // name of the input file
        fileName =  System.getProperty("user.dir") + "/src/thoughtworkschallenge/sample1.txt";       
                
        try {
            // this is the only value we need to calculate for the output           
            totalTax = new BigDecimal(0.00);
            totalSpent = new BigDecimal(0.00);
            
            // reading the lines in the input document
            fileReader = new FileReader(fileName);            
            bufferedReader = new BufferedReader(fileReader);                        
                                      
            // this prints out the correct line for each item bought
            while((currentLine = bufferedReader.readLine()) != null) {
                // variable declarations
                ParsedItems parsedItems;
                BigDecimal moneySpent;
                BigDecimal applicableRate;                
                BigDecimal salesTaxPaid;
                BigDecimal priceAfterTax;
                // exemptItems have a special tax rate                
                List<String> exemptItems = Arrays.asList("book", "headache pills", "chocolate");
                String lineItemMessage;
                                
                // get the important information from each line item in the input
                parsedItems = parseLine(currentLine);                                
                
                // this is price of items and number bought
                moneySpent = parsedItems.price.multiply(BigDecimal.valueOf(parsedItems.numberBought));
                moneySpent = moneySpent.setScale(2, BigDecimal.ROUND_UP);
 
                // determine the tax rate, based on what the item is                
                applicableRate = determineTaxRate(parsedItems.itemBought, exemptItems);                                
                salesTaxPaid = determineTaxPaid(moneySpent, applicableRate);
                priceAfterTax = parsedItems.price.add(salesTaxPaid);
                priceAfterTax = priceAfterTax.setScale(2, BigDecimal.ROUND_UP);
                                
                totalTax = totalTax.add(salesTaxPaid);                
                totalSpent = totalSpent.add(priceAfterTax);
                
                // output should be properly formatted;
                lineItemMessage = parsedItems.numberBought + " " + parsedItems.itemBought + " : " + priceAfterTax;
                System.out.println(lineItemMessage);
            }            
            
            // outputs the lines for total tax, and total money spent after line items
            salesTaxMessage = "Sales Taxes: " + totalTax;
            System.out.println(salesTaxMessage);
            totalSpentMessage = "Total: " + totalSpent;
            System.out.println(totalSpentMessage);
        }    

        catch (FileNotFoundException ex) {
            System.out.println("problem opening " + fileName);            
        }        
        catch(IOException ex) {
            System.out.println("problem reading " + fileName);
        }
    }
    
    public static ParsedItems parseLine(String line) {
        // variable declarations
        String[] words;
        String[] item;
        int numberBought;
        String itemBought;
        BigDecimal price;
        ParsedItems parsedItems;
        
        // parses the line for both the item, and the price of that item
        words = line.split(" at ");
        item = words[0].split(" ", 2);
        
        // information we need to determine sales tax
        numberBought = Integer.parseInt(item[0]);
        itemBought = item[1];
        price = new BigDecimal(Float.parseFloat(words[words.length - 1]));
                              
        // that information is returned as an object
        parsedItems = new ParsedItems(numberBought, itemBought, price);                               
        return parsedItems;
    }
    
    public static BigDecimal determineTaxRate(String itemBought, List exemptItems) {
        BigDecimal applicableRate;        
        
        // imported items override exemption rules
        if (itemBought.contains("imported")) {
            applicableRate = BigDecimal.valueOf(0.15);
        } else if (!itemBought.contains("imported")) {
            Boolean exemptionApplies = false;
            
            // determines if an exemption is found
            for (int i = 0; i < exemptItems.size(); i++) {
                String exemption;                
                exemption = (String) exemptItems.get(i);
                if (itemBought.contains(exemption)) {
                    exemptionApplies = true;
                    break;
                }                
            }
            
            if (exemptionApplies) {
                applicableRate = BigDecimal.valueOf(0.00);
            } else {
                applicableRate = BigDecimal.valueOf(0.10);    
            }                                    
        }                
        else { 
          // for edge cases
            applicableRate = BigDecimal.valueOf(0.10);
        }
        
        return applicableRate;        
    }
    
    public static BigDecimal determineTaxPaid(BigDecimal price, BigDecimal rate) {        
        BigDecimal taxPaid;
        
        taxPaid = price.multiply(rate);
        taxPaid = taxPaid.setScale(2, BigDecimal.ROUND_UP);
        
        return taxPaid;
    }
}