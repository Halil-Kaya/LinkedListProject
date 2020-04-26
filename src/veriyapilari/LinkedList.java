/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilari;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* @file LinkedList.java
* @description Linkedlistin kendisi
* @assignment 1.Odev
* @date 09.04.2020
* @author Halil Ibrahim Kaya halilibrahim.kaya@stu.fsm.edu.tr  <172 122 1017>
*/

public class LinkedList {

    private AnaNode head;

    public boolean sistemCalissin;

    public LinkedList(File file) {//gonderilen doysa isleme sokuluyor

        //onca ana dugum olusturuluyor eger bir sikinti cikmazsa true donuyor
        sistemCalissin = anaDugumuOlustur(file);//eger olusturmada bir problem yasarsa false donuyor

        if (sistemCalissin) {//eger ana dugum olustuysa liste dugum olusturuluyor

            listDugumuOlustur(file);

        } else {
            System.out.println("liste olusturulmadı dosya yolu hatali");
        }

    }

    public boolean anaDugumuOlustur(File file) {

        FileInputStream fis = null;//dosya acmak icin
        try {
            fis = new FileInputStream(file);//dosya aciliyor
            int deger;
            char harf;
            while ((deger = fis.read()) != -1) {//harf harf okunuyor
                if (deger >= 65 && deger <= 90) {//eger harf buyukse kucuk harfe donusturup ekliyor
                    deger = deger + 32;
                    harf = (char) deger;
                    anaDugumeEkle(harf);
                } else if (deger >= 97 && deger <= 122) {//harf kucukse ekliyor
                    harf = (char) deger;
                    anaDugumeEkle(harf);
                }
                //onun disindaki bosluk nokta virgul isaretlerini eklemiyor
            }
            //hata olmadan biterse islem basarili demektir
            return true;

            //bir hata olursa
        } catch (FileNotFoundException e) {
            System.out.println("dosya bulunamadi hicbir islem yapilamiyor");
        } catch (IOException e) {
            System.out.println("I/O hatasi oldu");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("dosya kapatlirken bir I/O hatasi oldu");
            } catch (Exception e) {
                System.out.println("hata");
            }
        }

        //hatadan dolayi buraya gelmise islem basarisiz demek
        return false;

    }

    public void listDugumuOlustur(File file) {

        FileInputStream fis = null;//dosya islemi

        try {

            int deger;
            char harf;

            AnaNode tmpAnaNode = head;//ilk ana dugumum

            while (tmpAnaNode != null) {//null olmadikca don

                //dosyayi aciyorum(her islemde tekrar aciyorum imlec tekrar basa gelsin diye)
                fis = new FileInputStream(file);

                //
                boolean oncekiHarf = false;

                //dosyayi okuyorum
                while ((deger = fis.read()) != -1) {

                    //gelen deger harf mi diye kontrol ediyorum
                    if (harfMi(deger)) {

                        //eger buyuk harf ise kucuk harfe donusturuyorum
                        if (deger >= 65 && deger <= 90) {
                            deger = deger + 32;
                        }
                        harf = (char) deger;

                        //eger harf ana dugumdeki harfe uyusuyorsa
                        if (harf == tmpAnaNode.harf) {
                            oncekiHarf = true;//oncekiHarf degiskinimi true yapiyorum
                        } else if (oncekiHarf) {//eger onceki harfim true ise listeye ekliyorum
                            listDugumeEkle(tmpAnaNode.harf, harf);
                            //sonraki islemde onceki harfim degisecegi icin false esitliyorum
                            oncekiHarf = false;
                        }
                    } else {//eger gelen deger harf degilse oncekiHarfi false yapiyorum
                        //bunu yapmamin sebebi or: <ali evde> onceki harfim i ama
                        //araya bosluk girmis oncekiHarf i false yapmazsam i dugumune e'yi ekleyecek
                        //bunu engellemek icin harf degilse oncekiHarf'i false a esitliyorum
                        oncekiHarf = false;
                    }
                }
                //ana dugumumun sonraki elemanina geciyorum
                tmpAnaNode = tmpAnaNode.nextAnaNode;
            }
            //islem bittiginde olusan listemi ekrana basiyorum
            print();

            //hata olursa girecek yerler
        } catch (FileNotFoundException e) {
            System.out.println("dosya bulunamadi hicbir islem yapilamiyor");
        } catch (IOException e) {
            System.out.println("I/O hatasi oldu");
        } finally {
            try {
                fis.close();//dosyayi kapatiyorum
            } catch (IOException e) {
                System.out.println("dosya kapatlirken bir I/O hatasi oldu");
            } catch (Exception e) {
                System.out.println("hata");
            }
        }

    }

    public void anaDugumeEkle(AnaNode newAnaNode) {
        if (anaNodeisEmpty()) {//eger ici bossa dogrudan esitliyorum
            newAnaNode.nextAnaNode = head;
            head = newAnaNode;
        } else {

            AnaNode tmp = head;

            while (tmp.nextAnaNode != null) {

                //eklenilecek olan harf zaten listede varsa buraya girecek
                if (tmp.harf == newAnaNode.harf) {
                    return;//var olan harfi diziye tekrardan eklemiyor ve fonksiyonu bitiriyor
                } else {
                    tmp = tmp.nextAnaNode;
                }
            }

            tmp.nextAnaNode = newAnaNode;

        }
    }

    public void anaDugumeEkle(char harf) {
        anaDugumeEkle(new AnaNode(harf));
    }

    public void listDugumeEkle(AnaNode anaNode, ListNode newListNode) {
        if (anaNode.listNode == null) {//gelen ana dugumun ici null sa dogrudan ekliyorum
            newListNode.nextListNode = null;
            anaNode.listNode = newListNode;
        } else {

            ListNode tmp = anaNode.listNode;

            //eger dizimin ilk elemani null degil ama sonraki elemani null ise buraya giriyor
            if (tmp != null && tmp.nextListNode == null) {
                //ekleyecegim harf zaten listede varsa onun adetini +1 arttiriyor
                if (tmp.harf == newListNode.harf) {
                    tmp.adet++;
                    return;//ve islemi bitiriyor
                }
            }

            //anadugumun listesini geziyorum
            while (tmp.nextListNode != null) {
                if (tmp.harf == newListNode.harf) {//eger eslesen varsa adetini arttiriyorum
                    tmp.adet++;
                    return;//ve islemi bitiriyorum
                } else {//yoksa aramaya devam
                    tmp = tmp.nextListNode;
                }
            }

            if (tmp.harf == newListNode.harf) {//en sona geldiginde tekrar bir kontrol yapiyorum
                tmp.adet++;//varsa adetini +1 arttirip
                return;//islemi bitiriyorum
            }

            tmp.nextListNode = newListNode;//hala islemden cikmadiysa yeni harfi ekliyorum

        }
    }
    
    public void listDugumeEkle(char anaDugum, char harf) {

        AnaNode tmp = head;
        // aranan ana dugumu bulmak icin ana dugumun hepsini donuyorum
        while (tmp != null) {
            //aradigim ana dugumu bulursam buraya giriyor
            if (tmp.harf == anaDugum) {
                //bulunan ana dugumu fonksiyona gonderiyor
                listDugumeEkle(tmp, new ListNode(harf));
                return;//ve islemi bitiriyorum

            } else {
                tmp = tmp.nextAnaNode;
            }
        }

    }

    public void ardisikKarakterler(char k) {

        AnaNode tmpAnaNode = head;
        //ana dugumu geziyorum
        while (tmpAnaNode != null) {
            //aranan harfi ariyorum
            if (tmpAnaNode.harf == k) {

                //guzel cikti vermesi icin
                System.out.print(tmpAnaNode.harf + " -> ");

                //simdide listeyi donmek icin degisken olusturuyorum
                ListNode tmpListNode = tmpAnaNode.listNode;

                //listeyi donup ekrana basiyorum
                while (tmpListNode != null) {

                    System.out.print(tmpListNode.harf + " ");
                    tmpListNode = tmpListNode.nextListNode;

                }
                System.out.println("");
                return;//aranani bastim bu yuzden islemi bitirebilirim.
            }

            //aranani bulana kadar donuyorum
            tmpAnaNode = tmpAnaNode.nextAnaNode;

        }
        System.out.println("");

    }

    public void enCokArdisik() {

        AnaNode tmpAnaNode = head;

        /*Kiyaslamak icin Kiyaslanan diye bir obje olusturdum
        bunun amaci diger degerlerle kendisni kiyaslamak*/
        //ana dugumunun liste dugumunundeki degerlerini ve ana dugumunun harfini esitliyorum
        Kiyaslanan.anaHarf = tmpAnaNode.harf;
        Kiyaslanan.listeHarf = tmpAnaNode.listNode.harf;
        Kiyaslanan.adet = tmpAnaNode.listNode.adet;

        while (tmpAnaNode != null) {//ana dugumu donuyorum

            ListNode tmpListNode = tmpAnaNode.listNode;

            while (tmpListNode != null) {//o anki ana dugumunun liste dugumunu donuyorum

                if (tmpListNode.adet > Kiyaslanan.adet) {//eger listenin adeti kiyaslanandan buyukse onun degerlerini aliyorum
                    Kiyaslanan.anaHarf = tmpAnaNode.harf;
                    Kiyaslanan.listeHarf = tmpListNode.harf;
                    Kiyaslanan.adet = tmpListNode.adet;
                }

                tmpListNode = tmpListNode.nextListNode;//liste dugumun sonraki elemanina geciyorum

            }

            tmpAnaNode = tmpAnaNode.nextAnaNode;//ana dugumunun sonraki elemanina geciyorum

        }//en buyuk elemani ekrana basiyorum
        System.out.println(Kiyaslanan.anaHarf + "" + Kiyaslanan.listeHarf + "(" + Kiyaslanan.adet + ")");

    }

    public void enCokArdisik(char k) {

        AnaNode tmpAnaNode = head;

        while (tmpAnaNode != null) {//ana dugumu geziyorum

            if (tmpAnaNode.harf == k) {//eger ana dugumun harfi buysa donguye giriyor

                ListNode tmpListNode = tmpAnaNode.listNode;//liste dugumu gezmek icin

                /*Kiyaslamak icin Kiyaslanan diye bir obje olusturdum
                unun amaci diger degerlerle kendisni kiyaslamak*/
                //ana dugumunun liste dugumunundeki degerlerini ve ana dugumunun harfini esitliyorum
                Kiyaslanan.anaHarf = tmpAnaNode.harf;
                Kiyaslanan.adet = tmpListNode.adet;
                Kiyaslanan.listeHarf = tmpListNode.harf;

                while (tmpListNode != null) {//liste dugumu donuyorum

                    if (tmpListNode.adet > Kiyaslanan.adet) {//eger listenin adeti kiyaslanandan buyukse onun degerlerini aliyorum
                        Kiyaslanan.anaHarf = tmpAnaNode.harf;
                        Kiyaslanan.listeHarf = tmpListNode.harf;
                        Kiyaslanan.adet = tmpListNode.adet;
                    }

                    tmpListNode = tmpListNode.nextListNode;//sonraki liste dugumunun elamanina geciyorum

                }
                //ekrana basiyorum
                System.out.println(Kiyaslanan.anaHarf + "" + Kiyaslanan.listeHarf + "(" + Kiyaslanan.adet + ")");
                return;//sonraki islemlere gerek olmadigi icin fonksiyonu bitiriyorum
            }

            tmpAnaNode = tmpAnaNode.nextAnaNode;

        }

    }

    public void enAzArdisik() {

        AnaNode tmpAnaNode = head;

        /*Kiyaslamak icin Kiyaslanan diye bir obje olusturdum
                unun amaci diger degerlerle kendisni kiyaslamak*/
        //ana dugumunun liste dugumunundeki degerlerini ve ana dugumunun harfini esitliyorum
        Kiyaslanan.anaHarf = tmpAnaNode.harf;
        Kiyaslanan.listeHarf = tmpAnaNode.listNode.harf;
        Kiyaslanan.adet = tmpAnaNode.listNode.adet;

        while (tmpAnaNode != null) {//ana dugumun elemanliarini geziyorum

            ListNode tmpListNode = tmpAnaNode.listNode;

            while (tmpListNode != null) {//ana dugumun liste dugumunu geziyorum

                if (tmpListNode.adet < Kiyaslanan.adet) {//eger listenin adeti kiyaslanandan kucukse onun degerlerini aliyorum
                    Kiyaslanan.anaHarf = tmpAnaNode.harf;
                    Kiyaslanan.listeHarf = tmpListNode.harf;
                    Kiyaslanan.adet = tmpListNode.adet;
                }

                tmpListNode = tmpListNode.nextListNode;//listenin sonraki dugumune geciyorum

            }

            tmpAnaNode = tmpAnaNode.nextAnaNode;//ana dugumunun sonraki elemanina geciyorum

        }//ekrana bastiriyorum
        System.out.println(Kiyaslanan.anaHarf + "" + Kiyaslanan.listeHarf + "(" + Kiyaslanan.adet + ")");

    }

    public void enAzArdisik(char k) {

        AnaNode tmpAnaNode = head;

        while (tmpAnaNode != null) {//ana dugumu geziyorum

            if (tmpAnaNode.harf == k) {//ana dugumun harfi buysa donguye giriyor

                ListNode tmpListNode = tmpAnaNode.listNode;

                /*Kiyaslamak icin Kiyaslanan diye bir obje olusturdum
                unun amaci diger degerlerle kendisni kiyaslamak*/
                //ana dugumunun liste dugumunundeki degerlerini ve ana dugumunun harfini esitliyorum
                Kiyaslanan.anaHarf = tmpAnaNode.harf;
                Kiyaslanan.adet = tmpListNode.adet;
                Kiyaslanan.listeHarf = tmpListNode.harf;

                while (tmpListNode != null) {//liste dugumu geziyorum

                    if (tmpListNode.adet < Kiyaslanan.adet) {//eger listenin adeti kiyaslanandan kucukse onun degerlerini aliyorum
                        Kiyaslanan.anaHarf = tmpAnaNode.harf;
                        Kiyaslanan.listeHarf = tmpListNode.harf;
                        Kiyaslanan.adet = tmpListNode.adet;
                    }

                    tmpListNode = tmpListNode.nextListNode;//listenin sonraki elemanina gidiyorum

                }
                //ekrana basiyorum
                System.out.println(Kiyaslanan.anaHarf + "" + Kiyaslanan.listeHarf + "(" + Kiyaslanan.adet + ")");
                return;//sonraki islemler gereksiz oldugu icin fonksiyonu bitiriyorum
            }

            tmpAnaNode = tmpAnaNode.nextAnaNode;//sonraki ana dugume geciyorum

        }

    }

    public boolean harfMi(int asciKodu) {
        //harf olup olmadigini kontrol ediyorum
        if ((asciKodu >= 65 && asciKodu <= 90) || (asciKodu >= 97 && asciKodu <= 122)) {
            return true;
        } else {
            return false;
        }

    }

    public void print() {

        if (anaNodeisEmpty()) {
            System.out.println("Liste boş");
        } else {
            AnaNode tmpAnaNode = head;
            while (tmpAnaNode != null) {//ana dugumleri dolasiyorum
                System.out.print(tmpAnaNode.harf + "(");//ana dugumun harfini basiyorum simdi sira liste dugumunde

                ListNode tmpListNode = tmpAnaNode.listNode;

                while (tmpListNode != null) {//liste dugumunu dolasiyorum harfini ve adetini bastiriyorum
                    System.out.print(tmpListNode.harf + "," + tmpListNode.adet + " ->");

                    tmpListNode = tmpListNode.nextListNode;
                }
                System.out.print("null) -> ");

                tmpAnaNode = tmpAnaNode.nextAnaNode;
            }
            System.out.println(" null");
        }

    }
    
    
    
    
    
    //ana dugumun bos olup olmadigini kontrol ediyorum
    public boolean anaNodeisEmpty() {
        return head == null;
    }

}
