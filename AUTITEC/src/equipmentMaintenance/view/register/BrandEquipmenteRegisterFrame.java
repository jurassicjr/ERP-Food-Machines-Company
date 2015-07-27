package equipmentMaintenance.view.register;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Brand;
import util.ShowMessage;
import equipmenteMaintenance.controller.BrandEquipamentMaintenceController;

public class BrandEquipmenteRegisterFrame extends JFrame {
	/**
	 * 
	 */
    private static final long serialVersionUID = -7319112093881994753L;
	
    private JTextField txtName;
	private JPanel btnPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	private final BrandEquipmenteRegisterFrame frame = this;
	private BrandEquipamentMaintenceController controller;
	private Brand brand;
	private JComboBox cbo;

	private void initialize() {
		setBounds(100, 100, 487, 142);
		setMinimumSize(new Dimension(487, 129));
		setTitle("Registro de Marca de Equipamento");

		JPanel panel = new JPanel();
		btnPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																btnPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																451,
																Short.MAX_VALUE)
														.addComponent(
																panel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																451,
																Short.MAX_VALUE))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 40,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, 27,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		btnAdd = new JButton("Registrar");
		btnAdd.setIcon(new ImageIcon(getClass()
				.getResource("/resources/ok.png")));

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(getClass().getResource(
				"/resources/cancel.png")));
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(gl_btnPanel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_btnPanel
						.createSequentialGroup()
						.addContainerGap(234, Short.MAX_VALUE)
						.addComponent(btnCancel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 106,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_btnPanel
				.setVerticalGroup(gl_btnPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_btnPanel
										.createSequentialGroup()
										.addGroup(
												gl_btnPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnAdd,
																GroupLayout.DEFAULT_SIZE,
																23,
																Short.MAX_VALUE)
														.addComponent(
																btnCancel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(4)));
		btnPanel.setLayout(gl_btnPanel);

		JLabel lblNewLabel = new JLabel("Nome");

		txtName = new JTextField();
		txtName.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 400,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(
														txtName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(38, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

	private void setListeners() {
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnAdd))
					registerBrand();
				else if (e.getSource().equals(btnCancel)) {
					close();
				}

			}
		};

		btnAdd.addActionListener(action);
		btnCancel.addActionListener(action);

	}

	private void close() {
		if (cbo != null)
			controller.fillCboBrand(cbo);
		controller.closeFrame(this.frame);

	}

	private void registerBrand() {
		if (!txtName.getText().equals("")) {
			brand = new Brand();
			brand.setName(txtName.getText());
			if (controller.persistBrand(brand))

				ShowMessage.successMessage(this, "Mensagem",
						"Marca inserida com sucesso !");

		} else {
			ShowMessage.errorMessage(this, "Atenção",
					" Insira um nome para a marca");

		}
	}

	public BrandEquipmenteRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		controller = new BrandEquipamentMaintenceController(this.frame);
		initialize();
		setListeners();

	}

	public JComboBox getCbo() {
		return cbo;
	}

	public void setCbo(JComboBox cbo) {
		this.cbo = cbo;
	}

}