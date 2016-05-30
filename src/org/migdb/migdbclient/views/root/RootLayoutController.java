package org.migdb.migdbclient.views.root;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.migdb.migdbclient.config.FxmlPath;
import org.migdb.migdbclient.controllers.MongoConnManager;
import org.migdb.migdbclient.main.MainApp;
import org.migdb.migdbclient.views.mongodatamanager.MySession;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RootLayoutController implements Initializable {

	@FXML
	private AnchorPane rootContainerAncpane;
	@FXML
	private AnchorPane sideBarAnchorpane;
	@FXML
	private Label datamanagerLabel;
	@FXML
	private Label connectionManagerLabel;
	@FXML
	private Label modificationEvaluatorLabel;
	@FXML
	private ListView<String> databaseList;

	/**
	 * Initialize method Called to initialize a controller after its root
	 * element has been completely processed The location used to resolve
	 * relative paths for the root object, or null if the location is not known
	 * The resources used to localize the root object, or null if the root
	 * object was not localized
	 */
	public void initialize(URL location, ResourceBundle resources) {

		MySession.INSTANCE.setRoot(rootContainerAncpane);

		ObservableList<String> list;
		try {
			list = FXCollections.observableArrayList(getDatabaseNames());
			databaseList.setItems(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Data manager navigation label click event
		datamanagerLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseevent) {
				showDataManager();
			}
		});

		// Connection manager navigation label click event
		connectionManagerLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseevent) {
				showConnectionManager();
			}
		});

		// Connection manager navigation label click event
		modificationEvaluatorLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseevent) {
				showModificationEvaluator();
			}
		});
	}

	/**
	 * Method for add connection manager layout to the root container anchor
	 * pane
	 */
	public void showConnectionManager() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(FxmlPath.CONNECTIONMANAGER.getPath()));
			AnchorPane connectionManager = loader.load();
			rootContainerAncpane.getChildren().clear();
			rootContainerAncpane.getChildren().add(connectionManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for add modification evaluator layout to the root container anchor
	 * pane
	 */
	public void showModificationEvaluator() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(FxmlPath.MODIFICATIONEVALUATOR.getPath()));
			AnchorPane modificationEvaluator = loader.load();
			rootContainerAncpane.getChildren().clear();
			rootContainerAncpane.getChildren().add(modificationEvaluator);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for add data manager layout to the root container anchor pane
	 */
	public void showDataManager() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(FxmlPath.DATAMANAGER.getPath()));
			AnchorPane dataManager = loader.load();
			rootContainerAncpane.getChildren().clear();
			rootContainerAncpane.getChildren().add(dataManager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getDatabaseNames() throws Exception {
		List<String> dbs = new ArrayList<String>();
		MongoClient client = MongoConnManager.INSTANCE.connect();
		MongoCursor<String> dbsCursor = client.listDatabaseNames().iterator();
		while (dbsCursor.hasNext()) {
			dbs.add(dbsCursor.next());
		}
		return dbs;
	}

}
