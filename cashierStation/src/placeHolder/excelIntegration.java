package placeHolder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class excelIntegration {//oh boy, the stuff I don't really know how I figured out
    // still don't know understand throwing exceptions, but it tells me to put it, and it worksl
    public static void main(String[] args) throws IOException{
        //for testing purposes, don't worry about it
        System.out.println("Beginning test");

        load();
        Item.allInv();
        System.out.println("Test 1");

        new Item("aaa","bbb",1.99,"ccc",1);

        update();
        Item.allInv();
        System.out.println("Test 2");

        load();
        Item.allInv();
        System.out.println("Test 3");
    }

    public static void load() throws IOException {//this went weirdly well
        //initializations, not too much to say here
        FileReader productsCSV = new FileReader("C:\\users\\theeo\\IdeaProjects\\cashierStation\\ProductsCSV.csv");
        BufferedReader csvReader = new BufferedReader(productsCSV);
        String brand = null;
        String product = null;
        Double price = null;
        String barcodeID = null;
        int stockAmount = 0;

        //okay, so this is something I don't fully understand, but here goes
        String s = csvReader.readLine();//reads a line from the file
        while (s != null) {//while it isn't null
            String[] data = s.split(",");//it separates by commas into an array
            barcodeID = data[0];//sets the arrays into values
            brand = data[1];
            product = data[2];
            price = Double.valueOf(data[3]);
            stockAmount = Integer.parseInt(data[4]);
            new Item(brand, product, price, barcodeID, stockAmount);//and creates a new item
            s = csvReader.readLine();//reads next line I assume? not too sure
        }
        //csvReader.close(); this gave trouble, so killed this line
    }
    public static void update() throws IOException{//this was hell. It took over a week to get writing working
        FileWriter fileWriter = new FileWriter("C:\\Users\\theeo\\IdeaProjects\\cashierStation\\ProductsCSV.csv");
        for(int j=1;j<499;j++) {//so goes through every row in the array
            for (int i = 0; i < 5; i++) {//for each column in the row
                if(Item.all[i][j]!=null) {//check if it has data
                    fileWriter.append(Item.all[i][j]);//updates the data with the list of all items
                    fileWriter.append(",");//adds comma, so the file still works
                }
            }
            if(Item.all[0][j]==null)break;//if it reaches a null row, it breaks
            fileWriter.append("\n");//otherwise it separates the next row, and repeats the process
        }
        fileWriter.flush();//I think this updates the csv file, it leads to some problems

        //fileWrite.close();
        /*This ^ was supposed to be necessary, and worked the first time, then I change something unrelated,
        and it all goes to hell and this breaks the program...
        I spent over a week, commenting and uncommenting that one line, to different results.
        I don't understand what it does, because it seems file writers supposedly self close,
        but yet some java dictionary told me it was necessary
         */


    }



}





