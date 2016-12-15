/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thoughtworkschallenge;

import java.io.*;
import java.math.BigDecimal;

public class SalesTaxCalculator {
    
    public static void main(String[] args) {        
        // name of the input file
        String fileName =  System.getProperty("user.dir") + "/src/thoughtworkschallenge/sample1.txt";       
                
        try {            
            FileReader fileReader = 
            new FileReader(fileName);
            
            BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
                                                                     
            BigDecimal totalTax = new BigDecimal(0.00);
            String currentLine = null;                        
                                                            
            while((currentLine = bufferedReader.readLine()) != null) {
                // get the important information from each line item in the input
                ParsedItems parsedItems = parseLine(currentLine);
                
                // calculate the tax, and round it
                // if not imported ...
                
                
                BigDecimal moneySpent = parsedItems.price.multiply(BigDecimal.valueOf(parsedItems.numberBought));
                moneySpent = moneySpent.setScale(2, BigDecimal.ROUND_HALF_UP);
            
                System.out.println("money spent: " + moneySpent + " on " + parsedItems.itemBought);
                
                BigDecimal rate = new BigDecimal(0.10);
                
                BigDecimal salesTax;
                                
                salesTax = determineTax(moneySpent, rate);
                totalTax = totalTax.add(salesTax);
                
                System.out.println("total tax: " + totalTax);
            }
            
        }    

        catch (FileNotFoundException ex) {
            System.out.println("problem opening " + fileName);
//            ex.printStackTrace();
        }        
        catch(IOException ex) {
            System.out.println("problem reading " + fileName);
        }
    }
    
    public static ParsedItems parseLine(String line) {
        // parses the line for both the item, and the price of that item
        String[] words = line.split(" at ");
        String[] item = words[0].split(" ", 2);
        
        // information we need to determine sales tax
        Float numberBought = Float.parseFloat(item[0]);
        String itemBought = item[1];
        BigDecimal price = new BigDecimal(Float.parseFloat(words[words.length - 1]));
        
        ParsedItems parsedItems = new ParsedItems(numberBought, itemBought, price);
                               
        return parsedItems;
    }
    
    public static BigDecimal determineTax(BigDecimal price, BigDecimal rate) {
        BigDecimal taxPaid = price.multiply(rate);
        taxPaid = taxPaid.setScale(2, BigDecimal.ROUND_DOWN);

        System.out.println("tax: " + taxPaid);
        
        return taxPaid;
    }           
}