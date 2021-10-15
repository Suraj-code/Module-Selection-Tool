package controller;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import model.Course;

import model.Schedule;
import model.Module;

import model.StudentProfile;
import view.ModuleSelectionToolRootPane;
import view.OverviewSelectionTabPane;
import view.ReserveModulesTabPane;
import view.SelectmodulesTabPane;
import view.Term1Pane;
import view.Term2Pane;
import view.CreateStudentProfilePane;
import view.ModuleSelectionToolMenuBar;

public class ModuleSelectionToolController {

	//fields to be used throughout class
	private ModuleSelectionToolRootPane view;
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private ModuleSelectionToolMenuBar mstmb;
	private SelectmodulesTabPane smtp;
	private Term1Pane t1p;
	private Term2Pane t2p;
	private ReserveModulesTabPane rmtp;
	private OverviewSelectionTabPane ostp;

	public ModuleSelectionToolController(ModuleSelectionToolRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smtp = view.getSelectmodulesTabPane();
		t1p = view.getTerm1Pane();
		t2p = view.getTerm2Pane();
		rmtp = view.getReserveModulesTabPane();
		ostp = view.getOverviewSelectionTabPane();
		
		
		//add courses to combobox in create student profile pane using the generateAndReturnCourses helper method below
		cspp.addCoursesToComboBox(generateAndReturnCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addAboutHandler(e -> this.alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Module Selection Tool v1.0"));
		mstmb.addSaveHandler(new SaveHandler());
		mstmb.addLoadHandler(new LoadHandler());
		
		//attach an event handler to add selected modules
		smtp.addSelectedTerm1ModuleHandler(new AddTerm1Handler());
		smtp.addSelectedTerm2ModuleHandler(new AddTerm2Handler());
		//attach an event handler to remove selected modules
		smtp.RemoveSelectedT1ModuleHandler(new RemoveTerm1Handler());
		smtp.RemoveSelectedT2ModuleHandler(new RemoveTerm2Handler());
		
		smtp.SubmitHandler(new SubmitHandler());
		
		ostp.SaveOverviewHandler(new SaveOverviewHandler());
		
		Term1Pane.addSelectedTerm1ReserveModuleHandler(new  AddReserveTerm1Handler());
		Term1Pane.removeSelectedTerm1ReserveModuleHandler(new RemoveReserveTerm1Handler());
		Term1Pane.confirmSelectedTerm1ReserveModuleHandler(new ConfirmReserveTerm1Handler());
		
		Term2Pane.addSelectedTerm1ReserveModuleHandler(new  AddReserveTerm2Handler());
		Term2Pane.removeSelectedTerm1ReserveModuleHandler(new RemoveReserveTerm2Handler());
		Term2Pane.confirmSelectedTerm2ReserveModuleHandler(new ConfirmReserveTerm2Handler());
		
		//attach an event handler to reset the tab
		smtp.ResetHandler(new ResetHandler());
	}
	

	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
		if(cspp.ValidatePNumber() == false && cspp.ValidateFirstName() == false && cspp.ValidateSurname() == false && cspp.ValidateEmail() == false && cspp.ValidateDate() == false) {
			
			model.setStudentPnumber(view.getCreateStudentProfilePane().getStudentPnumber());
			model.setStudentCourse(view.getCreateStudentProfilePane().getSelectedCourse());
			model.setStudentName(view.getCreateStudentProfilePane().getStudentName());
			model.setStudentEmail(view.getCreateStudentProfilePane().getStudentEmail());
			model.setSubmissionDate(view.getCreateStudentProfilePane().getStudentDate());
			
			ObservableList<Module> modules = FXCollections.observableArrayList (model.getStudentCourse().getAllModulesOnCourse());

			for (int i = 0; i < modules.size(); i++) {
				
				if(modules.get(i).isMandatory() == true && modules.get(i).getDelivery() == Schedule.TERM_1) {
					smtp.addMandatoryT1Module(modules.get(i));
					smtp.addCredit();
					smtp.updateTextField();
				}else if(modules.get(i).isMandatory() == true && modules.get(i).getDelivery() == Schedule.TERM_2) {
					smtp.addMandatoryT2Module(modules.get(i));
					smtp.addCredit2();
					smtp.updateTextField2();
				}else if(modules.get(i).getDelivery() == Schedule.YEAR_LONG) {
					smtp.addSYLM(modules.get(i));
					smtp.addCredit();
					smtp.updateTextField();
					smtp.addCredit2();
					smtp.updateTextField2();
				}else if (modules.get(i).getDelivery() == Schedule.TERM_2) {
					smtp.addUST2M(modules.get(i));
				}else if (modules.get(i).getDelivery() == Schedule.TERM_1) {
					smtp.addUST1M(modules.get(i));
				}
			}
			
				view.changeTab(1);
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "One or more fields empty", "Please fill in the empty field(s) to create your profile!");
			}
		}
	}
	
	
	private class SubmitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			if(smtp.getTotalCredit() < 120) {

				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Not Enough Credits Chosen!", "You need to select 120 credits");

			}else {

				ObservableList<Module> m = (view.getSelectmodulesTabPane().getTerm1SelectedModules());

				ObservableList<Module> m2 = (view.getSelectmodulesTabPane().getTerm2SelectedModules());

				ObservableList<Module> m3 = (view.getSelectmodulesTabPane().getYearLongModule());

				for (int i = 0; i < m.size(); i++) {

					model.addSelectedModule(m.get(i));
		
				}

				for (int i = 0; i < m2.size(); i++) {

					model.addSelectedModule(m2.get(i));
		
				}

				for (int i = 0; i < m3.size(); i++) {

					model.addSelectedModule(m3.get(i));
				}
				
				Term1Pane.addReserveTerm1Modules(smtp.getAllSelectedT1Modules());
				
				view.changeTab(2);
			}
		}
	}

	
	private class AddTerm1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			if(smtp.getCredit() >= 60) {

				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Credit limit reahced!", "You can not choose more than 60 credits for term 1.");

			}else if(smtp.isTerm1ModuleSelected() == false){
				
				smtp.getT1Modules();

				smtp.addST1();

				smtp.removeTerm1Module();

				smtp.addCredit();

				smtp.updateTextField();
				
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to add");
			}
				
				

		}
	}
	
	private class AddTerm2Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			if(smtp.getCredit2() >= 60) {

				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Credit limit reahced!", "You can not choose more than 60 credits for term 2.");
				
			}else if(smtp.isTerm2ModuleSelected() == false){

				smtp.getTerm2Modules();

				smtp.addST2();

				smtp.addCredit2();

				smtp.updateTextField2();

				smtp.removeTerm2Module();
				
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to add");
			}

		}
	}
	
	private class AddReserveTerm1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(Term1Pane.isReservedTerm1ModuleSelected() == false) {
				
			Term1Pane.addSelectedReservedT1Module();
			Term1Pane.removeSelectedReserveT1Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to add");
			}
			
			if(Term1Pane.CountModules() == 2) {
				Term1Pane.enableButton();
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Modules worth 30 credits selected!, Click confirm to select term 2 modules");
			}

		}
			
	}
	
	private class RemoveReserveTerm1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(Term1Pane.isReservedTerm1ModuleSelected2() == false) {
			
			Term1Pane.getSelectedT1Module();
			Term1Pane.addSelectedReserveT1ModuleBack();
			Term1Pane.removeReserveT1Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to remove");
			}
		}
	}
	
	private class ConfirmReserveTerm1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			ObservableList<Module> reservedModules = (view.getTerm1Pane().getReservedTerm1Modules());
			
			for(int i = 0; i < reservedModules.size(); i++) {
				model.addReservedModule(reservedModules.get(i));
			}
			
			rmtp.expandT2();
	
			Term2Pane.addReserveT2Modules(smtp.getAllSelectedT2Modules());
		}
	}
	
	private class AddReserveTerm2Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(Term2Pane.isReservedTerm2ModuleSelected() == false) {
			
			Term2Pane.addSelectedReservedT2Module();
			Term2Pane.removeSelectedReserveT2Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to add");
			}
			
			if(Term2Pane.CountModules() == 2) {
				Term2Pane.enableButton();
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Modules worth 30 credits selected!, click confirm to view final overview");
			}

		}
	}
	
	private class RemoveReserveTerm2Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(t2p.isReservedTerm2ModuleSelected2() == false) {
			
			Term2Pane.getSelectedT2Module();
			Term2Pane.addSelectedReserveT2ModuleBack();
			Term2Pane.removeReserveT2Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to remove");
			}
		}
	}
	
	private class ConfirmReserveTerm2Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {

			ObservableList<Module> reservedModules = (view.getTerm2Pane().getReservedTerm2Modules());
			
			ObservableList<Module> modules = FXCollections.observableArrayList (model.getAllSelectedModules());

			for(int i = 0; i < reservedModules.size(); i++) {
				model.addReservedModule(reservedModules.get(i));
			}

			ostp.getProfileName("Name: " + (model.getStudentName().getFirstName() + model.getStudentName().getFamilyName()).toString());
			ostp.getPNumber("PNo: " + (model.getStudentPnumber()));
			ostp.getEmail("Email: " + (model.getStudentEmail()));
			ostp.getDate("Date: " + (model.getSubmissionDate()));
			ostp.getCourse("Course: " + (model.getStudentCourse()));
			
			ObservableList<Module> Reservemodules = FXCollections.observableArrayList (model.getAllReservedModules());
			
			for(int i = 0; i < modules.size(); i++) {
				ostp.getSelectedModules(("Module Name: " + modules.get(i).getModuleName() + (", ")) + (" Module Code: " + modules.get(i).getModuleCode() + (", ")) + (" Credits: " + modules.get(i).getModuleCredits() + (", ")) + (" Mandatory: " + modules.get(i).isMandatory() + (", ")) + (" Delivery: " + modules.get(i).getDelivery()));
				
			}
			
			for(int i = 0; i < Reservemodules.size(); i++) {
				ostp.getReservedModules(("Module Name: " + Reservemodules.get(i).getModuleName() + (", ")) + (" Module Code: " + Reservemodules.get(i).getModuleCode() + (", ")) + (" Credits: " + Reservemodules.get(i).getModuleCredits() + (", ")) + (" Mandatory: " + Reservemodules.get(i).isMandatory() + (", ")) + (" Delivery: " + Reservemodules.get(i).getDelivery()));
				
			}
			
			view.changeTab(3);

		}
	}

	private class RemoveTerm1Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(smtp.isTerm1ModuleSelected2() == false) {
				
			smtp.getSelectedT1Module();
			
			smtp.addSelectedT1ModuleBack();
			
			smtp.removeCredit();
			
			smtp.updateTextField();
			
			smtp.removeSelectedT1Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to remove");
			}
			
			
		}
	}
	
	private class RemoveTerm2Handler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			if(smtp.isTerm2ModuleSelected2() == false) {
			
			smtp.getSelectedT2Module();
			
			smtp.addSelectedT2ModuleBack();
			
			smtp.removeCredit2();
			
			smtp.updateTextField2();
			
			smtp.removeSelectedT2Module();
			
			}else {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Select a module to remove");
			}
			
		}
	}
	
	
	private class ResetHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			smtp.reset();
			
		}
	}
	
	private class SaveOverviewHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			
			try {
				PrintWriter out = new PrintWriter("Student Details.txt");
				
				out.println(model);
				out.flush();
				out.close();
				
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Student profile details saved to Student Details.txt");
				
			} catch (FileNotFoundException e1) {
				System.out.println("Error saving");
				e1.printStackTrace();
			}
			
		}
	}
	
	private class SaveHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			//save the data model
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ModulesSelected.dat"));) {

				oos.writeObject(model);
				oos.flush();
				
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Module Selection saved to ModulesSelected.dat");
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
				ioExcep.printStackTrace();
			}
			
			smtp.reset();
		}
	}
	
	private class LoadHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			//load in the data model
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ModulesSelected.dat"));) {
				
				model = (StudentProfile) ois.readObject();	
				ois.close();
				
				cspp.setStudentPnumber(model.getStudentPnumber());
				cspp.setStudentFirstName(model.getStudentName().getFirstName());
				cspp.setStudentFamilyName(model.getStudentName().getFamilyName());
				cspp.setStudentEmail(model.getStudentEmail());
				cspp.setStudentDate(model.getSubmissionDate());
				
				
				List<Module> list = new ArrayList<Module>(model.getAllSelectedModules());
				
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).getDelivery() == Schedule.TERM_1) {
						smtp.addMandatoryT1Module(list.get(i));
						smtp.addCredit();
						smtp.updateTextField();
					}else if (list.get(i).getDelivery() == Schedule.TERM_2) {
						smtp.addMandatoryT2Module(list.get(i));
						smtp.addCredit2();
						smtp.updateTextField2();
					}else if(list.get(i).getDelivery() == Schedule.YEAR_LONG) {
						smtp.addSYLM(list.get(i));
						smtp.addCredit();
						smtp.updateTextField();
						smtp.addCredit2();
						smtp.updateTextField2();
					}
				}
				
				List<Module> list2 = new ArrayList<Module>(model.getAllReservedModules());
				
				for(int i = 0; i < list2.size(); i++) {
					if(list2.get(i).getDelivery() == Schedule.TERM_1) {
						Term1Pane.setSavedModule(list2.get(i));
					}else if (list.get(i).getDelivery() == Schedule.TERM_2) {
						Term2Pane.setSavedModule(list2.get(i));
					}
				}
				
				alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Load success", "Selected Modules loaded from ModulesSelected.dat");
			}
			catch (IOException ioExcep){
				System.out.println("Error loading");
			}
			catch (ClassNotFoundException c) {
				System.out.println("Class Not found");
			}
			
			
		}
	}
	
	//helper method to build dialogs - you may wish to use this during certain event handlers
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}


	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndReturnCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

}
