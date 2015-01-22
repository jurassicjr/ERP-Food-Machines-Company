package userInterface.controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JPanel;

import model.Bill;
import model.FinancialNotification;
import production.view.StagesProductionFrame;
import rh.view.EmployeeReportFrame;
import rh.view.RegisterEmployeeFrame;
import rh.view.RegisterUserFrame;
import rh.view.TechnicalStandardFrame;
import sales.controller.SalesController;
import userInterface.components.NotificationButton;
import userInterface.view.AboutFrame;
import userInterface.view.MainFrame;
import userInterface.view.RegisterIssueFrame;
import database.DataBase;
import financial.view.GenerateReportFrame;
import financial.view.ListBillsFrame;
import financial.view.ListDebtsFrame;
import financial.view.PayBillFrame;
import financial.view.RegisterBillFrame;
import financial.view.RegisterDebtsToReceiveFrame;

/**
 * Classe controladora do frame principal do sistema
 */
public class MainFrameController {
	
	public static final int approvalOfSupliers = 0;
	public static final int salesRequisition = 1;
	public static final int registerOfSuppliers = 2;
	public static final int salesOrder = 3;
	public static final int registerOfMaterial = 4;
	public static final int updateOfProduct = 5;
	public static final int updateOfSupplier = 6;
	public static final int supplierReport = 7;
	public static final int productReport = 8;
	public static final int clientRegistration = 9;
	public static final int registerProduct = 10;
	public static final int inventory = 11;
	public static final int registerOfKit = 12;

	
	private MainFrame mainFrame;

	/**
	 * Cria o controlador para o frame principal da aplicação
	 * 
	 * @param mainFrame
	 *            O Frame a ser controlado
	 */
	public MainFrameController(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
	}

	/**
	 * Exibe o frame para registro de funcionário
	 */
	public void registerEmployee() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterEmployeeFrame frame = new RegisterEmployeeFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});		

	}

	/**
	 * Exibe o frame para o registro de usuário
	 */
	public void registerUser() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterUserFrame frame = new RegisterUserFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});		

		
	}
	
	/**
	 * Inicializa o frame para registro de Norma Técnica
	 */
	public void technicalStandard() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
							
				TechnicalStandardFrame frame = new TechnicalStandardFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});		
		
	}
	
	public void registerBill() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
							
				RegisterBillFrame frame = new RegisterBillFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});	
		
	}

	public void setFinancialNotifications(JPanel notificationPanel) {
		
		DataBase database = new DataBase();
		database.connect();
		
		ArrayList<FinancialNotification> notifications = new ArrayList<FinancialNotification>();
		
		try {
			
			//todas as contas não pagas ou para vencerem num intervalo de 30 dias
//			String sql = "SELECT installment.*, bill.bill as 'bill_name' "
//					+ "FROM installment, bill "
//					+ "WHERE ((date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 31 DAY)) OR date < NOW()) AND paid = 0 AND installment.bill = bill.id "
//					+ "ORDER BY(installment.date);";
			
			String sql = "SELECT installment.date, installment.value, bill.* "
					+ "FROM installment, bill "
					+ "WHERE ((date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 31 DAY)) OR date < NOW()) "
					+ "AND paid = 0 AND installment.bill = bill.id ORDER BY(installment.date);"; 
			
			ResultSet resultSet = database.executeQuery(sql);
			
			while(resultSet.next()) {
				
				String bill = resultSet.getString("bill");
				String creditor = resultSet.getString("creditor");
				String observation = resultSet.getString("observation");
				int billId = resultSet.getInt("id");
				
				Date date = resultSet.getDate("date");
				double value = resultSet.getDouble("value");
				
				Bill b = new Bill(bill, creditor, observation, billId);
								
				notifications.add(new FinancialNotification(b, date, value));
				
			}			
			
			resultSet.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		database.close();
		
		for(FinancialNotification notification : notifications) {
			
			NotificationButton button = new NotificationButton(notification.toString(), notification.isUrgent());
			notificationPanel.add(button);
			notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
										
					PayBillFrame payBillFrame = new PayBillFrame(notification.getBill(), notification.getValue());
					payBillFrame.setVisible(true);
					payBillFrame.setLocationRelativeTo(mainFrame);
										
				}
			});
						
		}
				
	}
	
	public void registerDebts() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
							
				RegisterDebtsToReceiveFrame frame = new RegisterDebtsToReceiveFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});	
		
	}	
	
	public void payBill() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				ListBillsFrame frame = new ListBillsFrame(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void listBills() {
				
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				ListBillsFrame frame = new ListBillsFrame(false);
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void generateReport() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				GenerateReportFrame frame = new GenerateReportFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void receiveDebt() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				ListDebtsFrame frame = new ListDebtsFrame(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
	}
	
	public void listDebts() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				ListDebtsFrame frame = new ListDebtsFrame(false);
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void about() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				AboutFrame frame = new AboutFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void employeeReport() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				EmployeeReportFrame frame = new EmployeeReportFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void registerIssue() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterIssueFrame frame = new RegisterIssueFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
	
	public void stagesProduction() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				StagesProductionFrame frame = new StagesProductionFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});
		
	}
		
	public void Sales(int i) {
		SalesController controller = new SalesController(mainFrame);
		if(i == 0) {
			controller.ApprovalOfSuppliers();
		}else if(i == 1) {
			controller.salesRequisition();
		}else if(i == 2) {
			controller.registerOfSuppliers();
		}else if(i == 3) {
			controller.salesOrder();
		}else if(i==4) {
			controller.registerOfProduct();
		}else if(i==5) {
			controller.updateOfProducts();
		}else if(i==6) {
			controller.supplierUpdateFrame();
		}else if(i==7) {
			controller.supplierReportFrame();
		}else if(i==8){
			controller.productReport();
		}else if(i==9) {
			controller.registerOfClient();
		}else if(i == 10) {
			controller.registerProduct();
		}else if(i==11) {
			controller.invetoryFrame();
		}else if(i==12) {
			controller.registerOfKit();
		}
	}

}
