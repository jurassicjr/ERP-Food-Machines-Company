package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

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

	private JButton btnAdd;
	private JPanel bottonPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private UpdateOfTrainingController controller;

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
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTraining)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboTraining, 0, 330, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblObjetive)
							.addGap(18)
							.addComponent(rdbtnAplicative)
							.addGap(18)
							.addComponent(rdbtnInformative))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEventType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEventType, 0, 316, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPeriod)
								.addComponent(lblDuration))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(txtDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
								.addComponent(cboPeriod, 0, 361, Short.MAX_VALUE)))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblPlace)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPlace, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMotive)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtMotive, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEmployee, 0, 219, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnAdd)))
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		String[] header = new String[] {"FUNCIONÁRIO", "CARGO"};
		table.setModel(new DefaultTableModel(null, header));
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
			}
		};
		btnAdd.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboTraining))fillFields();
			}
		};
		
		cboTraining.addActionListener(cboListener);
	}
	
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
	
	private void clear(){
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void confirm(){
		
	}
	
	private void fillFields(){
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
	
	private void fillEmployeeTable(Training t){
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		table.getModel().setValueAt("ok", 1, 1);
		List<Employee> employeeList = t.getEmployeeList();
		if(employeeList == null)return;
		employeeList.forEach(e -> tbl.addRow(new Object[]{e, e.getJob().getCbo().getTitle()}));
	}
}
