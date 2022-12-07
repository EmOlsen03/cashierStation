# cashierStation

Ahoy hoy, Emiliano of "Emiliano's Sickaroni Cooltastic" coding group for CSC 211-03, here to introduce my program.
    Make sure to read this whole file, and look at that Biggest Issue section, it makes or breaks this code...
    Then run commandLine.java to get started with the actual program
    cool thanks bye



Biggest issue: FileNotFound- to fix this you may need to edit the code.
	1) Find the included productCSV.csv file
	2) Right click, and copy file path
	3) paste the copied text into the quotations on lines 31 and 54 of excelIntegration
	4) Hopefully it works, if not, then code no work-y, and you should probably hunt me down for revenge or something...


Other big issue: When it says array out of bounds, when loading the csv file/
    1) uh oh
    2) Trial and error the .close() and .flush() lines in excelIntegration.java
    3) Try other fixes
    ?) Success? I hope...


To quickly edit data, use the csv file.
For csv in Excel, make sure 'comma delimited' is selected


Commands for the program
    help - prints out these commands'
    sell - actual cashier register thing, does the whole item list and total price and has a receipt
    lookup - Finds product based on barcodeID
    quit - exits
    stock - short for change stock, updates amount of item on hand
    newItem - input a new item into the array.
    updatePrice - updates price


All known errors
  -Giving non numbers for Price and Stock when adding data.
  -Basically any sort of input mismatches
  -Probably many other I assume, but most relate to user error.
