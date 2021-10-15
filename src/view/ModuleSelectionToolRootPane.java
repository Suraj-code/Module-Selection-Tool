package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class ModuleSelectionToolRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private ModuleSelectionToolMenuBar mstmb;
	private SelectmodulesTabPane smtp;
	private ReserveModulesTabPane rmtp;
	private OverviewSelectionTabPane ostp;
	private Term1Pane t1p;
	private Term2Pane t2p;
	private TabPane tp;
	
	public ModuleSelectionToolRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		smtp = new SelectmodulesTabPane();
		rmtp = new ReserveModulesTabPane();
		ostp = new OverviewSelectionTabPane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Modules", smtp);
		Tab t3 = new Tab("Reserve Modules", rmtp);
		Tab t4 = new Tab("Overview Selection", ostp);
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);
		
		//create menu bar
		mstmb = new ModuleSelectionToolMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public ModuleSelectionToolMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	
	public SelectmodulesTabPane getSelectmodulesTabPane() {
		return smtp;
	}
	
	public Term1Pane getTerm1Pane() {
		return t1p;
	}
	
	public Term2Pane getTerm2Pane() {
		return t2p;
	}
	
	public ReserveModulesTabPane getReserveModulesTabPane() {
		return rmtp;
	}
	
	public OverviewSelectionTabPane getOverviewSelectionTabPane() {
		return ostp;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}


}
