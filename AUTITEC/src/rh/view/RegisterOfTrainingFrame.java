package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Training;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.RegisterOfTrainingController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.ShowMessage;

public class RegisterOfTrainingFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087487712693577771L;
	private JPanel principalPanel;
	private JTextField txtTitle;
	private JLabel lblObjective;
	private JTextField txtDuration;
	private JTextField txtPlace;
	private JTextField txtTrainingMotive;
	private JTable table;
	private DateField txtDate;
	private JComboBox<String> cboEventType;
	private JLabel lblEventType;
	private ButtonGroup bg;
	private JRadioButton rdbtnAplication;
	private JRadioButton rdbtnInfomative;
	private JLabel lblTitle;
	private JLabel lblPeriod;
	private JComboBox<String> cboPeriod;
	private JLabel lblDuration;
	private JLabel lblPlace;
	private JLabel lblTrainingMotive;
	private JLabel lblEmployee;
	private JButton btnAdd;
	private JLabel lblDate;
	private JScrollPane scrollPane;
	private JPanel bottonPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private RegisterOfTrainingController controller;
	private JComboBox<Employee> cboEmployee;

	
	public RegisterOfTrainingFrame() {
		controller = new RegisterOfTrainingController(this);
		initialize();
		setListeners();
	}


	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Registro de Treinamento");
		setBounds(100, 100, 458, 530);
		setMinimumSize(new Dimension(458, 530));
		setPreferredSize(new Dimension(458, 530));
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}


	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblTitle = new JLabel("Titulo");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		lblObjective = new JLabel("Objetivo:");
		
		rdbtnInfomative = new JRadioButton("Informativo");
		
		rdbtnAplication = new JRadioButton("Aplicação");
		
		bg = new ButtonGroup();
		bg.add(rdbtnAplication);
		bg.add(rdbtnInfomative);
		
		lblEventType = new JLabel("Tipo do Evento");
		
		cboEventType = new JComboBox<String>();
		cboEventType.addItem("Interno");
		cboEventType.addItem("Externo");
		cboEventType.addItem("InCompany");
		new ComboBoxAutoCompletion(cboEventType);
		cboEventType.setSelectedIndex(-1);
		
		
		lblPeriod = new JLabel("Periodo");
		
		cboPeriod = new JComboBox<String>();
		cboPeriod.addItem("Matutino");
		cboPeriod.addItem("Vespertino");
		cboPeriod.addItem("Integral");
		new ComboBoxAutoCompletion(cboPeriod);
		cboPeriod.setSelectedIndex(-1);
		
		lblDuration = new JLabel("Duração");
		
		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		
		lblPlace = new JLabel("Local");
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		
		lblTrainingMotive = new JLabel("Motivo do treinamento");
		
		txtTrainingMotive = new JTextField();
		txtTrainingMotive.setColumns(10);
		
		lblEmployee = new JLabel("Funcionário");
		
		cboEmployee = new JComboBox<Employee>();
		new ComboBoxAutoCompletion(cboEmployee);
		controller.fillEmployees(cboEmployee);
		cboEmployee.setSelectedIndex(-1);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/plus.png")));
		
		scrollPane = new JScrollPane();
		
		lblDate = new JLabel("Data");
		
		txtDate = CalendarFactory.createDateField();
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblObjective)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnInfomative)
							.addGap(18)
							.addComponent(rdbtnAplication))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEventType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEventType, 0, 258, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblPeriod)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboPeriod, 0, 307, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblDuration)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDate)
							.addGap(4)
							.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblPlace)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPlace, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTrainingMotive)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTrainingMotive, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObjective)
						.addComponent(rdbtnInfomative)
						.addComponent(rdbtnAplication))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEventType)
						.addComponent(cboEventType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeriod)
						.addComponent(cboPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuration)
						.addComponent(lblDate)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlace)
						.addComponent(txtPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTrainingMotive)
						.addComponent(txtTrainingMotive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		String[] Header = new String[]{"Nome", "função"};
		table.setModel(new DefaultTableModel(null, Header){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1355463789545401843L;
			
			boolean[] columnEditables = new boolean[] {
					false, false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		
		});
		
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);
		
		initializeBotton();
		
	}


	private void initializeBotton() {
		bottonPanel = new JPanel();
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterOfTrainingFrame.class.getResource("/resources/ClearFrame.png")));
		
		bottonPanel.add(btnClear);
		bottonPanel.add(btnCancel);
		bottonPanel.add(btnConfirm);
		
	}
	
	private void setListeners(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnAdd))add();
				else if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnClear))clear();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		
		btnCancel.addActionListener(buttonListener);
		btnAdd.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		KeyListener tableKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
														
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(table))removeTableItem(e);
			}
		};
		
		table.addKeyListener(tableKeyListener);
	}
	
	private void add(){
		if(cboEmployee.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione um empregados para adicionar");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String employeeFunction = e.getJob().getCbo().getTitle();
		tbl.addRow(new Object[]{e, employeeFunction});
	}
	
	private void register(){
		int i = ShowMessage.questionMessage(this, "Registro", "Deseja relamente registrar esse treinamento ?");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtDate.getValue().equals(null)){
			ShowMessage.errorMessage(this, "Erro", "Selecione a data do treinamento");
			return;
		}
		if(txtDuration.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira a duração do evento!");
			return;
		}
		if(txtPlace.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o local do evento");
			return;
		}
		if(txtTitle.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o nome do treinamento!");
			return;
		}
		if(txtTrainingMotive.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Insira o motivo do treinamento!");
			return;
		}
		if(cboEventType.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o tipo do treinamento!");
			return;
		}
		if(cboPeriod.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o periodo do treinamento");
			return;
		}
		if(table.getRowCount() == 0){
			ShowMessage.errorMessage(this, "Erro", "Adicione ao menos um funcionário para participar");
			return;
		}
		
		Training t = new Training();
		Date date = (Date) txtDate.getValue();
		String duration = txtDuration.getText();
		String eventType = (String) cboEventType.getSelectedItem();
		String motive = txtTrainingMotive.getText();
		String objective = null;
		if(rdbtnAplication.isSelected()){
			objective = "Aplicação";
		}else if(rdbtnInfomative.isSelected()){
			objective = "Informativo";
		}
		String period = (String) cboPeriod.getSelectedItem();
		String title = txtTitle.getText();
		List<Employee> list = fillList();
		
		t.setDate(date);
		t.setDuration(duration);
		t.setEventType(eventType);
		t.setMotive(motive);
		t.setObjective(objective);
		t.setPeriod(period);
		t.setTitle(title);
		t.setEmployeeList(list);
		t.setPlace(txtPlace.getText());
		controller.register(t);
		ShowMessage.successMessage(this, "Sucesso", "Treinamento registrado com êxito!");
		ClearFrame.clear(this);
	}
	
	private void clear(){
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar todos os campos ?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
	
	private void removeTableItem(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			int i = table.getSelectedRow();
			if(i == -1)return;
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			tbl.removeRow(i);
		}
	}
	
	private List<Employee> fillList(){
		int rowCount = table.getRowCount();
		List<Employee> list = new ArrayList<Employee>();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for( int i = 0; i < rowCount; i++){
			Employee e = (Employee) tbl.getValueAt(i, 0);
			list.add(e);
		}
		return list;
	}
}
