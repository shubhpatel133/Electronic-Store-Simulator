//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ElectronicStore {
  private final int MAX_PRODUCTS = 10;
  private String name;
  private double revenue;
  private double ValueOfCart;
  //arrays
  private Product[] ItemSoldOut = new Product[MAX_PRODUCTS];
  private Product[] products = new Product[MAX_PRODUCTS];
  private Product[] MostPopularItems = new Product[3]; //only need 3 spots as we are looking for the top 3 popular items
  private ArrayList<Product> ItemsInCart = new ArrayList<Product>();
  //sales that are made, this is to help with the "# sales"
  private int NumberOfSales=0;
  //different counters to keep track of things
  private int CartCounter=0;
  private int ProductCounter=0;
  private int SoldOutCounter=0;

  ElectronicStore(String initName){name= initName;}

  //keeps track of the item, and if the item(productCounter) is more then the given max product then it will return false.
  //otherwise it will add to the products array.
  public boolean addProduct(Product p){
    if (ProductCounter>=MAX_PRODUCTS) {
      return false;
    }
    products[ProductCounter++]=p;
    return true;
  }
  //looks at how many items are in the cart and it will then sell it.
  //The number of sales will then increase which will be used later in the code.
  //it then creates an array of the items that are in the user cart
  public void sellProducts(){
    int a=0;
    while (a<CartCounter) {
      ValueOfCart -= ItemsInCart.get(a).getPrice();
      revenue += ItemsInCart.get(a).sellUnits();
      a++;
    }
    NumberOfSales++;
    CartCounter=0;
    ItemsInCart=new ArrayList<Product>();
  }
  //looks at an item stock. So when the item soldout is equal to the amount of item then the soldout counter increases
  //and the value for the productcounter decreases(meaning item is removed form stock)
  public void removeFromStock(Product item){
    int a=0;
    while (a<ProductCounter) {
      if (products[a].equals(item)) {
        ItemSoldOut[SoldOutCounter]=item;
        SoldOutCounter++;
        products[a]=products[ProductCounter-1];
        ProductCounter--;
      }
      a++;
    }
  }
  //the value of the cart will decrease if item is removed from cart.
  //it then updates the product array as item will then be added, which in turn increases the product counter
  //it decreases the soldout counter as item will be added back into stock.
  //cart counter will decrease as less item in cart
  public void RemoveItemCart(int index){
    ValueOfCart-=ItemsInCart.get(index).getPrice();
    int a=0;
    while (a<SoldOutCounter) {
      if (!ItemSoldOut[a].equals(ItemsInCart.get(index))) {
      }else {
        products[ProductCounter] = ItemsInCart.get(index);
        ProductCounter++;
        ItemSoldOut[a]=ItemSoldOut[SoldOutCounter-1];
        SoldOutCounter--;
      }
      a++;
    }
    ItemsInCart.get(index).OutCartUnit();
    ItemsInCart.remove(index);
    CartCounter--;
  }
  //
  public Product[] getListOfProducts(boolean SoldOutItems){
    if(SoldOutItems) {
      Product[] ListOfProducts = new Product[ProductCounter + SoldOutCounter];
      {
        int a = 0;
        while (a < ProductCounter) {
          ListOfProducts[a]=products[a];
          a++;
        }
      }
      int a = ProductCounter;
      while (a < ProductCounter + SoldOutCounter) {
        ListOfProducts[a]=ItemSoldOut[a - ProductCounter];
        a++;
      }
      return ListOfProducts;
    }
    Product[] ListOfProducts=new Product[ProductCounter];
    int a=0;
    while (a<ProductCounter) {
      ListOfProducts[a]=products[a];
      a++;
    }
    return ListOfProducts;
  }
  //most popular item getter method
  public Product[] getMostPopularItems(){
    return MostPopularItems;
  }
  //sets the items in order from most to least bought(which item is most popular then 2nd most then 3rd most and so on)
  public void setMostPopularItems() {
    Product[] organized = getListOfProducts(true);
    Arrays.sort(organized);
    for(int a = 0; a < MostPopularItems.length; a++){
      MostPopularItems[a] = organized[a];
    }
  }
  //items are added to cart, and the cart counter then increases and so does the value of the cart.
  public void AddItemToCart(Product item){
    ItemsInCart.add(item);
    item.InCartUnit();
    ValueOfCart += item.getPrice();
    CartCounter++;
    if(item.getStockQuantity() == 0){
      removeFromStock(item);
    }
  }
  //gets the items that are in the cart. While it is lower than the counter then the items in the cart are added to the array
  public Product[] getCartList() {
    Product[] cartList;
    cartList = new Product[CartCounter];
    int a = 0;
    while (a < CartCounter) {
      cartList[a] = ItemsInCart.get(a);
      a++;
    }
    return cartList;
  }
  public void printStock(){
    for(int a = 0; a < ProductCounter; a++){
      System.out.printf("%d. ", a);
      System.out.println(products[a]);
    }
  }
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
  //getter methods
  public double getValueOfCart(){return ValueOfCart;}
  public double getRevenue(){ return revenue; }
  public String getName() {return name;}
  public int getNumberOfSales(){return NumberOfSales;}

}