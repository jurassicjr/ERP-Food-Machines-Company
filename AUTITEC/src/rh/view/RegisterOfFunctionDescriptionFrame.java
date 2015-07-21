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
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.FunctionDescription;
import rh.controller.RegisterOfFunctionDescriptionController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;
import core.CBO;
import database.FillCBO;

public class RegisterOfFunctionDescriptionFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -1345950695388694227L;

    private JTextField txtKnowledgementAndCourses;
	private JTextField txtPersonalHabilitiesNeeded;
	private JTextField txtPeriod;
	
	private JTable tblPersonalHabilities;
	private JTable tblKnowledgementAndCourses;

	private JLabel lblReportTo;
	private JLabel lblFunction;
	private JLabel lblSetor;
	private JLabel lblFuntionAtribuitions;
	private JLabel lblMinimalGraduation;
	private JLabel lblPersonalHabilitiesNeeded;
	private JLabel lblKnowledgementAndCourses;
	private JLabel lblNeededExperiences;
	private JLabel lblPeriod;

	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnAddPersonalHabilities;
	private JButton btnAddKnowledgementAndCourses;

	private RegisterOfFunctionDescriptionController controller;
	
	private JComboBox<core.CBO> cboFunction;
	private JComboBox<Employee> cboReportTo;
	private JComboBox<String> cboSector;
	private JComboBox<String> cboMinimalGraduation;

	private JCheckBox chbxYes;
	private JCheckBox chbxNo;

	private JTextArea txtFuntionAtribuitions;
    
    public RegisterOfFunctionDescriptionFrame() {
    	controller = new RegisterOfFunctionDescriptionController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    setTitle("Registro de Descrição de Cargo");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100,100, 666,650);
	    setMinimumSize(new Dimension(666, 650));
	    setPreferredSize(new Dimension(666, 650));
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblFunction = new JLabel("Cargo");
	    
	    cboFunction = new JComboBox<core.CBO>();
	    new FillCBO(cboFunction);
	    new ComboBoxAutoCompletion(cboFunction);
	    cboFunction.setSelectedIndex(-1);
	    
	    lblReportTo = new JLabel("Reporta-se á");
	    
	    cboReportTo = new JComboBox<Employee>();
	    controller.fillEmployeeCBO(cboReportTo);
	    new ComboBoxAutoCompletion(cboReportTo);
	    cboReportTo.setSelectedIndex(-1);
	    
	    lblSetor = new JLabel("Setor");
	    cboSector = new JComboBox<String>();
	    controller.fillSectorComboBox(cboSector);
	    new ComboBoxAutoCompletion(cboSector);
	    cboSector.setSelectedIndex(-1);
	    
	    lblFuntionAtribuitions = new JLabel("Atribuições de Cargo/Responsábilidade");
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    lblMinimalGraduation = new JLabel("Formação Escolar Mínima");
	    
	    cboMinimalGraduation = new JComboBox<String>();
	    cboMinimalGraduation.setModel(new DefaultComboBoxModel<String>(new String[] {"Fundamental Incompleto", "Fundamental Completo", "Médio Incompleto", "Médio Completo", "Técnico Incompleto", "Técnico Completo", "Superior Incompleto", "Superior Completo", "Pós-Graduado"}));
	    
	    lblPersonalHabilitiesNeeded = new JLabel("Habilidade Pessoais Nescessárias");
	    
	    txtPersonalHabilitiesNeeded = new JTextField();
	    txtPersonalHabilitiesNeeded.setColumns(10);
	    
	    btnAddPersonalHabilities = new JButton("Adicionar");
	    btnAddPersonalHabilities.setIcon(new ImageIcon(RegisterOfFunctionDescriptionFrame.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    lblKnowledgementAndCourses = new JLabel("Cursos e Conhecimentos Nescessários");
	    
	    txtKnowledgementAndCourses = new JTextField();
	    txtKnowledgementAndCourses.setColumns(10);
	    
	    btnAddKnowledgementAndCourses = new JButton("Adicionar");
	    btnAddKnowledgementAndCourses.setIcon(new ImageIcon(RegisterOfFunctionDescriptionFrame.class.getResource("/resources/plus.png")));
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    
	    lblNeededExperiences = new JLabel("Experiencia Nescessária ?");
	    
	    chbxYes = new JCheckBox("Sim");
	    
	    chbxNo = new JCheckBox("Não");
	    
	    ButtonGroup btnGroup = new ButtonGroup();
	    btnGroup.add(chbxYes);
	    btnGroup.add(chbxNo);
	    
	    lblPeriod = new JLabel("Periodo Desejável");
	    
	    txtPeriod = new JTextField();
	    txtPeriod.setColumns(10);
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
	    				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING, false)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblSetor)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboSector, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblFunction)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboFunction, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblReportTo)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboReportTo, 0, 269, Short.MAX_VALUE))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblMinimalGraduation)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboMinimalGraduation, 0, 213, Short.MAX_VALUE))))
	    				.addComponent(lblFuntionAtribuitions)
	    				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblPersonalHabilitiesNeeded)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtPersonalHabilitiesNeeded, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(btnAddPersonalHabilities))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblKnowledgementAndCourses)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtKnowledgementAndCourses, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(btnAddKnowledgementAndCourses))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblNeededExperiences)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(chbxYes)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(chbxNo)
	    					.addGap(18)
	    					.addComponent(lblPeriod)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtPeriod, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFunction)
	    				.addComponent(cboFunction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblReportTo)
	    				.addComponent(cboReportTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSetor)
	    				.addComponent(cboSector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblMinimalGraduation)
	    				.addComponent(cboMinimalGraduation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(lblFuntionAtribuitions)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblPersonalHabilitiesNeeded)
	    				.addComponent(txtPersonalHabilitiesNeeded, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddPersonalHabilities))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblKnowledgementAndCourses)
	    				.addComponent(txtKnowledgementAndCourses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnAddKnowledgementAndCourses))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblNeededExperiences)
	    				.addComponent(chbxYes)
	    				.addComponent(chbxNo)
	    				.addComponent(lblPeriod)
	    				.addComponent(txtPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(17, Short.MAX_VALUE))
	    );
	    
	    tblKnowledgementAndCourses = new JTable();
	    scrollPane_2.setViewportView(tblKnowledgementAndCourses);
	    String[] Coursesheader = new String[] {"Cursos e Conhecimentos Nescessários"};
	    tblKnowledgementAndCourses.setModel(new DefaultTableModel(null, Coursesheader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 2747685977760634104L;
	    	
            boolean[] columnEditables = new boolean[] {
					false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	    });
	    
	    tblPersonalHabilities = new JTable();
	    scrollPane_1.setViewportView(tblPersonalHabilities);
	    String[] habilitiesHeader = new String[] {"Habilidades Pessoais"};
	    tblPersonalHabilities.setModel(new DefaultTableModel(null, habilitiesHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 2747685977760634104L;
	    
            boolean[] columnEditables = new boolean[] {
					false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
            
	    });
	    
	    txtFuntionAtribuitions = new JTextArea();
	    scrollPane.setViewportView(txtFuntionAtribuitions);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(RegisterOfFunctionDescriptionFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(RegisterOfFunctionDescriptionFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(RegisterOfFunctionDescriptionFrame.class.getResource("/resources/ClearFrame.png")));
	    
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
	
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnAddKnowledgementAndCourses))addItemToTable(tblKnowledgementAndCourses, txtKnowledgementAndCourses);
				else if(e.getSource().equals(btnAddPersonalHabilities))addItemToTable(tblPersonalHabilities, txtPersonalHabilitiesNeeded);
				else if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		btnAddKnowledgementAndCourses.addActionListener(buttonListener);
		btnAddPersonalHabilities.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		KeyListener tblKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(tblKnowledgementAndCourses))removeRowFromTable(e, tblKnowledgementAndCourses);
				else if(e.getSource().equals(tblPersonalHabilities))removeRowFromTable(e, tblPersonalHabilities);
			}
		};
		
		tblKnowledgementAndCourses.addKeyListener(tblKeyListener);
		tblPersonalHabilities.addKeyListener(tblKeyListener);
		
		KeyListener txtKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(txtKnowledgementAndCourses) && e.getKeyCode() == KeyEvent.VK_ENTER)addItemToTable(tblKnowledgementAndCourses, txtKnowledgementAndCourses);
				else if(e.getSource().equals(txtPersonalHabilitiesNeeded) && e.getKeyCode() == KeyEvent.VK_ENTER)addItemToTable(tblPersonalHabilities, txtPersonalHabilitiesNeeded);
			}
		};
		
		txtKnowledgementAndCourses.addKeyListener(txtKeyListener);
		txtPersonalHabilitiesNeeded.addKeyListener(txtKeyListener);
	}
	
	private void addItemToTable(JTable table, JTextField txt) {
		String text = txt.getText();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String[] insertionString = new String[] {text};
		tbl.addRow(insertionString);
		txt.setText("");
	}
	
	private void removeRowFromTable(KeyEvent e, JTable table) {
		if(e.getKeyCode() == KeyEvent.VK_DELETE) {
			if(table.getSelectedRow() == -1)return;
			int i = table.getSelectedRow();
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			tbl.removeRow(i);
		}
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente apagar todos os dados ?");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void register() {
		if(cboFunction.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição selecione um cargo!");
			return;
		}
		if(cboMinimalGraduation.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição selecione a escolaridade minima!");
			return;
		}
		if(cboReportTo.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição selecione a quem ele deve se reportar!");
			return;
		}
		if(cboSector.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registar a descrição selecione o setor!");
			return;
		}
		int needExperience = 0;
		if(chbxYes.isSelected()) {
			if (txtPeriod.getText().isEmpty()) {
	            ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição insira a periodo de experiência!");
	            return;
            }
			needExperience = 1;
		}
		if(!chbxNo.isSelected() && !chbxYes.isSelected()) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição selecione se é ou não nescessário a experiência profissional!");
			return;
		}
		
		if(txtFuntionAtribuitions.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar a descrição insira a função do cargo!");
			return;
		}
		
		CBO cbo = (CBO) cboFunction.getSelectedItem();
		Employee e = (Employee) cboReportTo.getSelectedItem();
		String sector = (String) cboSector.getSelectedItem();
		String minimalGraduation = (String) cboMinimalGraduation.getSelectedItem();
		String functionAtribuition = txtFuntionAtribuitions.getText();
		String period = txtPeriod.getText();
		List<String> knowledgementList = tableToList(tblKnowledgementAndCourses);
		List<String> personalHabilitiesList = tableToList(tblPersonalHabilities);
		
		
		FunctionDescription funDes = new FunctionDescription();
		funDes.setEmployee(e);
		funDes.setFunction(cbo);
		funDes.setFunctionAtribuition(functionAtribuition);
		funDes.setKnowledgementList(knowledgementList);
		funDes.setMinimalGraduation(minimalGraduation);
		funDes.setNeedExperiences(needExperience);
		funDes.setPeriod(period);
		funDes.setPersonalHabilitiesList(personalHabilitiesList);
		funDes.setSector(sector);
		
		controller.register(funDes);
	}

	private List<String> tableToList(JTable table) {
	    int rowCount = table.getRowCount();
	    if(rowCount == 0)return null;
	    DefaultTableModel tbl = (DefaultTableModel) table.getModel();
	    List<String> list = new ArrayList<String>();
	    for(int i = 0; i < rowCount; i++) {
	    	String s = (String) tbl.getValueAt(i, 0);
	    	list.add(s);
	    }
	    return list;
    }
}
