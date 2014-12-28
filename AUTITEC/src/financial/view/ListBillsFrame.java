package financial.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListBillsFrame extends JFrame {

	private static final long serialVersionUID = -2945947774382521011L;
	
	private JTable billsTable;
	
	public ListBillsFrame() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Pagamento de Conta");
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		billsTable = new JTable();
		billsTable.setModel(new DefaultTableModel(null, new String[] {"Conta a Pagar", "Valor", "Parcelas"}
		){
			
			private static final long serialVersionUID = -2428534803601038088L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		billsTable.getColumnModel().getColumn(0).setPreferredWidth(183);
		scrollPane.setViewportView(billsTable);
	}

}
