package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import model.City;
import model.Client;
import model.State;
import sales.controller.ClientRegisterController;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class ClientRegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908360580088674556L;
	private JPanel principalPanel;
	private JPanel bottonPanel;

	private JTextField txtName;
	private JTextField txtStreet;
	private JTextField txtNeighborhood;
	private JTextField txtCEP;
	private JTextField txtPhone;

	private JLabel lblName;
	private JLabel lblStreet;
	private JLabel lblNeighborhood;
	private JLabel lblCep;
	private JLabel lblState;
	private JLabel lblCity;
	private JLabel lblPhone;

	private JComboBox<State> cboState;
	private JComboBox<City> cboCity;

	private JButton btnConfirmar;
	private JButton btnCancel;

	private ClientRegisterController controller;
	private JFrame frame;
	private JTextField txtEmail;
	private JButton btnImport;

	public ClientRegisterFrame() {
		frame = this;
		controller = new ClientRegisterController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(frame);
		setTitle("REGISTRO DE CLIENTES");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 506, 270);
		setMinimumSize(new Dimension(506, 270));
		setPreferredSize(new Dimension(506, 270));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblName = new JLabel("Nome");

		txtName = new UpperTextField();
		txtName.setColumns(10);

		lblStreet = new JLabel("Rua");

		txtStreet = new UpperTextField();
		txtStreet.setColumns(10);

		lblNeighborhood = new JLabel("Bairro");

		txtNeighborhood = new UpperTextField();
		txtNeighborhood.setColumns(10);

		lblCep = new JLabel("C.E.P");

		try {
			txtCEP = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtCEP.setColumns(10);

		lblState = new JLabel("Estado");

		cboState = new JComboBox<State>();
		ComboBoxAutoCompletion cboAState = new ComboBoxAutoCompletion(cboState);
		lblCity = new JLabel("Cidade");

		cboCity = new JComboBox<City>();
		ComboBoxAutoCompletion cboACity = new ComboBoxAutoCompletion(cboCity);
		controller.fillStateAndCity(cboState, cboCity);

		lblPhone = new JLabel("Telefone");

		try {
			txtPhone = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPhone.setColumns(10);

		JSeparator separator = new JSeparator();

		JLabel lblEmail = new JLabel("email");

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel
		        .setHorizontalGroup(gl_principalPanel
		                .createParallelGroup(Alignment.LEADING)
		                .addGroup(
		                        gl_principalPanel
		                                .createSequentialGroup()
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addGroup(
		                                                        gl_principalPanel
		                                                                .createSequentialGroup()
		                                                                .addContainerGap()
		                                                                .addGroup(
		                                                                        gl_principalPanel
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING)
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblName)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtName,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        439,
		                                                                                                        Short.MAX_VALUE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblStreet)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtStreet,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        447,
		                                                                                                        Short.MAX_VALUE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addGroup(
		                                                                                                        gl_principalPanel
		                                                                                                                .createParallelGroup(
		                                                                                                                        Alignment.LEADING,
		                                                                                                                        false)
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblPhone)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        txtPhone))
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblState)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        cboState,
		                                                                                                                                        0,
		                                                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                                                        Short.MAX_VALUE))
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblNeighborhood)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        txtNeighborhood,
		                                                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                                                        193,
		                                                                                                                                        GroupLayout.PREFERRED_SIZE)))
		                                                                                                .addGap(18)
		                                                                                                .addGroup(
		                                                                                                        gl_principalPanel
		                                                                                                                .createParallelGroup(
		                                                                                                                        Alignment.LEADING)
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblEmail)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        txtEmail,
		                                                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                                                        199,
		                                                                                                                                        Short.MAX_VALUE))
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblCep)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        txtCEP,
		                                                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                                                        196,
		                                                                                                                                        Short.MAX_VALUE))
		                                                                                                                .addGroup(
		                                                                                                                        gl_principalPanel
		                                                                                                                                .createSequentialGroup()
		                                                                                                                                .addComponent(
		                                                                                                                                        lblCity)
		                                                                                                                                .addPreferredGap(
		                                                                                                                                        ComponentPlacement.RELATED)
		                                                                                                                                .addComponent(
		                                                                                                                                        cboCity,
		                                                                                                                                        0,
		                                                                                                                                        190,
		                                                                                                                                        Short.MAX_VALUE))))))
		                                                .addGroup(
		                                                        gl_principalPanel
		                                                                .createSequentialGroup()
		                                                                .addGap(11)
		                                                                .addComponent(separator,
		                                                                        GroupLayout.DEFAULT_SIZE, 469,
		                                                                        Short.MAX_VALUE))).addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblName)
		                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblStreet)
		                                .addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNeighborhood)
		                                .addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblCep)
		                                .addComponent(txtCEP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblState)
		                                .addComponent(cboState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblCity)
		                                .addComponent(cboCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblPhone)
		                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblEmail)
		                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		principalPanel.setLayout(gl_principalPanel);
		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnImport = new JButton("Importar");
		btnImport.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/Import.png")));
		bottonPanel.add(btnImport);

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancel);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(ClientRegisterFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
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
				if (e.getSource().equals(btnCancel))
					controller.closeFrame(frame);
				else if (e.getSource().equals(btnConfirmar)) {
					int i = ShowMessage.questionMessage(frame, "REGISTRO", "Deseja mesmo registrar esse cliente ?");
					if (i == JOptionPane.YES_OPTION) {
						doCliente();
						ShowMessage.successMessage(frame, "REGISTRO", "registo do produto realizado com sucesso!");
						ClearFrame.clear(frame);
					}

				} else if (e.getSource().equals(btnImport)) {
					EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							ImportClientFrame cFrame = new ImportClientFrame();
							cFrame.pack();
							cFrame.setVisible(true);
							cFrame.setLocationRelativeTo(frame);
						}
					});
				}
			}
		};
		btnImport.addActionListener(buttonListener);
		btnConfirmar.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}

	private void doCliente() {
		Client client = new Client();
		String name = txtName.getText();
		String street = txtStreet.getText();
		String neighborhood = txtNeighborhood.getText();
		String cep = txtCEP.getText().replaceAll("\\.|-", "");
		String phone = txtPhone.getText().replaceAll("\\(|\\)|-", "");
		State state = (State) cboState.getSelectedItem();
		City city = (City) cboCity.getSelectedItem();
		String email = txtEmail.getText();

		client.setName(name);
		client.setStreet(street);
		client.setNeighborhood(neighborhood);
		client.setCep(cep);
		client.setCity(city);
		client.setState(state);
		client.setPhone(phone);
		client.setEmail(email);

		controller.doClienteRegistration(client);
	}
}
