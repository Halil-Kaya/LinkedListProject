/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilari;

import java.io.File;

/**
* @file Test.java
* @description Testlerin yapildigi sinif
* @assignment 1.Odev
* @date 09.04.2020
* @author Halil Ibrahim Kaya halilibrahim.kaya@stu.fsm.edu.tr  <172 122 1017>
*/
public class Test {

    public static void main(String[] args) {

        
        LinkedList linkedList = new LinkedList(new File("veri.txt"));
        
        
        linkedList.ardisikKarakterler('v');
        
        linkedList.enCokArdisik();
        
        linkedList.enCokArdisik('l');
           
        linkedList.enAzArdisik();
        
        linkedList.enAzArdisik('i');
       
        
    }
    
    
    
    
    
    
}
