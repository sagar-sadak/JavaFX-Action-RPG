// import libraries
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// TODO: 1. Build the GUI
// TODO: 2. Write "Random" Button Handler
// TODO: 3. "Add New Hero" button handler - Check for valid input before adding the new Hero to the list:
// TODO: 4. Update rightTextArea with updated information from heroList
public class HeroPane extends HBox {
	ArrayList<Hero> heroList;

	String selectedHeroType;

	TextArea rightTextArea;
	VBox leftVBox;
	ComboBox<String> heroTypeComboBox;
	ImageView imageView;

	// ONE GridPane to hold
	// FOUR Labels (Name, Strength, Charisma, Damage),
	// FOUR corresponding TextFields
	// ONE "Random" Button
	GridPane inputPane;
	
	Label Name, Strength, Charisma, Damage;
	
	TextField nameField, strengthField, charismaField, damageField;
	
	Button randomButton;
	
	Button newHeroButton;
	Label messageLabel;

	// Define window size
	public static final int WINSIZE_X = 950, WINSIZE_Y = 600;


	// Constructor - what to do when HeroPane is first created
	public HeroPane(ArrayList<Hero> heroList) {

		this.heroList = heroList;

		// Initialize main layout components
		this.leftVBox = new VBox();
		this.rightTextArea = new TextArea();

		// Setting up ComboBox
		String[] heroType = { "Mage", "Fighter", "Unicorn", "Zombie" };
		heroTypeComboBox = new ComboBox<String>();
		heroTypeComboBox.setValue("Hero Type");
		heroTypeComboBox.getItems().addAll(heroType);
		heroTypeComboBox.setOnAction(new HeroTypeComboBoxHandler());
		leftVBox.getChildren().add(heroTypeComboBox);

		Name = new Label("Name");
		Strength = new Label("Strength");	// initialize all variables
		Charisma = new Label("Charisma");
		Damage = new Label("Damage");
		
		nameField = new TextField();
		strengthField = new TextField();
		charismaField = new TextField();
		damageField = new TextField();
		
		randomButton = new Button("Random");
		
		newHeroButton = new Button("Add New Hero!!!");	// set Button text
		messageLabel = new Label();						
		messageLabel.setTextFill(Color.RED);	// set label color to red

		// Organize Labels, TextFields, and Button onto the GridPane

		inputPane = new GridPane();
		inputPane.add(Name, 0, 0);		// organize gridpane components
		inputPane.add(Strength, 0, 1);
		inputPane.add(Charisma, 0, 2);
		inputPane.add(Damage, 0, 3);
		
		inputPane.add(nameField, 1, 0);
		inputPane.add(strengthField, 1, 1);
		inputPane.add(charismaField, 1, 2);
		inputPane.add(damageField, 1, 3);
		
		inputPane.add(randomButton, 2, 3);

		// Bind buttons to their handlers (RandomButtonHandler and AddNewHeroButtonHandler)
		randomButton.setOnAction(new RandomButtonHandler());	// bind buttons to handlers

		leftVBox.getChildren().addAll(inputPane, newHeroButton, messageLabel);	// add all components to leftVBox

		// VBox layout alignment
		inputPane.setHgap(20);
		leftVBox.setPadding(new Insets(40, 50, 0, 50));
		leftVBox.setSpacing(40);
		leftVBox.setAlignment(Pos.TOP_CENTER);
		leftVBox.setPrefWidth(WINSIZE_X / 2);

		// Setting up ImageView
		imageView = new ImageView();
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);
		leftVBox.getChildren().add(imageView);
		FileInputStream input;
		try {
			input = new FileInputStream("unicorn.png");
			Image image = new Image(input);
			imageView.setImage(image);
		} catch (FileNotFoundException e) {
			imageView.setImage(null);
		}

		// Add main components to "Add Hero" tab
		this.getChildren().addAll(leftVBox, rightTextArea);
	}

	// Generate random damage value (50 <= damage <= 100)
	private class RandomButtonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			damageField.setEditable(false);			// make Damage textField non-editable
			if (damageField.getText().isEmpty()) {
				int rand = (int)Math.floor(Math.random() * 101);	// generate random int
				damageField.setText(Integer.toString(rand));	// set random int to damageField
			}
			else
			{
				messageLabel.setText("Damage is already generated");	// label if damageField is not empty
			}
		}
	}


	// "Add New Hero" button handler - Check for valid input before adding the new Hero to the list
	private class AddNewHeroButtonHandler implements EventHandler<ActionEvent> {

		// This method will be called once we click the button
		public void handle(ActionEvent event) {

			String name = nameField.getText();
			String strength = strengthField.getText();	// get values in the text fields
			String charisma = charismaField.getText();
			String damage = damageField.getText();
			
			try {

				// When the hero type is not selected
				if (selectedHeroType == null) {
					throw new Exception("Hero type is not yet selected");
				}

				// If one of the TextFields is empty, throw exception with
				// error message: "At least one of the text fields is empty
				if (name.isEmpty() || strength.isEmpty() || charisma.isEmpty() || damage.isEmpty())
				{
					throw new Exception("At least one of the text fields is empty");	// check if any field is empty
				}

				// Loop through heroList to check for hero that has the same name; throw exception with
				// error message: "Hero existed!"
				for (Hero hero : heroList)
				{
					if (name.equals(hero.getName()))
					{
						throw new Exception("Hero existed!");	// check if user entered hero already exists
					}
				}

				try {
					Integer.parseInt(strength);		// check if the text fields are integers
					Integer.parseInt(charisma);
					Integer.parseInt(damage);
				}
				catch (Exception e) {
					throw new Exception("At least one of the text fields is in the incorrect format");
				}

				if (Integer.parseInt(strength) < 0 || Integer.parseInt(charisma) < 0)
				{
					throw new Exception("Both Strength and Charisma must be positive numbers");	// check if integers are positive
				}

				if ((Integer.parseInt(strength) + Integer.parseInt(charisma)) > 100)	// check if strength+charisma <= 100
				{
					throw new Exception("The sum of strength and charisma must be less or equal to 100");
				}

				heroList.add(new Hero(name, selectedHeroType, 	// add hero to hero arraylist
						Integer.parseInt(strength), Integer.parseInt(charisma), Integer.parseInt(damage)));
				
				messageLabel.setText("Hero added successfully");	// clear text fields after adding hero
				nameField.clear();
				strengthField.clear();
				charismaField.clear();
				damageField.clear();
				
				updateTextArea();
				
			} catch (NumberFormatException exception) {
				// set RED LABEL to "At least one of the text fields is in the incorrect format"
				messageLabel.setText("At least one of the text fields is in the incorrect format");

			} catch (Exception exception) {
				messageLabel.setText(exception.getMessage());	// display error message
			}

		}
	}

	// Create a String containing all hero information and loop through heroList to add all heroes' data together
	private void updateTextArea() {
		String result = "";
		for (Hero hero : heroList)
		{
			result += hero.toString() + "\n";	// call to.String method of each Hero object in the list
		}
		rightTextArea.setText(result);	// set rightTextArea to the final String
	}

	private class HeroTypeComboBoxHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			selectedHeroType = heroTypeComboBox.getSelectionModel().getSelectedItem();
			FileInputStream input;
			try {
				input = new FileInputStream(selectedHeroType.toLowerCase() + ".png");
				Image image = new Image(input);
				imageView.setImage(image);
			} catch (FileNotFoundException e) {
				imageView.setImage(null);
			}
		}
	}
}