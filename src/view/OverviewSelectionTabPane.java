package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class OverviewSelectionTabPane extends GridPane {

	private TextArea profile;
	private TextArea SelectedModules;
	private TextArea ReservedModules;
	private String newLine = "\n";
	private Button save;
	
	public OverviewSelectionTabPane() {
		
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(50);
		this.setPadding(new Insets(25, 25, 25, 25));
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.CENTER);
		column0.setHgrow(Priority.ALWAYS);
		
		RowConstraints row = new RowConstraints();
		row.setValignment(VPos.CENTER);
		row.setVgrow(Priority.ALWAYS);

		RowConstraints row1 = new RowConstraints();
		row1.setValignment(VPos.CENTER);
		row1.setVgrow(Priority.ALWAYS);

		this.getRowConstraints().addAll(row, row1);

		this.getColumnConstraints().addAll(column0);
		
		profile = new TextArea("Profile will appear here");
		profile.setPrefSize(800, 100);
		profile.setEditable(false);
		this.add(profile, 0, 0);
		
		SelectedModules = new TextArea("Selected Modules: ");
		SelectedModules.setPrefSize(400, 350);
		SelectedModules.setEditable(false);
		HBox.setHgrow(SelectedModules, Priority.ALWAYS);
		
		ReservedModules = new TextArea("Reserved Modules: ");
		ReservedModules.setPrefSize(400, 350);
		ReservedModules.setEditable(false);
		HBox.setHgrow(ReservedModules, Priority.ALWAYS);
		
		save = new Button("Save Overview");
		save.setAlignment(Pos.CENTER);
		HBox.setHgrow(save, Priority.ALWAYS);
		
		HBox button = new HBox(save);
		button.setAlignment(Pos.BOTTOM_CENTER);
		this.add(button, 0, 2);
		
		HBox modules = new HBox(SelectedModules, ReservedModules);
		modules.setSpacing(20);
		modules.setAlignment(Pos.CENTER);
		this.add(modules, 0, 1);

	}
	
	public void getProfileName(String n) {
		profile.setText(n);
	}
	
	public void getPNumber(String PN) {
		
		profile.appendText(newLine);
		profile.appendText(PN);
	}
	
	public void getEmail(String email) {
		
		profile.appendText(newLine);
		profile.appendText(email);
	}
	
	public void getDate(String date) {
		
		profile.appendText(newLine);
		profile.appendText(date);
	}
	
	public void getCourse(String course) {
		
		profile.appendText(newLine);
		profile.appendText(course);
	}
	
	public void getSelectedModules(String module) {
		SelectedModules.appendText(newLine);
		SelectedModules.appendText(newLine);
		SelectedModules.appendText(module);
	}
	
	public void getReservedModules(String module) {
		ReservedModules.appendText(newLine);
		ReservedModules.appendText(newLine);
		ReservedModules.appendText(module);
	}
	
	//event Handlers
	public void SaveOverviewHandler(EventHandler<ActionEvent> handler) {
		save.setOnAction(handler);
	}
	
	
}
