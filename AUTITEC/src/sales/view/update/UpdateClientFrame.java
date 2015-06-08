package sales.view.update;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import model.CNPJ;
import model.City;
import model.Client;
import model.State;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ClientUpdateController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.ShowMessage;

public class UpdateClientFrame extends JFrame{
	
	private JTabbedPane tabPanClientData;
	private JPanel principalPanel;
	private JPanel panActions ;
	private JPanel panPJ;
	private JPanel panPF;
	private JPanel panAdresses;
	private JTextField txtCnpj;
	private JTextField txtStateInscription;
	private JTextField txtRG;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private UpperTextField txtStreet;
	private UpperTextField txtContactName;
	private UpperTextField txtCompanyName;
	private UpperTextField txtName;
	private UpperTextField txtNeighborhood;
	private JFormattedTextField txtCep;
	private JFormattedTextField txtPhone;
	private DateField txtBirthDate;
	private ClientUpdateController controller;
	private JComboBox cboClients;
	private JComboBox cboSex;
	private JComboBox<City> cboCity;
	private JComboBox<State> cboState;
	private JButton btnDelete;
	private JButton btnCancel;
	private JButton btnUpdate;
	private boolean clientSelectableFlag = false;
	private Client selectedClient;
	
	//ÍNDICES DO PAINEL TABULADO 
	private static final Integer PF_TAB_INDEX = 1;
	private static final Integer PJ_TAB_INDEX = 0;
	private static final Integer ADRESSES_TAB_INDEX = 2;
	

	public UpdateClientFrame() {
		controller = new ClientUpdateController();
		initialize();
		setListeners();
		clientSelectableFlag = !clientSelectableFlag;
	    
    }
	
	private void initialize() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Atualização/Remoção de Registro de Cliente");
		setBounds(100, 100, 740, 275);
		setMinimumSize(new Dimension(506, 270));
		setPreferredSize(new Dimension(506, 270));
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		initializePrincipal();
		controller.queryAll(cboClients,false);
	}

	private void initializePrincipal() {
	   principalPanel = new JPanel();
	   getContentPane().add(principalPanel, BorderLayout.CENTER);
	   
	   panActions = new JPanel();
	   panActions.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	   panActions.setLayout(new FlowLayout(FlowLayout.RIGHT));
	   
	   btnDelete = new JButton("Apagar");
	   btnDelete.setIcon(new ImageIcon(UpdateClientFrame.class.getResource("/resources/1419366170_17-16.png")));
	   panActions.add(btnDelete);
	   
	   btnCancel = new JButton("Cancelar");
	   btnCancel.setIcon(new ImageIcon(UpdateClientFrame.class.getResource("/resources/cancel.png")));
	   panActions.add(btnCancel);
	   
	   btnUpdate = new JButton("Atualizar");
	   btnUpdate.setIcon(new ImageIcon(UpdateClientFrame.class.getResource("/resources/update.png")));
	   panActions.add(btnUpdate);
	   
	   tabPanClientData = new JTabbedPane(JTabbedPane.TOP);
	   
	   JLabel lblClient = new JLabel("Cliente");
	   
	   cboClients = new JComboBox();
	   GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	   gl_principalPanel.setHorizontalGroup(
	   	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	   		.addGroup(gl_principalPanel.createSequentialGroup()
	   			.addContainerGap()
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_principalPanel.createSequentialGroup()
	   					.addComponent(lblClient)
	   					.addPreferredGap(ComponentPlacement.RELATED)
	   					.addComponent(cboClients, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE))
	   				.addComponent(tabPanClientData, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
	   			.addContainerGap())
	   		.addComponent(panActions, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
	   );
	   gl_principalPanel.setVerticalGroup(
	   	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	   		.addGroup(gl_principalPanel.createSequentialGroup()
	   			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	   				.addComponent(lblClient)
	   				.addComponent(cboClients, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addPreferredGap(ComponentPlacement.UNRELATED)
	   			.addComponent(tabPanClientData, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
	   			.addPreferredGap(ComponentPlacement.RELATED)
	   			.addComponent(panActions, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
	   );
	   
	   panPJ = new JPanel();
	   tabPanClientData.addTab("Pessoa Jurídica", null, panPJ, null);
	   
	   JLabel lblCompanyName = new JLabel("Razão Social");
	   
	   txtCompanyName = new UpperTextField();
	 
	   
	   JLabel lblCnpj = new JLabel("CNPJ");
	   
	   try {
			txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	   
	   JLabel lblStateInscription = new JLabel("IE");
	   
	   txtStateInscription = new JTextField();

	   
	   JLabel lblContactName = new JLabel("Nome do contato");
	   
	   txtContactName = new UpperTextField();
	   
	   GroupLayout gl_panPJ = new GroupLayout(panPJ);
	   gl_panPJ.setHorizontalGroup(
	   	gl_panPJ.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panPJ.createSequentialGroup()
	   			.addGap(10)
	   			.addGroup(gl_panPJ.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panPJ.createSequentialGroup()
	   					.addComponent(lblCompanyName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
	   					.addGap(181)
	   					.addComponent(lblCnpj, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
	   					.addGap(127)
	   					.addComponent(lblStateInscription, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
	   				.addGroup(gl_panPJ.createSequentialGroup()
	   					.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
	   					.addGap(10)
	   					.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
	   					.addGap(10)
	   					.addComponent(txtStateInscription, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
	   				.addComponent(lblContactName, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(txtContactName, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
	   );
	   gl_panPJ.setVerticalGroup(
	   	gl_panPJ.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panPJ.createSequentialGroup()
	   			.addGap(11)
	   			.addGroup(gl_panPJ.createParallelGroup(Alignment.LEADING)
	   				.addComponent(lblCompanyName)
	   				.addComponent(lblCnpj)
	   				.addComponent(lblStateInscription))
	   			.addGap(6)
	   			.addGroup(gl_panPJ.createParallelGroup(Alignment.LEADING)
	   				.addComponent(txtCompanyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(txtStateInscription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addGap(11)
	   			.addComponent(lblContactName)
	   			.addGap(9)
	   			.addComponent(txtContactName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   );
	   panPJ.setLayout(gl_panPJ);
	   
	   panPF = new JPanel();
	   tabPanClientData.addTab("Pessoa Física", null, panPF, null);
	   
	   txtName = new UpperTextField();
	   
	   JLabel lblName = new JLabel("Nome");
	   
	   txtBirthDate = CalendarFactory.createDateField();
	   
	   JLabel lblBirthDate = new JLabel("Data de nascimento");
	   
	   JLabel lblRg = new JLabel("RG");
	   
	   txtRG = new JTextField();
	   
	   JLabel lblCpf = new JLabel("CPF");
	   
	   try { txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##")); } 
		catch (ParseException e) { e.printStackTrace(); }

	   cboSex = new JComboBox();
	   cboSex.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
	   
	   JLabel lblSex = new JLabel("Sexo");
	   GroupLayout gl_panPF = new GroupLayout(panPF);
	   gl_panPF.setHorizontalGroup(
	   	gl_panPF.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panPF.createSequentialGroup()
	   			.addGap(10)
	   			.addGroup(gl_panPF.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
	   					.addGap(7)
	   					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
	   					.addGap(10)
	   					.addComponent(lblBirthDate, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
	   					.addGap(1)
	   					.addComponent(txtBirthDate, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	   					.addGap(4)
	   					.addComponent(txtRG, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
	   					.addGap(5)
	   					.addGroup(gl_panPF.createParallelGroup(Alignment.LEADING)
	   						.addGroup(gl_panPF.createSequentialGroup()
	   							.addGap(30)
	   							.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
	   						.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
	   					.addGap(10)
	   					.addGroup(gl_panPF.createParallelGroup(Alignment.LEADING)
	   						.addGroup(gl_panPF.createSequentialGroup()
	   							.addGap(37)
	   							.addComponent(cboSex, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
	   						.addComponent(lblSex, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))))
	   );
	   gl_panPF.setVerticalGroup(
	   	gl_panPF.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panPF.createSequentialGroup()
	   			.addGap(8)
	   			.addGroup(gl_panPF.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblName))
	   				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblBirthDate))
	   				.addComponent(txtBirthDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
	   			.addGap(22)
	   			.addGroup(gl_panPF.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblRg))
	   				.addComponent(txtRG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblCpf))
	   				.addComponent(cboSex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panPF.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblSex))))
	   );
	   panPF.setLayout(gl_panPF);
	   
	   panAdresses = new JPanel();
	   tabPanClientData.addTab("Correspondência", null, panAdresses, null);
	   
	   cboState = new JComboBox<State>();
	   
	   JLabel lblState = new JLabel("Estado");
	   
	   JLabel lblPhone = new JLabel("Tel.");
	   
	   try { txtPhone = new JFormattedTextField(new MaskFormatter("(##) ####-####")); }
		catch (ParseException e) { e.printStackTrace(); }
	    
	   txtStreet = new UpperTextField();

	   JLabel lblStreet = new JLabel("Rua");
	   
	   try { txtCep = new JFormattedTextField(new MaskFormatter("##.###-###")); }
		catch (ParseException e) { e.printStackTrace(); }
	   
	   JLabel lblCep = new JLabel("C.E.P");
	   
	   JLabel lblCity = new JLabel("Cidade");
	   
	   cboCity = new JComboBox<City>();
	   
	   txtEmail = new JTextField();
	   
	   JLabel lblEmail = new JLabel("email");
	   
	   txtNeighborhood = new UpperTextField();
	   
	   JLabel lblNeighborhood = new JLabel("Bairro");
	   GroupLayout gl_panAdresses = new GroupLayout(panAdresses);
	   gl_panAdresses.setHorizontalGroup(
	   	gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panAdresses.createSequentialGroup()
	   			.addGap(10)
	   			.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   						.addGroup(gl_panAdresses.createSequentialGroup()
	   							.addGap(39)
	   							.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
	   						.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
	   					.addGap(8)
	   					.addComponent(lblNeighborhood, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	   					.addGap(4)
	   					.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   						.addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
	   						.addGroup(gl_panAdresses.createSequentialGroup()
	   							.addGap(39)
	   							.addComponent(txtCep, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
	   					.addGap(10)
	   					.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	   					.addGap(10)
	   					.addComponent(cboState, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
	   					.addGap(18)
	   					.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
	   					.addGap(18)
	   					.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   						.addGroup(gl_panAdresses.createSequentialGroup()
	   							.addGap(39)
	   							.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
	   						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
	   					.addGap(10)
	   					.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
	   						.addGroup(gl_panAdresses.createSequentialGroup()
	   							.addGap(49)
	   							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))))))
	   );
	   gl_panAdresses.setVerticalGroup(
	   	gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_panAdresses.createSequentialGroup()
	   			.addGap(11)
	   			.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   				.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblStreet))
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(1)
	   					.addComponent(lblNeighborhood))
	   				.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addGap(11)
	   			.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblCep))
	   				.addComponent(txtCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblCity))
	   				.addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addGap(14)
	   			.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   				.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(3)
	   					.addComponent(lblPhone))
	   				.addGroup(gl_panAdresses.createSequentialGroup()
	   					.addGap(1)
	   					.addGroup(gl_panAdresses.createParallelGroup(Alignment.LEADING)
	   						.addGroup(gl_panAdresses.createSequentialGroup()
	   							.addGap(2)
	   							.addComponent(lblEmail))
	   						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
	   );
	   panAdresses.setLayout(gl_panAdresses);
	   principalPanel.setLayout(gl_principalPanel);
	   clearFields();
	  
	}
	public void clearFields()
	{
	
		controller.queryAll(cboClients,true);
		
		tabPanClientData.setEnabledAt(PJ_TAB_INDEX,false);
		tabPanClientData.setEnabledAt(PF_TAB_INDEX,false);
		tabPanClientData.setEnabledAt(ADRESSES_TAB_INDEX,false);
		for(int i=0;i<tabPanClientData.getTabCount();i++)
			ClearFrame.clear((Container)tabPanClientData.getComponentAt(i));
		
		controller.fillStateAndCity(cboState, cboCity);
		
		
	}
	public void getClientData()
	{
		SimpleDateFormat spdt =new SimpleDateFormat("dd/MM/yyyy");
		if(!txtName.getText().isEmpty())
		{
			selectedClient.setName(txtName.getText());
			
			if(txtBirthDate.getValue()!=null)
			{
				java.util.Date birth = (java.util.Date) txtBirthDate.getValue();	
				java.sql.Date birthDate =  new java.sql.Date(birth.getTime());
				selectedClient.setBirthDate(birthDate);
			}
			selectedClient.setRg(txtRG.getText());
			selectedClient.setCpf(txtCpf.getText());
			if(cboSex.getSelectedItem()!=null)
				selectedClient.setSex(String.valueOf(cboSex.getSelectedItem().toString().charAt(0)));
		}
		else
		{
			selectedClient.setCompanyNAme(txtCompanyName.getText());
			selectedClient.setCnpj(txtCnpj.getText());
			selectedClient.setStateInscrition(txtStateInscription.getText());
			selectedClient.setContactName(txtContactName.getText());
		}
		
		selectedClient.setStreet(txtStreet.getText());
		selectedClient.setNeighborhood(txtNeighborhood.getText());
		String formattedCep = txtCep.getText().replaceAll("\\D","");
		selectedClient.setCep(formattedCep);
		selectedClient.setState((State)cboState.getSelectedItem());
		selectedClient.setCity((City)cboCity.getSelectedItem());
		String formattedPhone = txtPhone.getText().replaceAll("\\D","");
		selectedClient.setPhone(formattedPhone);
		selectedClient.setEmail(txtEmail.getText());
	}
	public void setClientData(Client client)
	{
		
		clearFields();
		
		if(isPF(client))
		{
			txtName.setText(client.getName());
			txtBirthDate.setValue(client.getBirthDate());
			txtRG.setText(client.getRg());
			txtCpf.setText(client.getCpf());
			if(client.getSex()!=null)
				cboSex.setSelectedIndex(client.getSex().equals("M")? 0 : 1);
			tabPanClientData.setSelectedComponent(panPF);
			tabPanClientData.setEnabledAt(PJ_TAB_INDEX,false);
			tabPanClientData.setEnabledAt(PF_TAB_INDEX,true);
			tabPanClientData.setEnabledAt(ADRESSES_TAB_INDEX,true);
		}
		else
		{
			txtCompanyName.setText(client.getCompanyNAme());
			txtCnpj.setText(client.getCnpj());
			txtStateInscription.setText(client.getStateInscrition());
			txtContactName.setText(client.getContactName());
			tabPanClientData.setSelectedComponent(panPJ);
			tabPanClientData.setEnabledAt(PJ_TAB_INDEX,true);
			tabPanClientData.setEnabledAt(PF_TAB_INDEX,false);
			tabPanClientData.setEnabledAt(ADRESSES_TAB_INDEX,true);
		}	
		txtStreet.setText(client.getStreet());
		txtNeighborhood.setText(client.getNeighborhood());
		txtCep.setText(client.getCep());
		txtPhone.setText(client.getPhone());
		txtEmail.setText(client.getEmail());
		
		City city = controller.getCity(client.getCity().getId());
		State state = controller.getState(client.getState().getId());
		
		cboState.setSelectedItem(state);
		cboCity.setSelectedItem(city);
		
	}
	public boolean isPF(Client client)
	{
		return client.getName() != null &&  !client.getName().isEmpty();
	}

	ActionListener actionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			  if(e.getSource().equals(cboClients))
			  {
				  if(cboClients.getSelectedItem()!=null)
				  {
			    	selectedClient = (Client)cboClients.getSelectedItem();
				    setClientData(selectedClient);
				  }
			  }
			  else
			  if(e.getSource().equals(btnDelete))
			  {
				 if( ShowMessage.questionMessage(null, "Atualização", 
						  "Deseja realmente apagar o cliente ?") == JOptionPane.YES_OPTION)
				 {
					 controller.deleteClient(selectedClient);
					 clearFields();
					 String title = "Atualização/Remoção";
					 String message = "Ação concluida com sucesso!";
					 ShowMessage.successMessage(null,title, message);
				 }
			  }
			  else
			  if(e.getSource().equals(btnUpdate))
			  {
				  if( ShowMessage.questionMessage(null, "Atualização", 
							  "Deseja realmente atualizar o cliente ?") == JOptionPane.YES_OPTION)
					 {
						 getClientData();
					  	 controller.updateClient(selectedClient,isPF(selectedClient));
						 clearFields();
						 String title = "Atualização/Remoção";
						 String message = "Ação concluida com sucesso!";
						 ShowMessage.successMessage(null,title, message);
					 }
			  }
		}
	}; 
		

	public void setListeners()
	{
		cboClients.addActionListener(actionListener);
		btnDelete.addActionListener(actionListener);
		btnUpdate.addActionListener(actionListener);
	}
}
