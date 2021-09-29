//Base class for all products the store will sell
import java.util.*;

//Base class for all products the store will sell
public abstract class Product implements Comparable<Product>{
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int AmountCart;

    public Product(double initPrice, int initQuantity){
        soldQuantity=0;
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public int getSoldQuantity(){
        return soldQuantity;
    }

    public double getPrice(){
        return price;
    }
   //the amountcart "counter" increases and the stockquantity of a product/item decreases when it is in the cart.
    public void InCartUnit(){
        AmountCart++;
        stockQuantity--;
    }
    //the amountcart "counter" decreases and the stockquantity of a product/item increases when it is out of the cart
    public void OutCartUnit(){
        AmountCart--;
        stockQuantity++;
    }
    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits( ){
        double revenue=AmountCart*price;
        soldQuantity+=AmountCart;
        AmountCart=0;
        return revenue;
    }
    //compares the item, needed it for popular item
    public int compareTo(Product p){
        if(getSoldQuantity()<=p.getSoldQuantity()){
            return 1;
        }else{
            return -1;
        }
    }
}