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

import model.Procedure;
import model.ProcedureVersion;
import rh.controller.ProcedureFrameController;
import util.Icon;

public class ProcedureFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -6404070636818945684L;

	private JPanel contentPane;
	private JTable table;
	private JButton btnAddProcedure;
	
	private ProcedureFrameController controller;


	/**
	 * Cria o frame, inicializando os componentes gráficos e definindo os eventos
	 */
	public ProcedureFrame() {
		
		controller = new ProcedureFrameController(this);
		
		initialize();
		setProcedures();
		setListeners();
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		Icon.setIcon(this);
		setTitle("Registro/Atualização/Visuzalização de Procedimentos!");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnAddProcedure = new JButton("Adicionar Norma Técnica");
		btnAddProcedure.setIcon(new ImageIcon(TechnicalStandardFrame.class.getResource("/resources/plus.png")));
		panel.add(btnAddProcedure);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//User u = Session.getInstance().getUser();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Procedimento", "Última Atualização", "Visualizar", "Atualizar", "Remover"}
		)
		{
				
			private static final long serialVersionUID = 5591439288213423483L;
			
			public boolean editable[] = new boolean[]{false, false, true, true, true};
												
			@Override
			public boolean isCellEditable(int row, int column) {
				return editable[column];
			}
				
		});		

		new ButtonColumnProcedure(table, 2, new ImageIcon(getClass().getResource("/resources/view.png")), controller);
		new ButtonColumnProcedure(table, 3, new ImageIcon(getClass().getResource("/resources/update.png")), controller);
		new ButtonColumnProcedure(table, 4, new ImageIcon(getClass().getResource("/resources/cancel.png")), controller);
		
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
	 * Define na tabela as normas técnicas registradas
	 */
	private void setProcedures() {
		
		Procedure procedures[] = controller.getTechnicalStandards();
				
		for(int i = 0; i < procedures.length; ++i) {
			
			Procedure pc = procedures[i];
			ProcedureVersion lastUpdate = pc.getLastUpdate();
			
			addTableRow();
			
			table.setValueAt(pc, i, 0);
			table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(lastUpdate.getDateUpdate()), i, 1);			
			table.setValueAt("Visualizar", i, 2);
			table.setValueAt("Atualizar", i, 3);
			table.setValueAt("Remover", i, 4);
			
		}		
		
	}
	
	/**
	 * Adiciona uma linha na tabela de Procedimentos
	 */
	private void addTableRow() {
	
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.addRow(new Object[]{null, null, null, null, null});
		
	}

	/**
	 * Define listeners para a aplicação
	 */
	private void setListeners() { 
		
		btnAddProcedure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.addProcedure(table);				
			}
		});
		
	}
	
}
