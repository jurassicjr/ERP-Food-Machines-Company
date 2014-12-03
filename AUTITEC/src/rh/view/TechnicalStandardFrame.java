package rh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rh.controller.TechnicalStandardFrameController;
import model.TechnicalStandard;

import javax.swing.ImageIcon;

/**
 * Representa o frama para adição, remoção, alteração e remoção das normas técnicas
 */
public class TechnicalStandardFrame extends JFrame {

	private static final long serialVersionUID = 3422489151388088560L;
	
	private JPanel contentPane;
	private JTable table;
	private JButton btnAddTechnicalStandard;
	
	private TechnicalStandardFrameController controller;


	/**
	 * Cria o frame, inicializando os componentes gráficos e definindo os eventos
	 */
	public TechnicalStandardFrame() {
		
		controller = new TechnicalStandardFrameController(this);
		
		initialize();
		setTechnicalStandarts();
		setListeners();
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnAddTechnicalStandard = new JButton("Adicionar Norma Técnica");
		btnAddTechnicalStandard.setIcon(new ImageIcon(TechnicalStandardFrame.class.getResource("/resources/plus.png")));
		panel.add(btnAddTechnicalStandard);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Norma Técnica", "Última Atualização", "Ver", "Atualizar", "Remover"}
		)
		{
			
			private static final long serialVersionUID = 5591439288213423483L;
			
			Class<?>[] columnTypes = new Class[] {TechnicalStandardFrame.class, Object.class, Object.class, String.class, Object.class};
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		});
		
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(75);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);
        table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
				
	}
	
	/**
	 * Define na tabela as normas técnicas registradas
	 */
	private void setTechnicalStandarts() {
		
		TechnicalStandard technicalStandards[] = controller.getTechnicalStandards(); 
		
	}
	
	/**
	 * Adiciona uma linha na tabela de normas técnicas
	 */
	private void addTableRow() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.addRow(new Object[]{null, null, null, null, null});
		
	}

	/**
	 * Define listeners para a aplicação
	 */
	private void setListeners() { 
		
		btnAddTechnicalStandard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.addTechnicalStandard();				
			}
		});
		
	}
	
}
