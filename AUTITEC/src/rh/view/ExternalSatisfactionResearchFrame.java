package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import model.Client;
import model.ExternalSatisfactionResearch;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.ExternalSatisfactionResearchController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.ShowMessage;

public class ExternalSatisfactionResearchFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -3225094659981422336L;
	private DateField txtDate;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private ExternalSatisfactionResearchController controller;
	
	private JComboBox<Client> cboClient;
	
	private JLabel lblClient;
	private JLabel lblDate;
	private JLabel lblComunication;
	private JLabel lblCourtesy;
	private JLabel lbleffectiveness;
	private JLabel lblCredibility;
	private JLabel lblQuality;
	private JLabel lblCriticismsAndSuggestions;
	private JLabel lblTecnicalQuality;
	private JLabel lblGeneral;
	
	private JRadioButton rdbtnVeryDissatisfied01;
	private JRadioButton rdbtnDissatisfied01;
	private JRadioButton rdbtnSatisfied01;
	private JRadioButton rdbtnVerySatisfied01;

	private JRadioButton rdbtnVeryDissatisfied02;
	private JRadioButton rdbtnDissatisfied02;
	private JRadioButton rdbtnSatisfied02;
	private JRadioButton rdbtnVerySatisfied02;

	private JRadioButton rdbtnVeryDissatisfied03;
	private JRadioButton rdbtnDissatisfied03;
	private JRadioButton rdbtnSatisfied03;
	private JRadioButton rdbtnVerySatisfied03;
	
	private JRadioButton rdbtnVeryDissatisfied04;
	private JRadioButton rdbtnDissatisfied04;
	private JRadioButton rdbtnSatisfied04;
	private JRadioButton rdbtnVerySatisfied04;
	
	private JRadioButton rdbtnVeryDissatisfied05;
	private JRadioButton rdbtnDissatisfied05;
	private JRadioButton rdbtnSatisfied05;
	private JRadioButton rdbtnVerySatisfied05;

	private JRadioButton rdbtnVeryDissatisfied06;
	private JRadioButton rdbtnDissatisfied06;
	private JRadioButton rdbtnSatisfied06;
	private JRadioButton rdbtnVerySatisfied06;

	private JRadioButton rdbtnVeryDissatisfied07;
	private JRadioButton rdbtnDissatisfied07;
	private JRadioButton rdbtnSatisfied07;
	private JRadioButton rdbtnVerySatisfied07;
	private ButtonGroup btnGroup01;
	private ButtonGroup btnGroup02;
	private ButtonGroup btnGroup03;
	private ButtonGroup btnGroup04;
	private ButtonGroup btnGroup05;
	private ButtonGroup btnGroup06;
	private ButtonGroup btnGroup07;
	private JTextArea txtCriticismsAndSuggestions;

    public ExternalSatisfactionResearchFrame() {
    	controller = new ExternalSatisfactionResearchController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    setTitle("Pesquisa de Satisfação Externa");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100,100, 568,520);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblClient = new JLabel("Cliente");
	    
	    cboClient = new JComboBox<Client>();
	    controller.fillClient(cboClient);
	    new ComboBoxAutoCompletion(cboClient);
	    
	    lblDate = new JLabel("Data");
	    
	    txtDate = CalendarFactory.createDateField();
	    
	    JPanel panel = new JPanel();
	    panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Pontos as Serem Avaliados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblClient)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblClient)
	    				.addComponent(cboClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblDate)
	    				.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    
	    lblComunication = new JLabel("1.Comunicação");
	    
	    rdbtnVeryDissatisfied01 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied01 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied01 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied01 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator = new JSeparator();
	    
	    btnGroup01 = new ButtonGroup();
	    btnGroup01.add(rdbtnDissatisfied01);
	    btnGroup01.add(rdbtnSatisfied01);
	    btnGroup01.add(rdbtnVeryDissatisfied01);
	    btnGroup01.add(rdbtnVerySatisfied01);
	    
	    lblCourtesy = new JLabel("2.Cortesia");
	    
	    rdbtnVeryDissatisfied02 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied02 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied02 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied02 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_1 = new JSeparator();
	    
	    btnGroup02 = new ButtonGroup();
	    btnGroup02.add(rdbtnDissatisfied02);
	    btnGroup02.add(rdbtnSatisfied02);
	    btnGroup02.add(rdbtnVeryDissatisfied02);
	    btnGroup02.add(rdbtnVerySatisfied02);
	    
	    lbleffectiveness = new JLabel("3.Eficácia");
	    
	    rdbtnVeryDissatisfied03 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied03 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied03 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied03 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_2 = new JSeparator();
	    
	    btnGroup03 = new ButtonGroup();
	    btnGroup03.add(rdbtnDissatisfied03);
	    btnGroup03.add(rdbtnSatisfied03);
	    btnGroup03.add(rdbtnVeryDissatisfied03);
	    btnGroup03.add(rdbtnVerySatisfied03);
	    
	    lblCredibility = new JLabel("4.Credibilidade");
	    
	    rdbtnVeryDissatisfied04 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied04 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied04 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied04 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_3 = new JSeparator();
	    
	    btnGroup04 = new ButtonGroup();
	    btnGroup04.add(rdbtnDissatisfied04);
	    btnGroup04.add(rdbtnSatisfied04);
	    btnGroup04.add(rdbtnVeryDissatisfied04);
	    btnGroup04.add(rdbtnVerySatisfied04);
	    
	    lblQuality = new JLabel("5.Qualidade");
	    
	    rdbtnVeryDissatisfied05 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied05 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied05 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied05 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_4 = new JSeparator();
	    
	    btnGroup05 = new ButtonGroup();
	    btnGroup05.add(rdbtnDissatisfied05);
	    btnGroup05.add(rdbtnSatisfied05);
	    btnGroup05.add(rdbtnVeryDissatisfied05);
	    btnGroup05.add(rdbtnVerySatisfied05);
	    
	    lblTecnicalQuality = new JLabel("6.Qualidade Técnica");
	    
	    rdbtnVeryDissatisfied06 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied06 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied06 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied06 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_5 = new JSeparator();
	    
	    btnGroup06 = new ButtonGroup();
	    btnGroup06.add(rdbtnDissatisfied06);
	    btnGroup06.add(rdbtnSatisfied06);
	    btnGroup06.add(rdbtnVeryDissatisfied06);
	    btnGroup06.add(rdbtnVerySatisfied06);
	    
	    lblGeneral = new JLabel("7.Geral");
	    
	    rdbtnVeryDissatisfied07 = new JRadioButton("Muito Insatisfeito");
	    
	    rdbtnDissatisfied07 = new JRadioButton("Insatisfeito");
	    
	    rdbtnSatisfied07 = new JRadioButton("Satisfeito");
	    
	    rdbtnVerySatisfied07 = new JRadioButton("Muito Satisfeito");
	    
	    JSeparator separator_6 = new JSeparator();
	    
	    btnGroup07 = new ButtonGroup();
	    btnGroup07.add(rdbtnDissatisfied07);
	    btnGroup07.add(rdbtnSatisfied07);
	    btnGroup07.add(rdbtnVeryDissatisfied07);
	    btnGroup07.add(rdbtnVerySatisfied07);
	    
	    lblCriticismsAndSuggestions = new JLabel("Sugestões e Criticas");
	    
	    JScrollPane scrollPane = new JScrollPane();
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
	    					.addGap(12)
	    					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
	    				.addGroup(gl_panel.createSequentialGroup()
	    					.addContainerGap()
	    					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(separator_6, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addComponent(separator_5, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addComponent(separator_4, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblComunication)
	    							.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied01)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied01)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied01)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied01))
	    						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblCourtesy)
	    							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied02)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied02)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied02)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied02))
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lbleffectiveness)
	    							.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied03)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied03)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied03)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied03))
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblCredibility)
	    							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied04)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied04)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied04)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied04))
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblQuality)
	    							.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied05)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied05)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied05)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied05))
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblTecnicalQuality)
	    							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied06)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied06)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied06)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied06))
	    						.addGroup(gl_panel.createSequentialGroup()
	    							.addComponent(lblGeneral)
	    							.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
	    							.addComponent(rdbtnVerySatisfied07)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnSatisfied07)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnDissatisfied07)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnVeryDissatisfied07))
	    						.addComponent(lblCriticismsAndSuggestions))))
	    			.addContainerGap())
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblComunication)
	    				.addComponent(rdbtnVeryDissatisfied01)
	    				.addComponent(rdbtnDissatisfied01)
	    				.addComponent(rdbtnSatisfied01)
	    				.addComponent(rdbtnVerySatisfied01))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblCourtesy)
	    				.addComponent(rdbtnVeryDissatisfied02)
	    				.addComponent(rdbtnDissatisfied02)
	    				.addComponent(rdbtnSatisfied02)
	    				.addComponent(rdbtnVerySatisfied02))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lbleffectiveness)
	    				.addComponent(rdbtnVeryDissatisfied03)
	    				.addComponent(rdbtnDissatisfied03)
	    				.addComponent(rdbtnSatisfied03)
	    				.addComponent(rdbtnVerySatisfied03))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblCredibility)
	    				.addComponent(rdbtnVeryDissatisfied04)
	    				.addComponent(rdbtnDissatisfied04)
	    				.addComponent(rdbtnSatisfied04)
	    				.addComponent(rdbtnVerySatisfied04))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblQuality)
	    				.addComponent(rdbtnVeryDissatisfied05)
	    				.addComponent(rdbtnDissatisfied05)
	    				.addComponent(rdbtnSatisfied05)
	    				.addComponent(rdbtnVerySatisfied05))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblTecnicalQuality)
	    				.addComponent(rdbtnVeryDissatisfied06)
	    				.addComponent(rdbtnDissatisfied06)
	    				.addComponent(rdbtnSatisfied06)
	    				.addComponent(rdbtnVerySatisfied06))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblGeneral)
	    				.addComponent(rdbtnVeryDissatisfied07)
	    				.addComponent(rdbtnDissatisfied07)
	    				.addComponent(rdbtnSatisfied07)
	    				.addComponent(rdbtnVerySatisfied07))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(lblCriticismsAndSuggestions)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    
	    txtCriticismsAndSuggestions = new JTextArea();
	    scrollPane.setViewportView(txtCriticismsAndSuggestions);
	    panel.setLayout(gl_panel);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(ExternalSatisfactionResearchFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(ExternalSatisfactionResearchFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(ExternalSatisfactionResearchFrame.class.getResource("/resources/ClearFrame.png")));
	    
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
				if(e.getSource().equals(btnConfirm))register();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
	}
	
	private void register() {
		
		int i = ShowMessage.questionMessage(this, "Realizar", "Deseja Realmente concluir essa pesquisa com esses dados ?");
		if(i == JOptionPane.NO_OPTION)return;
		
		if(cboClient.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa porfavor selecione um cliente!");
			return;
		}
		Client c = (Client) cboClient.getSelectedItem();
		
		int comunication = -2;
		comunication = verifySelected(comunication, btnGroup01);
		if(comunication == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre comunicação!");
			return;
		}
		
		int courtesy = -2;
		courtesy = verifySelected(courtesy, btnGroup02);
		if(courtesy == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre cortesia!");
			return;
		}
		
		int effectiveness = -2;
		effectiveness = verifySelected(effectiveness, btnGroup03);
		if(effectiveness == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre efetividade!");
			return;
		}
		
		int credibility = -2;
		credibility = verifySelected(credibility, btnGroup04);
		if(credibility == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre credibilidade!");
			return;
		}
		
		int quality = -2;
		quality = verifySelected(quality, btnGroup05);
		if(quality == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre qualidade!");
			return;
		}
		
		int tecnicalQuality = -2;
		tecnicalQuality = verifySelected(tecnicalQuality, btnGroup06);
		if(tecnicalQuality == -2) {
			ShowMessage.errorMessage(this, "erro", "Erro ao realizar pesquisa responda a questão sobre qualidade técnica!");
			return;
		}
		
		int general = -2;
		general = verifySelected(general, btnGroup07);
		if(general == -2) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao realizar pesquisa responda a questão sobre a nota geral!");
			return;
		}
		String criticismsAndSuggestions = txtCriticismsAndSuggestions.getText();
		
		ExternalSatisfactionResearch esr = new ExternalSatisfactionResearch();
		esr.setComunication(comunication);
		esr.setCourtesy(courtesy);
		esr.setCredibility(credibility);
		esr.setCriticismsAndSuggestions(criticismsAndSuggestions);
		esr.setEffectiveness(effectiveness);
		esr.setGeneral(general);
		esr.setQuality(quality);
		esr.setTecnicalQuality(tecnicalQuality);
		esr.setClient(c);
		controller.register(esr);
		ShowMessage.successMessage(this, "Sucesso", "Sucesso ao realizar a pesquisa");
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente apagar todos os campos ?");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
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
		else if(string.equalsIgnoreCase("Insatisfeito")) {
			funtion = 0;
		}else if(string.equalsIgnoreCase("Muito Satisfeito")) {
			funtion = 2;
		}else if(string.equalsIgnoreCase("Satisfeito")) {
			funtion = 1;
		}else if(string.equalsIgnoreCase("Muito Insatisfeito")) {
			funtion = -1;
		}
		return funtion;
    }
}
