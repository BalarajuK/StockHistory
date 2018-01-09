package com.testjava.io;

import java.io.*;

/**
 * Created by bkadukun on 10/27/2017.
 */
public class TestFileOutputStream {

    public static void main(String[] args){

        //tranferData();

        //wireDatd();
        readData(); ;


    }

    private static void readData() {
        try(FileInputStream fo = new FileInputStream("D:\\temp\\text.txt"); DataInputStream do1 = new DataInputStream(fo)){
            System.out.println(do1.readDouble());
            System.out.println(do1.readDouble());
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }

    private static void wireDatd() {
        try(FileOutputStream fo = new FileOutputStream("D:\\temp\\text.txt"); DataOutputStream do1 = new DataOutputStream(fo)){
            do1.writeInt(16);
            do1.writeDouble(32);
            do1.flush();
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }

    private static void tranferData() {
        try(OutputStream os = new FileOutputStream("D:\\temp\\text.txt"); FileInputStream fin=new FileInputStream("D:\\temp\\input.txt"); ){

            int ch;
            while ((ch= fin.read())!=-1){
                System.out.print(ch);
                os.write(ch);
            }

        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }
}
