// import libraries
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class Main extends Application {
    // private class variables
	private TabPane tabPane;
    private HeroPane heroPane;
    private ArmyPane armyPane;
    private ArrayList<Hero> heroList;
    public static final int WINSIZE_X = 950, WINSIZE_Y = 600;

    // override abstract start method from Application parent class
    public void start(Stage stage) {
        StackPane root = new StackPane();

        // heroList to be used in all tabs
        Hero defaultUnicorn = new Hero("Aliz", "Unicorn", 20, 80, 60);
        heroList = new ArrayList<Hero>();
        heroList.add(defaultUnicorn);
        heroPane = new HeroPane(heroList);
        armyPane = new ArmyPane(heroList);

        tabPane = new TabPane();
        // first tab
        Tab tab1 = new Tab();
        tab1.setText("Add Hero");
        tab1.setContent(heroPane);
        // second tab
        Tab tab2 = new Tab();
        tab2.setText("Create Army");
        tab2.setContent(armyPane);

        // add both tabs to the tabPane
        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(tab1, tab2);

        root.getChildren().add(tabPane);

        // set scene
        Scene scene = new Scene(root, WINSIZE_X, WINSIZE_Y);
        // add scene to the stage
        stage.setTitle("Create your army!");
        stage.setScene(scene);
        stage.show();
    }
    // main method
    public static void main(String[] args) {
        launch(args);
    }
}