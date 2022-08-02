// import libraries
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ArmyPane extends BorderPane {
	// contains a list of heroes
	ArrayList<Hero> heroList;

	// Variables containing army Damage, Strength, and Charisma
	int totalDamage;
	int totalStrength;
	int totalCharisma;

	// ONE Label to display Army information
	// ONE VBox to contain CheckBoxes
	// ONE "Load Heroes/Clear Selection" Button
	Label armyInfo;	// declare variables
	VBox vbox;
	Button loadButton;

	public ArmyPane(ArrayList<Hero> heroList) {
		this.heroList = heroList;

		armyInfo = new Label();
		vbox = new VBox();
		loadButton = new Button("Load Heroes/Clear Selection");

		// Bind "Load Heroes/Clear Selection" Button to its handler
		loadButton.setOnAction(new LoadHeroesButtonHandler());

		// Organize components to their positions on BorderPane
		this.setTop(armyInfo);
		this.setCenter(vbox);
		this.setBottom(loadButton);

	}

	private class LoadHeroesButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			vbox.getChildren().clear();	// clear vbox

			CheckBox cbox;
			for (Hero hero : heroList)
			{
				cbox = new CheckBox(hero.toString());		// create checkbox elements
				cbox.setOnAction(new CheckBoxHandler(hero));
				vbox.getChildren().add(cbox);
				
			}
		}
	}

	private class CheckBoxHandler implements EventHandler<ActionEvent> {

		Hero hero;

		// When creating a new CheckBoxHandler, pass in a Hero object so it can be accessed later
		public CheckBoxHandler(Hero _hero) {
			this.hero = _hero;
		}

		@Override
		public void handle(ActionEvent event) {
			// Use event.getSource() to get the CheckBox that triggered the event, cast it to CheckBox
			CheckBox cbox = (CheckBox) event.getSource();

			// If the CheckBox was selected, add the current hero scores to totalStrength,
			// 	totalCharisma, and totalDamge. Otherwise, subtract the current hero scores
			if (cbox.isSelected())
			{
				totalStrength += hero.getStrength();	// update total variables
				totalCharisma += hero.getCharisma();
				totalDamage += hero.getDamage();
			}
			else
			{
				totalStrength -= hero.getStrength();
				totalCharisma -= hero.getCharisma();
				totalDamage -= hero.getDamage();
			}
			// Set the Label to
			// "Total Damage: " + totalDamage + "\t\tTotal Strength: " + totalStrength + "\tTotal Charisma: " + totalCharisma
			armyInfo.setText("Total Damage: " + totalDamage + "\t\tTotal Strength: " + 
					totalStrength + "\tTotal Charisma: " + totalCharisma);			// set Top label
		}
	}
}
