package sales.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.ApprovedPTC;
import model.Employee;
import model.PTC;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.PTCApprovationController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.MD5;
import util.ShowMessage;

public class PTCApprovationFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -2518226410428968323L;
	private JPanel principalPanel;
	private DateField txtApprovationDate;
	private JTable openPTCTable;
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnConfirm;
	private JTextArea txtObservation;
	private JLabel lblApprovationDate;
	private JTable PTCsApprovedTable;
	private PTCApprovationController controller;
	private JLabel lblAprovadoPor;
	private JComboBox<Employee> cboApprovedBy;

	public PTCApprovationFrame() {
		controller = new PTCApprovationController(this);
		initialize();
		setListener();
	}

	private void initialize() {
	    setTitle("Aprovação de P.T.C");
	    setBounds(100, 100, 600, 600);
	    setPreferredSize(new Dimension(600, 600));
	    setMinimumSize(new Dimension(600, 600));
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    Icon.setIcon(this);
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblApprovationDate = new JLabel("Data de Aprovação");
	    
	    txtApprovationDate = CalendarFactory.createDateField();
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P.T.Cs em aberto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    
	    JScrollPane approvedPTCTable = new JScrollPane();
	    approvedPTCTable.setViewportBorder(new TitledBorder(null, "P.T.Cs Aprovadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane_2.setViewportBorder(new TitledBorder(null, "Observa\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    
	    lblAprovadoPor = new JLabel("Aprovado por:");
	    
	    cboApprovedBy = new JComboBox<Employee>();
	    controller.fillcboEmployee(cboApprovedBy);
	    new ComboBoxAutoCompletion(cboApprovedBy);
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
	    					.addContainerGap()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblApprovationDate)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtApprovationDate, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
	    				.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
	    					.addGap(12)
	    					.addComponent(approvedPTCTable, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
	    				.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(lblAprovadoPor)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboApprovedBy, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblApprovationDate)
	    				.addComponent(txtApprovationDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addComponent(approvedPTCTable, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblAprovadoPor)
	    				.addComponent(cboApprovedBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(16, Short.MAX_VALUE))
	    );
	    gl_principalPanel.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, approvedPTCTable, scrollPane_2});
	    
	    PTCsApprovedTable = new JTable();
	    String[] header = new String[] {"NOME", "Codigo de Rastreio", "Data de Criação"};
	    PTCsApprovedTable.setModel(new DefaultTableModel(null, header) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 2839693289369060367L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
            
            @Override
            public boolean isCellEditable(int row, int column) {
            	return columnEditables[column];
            }
	    });
	    approvedPTCTable.setViewportView(PTCsApprovedTable);
	    controller.fillApprovedTable(PTCsApprovedTable);
	    
	    
	    txtObservation = new JTextArea();
	    txtObservation.setLineWrap(true);
	    txtObservation.setWrapStyleWord(true);
	    scrollPane_2.setViewportView(txtObservation);
	    
	    openPTCTable = new JTable();
	    openPTCTable.setModel(new DefaultTableModel(null, header) {
	    	  
	    	
	    	/**
			 * 
			 */
            private static final long serialVersionUID = -6775046275667222448L;
			
            boolean[] columnEditables = new boolean[] {
						false, false, false
				};
	    	
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}
	    });
	    scrollPane.setViewportView(openPTCTable);
	    principalPanel.setLayout(gl_principalPanel);
	    controller.fillOpenTable(openPTCTable);
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(PTCApprovationFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(PTCApprovationFrame.class.getResource("/resources/ClearFrame.png")));
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(PTCApprovationFrame.class.getResource("/resources/ok.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
	
	private void setListener() {
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
			}
		};
		
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		KeyListener tableKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource().equals(openPTCTable)) showPTC(openPTCTable);
				else if(e.getKeyCode() ==  KeyEvent.VK_ENTER && e.getSource().equals(PTCsApprovedTable)) showPTC(openPTCTable);
			}
		};
		
		openPTCTable.addKeyListener(tableKeyListener);
		
		MouseListener tableMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getSource().equals(openPTCTable))showPTC(openPTCTable);
				else if(e.getClickCount() == 2 && e.getSource().equals(PTCsApprovedTable))showPTC(PTCsApprovedTable);
			}
		};
		PTCsApprovedTable.addMouseListener(tableMouseListener);
		openPTCTable.addMouseListener(tableMouseListener);
	}
	
	
	private void confirm() {
		if(!verify()) return;
		int i = ShowMessage.questionMessage(this, "Aprovação", "Deseja realmente aprovar a ptc " + openPTCTable.getValueAt(openPTCTable.getSelectedRow(), 0) + "?");
		if( i==JOptionPane.NO_OPTION)return;
		boolean a = assign();
		if(a) {
			ApprovedPTC aPTC = new ApprovedPTC();
			aPTC.setApprovedBy((Employee) cboApprovedBy.getSelectedItem());
			aPTC.setDate((Date) txtApprovationDate.getValue());
			aPTC.setObservation(txtObservation.getText());
			aPTC.setPtc((PTC) openPTCTable.getValueAt(openPTCTable.getSelectedRow(), 0));
			controller.register(aPTC);
			ShowMessage.successMessage(this, "Sucesso", "P.T.C aprovada com sucesso!");
			ClearFrame.clear(this);
			ClearFrame.clearTable(PTCsApprovedTable);
			ClearFrame.clearTable(openPTCTable);
			controller.fillOpenTable(openPTCTable);
			controller.fillApprovedTable(PTCsApprovedTable);
		}
	}
	private boolean verify() {
	    if(openPTCTable.getSelectedRow() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione uma PTC");
	    	return false;
	    }
	    if(txtApprovationDate.getValue() == null) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira a data de aprovação");
	    	return false;
	    }
	    if(txtObservation.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira alguma observação sobre essa aprovação!");
	    	return false;
	    }
	    if(cboApprovedBy.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione quem foi que autorizou a aprovação");
	    	return false;
	    }
		return true;
    }

	private boolean assign() {
		Employee e = (Employee) cboApprovedBy.getSelectedItem();
		String pass = JOptionPane.showInputDialog(this, "Insira a senha de quem autorizou a aprovação!");
		pass = MD5.getMD5Code(pass);
		System.out.println(pass);
		if(controller.verifyUser(e, pass)) {	
			ShowMessage.successMessage(this, "Sucesso", "Assinatura digital concluida com sucesso!");
			return true;
		}
		else {
			ShowMessage.errorMessage(this, "Erro", "Senha ou usuário inválido!");
			return false;
		}
	}

	private void showPTC(JTable table) {
		if(table.getSelectedRow() == -1)return;
		PTC p = (PTC) table.getValueAt(table.getSelectedRow(), 0);
		new PTCUpdateFrame(p).setVisible(true);
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos da janela de aproavção de P.T.C?");
		if(i == JOptionPane.YES_OPTION) {
			ClearFrame.clear(this);
		}
	}
}
