package rh.view.search;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.itextpdf.text.List;

import model.Employee;
import net.sf.nachocalendar.components.DateField;
import rh.controller.RncSearchFrameController;
import userInterface.components.FrameController;
import userInterface.components.RncTableCellRenderer;
import util.Icon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class RncSearchFrame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RncSearchFrameController controller;
	private DateField txtInitialDate;
	private DateField txtFinalDate;
	private JTable tableRnc;
	private JComboBox<Employee> cboEmitter;
	private JButton btnSearch ;
	private JButton btnClearSearch;
	private RncSearchFrame thisFrame = this;
	private String frameName = "Consulta de RNC";
	
	
	public RncSearchFrame()
	{
		controller = new RncSearchFrameController();
		initialize();
		setListeners();
	}
	
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 549,401);
		setMinimumSize(new Dimension(549, 401));
		setPreferredSize(new Dimension(549,401));
		initializePrincipal();
		controller.fillEmployeeCbo(cboEmitter);
		getRncs();
		tableRnc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRnc.setColumnSelectionAllowed(false);
	}
	public void getRncs()
	{	
		controller.fillRncTable(tableRnc);
		tableRnc.getColumnModel().getColumn(2).setCellRenderer(new RncTableCellRenderer());
	}
	public void setListeners()
	{
		ActionListener buttonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))
				{
				}
			}
		};
		btnSearch.addActionListener(buttonActions);
		btnClearSearch.addActionListener(buttonActions);
		
		FrameController.addConfirmationOnClose(thisFrame, frameName);
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JPanel panelRncSearch = new JPanel();
		panelRncSearch.setBounds(0, -23, 539, 383);
		getContentPane().add(panelRncSearch);
		panelRncSearch.setLayout(null);
		
		JLabel lblInitialDate = new JLabel("Dt.Inicial");
		lblInitialDate.setBounds(12, 90, 70, 15);
		panelRncSearch.add(lblInitialDate);
		
		txtInitialDate = new DateField();
		txtInitialDate.setBounds(89, 80, 113, 25);
		panelRncSearch.add(txtInitialDate);
		
		cboEmitter = new JComboBox<Employee>();
		cboEmitter.setBounds(12, 41, 413, 24);
		panelRncSearch.add(cboEmitter);
		
		JLabel lblEmitter = new JLabel("Emissor");
		lblEmitter.setBounds(12, 24, 70, 15);
		panelRncSearch.add(lblEmitter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 156, 515, 227);
		panelRncSearch.add(scrollPane);
		
		tableRnc = new JTable();
		scrollPane.setViewportView(tableRnc);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(433, 41, 94, 25);
		panelRncSearch.add(btnSearch);
		
		btnClearSearch = new JButton("<html><center>Limpar<br>busca</center></html>");
		btnClearSearch.setBounds(433, 80, 94, 50);
		panelRncSearch.add(btnClearSearch);
		tableRnc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		txtFinalDate = new DateField();
		txtFinalDate.setBounds(273, 80, 113, 25);
		panelRncSearch.add(txtFinalDate);
		
		JLabel lblFinalDate = new JLabel("Dt. Final");
		lblFinalDate.setBounds(206, 90, 70, 15);
		panelRncSearch.add(lblFinalDate);
		
		JCheckBox chkbShowInactives = new JCheckBox("Mostrar Rnc(s) Inativas");
		chkbShowInactives.setBounds(12, 125, 222, 23);
		panelRncSearch.add(chkbShowInactives);
	}
}
