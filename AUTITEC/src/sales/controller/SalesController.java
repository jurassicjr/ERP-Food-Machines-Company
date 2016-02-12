package sales.controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.City;
import model.Material;
import model.MaterialModel;
import model.MaterialType;
import model.MeasureUnit;
import model.State;
import model.Supplier;
import product.view.RegisterKitFrame;
import product.view.RegisterProductFrame;
import product.view.UpdateOfKitFrame;
import product.view.UpdateOfProductFrame;
import product.view.search.SearchOfKitFrame;
import sales.view.ApprovalOfSuppliersFrame;
import sales.view.InventoryFrame;
import sales.view.SalesOrderFrame;
import sales.view.SalesRequisitionFrame;
import sales.view.register.ClientPropertiesRegisterFrame;
import sales.view.register.ClientRegisterFrame;
import sales.view.register.RegisterOfMaterialFrame;
import sales.view.register.RegisterSuppliersFrame;
import sales.view.report.ClientReportFrame;
import sales.view.report.MaterialReportFrame;
import sales.view.report.SupplierReportFrame;
import sales.view.search.SearchOfClientFrame;
import sales.view.search.SearchOfMaterialFrame;
import sales.view.search.SearchOfProductFrame;
import sales.view.update.MaterialUpdateFrame;
import sales.view.update.SupplierUpdateFrame;
import sales.view.update.UpdateClientFrame;
import userInterface.view.MainFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.MaterialDAO;
import database.dao.MeasureUnitDAO;
import database.dao.RegisterOfMaterialModelDAO;
import database.dao.RegisterOfMaterialTypeDAO;
import database.dao.SuppliersDAO;

public class SalesController {

	private MainFrame mainFrame;
	private DataBase dataBase;
	private SuppliersDAO sDao;
	private MaterialDAO materialDAO;
	private JFrame frame;

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

	public void updateOfMaterials() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MaterialUpdateFrame frame = new MaterialUpdateFrame();
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
				RegisterOfMaterialFrame frame = new RegisterOfMaterialFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void supplierReportFrame() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SupplierReportFrame frame = new SupplierReportFrame();
				frame.pack();
				frame.setLocationRelativeTo(mainFrame);
				frame.setVisible(true);

			}
		});
	}

	/**
	 * fecha o JFrame passado como parametro.
	 */

	public void closeFrame(JFrame frame) {

		this.frame = frame;
		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();
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

	public void doMaterialRegister(Material material) {
		try {
			if (material == null) {
				throw new Exception();
			} else {
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("name", material.getName());
				mapa.put("descricao", material.getDescrition());
				mapa.put("internalCode", material.getInternalCode());
				mapa.put("ncm", material.getNCM());
				mapa.put("measureUnit", material.getMeasureUnit());
				mapa.put("model", material.getModel());
				mapa.put("materialType", material.getMaterialType());
				mapa.put("x", material.getWidth());
				mapa.put("y", material.getLength());
				mapa.put("z", material.getHeigth());
				materialDAO = new MaterialDAO(mapa);
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
				mapa.put("registerDate", supplier.getRegisterDate());
				mapa.put("fiscalClassification", supplier.getFiscalClassification());
				mapa.put("materialCertification", supplier.isMaterialCertication());
				mapa.put("justificative", supplier.getJustificative());
				mapa.put("email", supplier.getEmail());
				mapa.put("phone", supplier.getPhone());
				mapa.put("cep", supplier.getCep());
				mapa.put("expirationDate", supplier.getExpireCertificateDate());
				sDao = new SuppliersDAO(mapa);
				sDao.makeProductAssociation(supplier.getMaterial(), supplier);
			}
		} catch (Exception e) {
			System.out.println("No Supplier");

		}
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

	public void productReport() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MaterialReportFrame frame = new MaterialReportFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});

	}

	public void registerOfClient() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ClientRegisterFrame frame = new ClientRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});

	}

	public void registerProduct() {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				RegisterProductFrame frame = new RegisterProductFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});

	}

	public void invetoryFrame() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				InventoryFrame frame = new InventoryFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void registerOfKit() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				RegisterKitFrame frame = new RegisterKitFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void updateOfProducts() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				UpdateOfProductFrame frame = new UpdateOfProductFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});

	}

	public void updateOfKit() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				UpdateOfKitFrame frame = new UpdateOfKitFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void ClientReport() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ClientReportFrame frame = new ClientReportFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	    
    }

	public void materialSearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SearchOfMaterialFrame frame = new SearchOfMaterialFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	    
    }

	public void productSearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SearchOfProductFrame frame = new  SearchOfProductFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	    
    }
	public void clientSearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SearchOfClientFrame frame = new  SearchOfClientFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	    
    }

	public void clientUpdate() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				UpdateClientFrame frame = new UpdateClientFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void searchOfKit() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SearchOfKitFrame frame = new SearchOfKitFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void clientPropertiesRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ClientPropertiesRegisterFrame frame = new ClientPropertiesRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void fillMaterialType(JComboBox<MaterialType> cboMaterialType) {
	    List<MaterialType> list = new RegisterOfMaterialTypeDAO().getTypes();
	    list.forEach(m -> cboMaterialType.addItem(m));
    }

	public JFrame getFrame() {
	    return this.frame;
    }

	public void fillMaterialModels(JComboBox<MaterialModel> cboMaterialModel) {
	    List<MaterialModel> list = new RegisterOfMaterialModelDAO().getModels();
	    if(list.isEmpty())return;
	    list.forEach(m -> cboMaterialModel.addItem(m));
    }

	public void fillMeasureUnit(JComboBox<MeasureUnit> cboMeasureUnit) {
	    List<MeasureUnit> list = new MeasureUnitDAO().getUnits();
	    if(list.isEmpty())return;
	    list.forEach(m -> cboMeasureUnit.addItem(m));
    }
}
