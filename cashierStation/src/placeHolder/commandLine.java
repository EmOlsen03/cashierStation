package placeHolder;
import java.io.IOException;
import static placeHolder.Item.*;

public class commandLine {//I couldn't figure out how to just make it as a command line, so I made a fake one, it was that or learn how to make a whole OS...
    public static void main(String[] args) throws IOException {
        boolean quit = false;//i knew this couldn't break it, so it goes first

        System.out.println("Welcome\nPlease open ReadMe.text to get started");

        excelIntegration.load();//this can break it, user will need to edit program to work

        System.out.println("Type help for help");

        do{
            System.out.print("Enter Command:");
            String command = Item.input.next();//I didn't want to make a new input variable, it's not lazy, it's efficient

            switch (command) {//woah such interesting switch cases
                case "lookup" -> lookup(query());//lookup defined in Item.java, query is lower
                case "quit" -> {
                    System.out.println("Saving & Exiting... Thank You");
                    excelIntegration.update();//updates the csv file
                    quit = true;// changes that boolean from the beginning
                }
                case "sell" -> calculations.ringUp();
                case "stock" -> {
                    excelIntegration.update();
                    String id = query();//asks for barcode
                    changeStock(lookup(id), getArrayId(id));//runs the stuff to stock an item
                }
                case "newItem" -> newItem();//Most of this file is just referring to other code
                case "updatePrice" -> {
                    excelIntegration.update();
                    String id = query();
                    updatePrice(lookup(id), getArrayId(id));
                }
                case "inventory" -> allInv();
                case "help"->{
                    System.out.println(
                            "help - take a wild guess\n"+
                            "lookup - Finds product based on barcodeID\n" +
                            "quit - exits\n" +
                            "stock - short for change stock, updates amount of item on hand\n" +
                            "newItem - input a new item into the array.\n" +
                            "updatePrice - updates price\n");
                }

            }
            excelIntegration.update();
        }while(!quit);//repeats until someone quits the program, or if it runs into an error...
    }
    public static String query(){//I just didn't want to write this more than once...
        System.out.print("What is the Barcode?: ");
        String query = Item.input.next();
        return query;
    }

}