package userInterface.controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JPanel;

import model.FinancialNotification;
import rh.view.RegisterEmployeeFrame;
import rh.view.RegisterUserFrame;
import rh.view.TechnicalStandardFrame;
import sales.controller.SalesController;
import userInterface.components.NotificationButton;
import userInterface.view.MainFrame;
import database.DataBase;
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
	public static final int registerOfProduct = 4;
	public static final int updateOfProduct = 5;
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
			String sql = "SELECT installment.*, bill.bill as 'bill_name' "
					+ "FROM installment, bill "
					+ "WHERE ((date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 31 DAY)) OR date < NOW()) AND paid = 0 AND installment.bill = bill.id "
					+ "ORDER BY(installment.date);"; 
			
			ResultSet resultSet = database.executeQuery(sql);
			
			while(resultSet.next()) {
				
				String bill = resultSet.getString("bill_name");
				Date date = resultSet.getDate("date");
				
				notifications.add(new FinancialNotification(bill, date));
				
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
		}
	}

}
