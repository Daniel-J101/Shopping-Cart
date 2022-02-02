import models.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static Store store = new Store();

    public static void main(String[] args) {

        try {
            loadItems("products.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            manageItems();

        }

//        Item[][] inventory = new Item[][] {
//                { new Item("Pepsi", 1.99), new Item("Crush", 1.99), new Item("Cola", 1.99) },
//                { new Item("Honey Oats", 3.99), new Item("Fruit Loops", 1.99), new Item("Cheerios", 2.99) },
//                { new Item("Milk", 4.99), new Item("Eggs", 0.99), new Item("Cheese", 1.89) },
//                { new Item("Pepperoni", 2.99), new Item("Salami", 4.49), new Item("Mortadella", 4.99) },
//                { new Item("Celery", 0.99), new Item("Spinach", 0.99), new Item("Coriander", 1.29) },
//                { new Item("Shirt", 12.99), new Item("Pants", 24.99), new Item("Sweater", 18.99) },
//                { new Item("Phone", 549.99), new Item("Printer", 349.99), new Item("Television", 1099) }
//        };
//        for(int i = 0; i < inventory.length; i++) {
//            for(int j = 0; j < inventory[i].length; j++) {
//                store.setItem(i, j, inventory[i][j]);
//            }
//        }
//
//        System.out.println("\n\t******************************JAVA GROCERS******************************\n");
//
//        System.out.println(store + "\n");

    }

    /**
     * Name: manageItems
     * Inside the function:
     *   • 1. Starts a new instance of Scanner;
     *   • 2. Creates an infinite loop:
     *   •        The user can choose to a) add or b) remove c) checkout.
     *   •          case a: asks for the aisle and item number. Then, adds item to cart.
     *   •          case b: asks for the name. Then, removes item from cart.
     *   •          case c: prints the receipt and closes Scanner.
     *   •        Prints the updated shopping cart.
     */
    public static void manageItems() {
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);
            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");
            String answer = scanner.nextLine();

            if(!(answer.equals("a") || answer.equals("b") || answer.equals("c"))) {
                System.err.println("Invalid input! Make sure your answer is lowercase a-c");
                continue;
            }

            switch(answer) {
                case "a":
                    System.out.print("\nChoose an aisle number between: 1 - 7: ");
                    int aisle = scanner.hasNextInt() ? scanner.nextInt() : 404; //reduce by 1
                    scanner.nextLine();
                    System.out.print("Choose an item number between: 1-3: "); //reduce by 1
                    int itemNum = scanner.hasNextInt() ? scanner.nextInt() : 404;
                    scanner.nextLine();

                    if(aisle == 404 || itemNum == 404) {
                        System.err.println("Invalid input!");
                        continue;
                    } else if(!(aisle > 0 && aisle < 8) || !(itemNum > 0 && itemNum < 4)) {
                        System.err.println("Aisle or item was out of bounds!");
                        continue;
                    }

                    Item tempItem = store.getItem(aisle - 1, itemNum - 1);
                    if(cart.add(tempItem)) {
                        System.out.println(tempItem.getName() + " was added to your shopping cart.");
                    } else
                        System.out.println(tempItem.getName() + " is already in your shopping cart.");
                    break;
                case "b":
                    if(cart.isEmpty()) {
                        System.err.println("The cart is empty!");
                        continue;
                    }
                    System.out.println("Enter the item you'd like to remove: ");
                    cart.remove(scanner.nextLine());
                    break;
                case "c":
                    if(cart.isEmpty()) {
                        System.err.println("The cart is empty!");
                        continue;
                    }
                    System.out.println(cart.checkout());
                    scanner.close();
                    return;
                default:
                    System.err.println("Something went wrong.");
            }

            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.print("Enter anything to continue: ");
            scanner.nextLine();
        }
    }


    /**
     * Name: loadItems
     * @param fileName (String)
     * @throws FileNotFoundException
     *
     * Inside the function:
     *   1. loads items from <fileName>.txt.
     *      • while loop runs through every line in <fileName>
     *      • scan.nextLine() picks up the entire line.
     *      • splits each String using the ";" separator.
     *      • splits both fields in each String using the "=" separator.
     *      • Parse each price into a Double.
     *   2. adds all items to the store object's items field.
     */
    public static void loadItems(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);


        //tried to figure out the logic for ~5 minutes staring at the screen. Probably the worst way to do it, but it works
        int row = 0;
        int column = 0;
        while(scanner.hasNextLine()) {
            String[] products = scanner.nextLine().split(";");
            for (String product : products) {
                String[] temp = product.split("=");
                for(int i = 0; i < temp.length - 1; i++) {
                    if(column > 2) {
                        row++;
                        column = 0;
                    }
                    store.setItem(row, column, new Item(temp[i], Double.parseDouble(temp[i+1])));
                    column++;
                }
            }
        }
        scanner.close();
    }

}