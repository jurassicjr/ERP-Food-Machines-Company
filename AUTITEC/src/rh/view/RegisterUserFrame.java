package rh.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Employee;
import rh.controller.RegisterUserFrameController;
import userInterface.components.ComboBoxAutoCompletion;
import util.Icon;

/**
 * Registra um usuário para acessar ao sistema criando senha e nível de acesso
 */
public class RegisterUserFrame extends JFrame {

	private static final long serialVersionUID = 2099832042494016395L;
	
	private JPanel contentPane;
	
	private JTextField txCPF;
	private JTextField txJob;
	private JPasswordField txPassword;
	
	private JComboBox<Employee> cbEmployee;
	private JComboBox<String> cbAccessLevel;
	
	private JButton btnCancel;
	private JButton btnRegister;
	
	private RegisterUserFrameController controller;
	
	/**
	 * Cria o frame para o registro de usuários do sistema
	 */
	public RegisterUserFrame() {
				
		controller = new RegisterUserFrameController(this);
		
		initialize();
		setListeners();
		
	}

	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 220);
		setTitle("Registro de Usuário");
		Icon.setIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblEmployee = new JLabel("Funcionário");
		cbEmployee = new JComboBox<Employee>();
		cbEmployee.setModel(new DefaultComboBoxModel<Employee>());
		
		JLabel lbljob = new JLabel("Cargo");
		txJob = new JTextField();
		txJob.setEditable(false);
				
		JLabel lblCpf = new JLabel("CPF");
		txCPF = new JTextField();
		txCPF.setEditable(false);
				
		JLabel lblAccessLevel = new JLabel("Nível de Acesso");
		cbAccessLevel = new JComboBox<String>();
		String accessLevels[] = new String[] {"Gerente", "Comercial", "Vendas", "Conferente de Produção", "Supervisor de Produção", "Financeiro", "Recursos Humanos"};
		cbAccessLevel.setModel(new DefaultComboBoxModel<String>(accessLevels));
		cbAccessLevel.setSelectedIndex(-1);
		
		JLabel lblPassword = new JLabel("Senha");
		txPassword = new JPasswordField();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblEmployee)
							.addGap(18)
							.addComponent(cbEmployee, 0, 308, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCpf)
							.addGap(18)
							.addComponent(txCPF, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lbljob)
							.addGap(18)
							.addComponent(txJob, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAccessLevel)
							.addGap(18)
							.addComponent(cbAccessLevel, 0, 231, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblPassword)
							.addGap(18)
							.addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(cbEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbljob)
						.addComponent(txJob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAccessLevel)
						.addComponent(cbAccessLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		panel.setLayout(layout);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		btnPanel.add(btnRegister);
		
		controller.setEmployees(cbEmployee);
		cbEmployee.setSelectedIndex(-1);
		new ComboBoxAutoCompletion(cbEmployee);
		
	}

	/**
	 * Define os eventos da interface gráfica
	 */
	private void setListeners() {
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnRegister)) register();
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnRegister.addActionListener(btnListener);
		
		ItemListener comboboxListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == 1) {
					
					if(e.getSource().equals(cbEmployee)) {
						
						Employee employee = (Employee) cbEmployee.getSelectedItem();
						
						controller.setCpf(employee, txCPF);
						txJob.setText(employee.getJob().getCbo().getTitle());
					}
					 
				}
			}
		};
			
		cbEmployee.addItemListener(comboboxListener);
			
	}
	
	/**
	 * Aciona o controlador para registro do usuário
	 */
	private void register() {
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
		
		Employee employee = (Employee) cbEmployee.getSelectedItem();
		controller.register(employee, cbAccessLevel.getSelectedIndex(), String.valueOf(txPassword.getPassword()));
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}
	
	
}
