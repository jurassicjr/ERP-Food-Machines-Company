package sales.controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.City;
import model.State;
import model.Supplier;
import sales.view.ApprovalOfSuppliersFrame;
import sales.view.RegisterSuppliersFrame;
import sales.view.SalesOrderFrame;
import sales.view.SalesRequisitionFrame;
import userInterface.view.MainFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.SuppliersDAO;

public class SalesController {

	private MainFrame mainFrame;
	private DataBase dataBase;
	private SuppliersDAO sDAo;

	public SalesController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		dataBase = new DataBase();
		dataBase.connect();
		
	}

	public SalesController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void ApprovalOfSuppliers() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ApprovalOfSuppliersFrame frame = new ApprovalOfSuppliersFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);

			}
		});
	}

	public void salesRequisition() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SalesRequisitionFrame frameReq = new SalesRequisitionFrame();
				frameReq.setMinimumSize(new Dimension(750, 370));
				frameReq.setVisible(true);
				frameReq.setLocationRelativeTo(mainFrame);

			}
		});

	}

	public void registerOfSuppliers() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				RegisterSuppliersFrame frame = new RegisterSuppliersFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				

			}
		});
	}

	public void salesOrder() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SalesOrderFrame frame = new SalesOrderFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);

			}
		});
	}

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();
			// dataBase.close();
		}

	}

	public void fillStateAndCity(JComboBox<State> states, JComboBox<City> cities) {

		setStates(states, cities);

		ItemListener comboboxListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == 1)
					setCities((State) states.getSelectedItem(), cities);
			}
		};

		states.addItemListener(comboboxListener);

	}

	private void setStates(JComboBox<State> states, JComboBox<City> cities) {

		try {

			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM state");

			while (resultSet.next()) {

				State state = new State(resultSet.getInt("id"), resultSet.getString("name"));
				states.addItem(state);

			}

			resultSet.close();
			states.setSelectedIndex(-1);

		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}

	}
	
private void setCities(State state, JComboBox<City> cities) {
		
		cities.removeAllItems();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM city WHERE city.state = ?", new Object[]{state.getId()});
			
			while(resultSet.next()) {
				
				City city = new City(resultSet.getInt("id"), resultSet.getString("name"), state);
				cities.addItem(city);
				
			}
			
			resultSet.close();
			
			cities.setSelectedIndex(-1);
			cities.setEnabled(true);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}	

	public void doSupplierRegister(Supplier supplier) throws SQLException {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("companyName", supplier.getCompanyName());
		mapa.put("CNPJ", supplier.getCNPJ());
		mapa.put("city", supplier.getCity().getId());
		mapa.put("state", supplier.getState().getId());
		mapa.put("street", supplier.getStreet());
		mapa.put("neighborhood", supplier.getNeighborhood());
		mapa.put("certificate", supplier.isCertificated());
		mapa.put("stateRegistration", supplier.getStateRegistration());
		mapa.put("registerDate", supplier.getRegisterDate());
		mapa.put("fiscalClassification", supplier.getFiscalClassification());
		mapa.put("materialCertification", supplier.isMaterialCertication());
		mapa.put("justicative", supplier.getJustificative());
		sDAo = new SuppliersDAO(mapa);
		
	}

}
