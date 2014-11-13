/******************************************************************
 *	NAME:				CHARLES PEZANKO
 *	DATE:				October 7, 2014
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 3
 *	FILE:				FastFood.java
 *	TARGET:				Java 6.0 and 7.0
 ******************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;

public class FastFood extends Frame {

	// Initial Frame size
	static final int WIDTH = 400;                // frame width
	static final int HEIGHT = 300;               // frame height

    // Arrays of selection items
    static final String[] JUNK_FOODS = {"Burger", "Hotdog", "Fries", "Pizza"};
    static final int[] JUNK_FOODS_PRICE = {199, 150, 99, 299};
    static final String[] FRUIT = {"Pear", "Apple", "Peach", "Banana"};
    static final int[] FRUIT_PRICE = {89, 79, 99, 69};
    static final String[] DESSERTS = {"Ice cream", "Pie", "Cake", "Donut"};
    static final int[] DESSERTS_PRICE = {149, 249, 299, 99};
    static final String[] BEVERAGE = {"Soda", "Coffee", "Milk", "Water"};
    static final int[] BEVERAGE_PRICE = {75, 95, 85, 65};
    static final int[] ORDERED_PRICE = new int [20];
    int INDEX=0;

    // List and Button control
    List junkFoodList;
    List fruitList;
    List dessertList;
    List beverageList;
    List itemsOrderedList;
    Button addJunkFood;
    Button addFruit;
    Button addDessert;
    Button addBeverage;
    Button deleteItem;

    // Total amount and display
    Label amountLabel;
    int amount = 0;

    /**
     * Constructor
     */
	public FastFood() {
	    setTitle("Fast Food To Go");
        setLayout(new BorderLayout());

        // create display for item selection
        Panel itemPanel = new Panel(new GridLayout(2, 1));
        add(itemPanel, BorderLayout.CENTER);
        

        // create display and control for junk food selection items
        Panel junkFoodPanel = new Panel(new BorderLayout());
        itemPanel.add(junkFoodPanel);
        Label junkFood = new Label("Fast Food Items", Label.CENTER);
        junkFood.setForeground(Color.red);
        junkFoodPanel.add(junkFood, BorderLayout.NORTH);
        junkFoodList = new List();
        for(int i=0; i<JUNK_FOODS.length; i++) {
            junkFoodList.add(JUNK_FOODS[i]+ "   $" + (float)JUNK_FOODS_PRICE[i]/100);
        }
        junkFoodPanel.add(junkFoodList, BorderLayout.CENTER);
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        junkFoodPanel.add(buttonPanel, BorderLayout.SOUTH);
        addJunkFood = new Button("Get Food Item");
        buttonPanel.add(addJunkFood);
        
        
        // create display and control for fruit selection items
        Panel fruitPanel = new Panel(new BorderLayout());
        itemPanel.add(fruitPanel);
        Label fruit = new Label("Fruit Items", Label.CENTER);
        fruit.setForeground(Color.red);
        fruitPanel.add(fruit, BorderLayout.NORTH);
        fruitList = new List();
        for(int i=0; i<FRUIT.length; i++){
        	fruitList.add(FRUIT[i]+ "   $" + (float)FRUIT_PRICE[i]/100);
        }
        fruitPanel.add(fruitList, BorderLayout.CENTER);
        Panel fruitbuttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        fruitPanel.add(fruitbuttonPanel, BorderLayout.SOUTH);
        addFruit = new Button("Get Fruit Item");
        fruitbuttonPanel.add(addFruit);

        
        // create display and control for dessert selection items
        Panel dessertPanel = new Panel(new BorderLayout());
        itemPanel.add(dessertPanel);
        Label dessertLabel = new Label("Dessert Items", Label.CENTER);
        dessertLabel.setForeground(Color.red);
        dessertPanel.add(dessertLabel, BorderLayout.NORTH);
        dessertList = new List();
        for(int i=0; i<DESSERTS.length; i++) {
            dessertList.add(DESSERTS[i]+ "   $" + (float)DESSERTS_PRICE[i]/100);
        }
        dessertPanel.add(dessertList, BorderLayout.CENTER);
        buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        dessertPanel.add(buttonPanel, BorderLayout.SOUTH);
        addDessert = new Button("Get Dessert Item");
        buttonPanel.add(addDessert);

        
        //create display and control for beverage selection items
        Panel beveragePanel = new Panel(new BorderLayout());
        itemPanel.add(beveragePanel);
        Label beverageLabel = new Label ("Beverage Items", Label.CENTER);
        beverageLabel.setForeground(Color.red);
        beveragePanel.add(beverageLabel, BorderLayout.NORTH);
        beverageList = new List();
        for(int i = 0; i < BEVERAGE.length; i++){
        	beverageList.add(BEVERAGE[i]+ "   $" + (float)BEVERAGE_PRICE[i]/100);
        }
        beveragePanel.add(beverageList, BorderLayout.CENTER);
        Panel beveragebuttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        beveragePanel.add(beveragebuttonPanel, BorderLayout.SOUTH);
        addBeverage = new Button("Get Dessert Item");
        beveragebuttonPanel.add(addBeverage);
        
        
        // create display and control for items ordered and amount
        Panel orderedPanel = new Panel(new BorderLayout());
        add(orderedPanel, BorderLayout.EAST);

        // create display and control for items ordered
        Panel itemsOrderedPanel = new Panel(new BorderLayout());
        orderedPanel.add(itemsOrderedPanel, BorderLayout.CENTER);
        Label itemsOrdered = new Label("Items Ordered", Label.CENTER);
        itemsOrdered.setForeground(Color.red);
        itemsOrderedPanel.add(itemsOrdered, BorderLayout.NORTH);
        itemsOrderedList = new List();
        itemsOrderedPanel.add(itemsOrderedList, BorderLayout.CENTER);
        buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        itemsOrderedPanel.add(buttonPanel, BorderLayout.SOUTH);
        deleteItem = new Button("Delete Ordered Item");
        buttonPanel.add(deleteItem);

        // create display for amount
        Panel amountPanel = new Panel(new BorderLayout());
        orderedPanel.add(amountPanel, BorderLayout.SOUTH);
        Label totalLabel = new Label("Total Amount: ");
        amountPanel.add(totalLabel, BorderLayout.WEST);
        amountLabel = new Label("$0.00");
        amountPanel.add(amountLabel, BorderLayout.CENTER);

        // create and add list selection listener
        SelectionListener selListener = new SelectionListener();
        junkFoodList.addActionListener(selListener);
        fruitList.addActionListener(selListener);
        dessertList.addActionListener(selListener);
        beverageList.addActionListener(selListener);
        itemsOrderedList.addActionListener(selListener);
        
        // create and add button listener
        ButtonListener buttonListener = new ButtonListener();
        addJunkFood.addActionListener(buttonListener);
        addFruit.addActionListener(buttonListener);
        addDessert.addActionListener(buttonListener);
        addBeverage.addActionListener(buttonListener);
        deleteItem.addActionListener(buttonListener);
	} // end of constructor

    /**
     *  Listener class for list selection
     */
    class SelectionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedItem;
            int selectedIndex;
            int itemPrice;

            if(event.getSource() == junkFoodList) {  // select item from junk food list
                selectedIndex = junkFoodList.getSelectedIndex();
                selectedItem = junkFoodList.getSelectedItem();
                itemPrice = JUNK_FOODS_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == fruitList) {  // select item from fruit list
                selectedIndex = fruitList.getSelectedIndex();
                selectedItem = fruitList.getSelectedItem();
                itemPrice = FRUIT_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == dessertList) {  // select item from dessert list
                selectedIndex = dessertList.getSelectedIndex();
                selectedItem = dessertList.getSelectedItem();
                itemPrice = DESSERTS_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == beverageList) {  // select item from beverage list
                selectedIndex = beverageList.getSelectedIndex();
                selectedItem = beverageList.getSelectedItem();
                itemPrice = BEVERAGE_PRICE[selectedIndex];
                addOrderedItem(selectedItem, itemPrice);
            }
            else if(event.getSource() == itemsOrderedList) {  // remove item from ordered list
                selectedIndex = itemsOrderedList.getSelectedIndex();
                selectedItem = itemsOrderedList.getSelectedItem();
                itemPrice = ORDERED_PRICE[selectedIndex];
                removeOrderedItem(selectedItem, itemPrice);
            }
        }
    }

    /**
     *  Listener class for button selection
     */
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedItem;
            int selectedIndex;
            int itemPrice;

            if(event.getSource() == addJunkFood) {  // get item from junk food list
                selectedIndex = junkFoodList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = junkFoodList.getSelectedItem();
                    itemPrice = JUNK_FOODS_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
            }
            else if(event.getSource() == addFruit) {  // get item from fruit list
                selectedIndex = fruitList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = fruitList.getSelectedItem();
                    itemPrice = FRUIT_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
            }
            else if(event.getSource() == addDessert) {  // get item from dessert list
                selectedIndex = dessertList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = dessertList.getSelectedItem();
                    itemPrice = DESSERTS_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
            }
            else if(event.getSource() == addBeverage) {  // get item from beverage list
                selectedIndex = beverageList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = beverageList.getSelectedItem();
                    itemPrice = BEVERAGE_PRICE[selectedIndex];
                    addOrderedItem(selectedItem, itemPrice);
                }
            }
            else if(event.getSource() == deleteItem) {  // remove item from ordered list
                selectedIndex = itemsOrderedList.getSelectedIndex();
                if(selectedIndex >= 0) {
                    selectedItem = itemsOrderedList.getSelectedItem();
                    itemPrice = ORDERED_PRICE[selectedIndex];
                    removeOrderedItem(selectedItem, itemPrice);
                }
            }
        }
    }

    /**
     *  method to add an ordered item
     */
    void addOrderedItem(String item, int price) {
        itemsOrderedList.add(item);        
        amount += price;
        amountLabel.setText("$"+(float)amount/100);
        ORDERED_PRICE[INDEX]=price;
        INDEX ++;
    }
    
    /**
     * method to remove an ordered item
     */
    void removeOrderedItem(String item, int price){
    	itemsOrderedList.remove(item);
    	amount -= price;
    	amountLabel.setText("$"+(float)amount/100);
    	INDEX --;
    }
    
    
	/**
     * the main method
     */
    public static void main(String[] argv) {
        // Create a frame
        FastFood frame = new FastFood();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(150, 100);

        // add window closing listener
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
