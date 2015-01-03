package financial.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.sf.nachocalendar.components.DateField;
import userInterface.components.FileChooser;
import financial.controller.GenerateReportFrameController;

public class GenerateReportFrame extends JFrame {
	
	private static final long serialVersionUID = 5225909404198325365L;
	
	private JTextField txReportFile;
	
	private JComboBox<String> cbBills;
	
	private JCheckBox ckIncludeOpenBills;
	private JCheckBox ckOpenFile;
	
	private DateField txStartDate;
	private DateField txEndDate;
	
	private JButton btnCancel;
	private JButton btnGenerate;
	private JButton btnSelectFile;
		
	private FileChooser fileChooser;
	
	private GenerateReportFrameController controller;
	
	/**
	 * Create the frame.
	 */
	public GenerateReportFrame() {
		
		controller = new GenerateReportFrameController(this);
		fileChooser = new FileChooser(this);
		
		initialize();
		setListeners();
		
		txReportFile.setText("C:\\Users\\Pedro\\Desktop\\Grade Curricular.pdf");
		cbBills.setSelectedIndex(1);
				
		txStartDate.setValue(new GregorianCalendar(2014, 11, 31).getTime());
		txEndDate.setValue(new GregorianCalendar(2015, 01, 01).getTime());
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 250);
		setMinimumSize(new Dimension(595, 195));
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(GenerateReportFrame.class.getResource("/resources/cancel.png")));
		panel.add(btnCancel);
		
		btnGenerate = new JButton("Criar Relatório");
		btnGenerate.setIcon(new ImageIcon(GenerateReportFrame.class.getResource("/resources/ok.png")));
		panel.add(btnGenerate);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.CENTER);
		
		JLabel lblReportFile = new JLabel("Arquivo de Relatório");
		txReportFile = new JTextField();
		txReportFile.setEditable(false);
		
		JLabel lblBills = new JLabel("Contas");
		cbBills = new JComboBox<String>();
		cbBills.setModel(new DefaultComboBoxModel<String>(new String[] {"Contas a Pagar e a Receber", "Contas a Pagar", "Contas a Receber"}));
		cbBills.setSelectedIndex(-1);
		
		ckIncludeOpenBills = new JCheckBox("Incluir Contas em Aberto");
		ckOpenFile = new JCheckBox("Abrir Arquivo Criado");
		
		btnSelectFile = new JButton("Selecionar Arquivo");
		btnSelectFile.setIcon(new ImageIcon(GenerateReportFrame.class.getResource("/resources/open.png")));
		
		JLabel lblIStartDate = new JLabel("Data Inicial");
		txStartDate = new DateField();
		txStartDate.setValue(null);
		
		JLabel lblEndDate = new JLabel("Data Final");
		txEndDate = new DateField();
		txEndDate.setValue(null);
				
		GroupLayout layout = new GroupLayout(btnPanel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblReportFile)
							.addGap(18)
							.addComponent(txReportFile, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSelectFile))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblBills)
							.addGap(18)
							.addComponent(cbBills, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(ckIncludeOpenBills, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ckOpenFile, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblIStartDate)
							.addGap(18)
							.addComponent(txStartDate, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblEndDate)
							.addGap(18)
							.addComponent(txEndDate, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReportFile)
						.addComponent(txReportFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectFile))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBills)
						.addComponent(cbBills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ckIncludeOpenBills)
						.addComponent(ckOpenFile))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIStartDate)
						.addComponent(txStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndDate)
						.addComponent(txEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		btnPanel.setLayout(layout);
		
	}

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
				else if(e.getSource().equals(btnSelectFile)) controller.selectOutput(fileChooser, txReportFile);
				else if(e.getSource().equals(btnGenerate)) generateReport(); 
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnGenerate.addActionListener(btnListener);
		btnSelectFile.addActionListener(btnListener);
		
	}
	
	private void generateReport() {
		
		String reportFilePath = txReportFile.getText();
		int bills = cbBills.getSelectedIndex();
		boolean includeOpenBills = ckIncludeOpenBills.isSelected();
		boolean openFile = ckOpenFile.isSelected();
		Date startDate = (Date) txStartDate.getValue();
		Date endDate = (Date) txEndDate.getValue();
				
		controller.generateReport(reportFilePath, bills, includeOpenBills, openFile, startDate, endDate);
		
	}


}
