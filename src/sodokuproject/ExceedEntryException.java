/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

/**
 *
 * @author Nouran
 */
public class ExceedEntryException extends Exception {
   
    static int hintClicks=0;
    static int checkClicks=0;
    
    
    ExceedEntryException(String string) {
        super(string);
        
        
    }
    
}
