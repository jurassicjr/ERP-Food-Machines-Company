package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.ClientProperties;
import model.ClientPropertiesMaterial;
import model.ClientPropertiesOutput;
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
	private JTextField txtOutputFiscalNote;
	private JLabel lblClientProperties;
	private JComboBox<ClientProperties> cboClientProperties;
	private JPanel subPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private ClientPropertiesOutputController controller;
	private JScrollPane scrollPaneOutputItens;
	private JButton btnAdd;
	private JTable tblOutputItens;
	private JTable tblPropertiesItens;
	private JScrollPane scrollPanePropertiesItens;
	private JLabel lblOutputFiscalNote;
	private JLabel lblExitDate;
	private JScrollPane scrollPaneMotive;
	private JButton btnRemove;
	private JTextArea txtMotive;

    public ClientPropertiesOutputFrame() {
    	controller = new ClientPropertiesOutputController(this);
    	initialize();
    	setListener();
    }

	private void initialize() {
	    setTitle("Saida de Propriedades do Cliente");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 630, 414);
	    setMinimumSize(new Dimension(630,414));
	    setPreferredSize(new Dimension(630, 414));
	    Icon.setIcon(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblClientProperties = new JLabel("Propriedade do Cliente");
	    
	    cboClientProperties = new JComboBox<ClientProperties>(); 
	    controller.fillCbo(cboClientProperties);
	    
	    lblExitDate = new JLabel("Data de Saída");
	    
	    txtExitDate = CalendarFactory.createDateField();
	    
	    scrollPaneMotive = new JScrollPane();
	    scrollPaneMotive.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Motivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    scrollPaneMotive.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    lblOutputFiscalNote = new JLabel("Nota Fiscal de Saida");
	    
	    txtOutputFiscalNote = new JTextField();
	    txtOutputFiscalNote.setColumns(10);
	    
	    scrollPanePropertiesItens = new JScrollPane();
	    scrollPanePropertiesItens.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    scrollPaneOutputItens = new JScrollPane();
	    scrollPaneOutputItens.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    btnAdd = new JButton("Adicionar");
	    btnAdd.setIcon(new ImageIcon(ClientPropertiesOutputFrame.class.getResource("/resources/plus.png")));
	    
	    btnRemove = new JButton("Remover");
	    btnRemove.setIcon(new ImageIcon(ClientPropertiesOutputFrame.class.getResource("/resources/clear.png")));
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblClientProperties)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboClientProperties, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblExitDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtExitDate, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblOutputFiscalNote)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtOutputFiscalNote, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
	    				.addComponent(scrollPaneMotive, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(scrollPanePropertiesItens, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    						.addComponent(btnAdd)
	    						.addComponent(btnRemove))
	    					.addGap(18)
	    					.addComponent(scrollPaneOutputItens, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
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
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPaneOutputItens, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	    				.addGroup(Alignment.LEADING, gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(scrollPanePropertiesItens, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
	    					.addGroup(gl_principalPanel.createSequentialGroup()
	    						.addComponent(btnAdd)
	    						.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
	    						.addComponent(btnRemove))))
	    			.addGap(18)
	    			.addComponent(scrollPaneMotive, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblOutputFiscalNote)
	    				.addComponent(txtOutputFiscalNote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap())
	    );
	    
	    tblPropertiesItens = new JTable();
	    scrollPanePropertiesItens.setViewportView(tblPropertiesItens);
	    String[] propertiesTableHeader = new String[] {"Propriedade", "Quantidade"};
	    tblPropertiesItens.setModel(new DefaultTableModel(null, propertiesTableHeader) {
			/**
			 * 
			 */
            private static final long serialVersionUID = -6082287351924083275L;
	    	
            boolean[] columnEditables = new boolean[] {
					false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
            
	    });
	    
	    tblOutputItens = new JTable();
	    String[] outputHeader = new String[] {"Propriedade", "Quantidade"};
	    tblOutputItens.setModel(new DefaultTableModel(null, outputHeader) {

			/**
			 * 
			 */
            private static final long serialVersionUID = 3589918699777274860L;
	    	
            
            boolean[] columnEditables = new boolean[] {
            		false, false
            };
            
            @Override
            public boolean isCellEditable(int row, int column) {
            	return columnEditables[column];
            };
            
	    });
	    scrollPaneOutputItens.setViewportView(tblOutputItens);
	    
	    txtMotive = new JTextArea();
	    txtMotive.setLineWrap(true);
	    scrollPaneMotive.setViewportView(txtMotive);
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
				else if(e.getSource().equals(btnAdd))addItemToTable();
				else if(e.getSource().equals(btnRemove))removeItemFromTable();
			}
		};
		
		btnConfirm.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnAdd.addActionListener(buttonListener);
		btnRemove.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboClientProperties))fillTable(tblPropertiesItens);
			}
		};
		
		cboClientProperties.addActionListener(cboListener);
	}
	
	private void removeItemFromTable() {
		if(tblOutputItens.getSelectedRow() == -1)return;
		int selection = tblOutputItens.getSelectedRow();
		DefaultTableModel tbl = (DefaultTableModel) tblOutputItens.getModel();
		ClientPropertiesMaterial cp = (ClientPropertiesMaterial) tbl.getValueAt(selection, 0);
		int ammount = (int) tbl.getValueAt(selection, 1);
		tbl.removeRow(selection);
		Object[] insert = new Object[] {cp, ammount};
		DefaultTableModel propertiesTbl = (DefaultTableModel) tblPropertiesItens.getModel();
		propertiesTbl.addRow(insert);
	}
	
	private void fillTable(JTable table) {
		ClearFrame.clearTable(table);
		if(cboClientProperties.getSelectedIndex() == -1)return;
		ClientProperties cp = (ClientProperties) cboClientProperties.getSelectedItem();
		List<ClientPropertiesMaterial> cpmList = cp.getPropertiesList();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		cpmList.forEach(cpm -> tbl.addRow(new Object[] {cpm, cpm.getAmmount()}));
	}
	
	private void addItemToTable() {
		if(tblPropertiesItens.getSelectedRow() == -1)return;
		int selection = tblPropertiesItens.getSelectedRow();
		DefaultTableModel tbl = (DefaultTableModel) tblPropertiesItens.getModel();
		ClientPropertiesMaterial cp = (ClientPropertiesMaterial) tbl.getValueAt(selection, 0);
		int ammount = (int) tbl.getValueAt(selection, 1);
		tbl.removeRow(selection);
		Object[] insert = new Object[] {cp, ammount};
		DefaultTableModel outputTbl = (DefaultTableModel) tblOutputItens.getModel();
		outputTbl.addRow(insert);
	}
	
	private void confirm() {
		int  i = ShowMessage.questionMessage(this, "Registro", "Deseja realmente confirmar a devolução dessas propriedades ?");
		if(i == JOptionPane.NO_OPTION)return;
		
		if(cboClientProperties.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar saide de propiedades do cliente!\nSelecione uma propriedade do cliente");
		}
		if(tblOutputItens.getRowCount() == 0) {
			ShowMessage.errorMessage(this, "Erro", "Insira ao menos uma propriedade para devolução");
			return;
		}
		if(txtOutputFiscalNote.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a nota fiscal de saida dessas propriedades!");
			return;
		}
		if(txtMotive.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira o motive da devolução!");
			return;
		}
		boolean propertiesEnd = false;
		if(tblPropertiesItens.getRowCount() == 0)propertiesEnd = true;
		
		Date exitDate = (Date) txtExitDate.getValue();
		String motive = txtMotive.getText();
		String outputFiscalNote = txtOutputFiscalNote.getText();
		ClientProperties cp = (ClientProperties) cboClientProperties.getSelectedItem();
		List<ClientPropertiesMaterial> cpmList = getOutputList();
		ClientPropertiesOutput cpo = new ClientPropertiesOutput();
		cpo.setExitDate(exitDate);
		cpo.setExitFiscalNote(outputFiscalNote);
		cpo.setMotive(motive);
		cpo.setCpmList(cpmList);
		cpo.setCp(cp);
		controller.register(cpo, propertiesEnd);
		ShowMessage.successMessage(this, "Sucesso", "Sucesso ao registrar devolução de propriedade do cliente!");
		ClearFrame.clear(this);
		controller.fillCbo(cboClientProperties);
	}
	
	private List<ClientPropertiesMaterial> getOutputList() {
	    int size = tblOutputItens.getRowCount();
	    List<ClientPropertiesMaterial> cpmList = new ArrayList<ClientPropertiesMaterial>();
	    DefaultTableModel tbl = (DefaultTableModel) tblOutputItens.getModel();
	    for(int i = 0; i<size; i++) {
	    	ClientPropertiesMaterial cpm = (ClientPropertiesMaterial) tbl.getValueAt(i, 0);
	    	cpmList.add(cpm);
	    }
		return cpmList;
    }

	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os dados?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
}
