/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
class editCustomer {
    
    ArrayList<Customer> customr = new ArrayList<>();
    File cusFile = new File("customer.txt");
    
    public void addCustomer(String userName ,String password){
        String contents;
        Customer cus = new Customer(userName, password, 0);
        customr.add(cus);
        
        try{
            FileWriter myWriter = new FileWriter(cusFile, true);  // This constructor allows me to append to the file so that the data in it doesn't get overwritten
            contents = cus.toString(); //by turning the book object b into a string I can easily write it into the file
            myWriter.write(contents);
            myWriter.close();
        }catch(IOException e) { 
            System.out.println("An error occurred.");
            e.printStackTrace();}  
    }
    
    
    public static void writeToFileCus(ArrayList<Customer> customers){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("customer.txt"));
            for(Customer x : customers){
                System.out.println(x);
                writer.write(x.toString());
            
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
        public void removeCustomer(List<Customer> list){
        customr.removeAll(list);
        writeToFileCus(customr);
    }
    
        public void saveChangesCus(){//Alex: added this to write changes to file after editing a book
        writeToFileCus(customr);
    }
    
    public void displayCustomers(){
        System.out.println(customr);
    }

    public ArrayList<Customer> getCustomers(){
        return customr;
    }
    
        public void loadCustomers(){ // This works by splitting the String written in the text file by the spaces between each value
        try {                        // Therefore putting spaces in a username or password will mess it up so we will make it an illegal character
            String tempstr;
            String[] arrOfStr ;
            Scanner scan = new Scanner(cusFile);
            while(scan.hasNextLine()){
                tempstr = scan.nextLine();
                arrOfStr = tempstr.split(" ", 6);
                String user;
                user = arrOfStr[1].substring(1, arrOfStr[1].length() - 1);
                String password;
                password = arrOfStr[3].substring(1, arrOfStr[3].length() - 1);
                int pts;
                pts = Integer.parseInt(arrOfStr[5].substring(1, arrOfStr[5].length() - 1));
                Customer fileCustomer = new Customer(user, password, pts);
                customr.add(fileCustomer);
            }
            
        } 
        catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
        }
    }
    
    public void updateCus(Customer cus, int pts){
        for (Customer customer : customr) {
            if(cus.getUsername().equals(customer.getUsername())){
                System.out.println("matched " + pts);
            customer.setPoints(pts);
            saveChangesCus();
            }  
        }
    }
    
    
}
