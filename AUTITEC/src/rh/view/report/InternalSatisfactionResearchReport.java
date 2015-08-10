package rh.view.report;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.sf.jasperreports.engine.JRException;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.InternalSatisfactionResearchReportController;
import userInterface.components.FileChooser;
import util.ClearFrame;
import util.ShowMessage;

public class InternalSatisfactionResearchReport extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -4380441754879789314L;

    
    
    private JPanel principalPanel;
	
    private JTextField txtReportFile;
	private DateField txtInicialDate;
	private DateField txtFinalDate;
	
	private JLabel lblInicialDate;
	private JLabel lblFinalDate;
	private JLabel lblReportArchive;
	
	private JCheckBox ckOpenFile;
	
	private JButton btnSelectFile;
	
	private JPanel subPanel;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private InternalSatisfactionResearchReportController controller;
	private FileChooser fileChooser;
	
	public InternalSatisfactionResearchReport() {
		initialize();
		setListeners();
		controller = new InternalSatisfactionResearchReportController(this);
		fileChooser = new FileChooser();
	}

	private void initialize() {
		setTitle("Relatório de Pesquisa de Satisfação Interna");
	    setBounds(100, 100, 600, 196);
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setMinimumSize(new Dimension(600,196));
	    setPreferredSize(new Dimension(600,196));
	    getContentPane().setLayout(new BorderLayout(0,0));
	    initializePrincipal();
	}

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblReportArchive = new JLabel("Arquivo de Relatório");
	    
	    txtReportFile = new JTextField();
	    txtReportFile.setColumns(10);
	    
	    btnSelectFile = new JButton("Selecionar Arquivo");
	    btnSelectFile.setIcon(new ImageIcon(ExternalSatisfactionResearchReport.class.getResource("/resources/open.png")));
	    
	    lblInicialDate = new JLabel("Data Inicial");
	    
	    txtInicialDate = CalendarFactory.createDateField();
	    
	    
	    lblFinalDate = new JLabel("Data Final");
	    
	    txtFinalDate = CalendarFactory.createDateField();
	    
	    ckOpenFile = new JCheckBox("Abrir Relatório Criado");
	    
	    JSeparator separator = new JSeparator();
	    
	    JLabel lblFuncionrio = new JLabel("Funcionário");
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
	    				.addComponent(ckOpenFile)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblReportArchive)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtReportFile, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnSelectFile))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblInicialDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtInicialDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(lblFinalDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtFinalDate, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblFuncionrio)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblReportArchive)
	    				.addComponent(txtReportFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnSelectFile))
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(18)
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    								.addComponent(txtInicialDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(txtFinalDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(20)
	    							.addComponent(lblInicialDate)))
	    					.addGap(18)
	    					.addComponent(ckOpenFile))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(20)
	    					.addComponent(lblFuncionrio))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(20)
	    					.addComponent(lblFinalDate)))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    principalPanel.setLayout(gl_principalPanel);
	    initializeSub();
	}

	private void initializeSub() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(ExternalSatisfactionResearchReport.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(ExternalSatisfactionResearchReport.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(ExternalSatisfactionResearchReport.class.getResource("/resources/ClearFrame.png")));
	    
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
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnSelectFile))controller.selectOutput(fileChooser, txtReportFile);
			}
		};
		btnConfirm.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnSelectFile.addActionListener(buttonListener);
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja relamente Limpar os Campos ?");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void confirm() {
		Map<String, Object> map = new HashMap<String, Object>();
		java.util.Date iDate = (java.util.Date) txtInicialDate.getValue();
		java.util.Date fDate = (java.util.Date) txtFinalDate.getValue();
		Date inicialDate = new Date(iDate.getTime());
		Date finalDate = new Date(fDate.getTime());
		map.put("inicialDate", inicialDate);
		map.put("finalDate", finalDate);
		
		int i = ShowMessage.questionMessage(this, "Gerar", "Deseja realmente gerar o relatório ?");
		if(i == JOptionPane.NO_OPTION)return;
		String reportFilePath = txtReportFile.getText();
		boolean openFile = ckOpenFile.isSelected();

		getContentPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
	        controller.generateReport(reportFilePath, openFile, map);
        } catch (JRException | IOException e) {
	        e.printStackTrace();
        }
		getContentPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
