/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilari;

/**
* @file ListNode.java
* @description Liste dugumleri tutmak icin olusturdugum ListNode objesi
* @assignment 1.Odev
* @date 09.04.2020
* @author Halil Ibrahim Kaya halilibrahim.kaya@stu.fsm.edu.tr  <172 122 1017>
*/
public class ListNode {
    public char harf;
    public int adet=1;
    public ListNode nextListNode;
    
    
    public ListNode(char harf){
        this.harf=harf;
    }
}
