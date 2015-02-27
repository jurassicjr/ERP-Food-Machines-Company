package financial.view;

import javax.swing.JDialog;

//
//import java.awt.BorderLayout;
//import java.awt.Cursor;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.TitledBorder;
//
//import model.Bill;
//import userInterface.components.RealNumberField;
//import util.Icon;
//import financial.controller.PayBillFrameController;
//
public class PayBillFrame extends JDialog {
//
//	private static final long serialVersionUID = -5976359939392792276L;
//	
//	private JTextField txBill;
//	private JTextField txCreditor;
//	private JTextField txValue;
//	private RealNumberField txPayedValue;
//	private JTextArea txObservation;
//		
//	private Bill bill;
//	private double value;
//	
//	private JButton btnPay;
//	private JButton btnCancel;
//	
//	private PayBillFrameController controller;
//
//	public PayBillFrame(Bill bill, double value) {
//		
//		this.bill = bill;
//		this.value = value;
//		
//		controller = new PayBillFrameController(this);
//		
//		initialize();
//		setListeners();
//				
//	}
//	
//	private void initialize() {
//		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setBounds(100, 100, 450, 411);
//		setMinimumSize(new Dimension(450, 411));
//		setTitle("Pagar conta");
//		setModal(true);
//		setLocationRelativeTo(getParent());
//		Icon.setIcon(this);
//		
//		JPanel contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//		
//		JPanel panel = new JPanel();
//		contentPane.add(panel, BorderLayout.CENTER);
//		
//		JLabel lblBill = new JLabel("Conta");
//		txBill = new JTextField();
//		txBill.setEditable(false);
//		txBill.setText(bill.getBill());
//		
//		JLabel lblCreditor = new JLabel("Credor");
//		txCreditor = new JTextField();
//		txCreditor.setEditable(false);
//		txCreditor.setText(bill.getCreditor());
//		txCreditor.setCaretPosition(0);
//		
//		JLabel lblValue = new JLabel("Valor");
//		txValue = new JTextField();
//		txValue.setEditable(false);
//		txValue.setText("R$ " + value);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBorder(new TitledBorder("Observações"));
//		
//		JLabel lblPayedValue = new JLabel("Valor Pago");
//		txPayedValue = new RealNumberField();
//		
//		GroupLayout panelLayout = new GroupLayout(panel);
//		panelLayout.setHorizontalGroup(
//			panelLayout.createParallelGroup(Alignment.TRAILING)
//				.addGroup(panelLayout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(panelLayout.createParallelGroup(Alignment.LEADING)
//						.addGroup(panelLayout.createSequentialGroup()
//							.addComponent(lblBill)
//							.addGap(18)
//							.addComponent(txBill, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
//						.addGroup(panelLayout.createSequentialGroup()
//							.addComponent(lblCreditor)
//							.addGap(18)
//							.addComponent(txCreditor, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
//							.addGap(18)
//							.addComponent(lblValue)
//							.addGap(18)
//							.addComponent(txValue, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
//						.addGroup(panelLayout.createSequentialGroup()
//							.addComponent(lblPayedValue)
//							.addGap(18)
//							.addComponent(txPayedValue, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
//						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		panelLayout.setVerticalGroup(
//			panelLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(panelLayout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblBill)
//						.addComponent(txBill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(18)
//					.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblCreditor)
//						.addComponent(txCreditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(lblValue)
//						.addComponent(txValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(18)
//					.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblPayedValue)
//						.addComponent(txPayedValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(18)
//					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(19, Short.MAX_VALUE))
//		);
//		
//		panel.setLayout(panelLayout);
//		
//		txObservation = new JTextArea();
//		txObservation.setEditable(false);
//		txObservation.setBackground(panel.getBackground());
//		txObservation.setText(bill.getObservation());
//		
//		scrollPane.setViewportView(txObservation);
//		
//		JPanel btnPanel = new JPanel();
//		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
//		flowLayout.setAlignment(FlowLayout.RIGHT);
//		contentPane.add(btnPanel, BorderLayout.SOUTH);
//		
//		btnCancel = new JButton("Cancelar");
//		btnCancel.setIcon(new ImageIcon(PayBillFrame.class.getResource("/resources/cancel.png")));
//		btnPanel.add(btnCancel);
//		
//		btnPay = new JButton("Pagar");
//		btnPay.setIcon(new ImageIcon(PayBillFrame.class.getResource("/resources/ok.png")));
//		btnPanel.add(btnPay);
//		
//	}
//	
//	private void setListeners() {
//		
//		ActionListener btnListern = new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				if(e.getSource().equals(btnCancel)) controller.cancel();
//				else if(e.getSource().equals(btnPay)) pay();
//				
//			}
//		};
//		
//		btnCancel.addActionListener(btnListern);
//		btnPay.addActionListener(btnListern);
//		
//		addWindowListener(new WindowAdapter() {
//			
//			@Override
//			public void windowClosing(WindowEvent e) {
//				controller.cancel();
//			}
//			
//		});
//		
//	}
//	
//	private void pay() {
//		
//		double payedValue = txPayedValue.getValue();
//		
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		controller.pay(bill, payedValue, value);
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//	}
}
