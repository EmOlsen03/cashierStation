package placeHolder;

import java.io.IOException;

public class calculations {
    public static void main(String[] args) throws IOException {
        //main for testing purposes
        excelIntegration.load();
        Item.allInv();
        ringUp();
    }

    public static void ringUp() {//the function of this file

        double[] items = new double[10000];//item prices
        String[] itemList = new String[10000];//items
        boolean more = true;//boolean for whether the transactions over
        int j =0;//int for length of lists
        double subtotal = 0;//aptly named

        while (more) {//while transaction is in progress
            int i = 0;//int i is for repeats, it resets to zero at the start of the loop

            System.out.println("Enter 'exit' when finished");//user input
            String query = commandLine.query();//barcode asker, if you look through the code, you've seen it enough times already

            if(query.equals("exit")){//if you exit
                receipt(subtotal, items, itemList);//prints the receipt
                more=false;//you exit
            }else {//if you don't exit

                String item = Item.lookup(query);//looks up barcode
                if (item == "error") {
                    //if item not found, then doesn't try to add it.
                } else {
                    int id = Item.getArrayId(item);//it gets the array location

                    //user input for how many of an item, to reduce putting in the same barcode many times
                    System.out.println("How many of this Item? ");
                    int repeat = Item.input.nextInt();

                    while(i < repeat){//while number of repeats is less than user amount of wanted item
                        items[j] = Double.parseDouble(Item.all[3][id]);//adds price to price array
                        itemList[j] = Item.all[1][id];//adds item to item list
                        i++;//increases number of times going through this
                        j++;//increasing count of items
                    }
                    stockEditing(id, repeat);//edits the information on the amount in stock
                    subtotal = calculate(items);//gives a subtotal


                    //can be commented out, but for testing purposes, it prints out all items
                    Item.allInv();
                }
            }
        }
        //double total = tax(subtotal);
    }
    public static double calculate(double[] items){
        double subtotal=0;//subtotal stores the subtotal

        for(int i=0; i<items.length;i++){//for loop calculates the entire price of items in the list
            if(items[i]==0){
                i = items.length;
            }else subtotal=subtotal+items[i];
        }

        double subRound = Math.round(subtotal * 100);//rounds subtotal
        subRound = subRound/100;

        //System.out.println("Subtotal......"+subtotal); was for testing purposes
        System.out.println("Subtotal......"+subRound);//prinds rounded subtotal
        return subRound;//returns rounded subtotal
    }

    public static double tax(double subtotal){//very simple tax calculator
        double total = subtotal+(subtotal*.097);
        total = Math.round(total * 100);
        total = total/100;
        System.out.println("Total........."+total);
        return total;
    }

    public static void receipt(double subtotal, double[] items, String[] itemList){

        //makes tax() irrelevant, oh well
        double total = subtotal+(subtotal*.097);
        total = Math.round(total * 100);
        total = total/100;

        //prints out all items and their price
        for(int i=0; i< items.length; i++){
            if(itemList[i]==null){
                i=items.length;
            }else System.out.println(itemList[i]+"......."+items[i]);
        }
        //prints sub and normal total
        System.out.println("Subtotal......."+subtotal);
        System.out.println("Total.........."+total);

    }

    public static void stockEditing(int id,int amount){//edits amount in stock
        boolean stockGood = true;//legibility boolean

        if(amount > Integer.parseInt(Item.all[4][id])){//checks if there's less in stock than requested
            System.out.println("Stock error, reverting item stock to 0, please manually fix");
            Item.all[4][id]="0";//it changes the stock amount to zero, and lets the staff know

            stockGood=false;//stock no good, but for testing purposes, as if the text didn't do enough
        }
        else{
            Item.all[4][id] = String.valueOf(Integer.parseInt(Item.all[4][id])-amount);//edits stock
        }

    }
}
