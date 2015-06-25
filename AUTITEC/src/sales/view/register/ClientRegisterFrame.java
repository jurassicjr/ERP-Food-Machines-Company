package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.City;
import model.Client;
import model.State;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ClientRegisterController;
import sales.view.ImportClientFrame;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ClientRegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908360580088674556L;
	private JPanel principalPanel;
	private JPanel bottonPanel;

	private JTextField txtName;

	private JLabel lblName;

	private JButton btnConfirmar;
	private JButton btnCancel;

	private ClientRegisterController controller;
	
	private JFrame frame;
	
	private JButton btnImport;
	
	private DateField txtBirthDate;
	
	private JLabel lblRg;
	private JLabel lblCpf;
	
	private JTextField txtRG;
	private JTextField txtCPF;
	private JTextField txtEmail;
	private UpperTextField txtCompanyName;
	private JTextField txtCnpj;
	private JTextField txtIE;
	private UpperTextField txtContato;
	private JFormattedTextField txtCEP;
	private UpperTextField txtStreet;
	private UpperTextField txtNeighborhood ;
	private JFormattedTextField txtPhone;
	
	
	private JComboBox<String> cboSex;
	private JComboBox<State> cboState;
	private JComboBox<City> cboCity;

	private JTabbedPane tabbedPane;
	
	public ClientRegisterFrame() {
		frame = this;
		controller = new ClientRegisterController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(frame);
		setTitle("REGISTRO DE CLIENTES");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 811, 270);
		setMinimumSize(new Dimension(506, 270));
		setPreferredSize(new Dimension(811, 270));
		initializePrincipal();
		controller.fillStateAndCity(cboState, cboCity);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addGap(7)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 741, GroupLayout.PREFERRED_SIZE))
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
		);
		principalPanel.setLayout(gl_principalPanel);
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
										
										JPanel panel = new JPanel();
										tabbedPane.addTab("Pessoa Jurídica", null, panel, null);
										panel.setLayout(null);
										
										JLabel lblCompanyName = new JLabel("Razão Social");
										lblCompanyName.setBounds(10, 11, 141, 14);
										panel.add(lblCompanyName);
										
										txtCompanyName = new UpperTextField();
										txtCompanyName.setBounds(10, 31, 312, 20);
										panel.add(txtCompanyName);
										txtCompanyName.setColumns(10);
										
										JLabel lblCnpj = new JLabel("CNPJ");
										lblCnpj.setBounds(332, 11, 46, 14);
										panel.add(lblCnpj);
										
										txtCnpj = new JTextField();
										txtCnpj.setBounds(332, 31, 163, 20);
										panel.add(txtCnpj);
										txtCnpj.setColumns(10);
										
										JLabel lblIe = new JLabel("IE");
										lblIe.setBounds(505, 11, 40, 14);
										panel.add(lblIe);
										
										txtIE = new JTextField();
										txtIE.setBounds(505, 31, 150, 20);
										panel.add(txtIE);
										txtIE.setColumns(10);
										
										JLabel lblNewLabel = new JLabel("Nome do contato");
										lblNewLabel.setBounds(10, 62, 187, 14);
										panel.add(lblNewLabel);
										
										txtContato = new UpperTextField();
										txtContato.setBounds(10, 85, 283, 20);
										panel.add(txtContato);
										txtContato.setColumns(10);
										
										JPanel panelPf = new JPanel();
										tabbedPane.addTab("Pessoa física", null, panelPf, null);
										panelPf.setLayout(null);
										
												txtName = new UpperTextField();
												txtName.setBounds(61, 8, 320, 20);
												panelPf.add(txtName);
												txtName.setColumns(10);
												
														lblName = new JLabel("Nome");
														lblName.setBounds(10, 11, 58, 14);
														panelPf.add(lblName);
														
														txtBirthDate = CalendarFactory.createDateField();
														txtBirthDate.setBounds(517, 8, 97, 20);
														panelPf.add(txtBirthDate);
									
														
														JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
														lblDataDeNascimento.setBounds(391, 11, 125, 14);
														panelPf.add(lblDataDeNascimento);
														
														lblRg = new JLabel("RG");
														lblRg.setBounds(10, 53, 33, 14);
														panelPf.add(lblRg);
														
														txtRG = new JTextField();
														txtRG.setBounds(47, 50, 133, 20);
														panelPf.add(txtRG);
														txtRG.setColumns(10);
														
														lblCpf = new JLabel("CPF");
														lblCpf.setBounds(185, 53, 38, 14);
														panelPf.add(lblCpf);
														
														txtCPF = new JTextField();
														txtCPF.setBounds(215, 50, 166, 20);
														panelPf.add(txtCPF);
														txtCPF.setColumns(10);
														
														cboSex = new JComboBox<String>();
														cboSex.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Feminino"}));
														cboSex.setBounds(428, 50, 186, 20);
														panelPf.add(cboSex);
														
														JLabel lblSexo = new JLabel("Sexo");
														lblSexo.setBounds(391, 53, 38, 14);
														panelPf.add(lblSexo);
										
										JPanel panel_1 = new JPanel();
										tabbedPane.addTab("Correspondência", null, panel_1, null);
										panel_1.setLayout(null);
										
										cboState = new JComboBox<State>();
										cboState.setBounds(219, 42, 128, 20);
										panel_1.add(cboState);
										
										JLabel label = new JLabel("Estado");
										label.setBounds(156, 42, 73, 20);
										panel_1.add(label);
										
										JLabel lblTe = new JLabel("Tel.");
										lblTe.setBounds(10, 79, 42, 14);
										panel_1.add(lblTe);
										
										txtPhone = new JFormattedTextField();
										txtPhone.setColumns(10);
										txtPhone.setBounds(49, 76, 191, 20);
										panel_1.add(txtPhone);
										
										txtStreet = new UpperTextField();
										txtStreet.setColumns(10);
										txtStreet.setBounds(49, 11, 393, 20);
										panel_1.add(txtStreet);
										
										JLabel label_2 = new JLabel("Rua");
										label_2.setBounds(10, 14, 42, 14);
										panel_1.add(label_2);
										
										txtCEP = new JFormattedTextField((Object) null);
										txtCEP.setColumns(10);
										txtCEP.setBounds(49, 42, 97, 20);
										panel_1.add(txtCEP);
										
										JLabel label_3 = new JLabel("C.E.P");
										label_3.setBounds(10, 45, 42, 14);
										panel_1.add(label_3);
										
										JLabel label_4 = new JLabel("Cidade");
										label_4.setBounds(365, 45, 51, 14);
										panel_1.add(label_4);
										
										cboCity = new JComboBox<City>();
										cboCity.setBounds(434, 42, 230, 20);
										panel_1.add(cboCity);
										
										txtEmail = new JTextField();
										txtEmail.setColumns(10);
										txtEmail.setBounds(299, 77, 365, 20);
										panel_1.add(txtEmail);
										
										JLabel label_5 = new JLabel("email");
										label_5.setBounds(250, 79, 97, 14);
										panel_1.add(label_5);
										
										txtNeighborhood = new UpperTextField();
										txtNeighborhood.setColumns(10);
										txtNeighborhood.setBounds(510, 11, 154, 20);
										panel_1.add(txtNeighborhood);
										
										JLabel label_6 = new JLabel("Bairro");
										label_6.setBounds(450, 12, 56, 14);
										panel_1.add(label_6);
		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnImport = new JButton("Importar");
		btnImport.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/Import.png")));
		bottonPanel.add(btnImport);

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancel);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		
		MouseListener mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(tabbedPane))
				{
					int selectedTab = tabbedPane.getSelectedIndex() ;
					if(selectedTab == 0)
					{
						clearPFData();
						txtCompanyName.grabFocus();
					}
					else
					if(selectedTab == 1)
					{
						clearPJData();
						txtName.grabFocus();
					}
					
				}
				
			}
		};
		
		KeyListener OnlyDigitsKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(txtCPF))
				{
					if(!Character.isDigit(e.getKeyChar()) || txtCPF.getText().length() >=11)
						e.setKeyChar('\0');
				}
				else
				if(e.getSource().equals(txtCnpj))
				{
					if(!Character.isDigit(e.getKeyChar()))
						e.setKeyChar('\0');
				}
				if(e.getSource().equals(txtIE))
				{
					if(!Character.isDigit(e.getKeyChar()))
						e.setKeyChar('\0');
				}
			}
				
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
				
		};
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource().equals(btnCancel))
				{
					controller.closeFrame(frame);
				}
				else if (e.getSource().equals(btnConfirmar)) {
					if(isValidaContactData())
					{
						if(ShowMessage.questionMessage(frame, "REGISTRO", "Deseja mesmo registrar esse cliente ?")==
								JOptionPane.YES_OPTION)
						{
							doCliente();
							ShowMessage.successMessage(frame, "REGISTRO", "registo de Cliente realizado com sucesso!");
							ClearFrame.clear(frame);
						}
					}
					else
						ShowMessage.errorMessage(null,"Dados inválidos", "Preencha os dados de correspondência");
						
				}
					
			  else if (e.getSource().equals(btnImport)) {
					EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							ImportClientFrame cFrame = new ImportClientFrame();
							cFrame.pack();
							cFrame.setVisible(true);
							cFrame.setLocationRelativeTo(frame);
						}
					});
				}
				
			}
		};
		
		btnImport.addActionListener(buttonListener);
		btnConfirmar.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		tabbedPane.addMouseListener(mouseListener);
		txtCPF.addKeyListener(OnlyDigitsKeyListener);
		txtCnpj.addKeyListener(OnlyDigitsKeyListener);
		txtIE.addKeyListener(OnlyDigitsKeyListener);
	}
	private boolean isValidaContactData()
	{
		return !txtStreet.getText().isEmpty() &&
			   !txtCEP.getText().isEmpty() &&	
			   !txtNeighborhood.getText().isEmpty() &&
			   !txtPhone.getText().isEmpty() && 
			   !txtEmail.getText().isEmpty() &&
			   cboCity.getSelectedItem()!=null &&
			   cboState.getSelectedItem()!=null;	
	}
	private void clearPJData()
	{	
		txtCompanyName.setText("");
		txtContato.setText("");
		txtCnpj.setText("");
		txtIE.setText("");
	}
	private void clearPFData()
	{
		txtName.setText("");
		txtRG.setText("");
		txtCPF.setText("");
		cboSex.setSelectedItem(null);
	}
	private void doCliente() {
		
	
		String sex = "";
		Date birthDate=null ;
		
		Client client = new Client();
		String name = txtName.getText();
		String street = txtStreet.getText();
		String neighborhood = txtNeighborhood.getText();
		String cep = txtCEP.getText().replaceAll("\\.|-", "");
		String phone = txtPhone.getText().replaceAll("\\(|\\)|-", "");
		State state = (State) cboState.getSelectedItem();
		City city = (City) cboCity.getSelectedItem();
		String email = txtEmail.getText();
		String cpf = txtCPF.getText();
		String cnpj = txtCnpj.getText();
		String razao = txtCompanyName.getText();
		String ie = txtIE.getText();
		if(cboSex.getSelectedItem()!=null)
			sex = String.valueOf(cboSex.getSelectedItem().toString().charAt(0));
		String contact =  txtContato.getText() ;
		String rg = txtRG.getText();
		if(txtBirthDate.getValue()!=null)
		{
			java.util.Date birth = (java.util.Date) txtBirthDate.getValue();
		    birthDate =  new java.sql.Date(birth.getTime());
		}
		
		client.setName(name);
		client.setStreet(street);
		client.setNeighborhood(neighborhood);
		client.setCep(cep);
		client.setCity(city);
		client.setState(state);
		client.setPhone(phone);
		client.setEmail(email);
		client.setCpf(cpf);
		client.setRg(rg);
		client.setCnpj(cnpj);
		client.setCompanyNAme(razao);
		client.setIe(ie);
		client.setSex(sex);
		client.setContactName(contact);
		client.setBirthDate(birthDate);
		
		

		

		controller.doClienteRegistration(client);
	}
}
