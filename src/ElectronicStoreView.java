import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;

public class ElectronicStoreView extends Pane {
    //listview
    ListView<Product>CartListView;
    ListView<Product>PopularItemListView;
    ListView<Product>StoreStockListView;
    //labels
    Label CurrentCart;
    Label StoreStock;
    Label Revenue;
    Label NumberOfSales;
    Label StoreSummary;
    Label PopularItems;
    Label AverageSales;
    //buttons
    Button ResetButton;
    Button RemoveButton;
    Button CompleteSaleButton;
    Button AddButton;
    //textfields
    TextField RevenueText;
    TextField NumberOfSalesText;
    TextField AverageSalesText;
    //variable that have a constant value
    private int TextLenght=78;
    private int TextHeight=19;
    private int TextXLocation=87;
    private int ButtonHeight=48;
    private int ButtonYLocation=336;
    ElectronicStoreView(){
        setPrefSize(800, 400);
        //sets all the labels to the text written
        StoreSummary = new Label("Store Summary: ");
        NumberOfSales = new Label("# Sales: ");
        Revenue = new Label("Revenue: ");
        AverageSales = new Label("$ / Sale: ");
        PopularItems = new Label("Most Popular Items: ");
        CurrentCart = new Label("Current Cart ($0.00): ");
        StoreStock = new Label("Store Stock: ");

        //sets the locations of the labels
        StoreSummary.relocate(28, 8);
        NumberOfSales.relocate(17, 41);
        Revenue.relocate(17, 71);
        AverageSales.relocate(18, 101);
        PopularItems.relocate(18, 127);
        StoreStock.relocate(295, 8);
        CurrentCart.relocate(595, 8);

        //sets the listviews
        StoreStockListView = new ListView<Product>();
        PopularItemListView = new ListView<Product>();
        CartListView = new ListView<Product>();

        //sets the size of the of the listview
        StoreStockListView.setPrefSize(290, 295);
        PopularItemListView.setPrefSize(165, 165);
        CartListView.setPrefSize(290, 295);

        //sets the location of listview
        StoreStockListView.relocate(188, 28);
        PopularItemListView.relocate(8, 158);
        CartListView.relocate(495, 30);

        //sets textFields
        NumberOfSalesText = new TextField();
        RevenueText = new TextField();
        AverageSalesText = new TextField();

        //sets size of the textfield
        NumberOfSalesText.setPrefSize(TextLenght, TextHeight);
        RevenueText.setPrefSize(TextLenght, TextHeight);
        AverageSalesText.setPrefSize(TextLenght, TextHeight);

        //sets location of textfield
        NumberOfSalesText.relocate(TextXLocation, 40);
        RevenueText.relocate(TextXLocation, 70);
        AverageSalesText.relocate(TextXLocation, 100);

        //sets the buttons
        ResetButton = new Button("Reset Store");
        AddButton = new Button("Add to Cart");
        RemoveButton = new Button("Remove from Cart");
        CompleteSaleButton = new Button("Complete Sale");

        //set the size of button
        ResetButton.setPrefSize(150, ButtonHeight);
        AddButton.setPrefSize(150, ButtonHeight);
        RemoveButton.setPrefSize(140, ButtonHeight);
        CompleteSaleButton.setPrefSize(140, ButtonHeight);

        //sets the location
        ResetButton.relocate(17, ButtonYLocation);
        AddButton.relocate(261, ButtonYLocation);
        RemoveButton.relocate(505, ButtonYLocation);
        CompleteSaleButton.relocate(643, ButtonYLocation);

        //how button should be
        AddButton.setDisable(true);
        RemoveButton.setDisable(true);
        CompleteSaleButton.setDisable(true);
        //gets eve
        getChildren().addAll(RemoveButton,AddButton,CompleteSaleButton, ResetButton, StoreStockListView, CartListView,PopularItemListView,AverageSalesText,NumberOfSalesText,RevenueText,
                Revenue,StoreSummary,PopularItems, NumberOfSales,AverageSales,CurrentCart,StoreStock);
    }
    //updates all the listview and text fields
    public void update(ElectronicStore model){
        NumberOfSalesText.setText(Integer.toString(model.getNumberOfSales()));
        AverageSalesText.setText(String.format("%.2f", model.getRevenue()/model.getNumberOfSales()));
        /* Thought this might work to get N/A for "$/sale" however when I do it it just stays as N/A and doesn't update
        when a sale is completed. This is why it is commented out and the the one above is being used.
     AverageSalesText.setText(String.format("N/A", model.getRevenue()/model.getNumberOfSales())); */
        PopularItemListView.setItems(FXCollections.observableArrayList(model.getMostPopularItems()));
        StoreStockListView.setItems(FXCollections.observableArrayList(model.getListOfProducts(false)));
        CurrentCart.setText(String.format("Current Cart (%.2f): ", model.getValueOfCart()));
        CartListView.setItems(FXCollections.observableArrayList(model.getCartList()));
        RevenueText.setText(String.format("%.2f", model.getRevenue()));

        //the conditions where buttons are either enabled or disabled
        if(StoreStockListView.getSelectionModel().getSelectedIndex() == -1)
            AddButton.setDisable(true);
        else
            AddButton.setDisable(false);
        if(CartListView.getSelectionModel().getSelectedIndex() == -1){
            RemoveButton.setDisable(true);
        }
        else{
            RemoveButton.setDisable(false);
        }
        if(CartListView.getItems().isEmpty())
            CompleteSaleButton.setDisable(true);
        else
            CompleteSaleButton.setDisable(false);
    }
    //getter methods of button and listview
    public Button getAddButton(){return AddButton;}
    public Button getRemoveButton(){return RemoveButton;}
    public Button getCompleteButton(){return CompleteSaleButton;}
    public Button getResetButton(){return ResetButton;}
    public ListView<Product> getStockList(){return StoreStockListView;}
    public ListView<Product> getCartList(){return CartListView;}
}
