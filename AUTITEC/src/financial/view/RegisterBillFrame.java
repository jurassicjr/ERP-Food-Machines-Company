package financial.view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.sf.nachocalendar.components.DateField;
import userInterface.components.RealNumberField;
import util.Icon;
import financial.controller.RegisterBillFrameController;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class RegisterBillFrame extends JFrame {

	private static final long serialVersionUID = -4336339858592180045L;
	
	private JPanel contentPane;
		
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
		
	private RealNumberField txValue;
	private DateField txPayDate;
	
	private JTextArea txObservation;
	
	private JComboBox cbGroup;
	private JComboBox cbSubGroup;
	private JComboBox cbBill;
	
	private RegisterBillFrameController controller;
	private JLabel lblCredor;
	private JTextField txCreditor;
	
		
	public RegisterBillFrame() {
		
		initialize();
		setListeners();
		
		controller = new RegisterBillFrameController(this);		
	}

	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 506, 513);
		setMinimumSize(new Dimension(506, 400));
		setTitle("Registrar Conta a Pagar");
		Icon.setIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblGroup = new JLabel("Grupo");
		cbGroup = new JComboBox();
		
		JLabel lblSubGroup = new JLabel("Subgrupo");
		cbSubGroup = new JComboBox();
		
		JLabel lblBill = new JLabel("Conta");
		cbBill = new JComboBox();
		
		JLabel lblValue = new JLabel("Valor");
		txValue = new RealNumberField();
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		txPayDate = new DateField();
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblCredor = new JLabel("Credor");
		
		txCreditor = new JTextField();
		txCreditor.setColumns(10);
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblGroup)
							.addGap(18)
							.addComponent(cbGroup, 0, 413, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblSubGroup)
							.addGap(18)
							.addComponent(cbSubGroup, 0, 396, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblBill)
							.addGap(18)
							.addComponent(cbBill, 0, 413, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblValue)
							.addGap(18)
							.addComponent(txValue, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataDeVencimento)
							.addGap(18)
							.addComponent(txPayDate, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblCredor)
							.addGap(18)
							.addComponent(txCreditor, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGroup)
						.addComponent(cbGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubGroup)
						.addComponent(cbSubGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBill)
						.addComponent(cbBill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValue)
						.addComponent(txValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeVencimento)
						.addComponent(txPayDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCredor)
						.addComponent(txCreditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txObservation = new JTextArea();
		txObservation.setLineWrap(true);
		txObservation.setWrapStyleWord(true);
		
		
		scrollPane.setViewportView(txObservation);
		scrollPane.setBorder(new TitledBorder("Obserações"));
		panel.setLayout(layout);
		
		JPanel buttonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/cancel.png")));
		buttonsPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/clear.png")));
		buttonsPanel.add(btnClear);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterBillFrame.class.getResource("/resources/ok.png")));
		buttonsPanel.add(btnRegister);
		
	}

	private void setListeners() {
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnClear)) controller.clear();
				else if(e.getSource().equals(btnRegister)) register();
				
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnClear.addActionListener(btnListener);
		btnRegister.addActionListener(btnListener);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
	}
	
	private void register() {
				
//		String bill = txBill.getText();
//		String creditor = txCreditor.getText();
//		Date dueDate = (Date) txDueDate.getValue();
//		int installments = cbInstallments.getSelectedIndex() + 1;
//		double value = txParcelValue.getValue();
//		String observation = txObservations.getText();
//		
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		controller.register(bill, creditor, dueDate, installments, observation, value);
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
