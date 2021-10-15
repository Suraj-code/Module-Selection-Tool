package view;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class ReserveModulesTabPane extends Accordion {

	private Term1Pane t1p;
	private Term2Pane t2p;
	private static TitledPane t1, t2; //declared as fields so they can be accessed to expand in method below
	
	public ReserveModulesTabPane() {
		
		t1p = new Term1Pane();
		t2p = new Term2Pane();
		
		//create titled panes with panes added
		t1 = new TitledPane("Term 1", t1p);
		t2 = new TitledPane("Term 2", t2p);

		//add title panes to tab accordion
		this.getPanes().addAll(t1, t2);
		
		//sets default expanded pane
		this.setExpandedPane(t1);
	}
	
	public void expandT2() {
		this.setExpandedPane(t2);
	}
	
}
