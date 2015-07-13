package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Employee;
import model.InternalSatisfactionResearch;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.InternalSatisfactionResearchController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class InternalSatisfactionResearchFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 1691796978128149422L;
	private JPanel principalPanel;
	private DateField txtDate;
	
	private JComboBox<Employee> cboEmployee;
	
	private JLabel lblUniforme;
	private JLabel lblFerramental;
	private JLabel lblDate;
	private JLabel lblFuncionrioColaborador;
	private JLabel lblWorkingArea;
	private JLabel lblFunctionPerfomed;
	private JLabel lblComunicationFacility;
	private JLabel lblRelationGroup;
	private JLabel lblWcs;
	private JLabel lblInternalLighting;
	private JLabel lblWhowYouAvaibleTheFormers;
	private JLabel lblEpis;
	
	private JRadioButton rbntNotApply01;
	private JRadioButton rbntGreat01;
	private JRadioButton rbntGood01;
	private JRadioButton rbntRegular01;
	private JRadioButton rbntBad01;
	
	private JRadioButton rbntNotApply02;
	private JRadioButton rbntGreat02;
	private JRadioButton rbntGood02;
	private JRadioButton rbntRegular02;
	private JRadioButton rbntBad02;

	private JRadioButton rbntNotApply03;
	private JRadioButton rbntGreat03;
	private JRadioButton rbntGood03;
	private JRadioButton rbntRegular03;
	private JRadioButton rbntBad03;

	private JRadioButton rbntNotApply04;
	private JRadioButton rbntGreat04;
	private JRadioButton rbntGood04;
	private JRadioButton rbntRegular04;
	private JRadioButton rbntBad04;
	
	private JSeparator separator_3;
	
	private JRadioButton rbntNotApply05;
	private JRadioButton rbntGreat05;
	private JRadioButton rbntGood05;
	private JRadioButton rbntRegular05;
	private JRadioButton rbntBad05;

	private JRadioButton rbntNotApply06;
	private JRadioButton rbntGreat06;
	private JRadioButton rbntGood06;
	private JRadioButton rbntRegular06;
	private JRadioButton rbntBad06;
	
	private JRadioButton rbntNotApply07;
	private JRadioButton rbntGreat07;
	private JRadioButton rbntGood07;
	private JRadioButton rbntRegular07;
	private JRadioButton rbntBad07;

	private JRadioButton rbntNotApply08;
	private JRadioButton rbntGreat08;
	private JRadioButton rbntGood08;
	private JRadioButton rbntRegular08;
	private JRadioButton rbntBad08;
	
	private JRadioButton rbntNotApply09;
	private JRadioButton rbntGreat09;
	private JRadioButton rbntGood09;
	private JRadioButton rbntRegular09;
	private JRadioButton rbntBad09;
	
	private JRadioButton rbntNotApply10;
	private JRadioButton rbntGreat10;
	private JRadioButton rbntGood10;
	private JRadioButton rbntRegular10;
	private JRadioButton rbntBad10;
	private JPanel subPanel;
	private InternalSatisfactionResearchController controller;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private ButtonGroup btnGroup01;
	private ButtonGroup btnGroup02;
	private ButtonGroup btnGroup03;
	private ButtonGroup btnGroup04;
	private ButtonGroup btnGroup05;
	private ButtonGroup btnGroup06;
	private ButtonGroup btnGroup07;
	private ButtonGroup btnGroup08;
	private ButtonGroup btnGroup09;
	private ButtonGroup btnGroup10;
	private JTextArea txtCriticismsAndSuggestions;

    public InternalSatisfactionResearchFrame() {
    	controller = new InternalSatisfactionResearchController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    setTitle("Pesquisa de Satisfação Interna");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 576, 667);
	    setPreferredSize(new Dimension(567, 667));
	    setMinimumSize(new Dimension(567, 667));
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblFuncionrioColaborador = new JLabel("Funcionário Colaborador");
	    
	    cboEmployee = new JComboBox<Employee>();
	    controller.fillEmployee(cboEmployee);
	    new ComboBoxAutoCompletion(cboEmployee);
	    cboEmployee.setSelectedIndex(-1);
	    
	    lblDate = new JLabel("Data");
	    
	    txtDate = CalendarFactory.createDateField();
	   // txtDate.setColumns(10);
	    
	    JPanel panel = new JPanel();
	    panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "PONTOS A SEREM ANALISADOS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblFuncionrioColaborador)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFuncionrioColaborador)
	    				.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblDate)
	    				.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    
	    lblWorkingArea = new JLabel("01 - Ambiente de Trabalho");
	    lblWorkingArea.setToolTipText("Ambiente de Trabalho");
	    
	    rbntNotApply01 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat01 = new JRadioButton("Ótimo");
	    
	    rbntGood01 = new JRadioButton("Bom");
	    
	    rbntRegular01 = new JRadioButton("Regular");
	    
	    rbntBad01 = new JRadioButton("Ruim");
	    
	    btnGroup01 = new ButtonGroup();
	    btnGroup01.add(rbntBad01);
	    btnGroup01.add(rbntGood01);
	    btnGroup01.add(rbntGreat01);
	    btnGroup01.add(rbntNotApply01);
	    btnGroup01.add(rbntRegular01);
	    
	    JSeparator separator = new JSeparator();
	    
	    lblFunctionPerfomed = new JLabel("02 - Função Desempenhada");
	    lblFunctionPerfomed.setToolTipText("Função Desempenhada");
	    
	    rbntNotApply02 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat02 = new JRadioButton("Ótimo");
	    
	    rbntGood02 = new JRadioButton("Bom");
	    
	    rbntRegular02 = new JRadioButton("Regular");
	    
	    rbntBad02 = new JRadioButton("Ruim");
	    
	    btnGroup02 = new ButtonGroup();
	    btnGroup02.add(rbntBad02);
	    btnGroup02.add(rbntGood02);
	    btnGroup02.add(rbntGreat02);
	    btnGroup02.add(rbntRegular02);
	    btnGroup02.add(rbntNotApply02);
	    
	    JSeparator separator_1 = new JSeparator();
	    
	    lblRelationGroup = new JLabel("03 - Grupo ao qual se relaciona");
	    lblRelationGroup.setToolTipText("Grupo ao qual se relaciona");
	    
	    rbntNotApply03 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat03 = new JRadioButton("Ótimo");
	    
	    rbntGood03 = new JRadioButton("Bom");
	    
	    rbntRegular03 = new JRadioButton("Regular");
	    
	    rbntBad03 = new JRadioButton("Ruim");
	    
	    btnGroup03 = new ButtonGroup();
	    
	    btnGroup03.add(rbntBad03);
	    btnGroup03.add(rbntGood03);
	    btnGroup03.add(rbntGreat03);
	    btnGroup03.add(rbntRegular03);
	    btnGroup03.add(rbntNotApply03);
	    
	    JSeparator separator_2 = new JSeparator();
	    
	    lblComunicationFacility = new JLabel("04 - Facilidade de Comunicação com Superiores");
	    lblComunicationFacility.setToolTipText("Facilidade de Comunicação com Superiores");
	    
	    rbntNotApply04 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat04 = new JRadioButton("Ótimo");
	    
	    rbntGood04 = new JRadioButton("Bom");
	    
	    rbntRegular04 = new JRadioButton("Regular");
	    
	    rbntBad04 = new JRadioButton("Ruim");
	    
	    btnGroup04 = new ButtonGroup();
	    btnGroup04.add(rbntBad04);
	    btnGroup04.add(rbntGood04);
	    btnGroup04.add(rbntGreat04);
	    btnGroup04.add(rbntRegular04);
	    btnGroup04.add(rbntNotApply04);
	    
	    separator_3 = new JSeparator();
	    
	    lblWcs = new JLabel("05 - Sanitários");
	    
	    rbntNotApply05 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat05 = new JRadioButton("Ótimo");
	    
	    rbntGood05 = new JRadioButton("Bom");
	    
	    rbntRegular05 = new JRadioButton("Regular");
	    
	    rbntBad05 = new JRadioButton("Ruim");
	    
	    btnGroup05 = new ButtonGroup();
	    btnGroup05.add(rbntBad05);
	    btnGroup05.add(rbntGood05);
	    btnGroup05.add(rbntGreat05);
	    btnGroup05.add(rbntNotApply05);
	    btnGroup05.add(rbntRegular05);
	    
	    JSeparator separator_4 = new JSeparator();
	    
	    lblInternalLighting = new JLabel("06 - Iluminação Interna da Fabrica");
	    
	    rbntNotApply06 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat06 = new JRadioButton("Ótimo");
	    
	    rbntGood06 = new JRadioButton("Bom");
	    
	    rbntRegular06 = new JRadioButton("Regular");
	    
	    rbntBad06 = new JRadioButton("Ruim");
	    
	    btnGroup06 = new ButtonGroup();
	    btnGroup06.add(rbntBad06);
	    btnGroup06.add(rbntGood06);
	    btnGroup06.add(rbntGreat06);
	    btnGroup06.add(rbntNotApply06);
	    btnGroup06.add(rbntRegular06);
	    
	    JSeparator separator_5 = new JSeparator();
	    
	    lblWhowYouAvaibleTheFormers = new JLabel("07 - Como avalia os formulários que se\r\n aplicam a sua atividade?");
	    
	    rbntNotApply07 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat07 = new JRadioButton("Ótimo");
	    
	    rbntGood07 = new JRadioButton("Bom");
	    
	    rbntRegular07 = new JRadioButton("Regular");
	    
	    rbntBad07 = new JRadioButton("Ruim");
	    
	    btnGroup07 = new ButtonGroup();
	    btnGroup07.add(rbntBad07);
	    btnGroup07.add(rbntRegular07);
	    btnGroup07.add(rbntGood07);
	    btnGroup07.add(rbntGreat07);
	    btnGroup07.add(rbntNotApply07);
	    
	    JSeparator separator_6 = new JSeparator();
	    
	    lblEpis = new JLabel("08 - EPI's");
	    
	    rbntNotApply08 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat08 = new JRadioButton("Ótimo");
	    
	    rbntGood08 = new JRadioButton("Bom");
	    
	    rbntRegular08 = new JRadioButton("Regular");
	    
	    rbntBad08 = new JRadioButton("Ruim");
	    
	    btnGroup08 = new ButtonGroup();
	    btnGroup08.add(rbntBad08);
	    btnGroup08.add(rbntGood08);
	    btnGroup08.add(rbntGreat08);
	    btnGroup08.add(rbntNotApply08);
	    btnGroup08.add(rbntRegular08);
	    
	    JSeparator separator_7 = new JSeparator();
	    
	    lblUniforme = new JLabel("09 - Uniforme");
	    
	    rbntNotApply09 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat09 = new JRadioButton("Ótimo");
	    
	    rbntGood09 = new JRadioButton("Bom");
	    
	    rbntRegular09 = new JRadioButton("Regular");
	    
	    rbntBad09 = new JRadioButton("Ruim");
	    
	    btnGroup09 = new ButtonGroup();
	    btnGroup09.add(rbntBad09);
	    btnGroup09.add(rbntGood09);
	    btnGroup09.add(rbntGreat09);
	    btnGroup09.add(rbntRegular09);
	    btnGroup09.add(rbntNotApply09);
	    
	    JSeparator separator_8 = new JSeparator();
	    
	    lblFerramental = new JLabel("10 - Ferramental");
	    
	    rbntNotApply10 = new JRadioButton("Não se Aplica");
	    
	    rbntGreat10 = new JRadioButton("Ótimo");
	    
	    rbntGood10 = new JRadioButton("Bom");
	    
	    rbntRegular10 = new JRadioButton("Regular");
	    
	    rbntBad10 = new JRadioButton("Ruim");
	    
	    btnGroup10 = new ButtonGroup();
	    btnGroup10.add(rbntBad10);
	    btnGroup10.add(rbntGood10);
	    btnGroup10.add(rbntRegular10);
	    btnGroup10.add(rbntNotApply10);
	    btnGroup10.add(rbntGreat10);
	    
	    JSeparator separator_9 = new JSeparator();
	    
	    JLabel lblCriticasESugestes = new JLabel("Criticas e Sugestões");
	    
	    JScrollPane scrollPane = new JScrollPane();
	    
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(lblFerramental)
	    				.addComponent(lblUniforme)
	    				.addComponent(lblEpis)
	    				.addComponent(lblWhowYouAvaibleTheFormers, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblInternalLighting)
	    				.addComponent(lblWcs)
	    				.addComponent(lblComunicationFacility, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblRelationGroup)
	    				.addComponent(lblFunctionPerfomed)
	    				.addComponent(lblWorkingArea))
	    			.addGap(18, 33, Short.MAX_VALUE)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(rbntNotApply10, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply09, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply08, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply07, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply06, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply05, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply04, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply03, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply02, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntNotApply01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(rbntGreat10, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat09, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat08, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat07, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat06, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat05, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat04, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat03, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat02, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGreat01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(rbntGood10, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood09, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood08, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood07, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood06, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood05, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood04, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood03, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood02, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntGood01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(rbntRegular10, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular09, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular08, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular07, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular06, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular05, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular04, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular03, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular02, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntRegular01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(rbntBad10, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad09, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad08, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad07, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad06, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad05, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad04, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad03, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad02, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(rbntBad01))
	    			.addContainerGap())
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(10)
	    			.addComponent(separator_7, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addGap(10))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_6, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap())
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_5, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
	    			.addGap(11))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap())
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addComponent(separator, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
	    			.addContainerGap(10, Short.MAX_VALUE))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(11)
	    			.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
	    			.addContainerGap(10, Short.MAX_VALUE))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(11)
	    			.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
	    			.addContainerGap(10, Short.MAX_VALUE))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_4, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap(10, Short.MAX_VALUE))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_8, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap())
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(separator_9, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap())
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(lblCriticasESugestes)
	    			.addContainerGap(470, Short.MAX_VALUE))
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblWorkingArea)
	    				.addComponent(rbntBad01)
	    				.addComponent(rbntRegular01)
	    				.addComponent(rbntGood01)
	    				.addComponent(rbntGreat01)
	    				.addComponent(rbntNotApply01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFunctionPerfomed)
	    				.addComponent(rbntNotApply02)
	    				.addComponent(rbntGreat02)
	    				.addComponent(rbntGood02)
	    				.addComponent(rbntRegular02)
	    				.addComponent(rbntBad02))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblRelationGroup)
	    				.addComponent(rbntNotApply03)
	    				.addComponent(rbntGreat03)
	    				.addComponent(rbntGood03)
	    				.addComponent(rbntRegular03)
	    				.addComponent(rbntBad03))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(rbntNotApply04)
	    				.addComponent(rbntGreat04)
	    				.addComponent(rbntGood04)
	    				.addComponent(rbntRegular04)
	    				.addComponent(rbntBad04)
	    				.addComponent(lblComunicationFacility))
	    			.addGap(9)
	    			.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblWcs)
	    				.addComponent(rbntNotApply05)
	    				.addComponent(rbntGreat05)
	    				.addComponent(rbntGood05)
	    				.addComponent(rbntRegular05)
	    				.addComponent(rbntBad05))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(lblInternalLighting))
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addGap(7)
	    					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(rbntNotApply06)
	    						.addComponent(rbntGreat06)
	    						.addComponent(rbntGood06)
	    						.addComponent(rbntRegular06)
	    						.addComponent(rbntBad06))))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addGap(10)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(rbntNotApply07)
	    				.addComponent(rbntGreat07)
	    				.addComponent(rbntGood07)
	    				.addComponent(rbntRegular07)
	    				.addComponent(rbntBad07)
	    				.addComponent(lblWhowYouAvaibleTheFormers))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblEpis)
	    				.addComponent(rbntNotApply08)
	    				.addComponent(rbntGreat08)
	    				.addComponent(rbntGood08)
	    				.addComponent(rbntRegular08)
	    				.addComponent(rbntBad08))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(separator_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblUniforme)
	    				.addComponent(rbntNotApply09)
	    				.addComponent(rbntGreat09)
	    				.addComponent(rbntGood09)
	    				.addComponent(rbntRegular09)
	    				.addComponent(rbntBad09))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(separator_8, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFerramental)
	    				.addComponent(rbntNotApply10)
	    				.addComponent(rbntGreat10)
	    				.addComponent(rbntGood10)
	    				.addComponent(rbntRegular10)
	    				.addComponent(rbntBad10))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_9, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(lblCriticasESugestes)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    
	    txtCriticismsAndSuggestions = new JTextArea();
	    txtCriticismsAndSuggestions.setWrapStyleWord(true);
	    scrollPane.setViewportView(txtCriticismsAndSuggestions);
	    panel.setLayout(gl_panel);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
	}

	private void initializeSub() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(InternalSatisfactionResearchFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(InternalSatisfactionResearchFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(InternalSatisfactionResearchFrame.class.getResource("/resources/ClearFrame.png")));
	    
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
				if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clear();
				else if(e.getSource().equals(btnConfirm))register();
			}
		};
		
		btnConfirm.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboEmployee)) checkEmployee();
			}
		};
		
		cboEmployee.addActionListener(cboListener);
	}
	
	//Verifica se o funcionário em questão já fez o teste hoje !
	private void checkEmployee() {
		if(cboEmployee.getSelectedIndex() == -1)return;
		if(cboEmployee.getItemCount() == 0)return;
		Employee e = (Employee) cboEmployee.getSelectedItem();
		Date date = new Date();
		InternalSatisfactionResearch iSR = controller.verifyResearchShock(e, date);
		if(iSR != null) {
			int i = ShowMessage.questionMessage(this, "Pesquisa", "Esse funcionário já realizou essa pesquisa hoje!/nDeseja reafazer a pesquisa com ele ?"); 
			if(i == JOptionPane.NO_OPTION)this.dispose();
		}
	}
	
	//Faz a limpeza dos campos no frame
	private void clear() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja limpar todos os dados já preenchidos ?");
		if( i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	//registra e finaliza a pesquisa
	private void register() {
		int i = ShowMessage.questionMessage(this, "Registrar Pesquisa", "Deseja realmente registrar essa pesquisa ?");
		if( i == JOptionPane.NO_OPTION)return;
		if(cboEmployee.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "informe qual funcionário está realizando está pesquisa");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		
		int workingAreaPoint = -2;
		workingAreaPoint = verifySelected(workingAreaPoint, btnGroup01);
		if(workingAreaPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre o \"Ambiente de Trabalho\"");
			return;
		}
		
		int functionPerfomedPoint = -2;
		functionPerfomedPoint = verifySelected(functionPerfomedPoint, btnGroup02);
		if(functionPerfomedPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre a \"Função Desempenhada\"");
			return;
		}
		
		int relationGroupPoint = -2;
		relationGroupPoint = verifySelected(relationGroupPoint, btnGroup03);
		if(relationGroupPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre o \"grupo ao qual se relaciona\"");
			return;
		}
		
		int comunicationFacilityPoint = -2;
		comunicationFacilityPoint = verifySelected(comunicationFacilityPoint, btnGroup04);
		if(comunicationFacilityPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre a \"facilidade de comunicação com seus superiores\"");
			return;
		}
		
		int  wcPoint = -2;
		wcPoint = verifySelected(wcPoint, btnGroup05);
		if(wcPoint == -2 ) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre os banheiros");
			return;
		}
		
		int internalLightingPoint = -2;
		internalLightingPoint = verifySelected(internalLightingPoint, btnGroup06);
		if(internalLightingPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre \"Iluminação Interna\"");
			return;
		}
		
		int whoYouAvaibleTheFormersPoint = -2;
		whoYouAvaibleTheFormersPoint = verifySelected(whoYouAvaibleTheFormersPoint, btnGroup07);
		if(whoYouAvaibleTheFormersPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre \"Como você avalia os formulários\"");
			return;
		}
		
		int epiPoint = -2;
		epiPoint = verifySelected(epiPoint, btnGroup08);
		if(epiPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pegunta sobre \"epis\"");
			return;
		}
		
		int uniformPoint = -2;
		uniformPoint = verifySelected(uniformPoint, btnGroup09);
		if(uniformPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre \"Uniformes\"");
			return;
		}
		
		int  toolingPoint = -2;
		toolingPoint = verifySelected(toolingPoint, btnGroup10);
		if(toolingPoint == -2) {
			ShowMessage.errorMessage(this, "Erro ao realizar pesquisa", "Insira uma resposta a pergunta sobre \"ferramental\"");
			return;
		}
		
		String criticismsAndSuggestions = txtCriticismsAndSuggestions.getText();
		InternalSatisfactionResearch isr = new InternalSatisfactionResearch();
		isr.setComunicationFacility(comunicationFacilityPoint);
		Date date = (Date) txtDate.getValue();
		isr.setDate(date);
		isr.setEmployee(e);
		isr.setEPIs(epiPoint);
		isr.setFormers(whoYouAvaibleTheFormersPoint);
		isr.setFunctionPerfomed(functionPerfomedPoint);
		isr.setIndustryIntenalLighting(internalLightingPoint);
		isr.setRelationGroup(relationGroupPoint);
		isr.setTooling(toolingPoint);
		isr.setUniform(uniformPoint);
		isr.setWCs(wcPoint);
		isr.setWorkingArea(workingAreaPoint);
		isr.setCriticismsAndSuggestions(criticismsAndSuggestions);
		controller.register(isr);
		ShowMessage.successMessage(this, "Sucesso", "Pesquisa registrada com sucesso!");
		ClearFrame.clear(this);
	}

	private int verifySelected(int funtion, ButtonGroup btnGroup) {
		String string = null;
		for (Enumeration<AbstractButton> buttons = btnGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	            	 string = button.getText();
	           }
		}
		if(string == null)return funtion;
		else if(string.equalsIgnoreCase("Não se Aplica")) {
			funtion = 0;
		}else if(string.equalsIgnoreCase("Ótimo")) {
			funtion = 3;
		}else if(string.equalsIgnoreCase("Bom")) {
			funtion = 2;
		}else if(string.equalsIgnoreCase("Regular")) {
			funtion = 1;
		}else if(string.equalsIgnoreCase("ruim")) {
			funtion = -1;
		}
		return funtion;
    }
}
