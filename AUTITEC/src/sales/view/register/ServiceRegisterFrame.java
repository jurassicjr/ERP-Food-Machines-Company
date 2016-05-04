package sales.view.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.OutSourcedServices;
import model.Service;
import sales.controller.ServiceRegisterController;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class ServiceRegisterFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -3086335714346658709L;
	
    private JTextField txtServiceName;
	private JTextField txtPrice_I_I_I;
	private JTextField txtPrice_I_I_II;
	private JTextField txtPrice_I_II_I;
	private JTextField txtPrice_I_II_II;
	private JTextField txtPrice_I_III_I;
	private JTextField txtPrice_I_III_II;
	private JTextField txtPrice_II_I_I;
	private JTextField txtPrice_II_I_II;
	private JTextField txtPrice_II_II_I;
	private JTextField txtPrice_II_II_II;
	private JTextField txtPrice_II_III_I;
	private JTextField txtPrice_II_III_II;
	private JTextField txtPricePerKm;
	private JTextField txtMeal;
	private JTextField txtStayPrice;
	
	private JScrollPane scrollPane;
	private JTextArea txtDescription;
	
	private JPanel subPanel;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private ServiceRegisterController controller;

	private JLabel lblPricePerKm;
	private JLabel lblMeal;
	private JLabel lblStayPrice;
	private JLabel lblServiceName;
	private JLabel lblManHour;
	private JLabel lbl08to18;
	private JLabel lbl18to08;
	private JLabel lblMondaytoFriday;
	private JLabel lblSaturday;
	private JLabel lblSunday;
	
	private double price_I_I_I;
	private double price_I_I_II;
	private double price_I_II_I;
	private double price_I_II_II;
	private double price_I_III_I;
	private double price_I_III_II;
	private double price_II_I_I;
	private double price_II_I_II;
	private double price_II_II_II;
	private double price_II_II_I;
	private double price_II_III_I;
	private double price_II_III_II;
	private String stayPrice;
	private double pricePerKm;
	private double meal;

	private JCheckBox chkBoxThird;

    public ServiceRegisterFrame() {
    	controller = new ServiceRegisterController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    Icon.setIcon(this);
	    setTitle("Registro de Serviço");
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 512, 614);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblServiceName = new JLabel("Nome do Serviço");
	    
	    txtServiceName = new JTextField();
	    txtServiceName.setColumns(10);
	    
	    lblManHour = new JLabel("Homem/Hora Normal");
	    
	    lbl08to18 = new JLabel("Das 08:00 ás 18:00");
	    
	    lbl18to08 = new JLabel("Das 18:01 ás 07:59");
	    
	    lblMondaytoFriday = new JLabel("Segunda á Sexta");
	    
	    lblSaturday = new JLabel("Sábado");
	    
	    lblSunday = new JLabel("Domingos e Feriado");
	    
	    txtPrice_I_III_I = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_III_I.setColumns(10);
	    
	    txtPrice_I_II_I =new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_II_I.setColumns(10);
	    
	    txtPrice_I_I_I = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_I_I.setColumns(10);
	    
	    txtPrice_I_I_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_I_II.setColumns(10);
	    
	    txtPrice_I_II_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_II_II.setColumns(10);
	    
	    txtPrice_I_III_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_I_III_II.setColumns(10);
	    
	    JLabel lblHomemhoraEspecializado = new JLabel("Homem/Hora Especializado");
	    
	    JLabel lblModayToFriday_II = new JLabel("Segunda á Sexta");
	    
	    JLabel lblSaturday_II = new JLabel("Sábado");
	    
	    JLabel lblSunday_II = new JLabel("Domingos e Feriado");
	    
	    txtPrice_II_I_I = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_I_I.setColumns(10);
	    
	    txtPrice_II_II_I = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_II_I.setColumns(10);
	    
	    txtPrice_II_III_I = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_III_I.setColumns(10);
	    
	    txtPrice_II_I_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_I_II.setColumns(10);
	    
	    txtPrice_II_II_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_II_II.setColumns(10);
	    
	    txtPrice_II_III_II = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPrice_II_III_II.setColumns(10);
	    
	    scrollPane = new JScrollPane();
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Descri\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    
	    lblPricePerKm = new JLabel("Preço por km rodado");
	    
	    txtPricePerKm = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtPricePerKm.setColumns(10);
	    
	    lblMeal = new JLabel("Refeição");
	    
	    lblStayPrice = new JLabel("Estádia");
	    
	    txtMeal = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
	    txtMeal.setColumns(10);
	    
	    txtStayPrice = new JTextField();
	    txtStayPrice.setColumns(10);
	    
	    chkBoxThird = new JCheckBox("Serviço de Terceiro");
	    
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(lblMeal)
	    						.addComponent(lblStayPrice)
	    						.addComponent(lblPricePerKm))
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addComponent(txtMeal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addComponent(txtPricePerKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
	    							.addComponent(chkBoxThird))
	    						.addComponent(txtStayPrice, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblServiceName)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(160)
	    							.addComponent(lblManHour))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGap(134)
	    							.addComponent(lblHomemhoraEspecializado))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lblSunday)
	    								.addComponent(lblSaturday)
	    								.addComponent(lblMondaytoFriday))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lbl08to18)
	    								.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    									.addComponent(txtPrice_I_I_I, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addComponent(txtPrice_I_II_I, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addComponent(txtPrice_I_III_I, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    							.addGap(18)
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(txtPrice_I_I_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(lbl18to08)
	    								.addComponent(txtPrice_I_II_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
	    									.addComponent(txtPrice_II_I_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addComponent(txtPrice_I_III_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addComponent(txtPrice_II_II_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    									.addComponent(txtPrice_II_III_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
	    						.addGroup(gl_principalPanel.createSequentialGroup()
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(lblModayToFriday_II)
	    								.addComponent(lblSaturday_II)
	    								.addComponent(lblSunday_II))
	    							.addGap(18)
	    							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    								.addComponent(txtPrice_II_III_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(txtPrice_II_II_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    								.addComponent(txtPrice_II_I_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	    						.addComponent(txtServiceName, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblServiceName)
	    				.addComponent(txtServiceName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(lblManHour)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lbl08to18)
	    				.addComponent(lbl18to08))
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(lblMondaytoFriday))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGap(11)
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    						.addComponent(txtPrice_I_I_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    						.addComponent(txtPrice_I_I_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
	    			.addGap(11)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSaturday)
	    				.addComponent(txtPrice_I_II_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtPrice_I_II_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSunday)
	    				.addComponent(txtPrice_I_III_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtPrice_I_III_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(lblHomemhoraEspecializado)
	    			.addGap(11)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblModayToFriday_II)
	    				.addComponent(txtPrice_II_I_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtPrice_II_I_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSaturday_II)
	    				.addComponent(txtPrice_II_II_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtPrice_II_II_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblSunday_II)
	    				.addComponent(txtPrice_II_III_I, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(txtPrice_II_III_II, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblStayPrice)
	    				.addComponent(txtStayPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblMeal)
	    				.addComponent(txtMeal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(txtPricePerKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(chkBoxThird))
	    				.addComponent(lblPricePerKm))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    
	    txtDescription = new JTextArea();
	    txtDescription.setLineWrap(true);
	    txtDescription.setWrapStyleWord(true);
	    scrollPane.setViewportView(txtDescription);
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeButton();
    }

	private void initializeButton() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(ServiceRegisterFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(ServiceRegisterFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(ServiceRegisterFrame.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
	    
    }
	
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnConfirm))confirm();
			}
		};
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		
		ActionListener checkBoxListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(chkBoxThird))disableFields();
			}
		};
		chkBoxThird.addActionListener(checkBoxListener);
	}
	
	private void disableFields() {
		boolean enabled;
		if(chkBoxThird.isSelected()) {
			enabled = false;
			txtMeal.setEnabled(enabled);
			txtPrice_I_I_I.setEnabled(enabled);
			txtPrice_I_I_II.setEnabled(enabled);
			txtPrice_I_II_I.setEnabled(enabled);
			txtPrice_I_II_II.setEnabled(enabled);
			txtPrice_I_III_I.setEnabled(enabled);
			txtPrice_I_III_II.setEnabled(enabled);
			txtPrice_II_I_I.setEnabled(enabled);
			txtPrice_II_I_II.setEnabled(enabled);
			txtPrice_II_II_I.setEnabled(enabled);
			txtPrice_II_II_II.setEnabled(enabled);
			txtPrice_II_III_I.setEnabled(enabled);
			txtPrice_II_III_II.setEnabled(enabled);
			txtPricePerKm.setEnabled(enabled);
			txtStayPrice.setEnabled(enabled);
		}else {
			enabled = true;
			txtMeal.setEnabled(enabled);
			txtPrice_I_I_I.setEnabled(enabled);
			txtPrice_I_I_II.setEnabled(enabled);
			txtPrice_I_II_I.setEnabled(enabled);
			txtPrice_I_II_II.setEnabled(enabled);
			txtPrice_I_III_I.setEnabled(enabled);
			txtPrice_I_III_II.setEnabled(enabled);
			txtPrice_II_I_I.setEnabled(enabled);
			txtPrice_II_I_II.setEnabled(enabled);
			txtPrice_II_II_I.setEnabled(enabled);
			txtPrice_II_II_II.setEnabled(enabled);
			txtPrice_II_III_I.setEnabled(enabled);
			txtPrice_II_III_II.setEnabled(enabled);
			txtPricePerKm.setEnabled(enabled);
			txtStayPrice.setEnabled(enabled);

		}
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos do cadastro de serviço? ");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void confirm() {
		int i = ShowMessage.questionMessage(this, "Registro", "Deseja realmente registrar esse serviço ?");
		if(i == JOptionPane.NO_OPTION)return;
		if(chkBoxThird.isSelected()) {
			if(txtServiceName.getText().trim().isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira o nome do serviço!");
				return;
			}if(txtDescription.getText().trim().isEmpty()) {
				ShowMessage.errorMessage(this, "Erro", "Insira uma descrição ao serviço");
				return;
			}
			String name = txtServiceName.getText();
			String description = txtDescription.getText();
			OutSourcedServices oss = new OutSourcedServices(name, description);
			controller.registerOutSourcedService(oss);
			ShowMessage.successMessage(this, "Registro", "Registro de servio de terceiros realizado com sucesso!");
			ClearFrame.clear(this);
			return;
		}
		boolean isOk = verifyFields();
		if(isOk) {
			Service s = new Service();
			s.setMeal(meal);
			s.setName(txtServiceName.getText());
			s.setObservation(txtDescription.getText());
			s.setPrice_I_I_I(price_I_I_I);
			s.setPrice_I_I_II(price_I_I_II);
			s.setPrice_I_II_I(price_I_II_I);
			s.setPrice_I_II_II(price_I_II_II);
			s.setPrice_I_III_I(price_I_III_I);
			s.setPrice_I_III_II(price_I_III_II);
			s.setPrice_II_I_I(price_II_I_I);
			s.setPrice_II_I_II(price_II_I_II);
			s.setPrice_II_II_I(price_II_II_I);
			s.setPrice_II_II_II(price_II_II_II);
			s.setPrice_II_III_I(price_II_III_I);
			s.setPrice_II_III_II(price_II_III_II);
			s.setStayValue(stayPrice);
			s.setPricePerKm(pricePerKm);
			controller.register(s);
			ShowMessage.successMessage(this, "Sucesso!", "Sucesso ao registrar serviço");
			ClearFrame.clear(this);
		}
	}

	private boolean verifyFields() {
		price_I_I_I = Double.parseDouble(txtPrice_I_I_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_I_I_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira um valor para a Hora/Homem normal de segunda as sexta, no primeiro periodo!");
	    	return false;
	    }
	    price_I_I_II = Double.parseDouble(txtPrice_I_I_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\."));
	    if(price_I_I_II == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira um valor para a Hora/Home normal de segunda à sexta no segundo periodo!");
	    	return false;
	    }
	    price_I_II_I = Double.parseDouble(txtPrice_I_II_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_I_II_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valo para a Hora/Homem normal de Sabado no primeiro periodo!");
	    	return false;
	    }
	    price_I_II_II = Double.parseDouble(txtPrice_I_II_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_I_II_II == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para a Hora/Homem normal de Sábado no segundo periodo!");
	    	return false;
	    }
	    price_I_III_I = Double.parseDouble(txtPrice_I_III_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_I_III_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora normal de domingo no primeiro periodo!");
	    	return false;
	    }
	    price_I_III_II = Double.parseDouble(txtPrice_I_III_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_I_III_II == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora normal de Domingo no segundo periodo!");
	    	return false;
	    }
	    price_II_I_I = Double.parseDouble(txtPrice_II_I_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_I_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de Segunda à Sexta no primeio periodo!");
	    	return false;
	    }
	    price_II_I_II = Double.parseDouble(txtPrice_II_I_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_I_II == 0.0) {
	    	ShowMessage.questionMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de Segunda à Sexta no segundo periodo!");
	    	return false;
	    }
	    price_II_II_II = Double.parseDouble(txtPrice_II_II_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_II_II == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de sábado no segundo periodo!");
	    	return false;
	    }
	    price_II_II_I = Double.parseDouble(txtPrice_II_II_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_II_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de sábado no primeiro periodo!");
	    	return false;
	    }
	    price_II_III_I = Double.parseDouble(txtPrice_II_III_I.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_III_I == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de domingo e feriado no primeiro periodo!");
	    	return false;
	    }
	    price_II_III_II = Double.parseDouble(txtPrice_II_III_II.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(price_II_III_II == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor para Homem/Hora especializado de domingo e feriado no segundo periodo!");
	    	return false;
	    }
	    stayPrice = txtStayPrice.getText();
	    if(stayPrice.isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira as condições para estádia!");
	    	return false;
	    }
	    if(txtDescription.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira a descrição do serviço");
	    	return false;
	    }
	    pricePerKm = Double.parseDouble(txtPricePerKm.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    if(pricePerKm == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor por Kilometro!");
	    	return false;
	    }
	    
	    meal = Double.parseDouble(txtMeal.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim());
	    
	    if(meal == 0.0) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor da refeição por técnico!");
	    	return false;
	    }
	    return true;
    }
}
