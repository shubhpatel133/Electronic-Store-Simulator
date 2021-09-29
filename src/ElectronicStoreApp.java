import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    public ElectronicStoreApp(){model = ElectronicStore.createStore();}
    public void start(Stage primaryStage)  {
        Pane aPane = new Pane();
        ElectronicStoreView view = new ElectronicStoreView();
        model.setMostPopularItems();
        view.update(model);
        aPane.getChildren().add(view);
        //sets the title of the GUI to whatever the store name(model) is
        primaryStage.setTitle("Electronic Store Application - "+ model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
        //if something in the cart(current cart) is clickled
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });
        //when the completesale button is pressed it sells the item from the model, updates the model, also updates popular items
        view.getCompleteButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.sellProducts();
                model.setMostPopularItems();
                view.update(model);
            }
        });
        //when the remove button is pressed it removes the item from the cart and updates model
        view.getRemoveButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.RemoveItemCart(view.getCartList().getSelectionModel().getSelectedIndex());
                view.update(model);
            }
        });
        //when items in store stock is pressed it updates the models
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });
        //when add button is pressed the item gets added to the current cart list view and removed from stocklist view
        view.getAddButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.AddItemToCart(model.getListOfProducts(false)[view.getStockList().getSelectionModel().getSelectedIndex()]);
                view.update(model);
            }
        });
        //everything goes back to the way it was
        view.getResetButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model = ElectronicStore.createStore();
                model.setMostPopularItems();
                view.update(model);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}