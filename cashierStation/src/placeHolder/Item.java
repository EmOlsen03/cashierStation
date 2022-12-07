package placeHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Item {//Item is separate because I set it up before the Excel stuff was set up
    String brand; String product; Double price; String barcodeID; int stockAmount;//variables

    public static String[][] all = new String[5][500];//don't go over 499 products please, thanks :)
    static int count;//number of items stocked
    public static  Scanner input = new Scanner(System.in);//I just really didn't want to make multiple scanners
    static BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));//see comment on line 12
    public Item(String brand, String product, Double price, String barcodeID, int stockAmount){//constructs
        all[0][0]="BarcodeID";//I wasn't sure where to set this, so now it goes first
        all[1][0]="Brand";//yes I know making this happen for every new Item is bad
        all[2][0]="Product";//I should've done something else
        all[3][0]="Price";//but it works
        all[4][0]="Stock Amount";

        this.brand = brand;//basic constructor stuff
        this.product = product;
        this.price = price;
        this.barcodeID = barcodeID;
        this.stockAmount = stockAmount;

        count++;//wow, it increases the amount of items
        all[0][count]=barcodeID;//updates array for new item
        all[1][count]=brand;
        all[2][count]=product;
        all[3][count]=price.toString();
        all[4][count]=String.valueOf(stockAmount);
    }

    public static void newItem() throws IOException {//fancy new item code
        System.out.print("Enter Barcode ID: ");
        String barcodeID = input.next();

        System.out.print("Enter Brand Name: ");
        String brand = input2.readLine();

        System.out.print("Enter Product Type: ");
        String product = input2.readLine(); ;

        System.out.print("Enter Price: $");
        Double price = input.nextDouble();

        System.out.print("Enter Amount in stock: ");
        int stockAmount = input.nextInt(); ;

        count++;// it doesn't even actually make it an item, it just adds to the array
        all[0][count]=barcodeID;//I know it's dumb
        all[1][count]=brand;//it works weirdly well
        all[2][count]=product;//mostly since new items have that redundant part about the array
        all[3][count]=price.toString();
        all[4][count]=String.valueOf(stockAmount);

        System.out.println("New Entry Acquired");
        lookup(barcodeID);//prints new item
    }

    public void info() {//prints info on product
        System.out.println(barcodeID);//this isn't used
        System.out.print(this.brand);//but I won't delete it
        System.out.print(" | " +this.product+" | ");
        System.out.println(this.price);
    }

    static public void allInv(){//prints out entire inventory
        int j = 0;//for the do while loop
        int i = 0;//for the while loop
        boolean empty = false;//checks if an array value is empty

        do{//for loops were messing it up for some reason
            while(i<5&&!empty){//goes through a row
                System.out.print(" | ");
                System.out.print(Item.all[i][j]);//prints entry
                i++;//moves on to next in row and repeats
            }
            j++;//next row
            i=0;//resets the column position
            System.out.println(" ");//separates from last row
            empty=all[i][j]==null;//checks if next row is empty
        }while(j<499&&!empty);
    }

    public static String lookup(String query){//looks up an item
        boolean exists = false;
        String barcode = " ";

        for(int i=1; i < 500;i++){//it really is unoptimized, looking back at it
            if(query.equals(all[0][i])){
                System.out.print(all[0][i]+" | ");
                System.out.print(all[1][i]+" | ");
                System.out.print(all[2][i]+" | ");
                System.out.print(all[3][i]+" | ");
                System.out.println(all[4][i]);
                exists = true;//if it exists, it exists
                barcode = all[0][i];
            }
        }
        if(exists){
            return barcode;
        }else {
            System.out.println("None with that barcode found");
            return "error";
        }

    }

    public static void changeStock(String item, int query){//I'm realizing how much of commandLine's stuff is here
        if(lookup(item).equals("error")){

        }else {
            int amount;

            System.out.println("Item is:");
            lookup(item);//looks up item

            System.out.println("Would you like to add or subtract stock? (Enter + or -)");
            char pluMin = input.next().charAt(0);//very simple code

            if (pluMin == '+') {//It's boring, and I wrote it a bit ago, shouldn't be too hard to figure out without comments...
                System.out.print("How many came in?: ");
                amount = input.nextInt();
                int nowAmount = Integer.parseInt(all[4][query]);
                nowAmount = nowAmount + amount;
                all[4][query] = String.valueOf(nowAmount);
            } else if (pluMin == '-') {
                System.out.print("How much would you like to subtract?: ");
                amount = input.nextInt();
                int nowAmount = Integer.parseInt(all[4][query]);
                nowAmount = nowAmount - amount;
                all[4][query] = String.valueOf(nowAmount);
            } else {
                System.out.println("Error 101");//oh right, I was going to make a list of all errors... Oh well
            }

            System.out.println("Updated Item:");
            lookup(item);//I really use that lookup function a lot, don't I?
        }

    }
    public static void updatePrice(String item, int query){
        double amount;//this is so reliant on other methods, there isn't much to explain

        System.out.println("Enter the new price: ");//so these lines require a double
        amount = input.nextDouble();//solely to be turned into a string
        all[3][query]= String.valueOf(amount);//i could've just made it accept a string, and cut a line

        System.out.println("Updated Item:");
        lookup(item);//I wrote this in multiple methods, should've made another method for it

    }
    public static int getArrayId(String query){//finds the location in the array, given a barcode
        boolean exists = false;
        int arrayId = 0;
        for(int i=1; i < 500;i++){
            if(query.equals(all[0][i])){
                exists = true;
                arrayId=i;
            }
        }
        return arrayId;

    }

    public static void main(String[] args) throws Exception {//for testing purposes
        Item bbqPringles = new Item("Pringles","Barbeque Chips",2.99,"101",4);
        lookup("101");
    }
}

