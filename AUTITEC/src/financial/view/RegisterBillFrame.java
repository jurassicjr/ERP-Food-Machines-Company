package financial.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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
import javax.swing.border.TitledBorder;

import net.sf.nachocalendar.components.DateField;
import userInterface.components.RealNumberField;
import userInterface.components.UpperTextField;
import util.Icon;
import financial.controller.RegisterBillFrameController;

public class RegisterBillFrame extends JFrame {

	private static final long serialVersionUID = -4336339858592180045L;
	
	private JPanel contentPane;
	
	private UpperTextField txBill;
	private DateField txDueDate;
	private UpperTextField txCreditor;
	private JTextArea txObservations;
	private RealNumberField txParcelValue;
	
	private JComboBox<String> cbInstallments;
	
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
	
	private RegisterBillFrameController controller;
	
		
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
		setBounds(100, 100, 506, 463);
		setMinimumSize(new Dimension(506, 400));
		setTitle("Registrar Conta a Pagar");
		Icon.setIcon(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblBill = new JLabel("Conta a Pagar");
		txBill = new UpperTextField();
		
		JLabel lblDueDate = new JLabel("Data de Vencimento");
		txDueDate = new DateField();
		txDueDate.setValue(null);
		
		JLabel lblInstallments = new JLabel("Número de Parcelas");
		
		cbInstallments = new JComboBox<String>();
		for(int i = 1; i <= 24; ++i) {
			cbInstallments.addItem(String.valueOf(i));
		}
		cbInstallments.setSelectedIndex(-1);
		
		JPanel observationsPanel = new JPanel();
		observationsPanel.setBorder(new TitledBorder(null, "Observções", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCreditor = new JLabel("Credor:");
		txCreditor = new UpperTextField();
		
		JLabel lblParcelValue = new JLabel("Valor da Parcela");
		txParcelValue = new RealNumberField();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblBill)
							.addGap(18)
							.addComponent(txBill, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblDueDate)
							.addGap(18)
							.addComponent(txDueDate, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblInstallments)
							.addGap(18)
							.addComponent(cbInstallments, 0, 117, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblParcelValue)
							.addGap(18)
							.addComponent(txParcelValue, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblCreditor)
							.addGap(18)
							.addComponent(txCreditor, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
						.addComponent(observationsPanel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBill)
						.addComponent(txBill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDueDate)
						.addComponent(txDueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInstallments)
						.addComponent(cbInstallments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParcelValue)
						.addComponent(txParcelValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreditor)
						.addComponent(txCreditor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(observationsPanel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		observationsPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		observationsPanel.add(scrollPane, BorderLayout.CENTER);
		
		txObservations = new JTextArea();
		txObservations.setFont(new Font("Arial", Font.PLAIN, 12));
		txObservations.setWrapStyleWord(true);
		scrollPane.setViewportView(txObservations);
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
				
		String bill = txBill.getText();
		String creditor = txCreditor.getText();
		Date dueDate = (Date) txDueDate.getValue();
		int installments = cbInstallments.getSelectedIndex() + 1;
		double value = txParcelValue.getValue();
		String observation = txObservations.getText();
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.register(bill, creditor, dueDate, installments, observation, value);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
