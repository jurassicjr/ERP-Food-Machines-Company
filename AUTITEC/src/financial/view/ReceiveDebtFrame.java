//package financial.view;
//
//import java.awt.BorderLayout;
//import java.awt.Cursor;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.text.NumberFormat;
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
//import model.DebtToReceive;
//import userInterface.components.RealNumberField;
//import util.Icon;
//import financial.controller.ReceiveDebtFrameController;
//
//public class ReceiveDebtFrame extends JDialog {
//
//	private static final long serialVersionUID = -5976359939392792276L;
//	
//	private JTextField txBill;
//	private JTextField txDebtor;
//	private JTextField txValue;
//	private RealNumberField txReceivedValue;
//	private JTextArea txObservation;
//		
//	private DebtToReceive debtToReceive;
//	private double value;
//	
//	private JButton btnReceive;
//	private JButton btnCancel;
//	
//	private ReceiveDebtFrameController controller;
//	
//	public ReceiveDebtFrame(DebtToReceive debtToReceive, double value) {
//		
//		this.debtToReceive = debtToReceive;
//		this.value = value;
//		
//		controller = new ReceiveDebtFrameController(this);
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
//		txBill.setText(debtToReceive.getDebt());
//		txBill.setCaretPosition(0);
//		
//		JLabel lblDebtor = new JLabel("Credor");
//		txDebtor = new JTextField();
//		txDebtor.setEditable(false);
//		txDebtor.setText(debtToReceive.getDebtor());
//		
//		JLabel lblValue = new JLabel("Valor");
//		txValue = new JTextField();
//		txValue.setEditable(false);
//		txValue.setText(NumberFormat.getCurrencyInstance().format(value));		
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBorder(new TitledBorder("Observações"));
//		
//		JLabel lblPayedValue = new JLabel("Valor Recebido");
//		txReceivedValue = new RealNumberField();
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
//							.addComponent(lblDebtor)
//							.addGap(18)
//							.addComponent(txDebtor, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
//							.addGap(18)
//							.addComponent(lblValue)
//							.addGap(18)
//							.addComponent(txValue, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
//						.addGroup(panelLayout.createSequentialGroup()
//							.addComponent(lblPayedValue)
//							.addGap(18)
//							.addComponent(txReceivedValue, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
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
//						.addComponent(lblDebtor)
//						.addComponent(txDebtor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(lblValue)
//						.addComponent(txValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(18)
//					.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblPayedValue)
//						.addComponent(txReceivedValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
//		txObservation.setText(debtToReceive.getObservation());
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
//		btnReceive = new JButton("Receber");
//		btnReceive.setIcon(new ImageIcon(PayBillFrame.class.getResource("/resources/ok.png")));
//		btnPanel.add(btnReceive);
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
//				else if(e.getSource().equals(btnReceive)) receive();
//				
//			}
//		};
//		
//		btnCancel.addActionListener(btnListern);
//		btnReceive.addActionListener(btnListern);
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
//	private void receive() {
//		
//		double receivedValue = txReceivedValue.getValue();
//						
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		controller.receive(debtToReceive, value, receivedValue);
//		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//	}
//}
