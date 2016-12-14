/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thoughtworkschallenge;

import java.io.*;

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
            
            String line = null;
            
            while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
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
    
}
