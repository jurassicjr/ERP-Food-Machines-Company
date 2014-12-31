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
import model.Product;
import model.State;
import model.Supplier;
import sales.view.ApprovalOfSuppliersFrame;
import sales.view.ProductUpdateFrame;
import sales.view.RegisterOfProductFrame;
import sales.view.RegisterSuppliersFrame;
import sales.view.SalesOrderFrame;
import sales.view.SalesRequisitionFrame;
import sales.view.SupplierUpdateFrame;
import userInterface.view.MainFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.ProductDAO;
import database.dao.SuppliersDAO;

public class SalesController {

	private MainFrame mainFrame;
	private DataBase dataBase;
	private SuppliersDAO sDAo;
	private ProductDAO produtoDAO;

	/**
	 * Construtor para determinar o MainFrame.
	 */

	public SalesController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		dataBase = new DataBase();
		dataBase.connect();

	}

	/**
	 * Construto padrão, faz a conexão com o DataBase.
	 */

	public SalesController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	/**
	 * Inicializa o JFrame de aprovação de fornecedores.
	 */

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

	/**
	 * Inicializa o JFrame de requisição de compra.
	 */

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

	/**
	 * Inicializa o Jframe de registro de fornecedores.
	 */

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

	/**
	 * Inicializa o JFrame de atualização de produtos.
	 */

	public void updateOfProducts() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ProductUpdateFrame frame = new ProductUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	/**
	 * Inicializa o JFrame de pedidos de compra.
	 */

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

	/**
	 * Inicializa o JFrame de registro de produtos.
	 */

	public void registerOfProduct() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				RegisterOfProductFrame frame = new RegisterOfProductFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	/**
	 * fecha o JFrame passado como parametro.
	 */

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();
		}

	}

	/**
	 * Popula o JComboBox<Produto> com produtos do banco.
	 */

	public void fillProducts(JComboBox<Product> product) {
		product.removeAllItems();
		try {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product");
			while (rs.next()) {
				Product produto = new Product();
				produto.setName(rs.getString("name"));
				produto.setDescrition(rs.getString("descricao"));
				produto.setId(rs.getInt("id"));
				produto.setAmmount(rs.getInt("quantidade"));
				product.addItem(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Popula os JComboBox com estado e cidades
	 */

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

	/**
	 * configura os estados que irão ser populados no JComboBox.
	 */

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

	/**
	 * Configura as cidades para popularem o JComboBox.
	 */

	private void setCities(State state, JComboBox<City> cities) {

		cities.removeAllItems();

		try {

			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM city WHERE city.state = ?",
			        new Object[] { state.getId() });

			while (resultSet.next()) {

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

	/**
	 * Realiza o registro de produtos
	 */

	public void doProductRegister(Product product) {
		try {
			if (product.equals(null)) {
				throw new Exception();
			} else {
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("name", product.getName());
				mapa.put("quantidade", product.getAmmount());
				mapa.put("descricao", product.getDescrition());
				produtoDAO = new ProductDAO(mapa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza os registro de fornecedores.
	 */

	public void doSupplierRegister(Supplier supplier) throws SQLException {
		try {
			if (supplier.equals(null)) {
				throw new Exception();
			} else {
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("companyName", supplier.getCompanyName());
				mapa.put("CNPJ", supplier.getCNPJ());
				mapa.put("city", supplier.getCity().getId());
				mapa.put("state", supplier.getState().getId());
				mapa.put("street", supplier.getStreet());
				mapa.put("neighborhood", supplier.getNeighborhood());
				mapa.put("certificate", supplier.isCertificated());
				mapa.put("stateRegistration", supplier.getStateRegistration());
				// mapa.put("registerDate", supplier.getRegisterDate());
				mapa.put("fiscalClassification", supplier.getFiscalClassification());
				mapa.put("materialCertification", supplier.isMaterialCertication());
				mapa.put("justificative", supplier.getJustificative());
				mapa.put("email", supplier.getEmail());
				mapa.put("phone", supplier.getPhone());
				mapa.put("cep", supplier.getCep());
				mapa.put("expirationDate", supplier.getExpireCertificateDate());
				sDAo = new SuppliersDAO(mapa, supplier.getRegisterDate());
			}
		} catch (Exception e) {
			System.out.println("No Supplier");
			
		}
	}
	
	public void updateSupplier(Supplier supplier) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("companyName", supplier.getCompanyName());
		mapa.put("CNPJ", supplier.getCNPJ());
		mapa.put("city", supplier.getCity().getId());
		mapa.put("state", supplier.getState().getId());
		mapa.put("street", supplier.getStreet());
		mapa.put("neighborhood", supplier.getNeighborhood());
		mapa.put("certificate", supplier.isCertificated());
		mapa.put("stateRegistration", supplier.getStateRegistration());
		mapa.put("fiscalClassification", supplier.getFiscalClassification());
		mapa.put("materialCertification", supplier.isMaterialCertication());
		mapa.put("justificative", supplier.getJustificative());
		mapa.put("email", supplier.getEmail());
		mapa.put("phone", supplier.getPhone());
		mapa.put("cep", supplier.getCep());
		mapa.put("expirationDate", supplier.getExpireCertificateDate());
		mapa.put("id", supplier.getId());
		sDAo = new SuppliersDAO();
		sDAo.updatePersist(mapa);
	}

	public void supplierUpdateFrame() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SupplierUpdateFrame frame = new SupplierUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void fillSuppliers(JComboBox<Supplier> cboSupplier) {
		City city = null;
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers")) {
			while (rs.next()) {
				String razaoSocial = rs.getString("corporate_name");
				String CNPJ = rs.getString("CNPJ");

				Supplier supplier = new Supplier(razaoSocial, CNPJ);

				supplier.setCep(rs.getString(15));
				supplier.setCertificated(rs.getBoolean("certificate"));
				supplier.setEmail(rs.getString("email"));
				supplier.setExpireCertificateDate(rs.getDate("expireCertificationDate"));
				supplier.setFiscalClassification(rs.getString("fical_classification"));
				supplier.setId(rs.getInt("id"));
				supplier.setJustificative(rs.getString("justificative"));
				supplier.setMaterialCertication(rs.getBoolean("material_certificate"));
				supplier.setNeighborhood(rs.getString("neighborhood"));
				supplier.setPhone(rs.getString("phone"));
				supplier.setStateRegistration(rs.getString("state_registration"));
				supplier.setStreet(rs.getString("street"));
				try {
					ResultSet rsState = dataBase.executeQuery("SELECT *FROM state WHERE id = ?", rs.getInt("state"));
					while (rsState.next()) {
						int id = rsState.getInt("id");
						String name = rsState.getString("name");
						State state = new State(id, name);
						try {
							ResultSet rsCity = dataBase.executeQuery("SELECT *FROM city where id = ?",
							        rs.getInt("city"));
							while (rsCity.next()) {
								int idCity = rsCity.getInt("id");
								String nameCity = rsCity.getString("name");
								city = new City(idCity, nameCity, state);
							}
							rsCity.close();
							supplier.setCityState(city, state);
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
					}
					rsState.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				cboSupplier.addItem(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cboSupplier.setSelectedIndex(-1);
	}
}
