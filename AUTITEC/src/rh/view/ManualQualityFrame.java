package rh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.ManualQuality;
import model.ManualQualityVersion;
import rh.controller.ManualQualityFrameController;
import util.Icon;

/**
 * Representa o frama para adição, remoção, alteração e remoção do Manual de Qualidade
 */
public class ManualQualityFrame extends JFrame{

	
	/**
	 * 
	 */
    private static final long serialVersionUID = -4756840964052837801L;
	
    private JPanel contentPane;
	private JTable table;
	private JButton btnAddQualityManual;
	
	private ManualQualityFrameController controller;


	/**
	 * Cria o frame, inicializando os componentes gráficos e definindo os eventos
	 */
	public ManualQualityFrame(){
		
		controller = new ManualQualityFrameController(this);
		
		initialize();
		setQualityManuals();
		setListeners();
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		Icon.setIcon(this);
		setTitle("Registro/Remoção/Atualização do Manual de Qualidade");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnAddQualityManual = new JButton("Adicionar Manual de Qualidade");
		btnAddQualityManual.setIcon(new ImageIcon(TechnicalStandardFrame.class.getResource("/resources/plus.png")));
		panel.add(btnAddQualityManual);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//User u = Session.getInstance().getUser();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Manual de Qualidade", "Última Atualização", "Visualizar", "Atualizar", "Remover"}
		)
		{
				
			private static final long serialVersionUID = 5591439288213423483L;
			
			public boolean editable[] = new boolean[]{false, false, true, true, true};
												
			@Override
			public boolean isCellEditable(int row, int column) {
				return editable[column];
			}
				
		});		

		new ButtonColumnsManualQuality(table, 2, new ImageIcon(getClass().getResource("/resources/view.png")), controller);
		new ButtonColumnsManualQuality(table, 3, new ImageIcon(getClass().getResource("/resources/update.png")), controller);
		new ButtonColumnsManualQuality(table, 4, new ImageIcon(getClass().getResource("/resources/cancel.png")), controller);
		
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(75);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
				
	}
	
	/**
	 * Define na tabela aos manuais de qualidade registrados
	 */
	private void setQualityManuals() {
		
		ManualQuality[] QualityManual = controller.getManualsQuality();
				
		for(int i = 0; i < QualityManual.length; ++i) {
			
			ManualQuality mq = QualityManual[i];
			ManualQualityVersion lastUpdate = mq.getLastUpdate();
			
			addTableRow();
			
			table.setValueAt(mq, i, 0);
			table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(lastUpdate.getDateUpdate()), i, 1);			
			table.setValueAt("Visualizar", i, 2);
			table.setValueAt("Atualizar", i, 3);
			table.setValueAt("Remover", i, 4);
			
		}		
		
	}
	
	/**
	 * Adiciona uma linha na tabela do manual de qualidade
	 */
	private void addTableRow() {
	
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.addRow(new Object[]{null, null, null, null, null});
		
	}

	/**
	 * Define listeners para a aplicação
	 */
	private void setListeners() { 
		
		btnAddQualityManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addManualQuality(table);				
			}
		});
		
	}
	
}
