package userInterface.controller;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import maintenance.view.register.RouteRegisterFrame;
import maintenance.view.register.ToolBoxRegisterFrame;
import maintenance.view.register.ToolBoxUpdateFrame;
import maintenance.view.register.ToolRegisterFrame;
import maintenance.view.register.VehicleDebtRegisterFrame;
import maintenance.view.register.VehicleRegisterFrame;
import maintenance.view.search.VehicleSearchFrame;
import maintenance.view.update.ToolUpdateFrame;
import maintenance.view.update.VehicleReturnUpdateFrame;
import maintenance.view.update.VehicleUpdateFrame;
import production.view.StagesProductionFrame;
import rh.view.AssessmentOfCompetenceFrame;
import rh.view.ExternalSatisfactionResearchFrame;
import rh.view.InternalSatisfactionResearchFrame;
import rh.view.ManualQualityFrame;
import rh.view.MonitoringImplementationFrame;
import rh.view.ProcedureFrame;
import rh.view.RegisterEmployeeFrame;
import rh.view.RegisterOfFunctionDescriptionFrame;
import rh.view.RegisterOfTrainingFrame;
import rh.view.RegisterUserFrame;
import rh.view.RemoveOfEPIFrame;
import rh.view.RncRegisterFrame;
import rh.view.TechnicalStandardFrame;
import rh.view.UpdateEmployeeFrame;
import rh.view.UpdateOfTrainingFrame;
import rh.view.UpdatePermissionsFrame;
import rh.view.report.EmployeeReportFrame;
import rh.view.report.ExternalSatisfactionResearchReport;
import rh.view.report.InternalSatisfactionResearchReport;
import rh.view.search.RncSearchFrame;
import sales.controller.SalesController;
import sales.view.InventoryFrame;
import sales.view.PTCApprovationFrame;
import sales.view.PTCUpdateFrame;
import sales.view.register.ClientPropertiesOutputFrame;
import sales.view.register.PTCRegisterFrame;
import sales.view.register.ServiceRegisterFrame;
import sales.view.search.PurchaseRequisitionSearchFrame;
import sales.view.update.MaterialUpdateFrame;
import userInterface.view.AboutFrame;
import userInterface.view.MainFrame;
import userInterface.view.RegisterIssueFrame;
import util.ShowMessage;
import financial.view.GenerateReportFrame;
import financial.view.ListBillsFrame;
import financial.view.RegisterBankAccountFrame;
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
	public static final int updateOfMaterial = 5;
	public static final int updateOfSupplier = 6;
	public static final int supplierReport = 7;
	public static final int productReport = 8;
	public static final int clientRegistration = 9;
	public static final int registerProduct = 10;
	public static final int inventory = 11;
	public static final int registerOfKit = 12;
	public static final int updateOfCompostProduct = 13;
	public static final int updateOfKit = 14;
	public static final int clientReport = 15;
	public static final int materialSearch = 16;
	public static final int productSearch = 17;
	public static final int clientSearch = 18;
	public static final int clientUpdate = 19;
	public static final int kitSearch = 20;
	public static final int clientPropertiesRegister = 21;
	
	private MainFrame mainFrame;

	/**
	 * Cria o controlador para o frame principal da aplica????o
	 * 
	 * @param mainFrame
	 *            O Frame a ser controlado
	 */
	public MainFrameController(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
	}

	/**
	 * Exibe o frame para registro de funcion??rio
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
	 * Exibe o frame para o registro de usu??rio
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
	 * Inicializa o frame para registro de Norma T??cnica
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

//	public void setFinancialNotifications(JPanel notificationPanel) {
//		
//		BillDAO dao = new BillDAO();
//		
//		List<Bill> bills = dao.getAllUnpaid();
//		ArrayList<FinancialNotification> notifications = new ArrayList<FinancialNotification>();
//		
//		Date now = DateUtil.truncate(new Date());
//		
//		for(Bill bill : bills) {
//			
//			Date dueDate = DateUtil.truncate(dao.getNextPaymentDate(bill));
//			
//			if(dueDate.before(now) || dueDate.equals(now) || DateUtil.diffBetweenDate(now, dueDate) <= 30) {
//				notifications.add(new FinancialNotification(bill, dueDate, 10.0));
//			}
//			
//		}
//		
//		for(FinancialNotification notification : notifications) {
//			
//			NotificationButton button = new NotificationButton(notification.toString(), notification.isUrgent());
//			notificationPanel.add(button);
//			notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//			
////			button.addActionListener(new ActionListener() {
////				
////				@Override
////				public void actionPerformed(ActionEvent e) {
////										
////					PayBillFrame payBillFrame = new PayBillFrame(notification.getBill(), notification.getValue());
////					payBillFrame.setVisible(true);
////					payBillFrame.setLocationRelativeTo(mainFrame);
////										
////				}
////			});
//						
//		}
//				
//	}
	
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
		
//		EventQueue.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				ListDebtsFrame frame = new ListDebtsFrame(true);
//				frame.setVisible(true);
//				frame.setLocationRelativeTo(mainFrame);
//				
//			}
//		});
	}
	
	public void listDebts() {
		
//		EventQueue.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				ListDebtsFrame frame = new ListDebtsFrame(false);
//				frame.setVisible(true);
//				frame.setLocationRelativeTo(mainFrame);
//				
//			}
//		});
		
	}
	
	public void updateUser() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				UpdatePermissionsFrame frame = new UpdatePermissionsFrame();
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
		if(i == 0) controller.ApprovalOfSuppliers();
		else if(i==1)controller.salesRequisition();
		else if(i==2)controller.registerOfSuppliers();
		else if(i==3)controller.salesOrder();
		else if(i==4)controller.registerOfProduct();
		else if(i==5)controller.updateOfMaterials();
		else if(i==6)controller.supplierUpdateFrame();
		else if(i==7)controller.supplierReportFrame();
		else if(i==8)controller.productReport();
		else if(i==9)controller.registerOfClient();
		else if(i==10)controller.registerProduct();
		else if(i==11)controller.invetoryFrame();
		else if(i==12)controller.registerOfKit();
		else if(i==13)controller.updateOfProducts();
		else if(i==14)controller.updateOfKit();
		else if(i==15)controller.ClientReport();
		else if(i==16)controller.materialSearch();
		else if(i==17)controller.productSearch();
		else if(i==18)controller.clientSearch();
		else if(i==19)controller.clientUpdate();
		else if(i==20)controller.searchOfKit();
		else if(i==21)controller.clientPropertiesRegister();
		
	}

	public void closeFrame() {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados n??o salvos ser??o perdidos.";

		int response = ShowMessage.questionMessage(mainFrame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}


	public void epiUpdate() {
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

	public void insertOfEPI() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				InventoryFrame frame = new InventoryFrame(true);
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void removeOfEPI() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RemoveOfEPIFrame frame = new RemoveOfEPIFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void registerOfTraining() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RegisterOfTrainingFrame frame = new RegisterOfTrainingFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void updateOfTraining() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				UpdateOfTrainingFrame frame = new UpdateOfTrainingFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void updateOfEmployee() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				UpdateEmployeeFrame frame = new UpdateEmployeeFrame(null);
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
	}

	public void internalSatisfactionResearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				InternalSatisfactionResearchFrame frame = new InternalSatisfactionResearchFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void vehicleRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VehicleRegisterFrame frame = new VehicleRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void vehicleUpdate() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VehicleUpdateFrame frame = new VehicleUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void vehicleSearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VehicleSearchFrame frame = new VehicleSearchFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void externalSatisfactionResearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ExternalSatisfactionResearchFrame frame = new ExternalSatisfactionResearchFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void registerFunctionDescription() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RegisterOfFunctionDescriptionFrame frame = new RegisterOfFunctionDescriptionFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void routeRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RouteRegisterFrame frame = new RouteRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void updateRoute() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VehicleReturnUpdateFrame frame = new VehicleReturnUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void assessmentOfCompetence() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				AssessmentOfCompetenceFrame frame = new AssessmentOfCompetenceFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void vechileDebtRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VehicleDebtRegisterFrame frame = new VehicleDebtRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void rnc() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RncRegisterFrame frame = new RncRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void monitoringImplementation() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MonitoringImplementationFrame frame = new MonitoringImplementationFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void searchOfNC() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RncSearchFrame frame = new RncSearchFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void exitOfClientProperties() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ClientPropertiesOutputFrame frame = new ClientPropertiesOutputFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void toolsRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ToolRegisterFrame frame = new ToolRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void toolsUpdate() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ToolUpdateFrame frame = new ToolUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void externalSatisfactionReport() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ExternalSatisfactionResearchReport frame = new ExternalSatisfactionResearchReport();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void internalSatisfactionReport() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				InternalSatisfactionResearchReport frame = new InternalSatisfactionResearchReport();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void toolsBoxRegister() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ToolBoxRegisterFrame frame = new ToolBoxRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void procedures() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ProcedureFrame frame = new ProcedureFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void manualQuality() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ManualQualityFrame frame = new ManualQualityFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		}); 
	    
    }

	public void toolBoxUpdate() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ToolBoxUpdateFrame frame = new ToolBoxUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void registerBankAccount() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RegisterBankAccountFrame frame = new RegisterBankAccountFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void registerService() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ServiceRegisterFrame frame = new ServiceRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void registerPTC() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PTCRegisterFrame frame = new PTCRegisterFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void updatePTC() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PTCUpdateFrame frame = new PTCUpdateFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void pTCApprovation() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PTCApprovationFrame frame = new PTCApprovationFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }

	public void salesRequisitionSearch() {
	    EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PurchaseRequisitionSearchFrame frame = new PurchaseRequisitionSearchFrame();
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
			}
		});
    }
}
