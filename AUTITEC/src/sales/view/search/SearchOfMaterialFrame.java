package sales.view.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.MaterialModel;
import model.MaterialType;
import model.MeasureUnit;
import model.Session;
import sales.controller.SalesController;
import sales.controller.SearchOfMaterialController;
import sales.view.update.MaterialUpdateFrame;
import userInterface.components.ComboBoxAutoCompletion;
import util.ShowMessage;

public class SearchOfMaterialFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -701640555656047134L;
	private JPanel principalPanel;

	private JSpinner spinner;
	private JSpinner spinnerMinimum;
	
	private JTable table;

	private JTextField txtName;
	
	private JLabel lblMaterialType;
	private JLabel lblMaximusAmmount;
	private JLabel lblQuantidadeMinima;
	private JLabel lblMeasureUnit;
	private JLabel lblName;

	private JButton btnSearch;

	private SearchOfMaterialController controller;
	private SalesController salesController;

	private JScrollPane scrollPaneTable;
	
	private JFrame frame = this;
	
	private JButton btnClear;

	private JComboBox<MaterialModel> cboModel;
	private JComboBox<MeasureUnit> cboMeasureUnit;
	private JComboBox<MaterialType> cboMaterialType;
	
	public SearchOfMaterialFrame() {
		controller = new SearchOfMaterialController();
		salesController = new SalesController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Consulta de Material");
		util.Icon.setIcon(this);
		setBounds(0, 0, 478, 587);
		setPreferredSize(new Dimension(478,587));
		setMinimumSize(new Dimension(478, 587));
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
		controller.queryAll(table);
	}

	private void initializePrincipal() {
		
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblName = new JLabel("Nome");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		lblMaximusAmmount = new JLabel("Quantidade Maxima");
		
		spinner = new JSpinner();
		
		lblQuantidadeMinima = new JLabel("Quantidade Minima");
		
		spinnerMinimum = new JSpinner();
		
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int number = (int) spinner.getValue();
				if(number <= 0)
					spinner.setValue(0);
				
			}
		});
		spinnerMinimum.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int number = (int) spinnerMinimum.getValue();
				if(number <= 0)
					spinnerMinimum.setValue(0);
				
			}
		});
		
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/view.png")));
		btnSearch.setSelectedIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ok.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblModel = new JLabel("Categoria");
		
		cboModel = new JComboBox<MaterialModel>();
		salesController.fillMaterialModels(cboModel);
		new ComboBoxAutoCompletion(cboModel);
		cboModel.setSelectedIndex(-1);
		
		lblMeasureUnit = new JLabel("Unidade de Medida");

		cboMeasureUnit = new JComboBox<MeasureUnit>();
		salesController.fillMeasureUnit(cboMeasureUnit);
		new ComboBoxAutoCompletion(cboMeasureUnit);
		cboMeasureUnit.setSelectedIndex(-1);
		
		lblMaterialType = new JLabel("Tipo");
		
		cboMaterialType = new JComboBox<MaterialType>();
		salesController.fillMaterialType(cboMaterialType);
		new ComboBoxAutoCompletion(cboMaterialType);
		cboMaterialType.setSelectedIndex(-1);
		
		separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaximusAmmount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblQuantidadeMinima)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerMinimum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblModel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboModel, 0, 391, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMeasureUnit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMeasureUnit, 0, 347, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblMaterialType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboMaterialType, 0, 418, Short.MAX_VALUE))
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximusAmmount)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantidadeMinima)
						.addComponent(spinnerMinimum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModel)
						.addComponent(cboModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMeasureUnit)
						.addComponent(cboMeasureUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterialType)
						.addComponent(cboMaterialType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		
		table = new JTable();
		String[] header = new String[] {"Material", "Quantidade" ,"Unidade de Medida"};
		table.setModel(new DefaultTableModel(null, header) {


            private static final long serialVersionUID = 6737039895901587009L;
			
            boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		principalPanel.setLayout(gl_principalPanel);
		
		initializeSub();
	}

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ClearFrame.png")));
	    btnExit = new JButton("Sair");
	    btnExit.setIcon(new ImageIcon(SearchOfMaterialFrame.class.getResource("/resources/ok.png")));
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    subPanel.add(btnClear);
	    subPanel.add(btnExit);
	    
    }

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnSearch))verify();
				else if(e.getSource().equals(btnClear))clear();
			}
		};
		btnSearch.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		KeyListener txtKeyListerner = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource().equals(txtName))
				{
					if(txtName.getText().isEmpty())
						controller.queryAll(table);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getSource().equals(txtName))
				{
					if(e.getKeyChar() == KeyEvent.VK_ENTER)
						verify();
					
				}
			}
		};
		txtName.addKeyListener(txtKeyListerner);
		table.addMouseListener(mouseListener);
		
	}
	
	MouseListener mouseListener = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2)
			{	
					int line = table.getSelectedRow();
					if(line > -1)
					{
					   if(!Session.getInstance().havePermission("UPD_MAT"))
						   ShowMessage.errorMessage(null,"Solicite permissão"," Você não tem permissão para atualizar/excluir materiais");
					   else{
						      MaterialUpdateFrame fr =  new MaterialUpdateFrame();
							  String materialName = table.getModel().getValueAt(line,0).toString();
							  Material material  = controller.getMaterialByName(materialName);
							  fr.setSelectedMaterial(material);
							  fr.addWindowListener(windowListener);
							  fr.setVisible(true);
						 
					   }
					   
					}
				}
			}
				
	};
	
	private void clear() {
		txtName.setText("");
		spinner.setValue(0);
		spinnerMinimum.setValue(0);
		cboMaterialType.setSelectedIndex(-1);
		cboMeasureUnit.setSelectedIndex(-1);
		cboModel.setSelectedIndex(-1);
	}
	
	
	private void verify() {
		int max = (int) spinner.getValue();
		int min = (int) spinnerMinimum.getValue();
		String name = txtName.getText();
		int materialModel = cboModel.getSelectedIndex() + 1;
		int measureUnit = cboMeasureUnit.getSelectedIndex() +1;
		int materialType = cboMaterialType.getSelectedIndex() + 1;
		controller.search(table, max, min, name, materialModel, materialType, measureUnit);		
	}
	WindowListener windowListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			controller.queryAll(table);
		}
	}; 
	private JLabel lblModel;
	private JButton btnExit;
	private JSeparator separator;
	
}
