/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thoughtworkschallenge;

import java.io.*;
import java.util.Arrays;

public class SalesTaxCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        // name of the input file
        String fileName =  System.getProperty("user.dir") + "/src/thoughtworkschallenge/sample1.txt";       
        
        System.out.println("calculating sales tax for " + fileName + "...");
        
        try {
            System.out.println("reading info in " + fileName + "...");
            
            FileReader fileReader = 
            new FileReader(fileName);
            
            BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
            
            String currentLine = null;                        
            
            while((currentLine = bufferedReader.readLine()) != null) {
//                System.out.println(currentLine);
                parseLine(currentLine);
                
            }
            
        }    

        catch (FileNotFoundException ex) {
            System.out.println("problem opening " + fileName);
            ex.printStackTrace();
        }        
        catch(IOException ex) {
            System.out.println("problem reading " + fileName);
        }
    }
    
    public static void parseLine(String line) {
        // parses the line for both the item, and the price of that item
        String[] words = line.split(" at ");
        String[] item = words[0].split(" ", 2);
        
        // information we need to determine sales tax
        Float numberBought = Float.parseFloat(item[0]);
        String itemBought = item[1];
        Float price = Float.parseFloat(words[words.length - 1]);
        
        Triplet<Float, String, Float> parsedItems = Triplet.with(
                numberBought, itemBought, price)
     
        
        System.out.println("number of items: " + numberBought);
        System.out.println("item bought: " + itemBought);
        System.out.println("price: " + price);
        System.out.println("-----------------");
        
    }
    
    public static void determineTax() {
        
    }        
    
}

public class parsedItems {
    private Float numberBought;
    private String itemBought;
    private Float price;
    
    
}