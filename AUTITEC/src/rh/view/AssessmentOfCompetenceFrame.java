package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.AssessmentOfCompetence;
import model.CBO;
import model.Employee;
import model.FunctionDescription;
import model.Training;
import net.miginfocom.swing.MigLayout;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.AssessmentOfCompetenceController;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class AssessmentOfCompetenceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4375143092538685334L;
	private JTextField txtSector;
	private JTextField txtFunction;
	private JTextField txtScholarity;
	private JTextField txtExperience;
	private JTextField txtPoints;

	private DateField txtHireDate;

	private JTable trainingTable;

	private JComboBox<Employee> cboEmployee;

	private JLabel lblSector;
	private JLabel lblNome;
	private JLabel lblFunction;
	private JLabel lblHireDate;
	private JLabel lblScholarity;
	private JLabel lblExperience;
	private JLabel lblIsEnable;
	private JLabel lblPoints;
	
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	
	private JPanel subPanel;
	private JPanel principalPanel;
	private JPanel habilitiesPanel;
	private JPanel qualificationPanel;

	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;

	private AssessmentOfCompetenceController controller;
	
	private DateField txtAssessmentDate;

	private List<ButtonGroup> btnGroupList = new ArrayList<ButtonGroup>();
	
	private JLabel lblRequiredScholarity;
	private JLabel lblRequiredExperience;

	private JTextField txtRequiredScholarit;
	private JTextField txtRequiredExperience;
	
	private List<String> functionList = new ArrayList<String>();
	private ButtonGroup btnGroupIsEnable;
	private FunctionDescription funDes;

	public AssessmentOfCompetenceFrame() {
		controller = new AssessmentOfCompetenceController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Avaliação de Competência");
		setBounds(100, 100, 632, 656);
		setPreferredSize(new Dimension(632, 656));
		setMinimumSize(new Dimension(632, 656));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblNome = new JLabel("Nome");

		cboEmployee = new JComboBox<Employee>();
		controller.fillEmployee(cboEmployee);
		cboEmployee.setSelectedIndex(-1);
		
		lblSector = new JLabel("Departamento/Setor");

		txtSector = new JTextField();
		txtSector.setEditable(false);
		txtSector.setColumns(10);

		lblFunction = new JLabel("Cargo");

		txtFunction = new JTextField();
		txtFunction.setEditable(false);
		txtFunction.setColumns(10);

		lblHireDate = new JLabel("Data da Admissão");

		txtHireDate = CalendarFactory.createDateField();

		lblScholarity = new JLabel("Escolaridade Apresentada");

		txtScholarity = new JTextField();
		txtScholarity.setEditable(false);
		txtScholarity.setColumns(10);

		lblExperience = new JLabel("Experiência Apresentada");

		txtExperience = new JTextField();
		txtExperience.setColumns(10);

		JScrollPane scrollPaneQualification = new JScrollPane();
		scrollPaneQualification.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3),
		        "Qualifica\u00E7\u00F5es Profissionais Apresentadas", TitledBorder.LEADING, TitledBorder.TOP, null,
		        null));
		scrollPaneQualification.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollPane scrollPaneTrainer = new JScrollPane();
		scrollPaneTrainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollPane scrollPaneHabilities = new JScrollPane();
		scrollPaneHabilities.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3),
		        "Habilidades Pessoais Apresentadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblIsEnable = new JLabel("O colaborador está apto à trabalhar na empresa ?");

		rdbtnYes = new JRadioButton("Sim");
		rdbtnYes.setActionCommand("Yes");
		rdbtnNo = new JRadioButton("Não");
		rdbtnNo.setActionCommand("No");
		btnGroupIsEnable = new ButtonGroup();
		btnGroupIsEnable.add(rdbtnNo);
		btnGroupIsEnable.add(rdbtnYes);
		
		lblPoints = new JLabel("Se sim, qual a nota da avaliação");

		txtPoints = new JTextField();
		txtPoints.setColumns(10);
		
		JLabel lblAssessmentDate = new JLabel("Data da Avaliação");
		
		txtAssessmentDate = CalendarFactory.createDateField();
		
		lblRequiredScholarity = new JLabel("Escolaridade Requerida");
		
		txtRequiredScholarit = new JTextField();
		txtRequiredScholarit.setEditable(false);
		txtRequiredScholarit.setColumns(10);
		
		lblRequiredExperience = new JLabel("Experiência Requerida");
		
		txtRequiredExperience = new JTextField();
		txtRequiredExperience.setEditable(false);
		txtRequiredExperience.setColumns(10);
		
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblIsEnable)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnYes)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnNo))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblPoints)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAssessmentDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAssessmentDate, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPaneHabilities, Alignment.LEADING)
								.addComponent(scrollPaneTrainer, Alignment.LEADING)
								.addComponent(scrollPaneQualification, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
									.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblRequiredScholarity)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtRequiredScholarit))
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblScholarity)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtScholarity))
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblNome)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblFunction)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtFunction)))
									.addGap(18)
									.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblRequiredExperience)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtRequiredExperience))
										.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
											.addComponent(lblExperience)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtExperience))
										.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
											.addComponent(lblHireDate)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtHireDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_principalPanel.createSequentialGroup()
											.addComponent(lblSector)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtSector, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSector)
						.addComponent(txtSector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFunction)
						.addComponent(txtFunction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHireDate)
						.addComponent(txtHireDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScholarity)
						.addComponent(txtScholarity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExperience)
						.addComponent(txtExperience, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRequiredScholarity)
						.addComponent(txtRequiredScholarit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRequiredExperience)
						.addComponent(txtRequiredExperience, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPaneQualification, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneTrainer, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneHabilities, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIsEnable)
								.addComponent(rdbtnYes)
								.addComponent(rdbtnNo))
							.addGap(18)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoints)
								.addComponent(txtPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAssessmentDate)))
						.addComponent(txtAssessmentDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);

		habilitiesPanel = new JPanel();
		scrollPaneHabilities.setViewportView(habilitiesPanel);
		habilitiesPanel.setLayout(new MigLayout("wrap 3", "[] 100 [] 20 []"));

		trainingTable = new JTable();
		String[] header = new String[] {"Treinamentos realizados"};
		trainingTable.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 6243666037667322458L;
			
            boolean[] columnEditables = new boolean[] {
					false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneTrainer.setViewportView(trainingTable);

		qualificationPanel = new JPanel();
		scrollPaneQualification.setViewportView(qualificationPanel);
		qualificationPanel.setLayout(new MigLayout("wrap 3", "[] 100 [] 20 []"));

		principalPanel.setLayout(gl_principalPanel);

		intializeSub();
	}

	private void intializeSub() {
		subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(subPanel, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/ok.png")));
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/cancel.png")));
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/ClearFrame.png")));

		subPanel.add(btnClear);
		subPanel.add(btnCancel);
		subPanel.add(btnConfirm);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboEmployee))fillFields();
			}

		};
		cboEmployee.addActionListener(cboListener);
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		btnConfirm.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
	}
	
	private void register() {
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar a avaliação de competência com esses dados?");
		if(i == JOptionPane.NO_OPTION)return;
		if(!verifySelections()) return;
		if(txtExperience.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a experiência apresentada pelo colaborador");
			return;
		}
		if(btnGroupIsEnable.getSelection() == null) {
			ShowMessage.errorMessage(this, "Erro", "Selecione se o colaborar está apto ou não à trabalhar na empresa !");
			return;
		}
		if(txtPoints.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a nota do colaborador!");
			return;
		}
		if(cboEmployee.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione um colaborador!");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		String isE = btnGroupIsEnable.getSelection().getActionCommand();
		String point = txtPoints.getText();
		String experience = txtExperience.getText();
		boolean isEna;
		AssessmentOfCompetence assOfCom = new AssessmentOfCompetence();
		assOfCom.setBtnGroupList(btnGroupList);
		assOfCom.setEmployee(e);
		assOfCom.setExperience(experience);
		assOfCom.setFunDes(funDes);
		assOfCom.setFuntion(functionList);
		assOfCom.setPoint(point);
		if(isE.equalsIgnoreCase("Yes")) {
			isEna = true;
		}else {
			isEna = false;
		}
		assOfCom.setEnable(isEna);
		controller.register(assOfCom);
		ShowMessage.successMessage(this, "Sucesso", "sucesso ao realizar a Avaliação de Competência!");
		ClearFrame.clear(this);
		this.revalidate();
		habilitiesPanel.removeAll();
		habilitiesPanel.repaint();
		qualificationPanel.removeAll();
		qualificationPanel.removeAll();
	}
	
	private boolean verifySelections() {
		for (ButtonGroup buttonGroup : btnGroupList) {
	          if(buttonGroup.getSelection() == null) {
	          	ShowMessage.errorMessage(this, "Erro", "Selecione sim ou não para as habilidades e cursos!");
	           	return false;
	          }
        }
	    return true;
    }

	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar esse dados ?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
	
	private void fillFields() {
		if(cboEmployee.getSelectedIndex() == -1)return;
		Employee e = (Employee) cboEmployee.getSelectedItem();
		txtFunction.setText(e.getJob().getCbo().getTitle());
		CBO cbo = e.getJob().getCbo();
		funDes = controller.getFunctionDescription(cbo);
		txtScholarity.setText(e.getSchoolingStr());
		txtSector.setText(funDes.getSector());
		fillPanel(habilitiesPanel, funDes);
		fillPanel(qualificationPanel, funDes);
		txtHireDate.setValue(e.getJob().getAdmissionDate());
		if(funDes.getPeriod() == null)txtRequiredExperience.setText("Não é nescessário experiência");
		else txtRequiredExperience.setText(funDes.getPeriod());
		List<Training> tList = controller.getTraining(e.getId());
		fillTable(trainingTable, tList);
		txtRequiredScholarit.setText(funDes.getMinimalGraduation());
	}

	private void fillTable(JTable table, List<Training> tList) {
	    DefaultTableModel tbl = (DefaultTableModel) table.getModel();
	    tList.forEach(t -> tbl.addRow(new Object[] {t}));
    }

	private void fillPanel(JPanel panel, FunctionDescription funDes) {
		List<String> list = null;
		if(panel == habilitiesPanel) {			
			list =funDes.getPersonalHabilitiesList();
		}else if(panel == qualificationPanel) {
			list = funDes.getKnowledgementList();
		}
		if(list == null)return;
		for (String s : list) {
	        panel.add(new JLabel(s));
	        JRadioButton rdbtnYes = new JRadioButton("Sim");
	        rdbtnYes.setActionCommand("Yes");
	        JRadioButton rdbtnNo = new JRadioButton("Não");
	        rdbtnNo.setActionCommand("No");
	        panel.add(rdbtnNo);
	        panel.add(rdbtnYes);
	        ButtonGroup btnGroup = new ButtonGroup();
	        btnGroup.add(rdbtnNo);
	        btnGroup.add(rdbtnYes);
	        btnGroupList.add(btnGroup);
	        functionList.add(s);
        }
		this.revalidate();
		panel.repaint();
    }
}
