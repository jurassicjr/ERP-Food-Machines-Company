package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Employee;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import util.Icon;

public class MonitoringImplementationFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 8442893759386910295L;
	private JPanel principalPanel;
	private DateField txtMonitoringDate;
	private DateField txtNewDeadLine;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private JPanel subPanel;
	private JLabel lblMonitoringDate;
	private JLabel lblNonCompliance;
	private JLabel lblNewDeadLine;
	private JRadioButton rdbtnImplemented;
	private JRadioButton rdbtnNotImplemented;
	private JLabel lblApprovedBy;
	private JComboBox<Employee> cboApprovedBy;
	private JButton btnsign;
	private JComboBox<String> cboNonCompliance;

    public MonitoringImplementationFrame() {
    	initialize();
    }

	private void initialize() {
	    setTitle("Acompanhamento de Implementação");
	    Icon.setIcon(this);
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 573, 347);
	    setMinimumSize(new Dimension(573, 347));
	    setPreferredSize(new Dimension(573,  347));
	    getContentPane().setLayout(new BorderLayout(0,0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblNonCompliance = new JLabel("Não Conformidade");
	    
	    cboNonCompliance = new JComboBox<String>();
	    
	    lblMonitoringDate = new JLabel("Data da verificação");
	    
	    txtMonitoringDate = CalendarFactory.createDateField();
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Acompanhamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    
	    rdbtnImplemented = new JRadioButton("Implementado");
	    
	    rdbtnNotImplemented = new JRadioButton("Não Implementado");
	    
	    lblNewDeadLine = new JLabel("Novo prazo:");
	    
	    txtNewDeadLine = CalendarFactory.createDateField();
	    
	    lblApprovedBy = new JLabel("Aprovado por");
	    
	    cboApprovedBy = new JComboBox<Employee>();
	    
	    btnsign = new JButton("Assinar");
	    
	    btnsign.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/assign.png")));
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblNonCompliance)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboNonCompliance, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblMonitoringDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtMonitoringDate, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING, false)
	    						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblApprovedBy)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboApprovedBy, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    							.addComponent(rdbtnImplemented)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnNotImplemented)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblNewDeadLine)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtNewDeadLine, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnsign)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblNonCompliance)
	    				.addComponent(cboNonCompliance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblMonitoringDate)
	    				.addComponent(txtMonitoringDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(rdbtnImplemented)
	    				.addComponent(rdbtnNotImplemented)
	    				.addComponent(lblNewDeadLine)
	    				.addComponent(txtNewDeadLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblApprovedBy)
	    				.addComponent(cboApprovedBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnsign))
	    			.addContainerGap(120, Short.MAX_VALUE))
	    );
	    principalPanel.setLayout(gl_principalPanel);
	    initializeSub();
	    
    }

	private void initializeSub() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/Clearframe.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
}
