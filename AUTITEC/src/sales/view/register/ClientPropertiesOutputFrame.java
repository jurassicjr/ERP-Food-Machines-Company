package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.ClientProrpeties;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import sales.controller.ClientPropertiesOutputController;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class ClientPropertiesOutputFrame extends JFrame {

	/**
	 * 
	 */
    private static final long serialVersionUID = -81792765345520035L;
	private DateField txtExitDate;
	private JTextField textField_1;
	private JLabel lblClientProperties;
	private JComboBox<ClientProrpeties> cboClientProperties;
	private JPanel subPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private ClientPropertiesOutputController controller;

    public ClientPropertiesOutputFrame() {
    	controller = new ClientPropertiesOutputController(this);
    	initialize();
    	setListener();
    }

	private void initialize() {
	    setTitle("Saida de Propriedades do Cliente");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 630, 270);
	    setMinimumSize(new Dimension(630,270));
	    setPreferredSize(new Dimension(630, 270));
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblClientProperties = new JLabel("Propriedade do Cliente");
	    
	    cboClientProperties = new JComboBox<ClientProrpeties>();
	    
	    JLabel lblExitDate = new JLabel("Data de Saída");
	    
	    txtExitDate = CalendarFactory.createDateField();
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Motivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    JLabel lblNotaFiscalDe = new JLabel("Nota Fiscal de Saida");
	    
	    textField_1 = new JTextField();
	    textField_1.setColumns(10);
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
	    				.addGroup(Alignment.TRAILING, gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblClientProperties)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboClientProperties, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblExitDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtExitDate, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblNotaFiscalDe)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblClientProperties)
	    				.addComponent(cboClientProperties, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblExitDate)
	    				.addComponent(txtExitDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblNotaFiscalDe)
	    				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(21, Short.MAX_VALUE))
	    );
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setLineWrap(true);
	    scrollPane.setViewportView(textArea);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(ClientPropertiesOutputFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(ClientPropertiesOutputFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(ClientPropertiesOutputFrame.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnCancel);
	    subPanel.add(btnClear);
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
				if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		
		btnConfirm.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	
	private void confirm() {
		if(cboClientProperties.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar saide de propiedades do cliente!\nSelecione uma propriedade do cliente");
		}
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os dados?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
}
