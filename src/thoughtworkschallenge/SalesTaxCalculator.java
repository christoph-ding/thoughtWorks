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
        String currentLine;
        
        // name of the input file
        fileName =  System.getProperty("user.dir") + "/src/thoughtworkschallenge/sample2.txt";       
                
        try {
            // this is the only value we need to calculate for the output           
            totalTax = new BigDecimal(0.00);
            
            // reading the lines in the input document
            fileReader = new FileReader(fileName);            
            bufferedReader = new BufferedReader(fileReader);                        
                                                            
            while((currentLine = bufferedReader.readLine()) != null) {
                // variable declarations
                ParsedItems parsedItems;
                BigDecimal applicableRate;
                BigDecimal moneySpent;
                BigDecimal rate;
                BigDecimal salesTax;                
                // exemptItems have a special tax rate
                // with more time, I would make the exemptItems be an argument to the program
                List<String> exemptItems = Arrays.asList("book", "headache pills", "chocolate");                
                                
                // get the important information from each line item in the input
                parsedItems = parseLine(currentLine);
                
                // determine the tax rate, based on what the item is                
                applicableRate = determineTaxRate(parsedItems.itemBought, exemptItems);
                
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
        // variable declarations
        String[] words;
        String[] item;
        Float numberBought;
        String itemBought;
        BigDecimal price;
        ParsedItems parsedItems;
        
        // parses the line for both the item, and the price of that item
        words = line.split(" at ");
        item = words[0].split(" ", 2);
        
        // information we need to determine sales tax
        numberBought = Float.parseFloat(item[0]);
        itemBought = item[1];
        price = new BigDecimal(Float.parseFloat(words[words.length - 1]));
        
        // that information is returned as an object
        parsedItems = new ParsedItems(numberBought, itemBought, price);                               
        return parsedItems;
    }
    
    public static BigDecimal determineTax(BigDecimal price, BigDecimal rate) {        
        BigDecimal taxPaid;
        
        taxPaid = price.multiply(rate);
        taxPaid = taxPaid.setScale(2, BigDecimal.ROUND_DOWN);
        
        return taxPaid;
    }
    
    public static BigDecimal determineTaxRate(String itemBought, List exemptItems) {
        BigDecimal applicableRate;        
      
        if (parsedItems.itemBought.contains("imported")) {
            applicableRate = 0.15;
        } else if (!parsedItems.itemBought.contains("imported")) {
            
            
        }                
        else { 
          // I am assuming, in all other cases, we will just use 10%
            applicableRate = 0.10;
        }
        
        return applicableRate;
        
    }
}