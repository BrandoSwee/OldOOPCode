/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Brandon
 */
public class SerializationTestMain {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program reads MyMap objects from
//      a binary file.
//  
// INPUTS:  A binary file with MyMap objects.
// 
// OUTPUTS:  The program displays values in the
//      MyMap objects.
// 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public static void main (String[] agrs){
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("MyMap.bin"));
            MyMap<Integer,String> map;
            MyMap<Integer,String> map2;
            map = (MyMap<Integer,String>)o.readObject();
            map2 = (MyMap<Integer,String>)o.readObject();
            System.out.println("Map one keys and values");
            for (Integer i : map.getKeys())
			System.out.println(i + " " + map.getData(i));
            System.out.println("Map two keys and values");
            for (Integer i : map2.getKeys())
			System.out.println(i + " " + map2.getData(i));
            } catch (FileNotFoundException e) {
                System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
