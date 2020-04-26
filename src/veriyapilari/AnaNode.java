/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilari;

/**
* @file AnaNode.java
* @description Ana dugumleri tutmak icin olusturulan AnaNode objesi
* @assignment 1.Odev
* @date 09.04.2020
* @author Halil Ibrahim Kaya halilibrahim.kaya@stu.fsm.edu.tr  <172 122 1017>
*/

public class AnaNode {
    public char harf;
    public AnaNode nextAnaNode;
    public ListNode listNode;
    
    public AnaNode(char harf){
        this.harf=harf;
    }
    
}

