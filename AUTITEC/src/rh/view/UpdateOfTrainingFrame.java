package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import rh.controller.UpdateOfTrainingController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class UpdateOfTrainingFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087073951163459364L;
	private JPanel principalPanel;
	
	private JTextField txtTitle;
	private JTextField txtDuration;
	private DateField txtDate;
	private JTextField txtPlace;
	private JTextField txtMotive;
	
	private JTable table;
	
	private JLabel lblTraining;
	private JLabel lblTitle;
	private JLabel lblObjetive;
	private JLabel lblPeriod;
	private JLabel lblEventType;
	private JLabel lblDuration;
	private JLabel lblDate;
	private JLabel lblPlace;
	private JLabel lblMotive;
	private JLabel lblEmployee;

	private JRadioButton rdbtnAplicative;
	private JRadioButton rdbtnInformative;
	
	private JComboBox<Training> cboTraining;
	private JComboBox<String> cboEventType;
	private JComboBox<String> cboPeriod;
	private JComboBox<Employee> cboEmployee;

	private JPanel bottonPanel;

	private JButton btnAdd;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private UpdateOfTrainingController controller;
	private JButton btnDelete;

	public UpdateOfTrainingFrame() {
		
		controller = new UpdateOfTrainingController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Atualização e Remoção de fornecedores");
		setBounds(100, 100, 458, 530);
		setPreferredSize(new Dimension(458, 530));
		setMinimumSize(new Dimension(458, 530));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblTraining = new JLabel("Treinamento");
		
		cboTraining = new JComboBox<Training>();
		controller.fillTraining(cboTraining);
		new ComboBoxAutoCompletion(cboTraining);
		cboTraining.setSelectedIndex(-1);
		
		lblTitle = new JLabel("Título");
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		
		lblObjetive = new JLabel("Objetivo:");
		
		rdbtnAplicative = new JRadioButton("Aplicação");
		
		rdbtnInformative = new JRadioButton("Informação");
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnAplicative);
		btnGroup.add(rdbtnInformative);
		
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
		
		lblDate = new JLabel("Data");
		
		txtDate = CalendarFactory.createDateField();
		
		lblPlace = new JLabel("Local");
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		
		lblMotive = new JLabel("Motivo");
		
		txtMotive = new JTextField();
		txtMotive.setColumns(10);
		
		lblEmployee = new JLabel("Funcionário");
		
		cboEmployee = new JComboBox<Employee>();
		controller.fillEmployees(cboEmployee);
		new ComboBoxAutoCompletion(cboEmployee);
		cboEmployee.setSelectedIndex(-1);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(UpdateOfTrainingFrame.class.getResource("/resources/plus.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTraining)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboTraining, 0, 358, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblObjetive)
							.addGap(18)
							.addComponent(rdbtnAplicative)
							.addGap(18)
							.addComponent(rdbtnInformative))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEmployee, 0, 248, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAdd))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEventType)
								.addComponent(lblPeriod)
								.addComponent(lblDuration)
								.addComponent(lblPlace)
								.addComponent(lblMotive))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtMotive, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
									.addComponent(txtPlace, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
										.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblDate)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
									.addComponent(cboPeriod, Alignment.TRAILING, 0, 346, Short.MAX_VALUE)
									.addComponent(cboEventType, 0, 346, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTraining)
						.addComponent(cboTraining, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObjetive)
						.addComponent(rdbtnAplicative)
						.addComponent(rdbtnInformative))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEventType)
						.addComponent(cboEventType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeriod)
						.addComponent(cboPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuration)
						.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlace)
						.addComponent(txtPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMotive)
						.addComponent(txtMotive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		String[] header = new String[] {"FUNCIONÁRIO", "CARGO"};
		table.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 1473752805893999196L;
            
            boolean[] columnEditables = new boolean[] { false, false };

			@Override
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
		btnConfirm.setIcon(new ImageIcon(UpdateOfTrainingFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(UpdateOfTrainingFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnDelete = new JButton("excluir");
		btnDelete.setIcon(new ImageIcon(UpdateOfTrainingFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnDelete);
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(UpdateOfTrainingFrame.class.getResource("/resources/ClearFrame.png")));
		
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
				if(e.getSource().equals(btnAdd))addEmployee();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clear();
				else if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnDelete))delete();
			}
		};
		btnAdd.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnDelete.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboTraining))fillFields();
			}
		};
		
		cboTraining.addActionListener(cboListener);
		
		KeyListener tableKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(table)) {
					if(e.getKeyCode() == KeyEvent.VK_DELETE)removeRow();
				}
			}
		};
		table.addKeyListener(tableKeyListener);
	}
	
	
	public void delete() {
		int i = ShowMessage.questionMessage(this, "Excluir", "Deseja realmente excluir esse treinamento?");
		if(i == JOptionPane.NO_OPTION)return;
		if(cboTraining.getSelectedIndex() == -1)return;
		int a = ShowMessage.questionMessage(this, "Motivo", "Responda sim caso o motivo da exclusão tenha sido erro ao criar, e não caso foi cancelado!");
		if(a == JOptionPane.YES_OPTION)controller.delete((Training) cboTraining.getSelectedItem());
		else {
			String delMotive = JOptionPane.showInputDialog(this, "Motivo", "Qual o motivo do cancelamento ?");
			Training training = (Training) cboTraining.getSelectedItem();
			controller.cancel(training, delMotive);
		}
		
	}
	/*
	 * Deleta uma linha desejada da tabela de funcionários participantes do treinamento.
	 */
	public void removeRow() {
		if(table.getSelectedRow() == -1)return;
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		int i = table.getSelectedRow();
		tbl.removeRow(i);
	}
	
	/*
	 * Adiciona um funcionário á tabelas de funcionários que irão participar do treinamento.
	 */
	private void addEmployee(){
		if(cboEmployee.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione um empregados para adicionar");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String employeeFunction = e.getJob().getCbo().getTitle();
		tbl.addRow(new Object[]{e, employeeFunction});
	}
	
	/*
	 * Função responsável por retirar as informações nos campos presentes no formulário.
	 */
	private void clear(){
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void confirm(){
		int i = ShowMessage.questionMessage(this, "Atualização", "Deseja realmente atualizar esse treinamento ?");
		if(i == JOptionPane.NO_OPTION)return;
		boolean isOk = verifyFields();
		if(!isOk)return;
		Training training = (Training) cboTraining.getSelectedItem();
		training.setDate((Date) txtDate.getValue());
		training.setDuration(txtDuration.getText());
		String aplication;
		
		if(rdbtnAplicative.isSelected())aplication = "Aplicação";
		else aplication = "Informativo";
		
		training.setEventType((String) cboEventType.getSelectedItem());
		training.setMotive(txtMotive.getText());
		training.setObjective(aplication);
		training.setPeriod((String) cboPeriod.getSelectedItem());
		training.setPlace(txtPlace.getText());
		training.setTitle(txtTitle.getText());
		List<Employee> employeeList = new ArrayList<Employee>();
		int rowCount = table.getRowCount();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for(int counter = 0; counter < rowCount; counter++) {
			Employee e = (Employee) tbl.getValueAt(counter, 0);
			employeeList.add(e);
		}
		training.setEmployeeList(employeeList);
		controller.update(training);
		ShowMessage.successMessage(this, "Atualização", "Treinamento atualizado com sucesso!");
		ClearFrame.clear(this);
		ClearFrame.clearTable(table);
		controller.fillEmployees(cboEmployee);
	}
	
	/*
	 * Verifica se os campos nescessários para atualizar os treinamentos estão devidamente preenchidos.
	 */
	private boolean verifyFields() {
	    String title = "Erro de atualização";
	    if(cboTraining.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, title, "Selecione o Treinamento que deseja atualizar!");
	    	return false;
	    }

	    if(cboEventType.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, title, "Selecione o tipo do evento");
	    	return false;
	    }
		
	    if(cboPeriod.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, title, "Selecione o periodo de realização do treinamento");
			return false;
		}
	    Date date = (Date) txtDate.getValue();
	    if(txtDate.getValue() ==  null || date.compareTo(new Date(System.currentTimeMillis())) < 0) {
	    	ShowMessage.errorMessage(this, title, "A data do treinamento dever ser maior que a data atual !");
	    	return false;
	    }
	    if(txtDuration.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, title, "Insira a duração do treinamento");
	    	return false;
	    }
	    if(txtMotive.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, title, "Insira o motivo de realização desse treinamento!");
	    	return false;
	    }
	    if(txtPlace.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, title, "Insira o local que será realizado esse treinamento!");
	    	return false;
	    }
	    if(txtTitle.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, title, "Insira o título desse treinamento!");
	    	return false;
	    }if(!rdbtnAplicative.isSelected() && !rdbtnInformative.isSelected()){
	    	ShowMessage.errorMessage(this, title, "Selecione se o treinamento é aplicativo ou informativo !");
	    	return false;
	    }
		return true;
    }

	/*
	 * Popula os campos presentes no formulários com as informações referentes ao treinamento selecionado.
	 */
	private void fillFields(){
		ClearFrame.clearTable(table);
		if(cboTraining.getSelectedIndex() == -1)return;
		Training t = (Training) cboTraining.getSelectedItem();
		txtTitle.setText(t.getTitle());
		txtDate.setValue(t.getDate());
		txtMotive.setText(t.getMotive());
		txtPlace.setText(t.getPlace());
		txtDuration.setText(t.getDuration());
		cboEventType.setSelectedItem(t.getEventType());
		cboPeriod.setSelectedItem(t.getPeriod());
		txtPlace.setText(t.getPlace());
		String objective = t.getObjective();
		if(objective.equalsIgnoreCase("Aplicação")){
			rdbtnAplicative.setSelected(true);
			rdbtnInformative.setSelected(false);
		}else if(objective.equalsIgnoreCase("Informativo")){
			rdbtnAplicative.setSelected(false);
			rdbtnInformative.setSelected(false);
		}
		fillEmployeeTable(t);
	}
	
	/*
	 * Popula a lista de funcionários que irão participar do treinamento.
	 */
	private void fillEmployeeTable(Training t){
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		List<Employee> employeeList = t.getEmployeeList();
		if(employeeList == null)return;
		employeeList.forEach(e -> tbl.addRow(new Object[]{e, e.getJob().getCbo().getTitle()}));
	}
}
