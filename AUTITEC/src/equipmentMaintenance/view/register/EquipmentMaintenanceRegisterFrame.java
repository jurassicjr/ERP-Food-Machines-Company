package equipmentMaintenance.view.register;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.omg.CORBA.INITIALIZE;

import product.view.RegisterKitFrame;
import util.ShowMessage;
import equipmenteMaintenance.controller.EquipmentMaintenceController;
import equipmenteMaintenance.controller.EquipmentRegisterController;
import financial.view.GenerateFinancialReportFrame;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import maintenance.view.register.VehicleRegisterFrame;
import model.Equipment;
import model.Maintenance;
import net.sf.nachocalendar.components.DateField;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EquipmentMaintenanceRegisterFrame extends JFrame {

	private JButton btnCancel;
	private JButton btnConfirm;
	private JButton btnClear;
	private JTextArea txtExecutedMaintenence;
	private DateField txtLastMaintenence;
	private DateField txtExpiredMaintenceDate;
	private JLabel label_2;
	private JComboBox cboEquip;
	private JLabel lblEquipamento;
	private final EquipmentMaintenanceRegisterFrame frame = this;
	private EquipmentMaintenceController controller;
	private Maintenance maintenance;
	private JPanel panel;
	private JPanel panelBottom;

	private void initialize() {

		setBounds(100, 100, 491, 312);
		setMinimumSize(new Dimension(491, 312));
		setTitle("Registro de Manuntenção de Equipamentos");
		panel = new JPanel();

		panelBottom = new JPanel();

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(GenerateFinancialReportFrame.class
				.getResource("/resources/cancel.png")));

		btnConfirm = new JButton("Confirmar");

		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class
				.getResource("/resources/ok.png")));

		btnClear = new JButton("Limpar");

		btnClear.setIcon(new ImageIcon(RegisterKitFrame.class
				.getResource("/resources/clear.png")));

		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(gl_panelBottom
				.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 461, Short.MAX_VALUE)
				.addGroup(
						gl_panelBottom.createSequentialGroup()
								.addContainerGap(162, Short.MAX_VALUE)
								.addComponent(btnCancel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnClear)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnConfirm).addContainerGap()));
		gl_panelBottom
				.setVerticalGroup(gl_panelBottom
						.createParallelGroup(Alignment.TRAILING)
						.addGap(0, 42, Short.MAX_VALUE)
						.addGroup(
								gl_panelBottom
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_panelBottom
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnConfirm)
														.addComponent(
																btnClear,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnCancel))
										.addContainerGap()));
		panelBottom.setLayout(gl_panelBottom);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panelBottom,
												GroupLayout.DEFAULT_SIZE, 455,
												Short.MAX_VALUE)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												455, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 200,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE,
								42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(51, Short.MAX_VALUE)));

		lblEquipamento = new JLabel("Equipamento");

		cboEquip = new JComboBox();

		JLabel label = new JLabel("Data da Ultima Manutenção");

		txtLastMaintenence = new DateField();

		JLabel label_1 = new JLabel("Data do Vencimento da Manutenção");

		txtExpiredMaintenceDate = new DateField();

		label_2 = new JLabel("Descrição da Manutenção Executada");

		txtExecutedMaintenence = new JTextArea();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblEquipamento)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		cboEquip,
																		0,
																		375,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						label,
																						GroupLayout.PREFERRED_SIZE,
																						140,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						txtLastMaintenence,
																						GroupLayout.PREFERRED_SIZE,
																						128,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						label_1,
																						GroupLayout.PREFERRED_SIZE,
																						189,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						txtExpiredMaintenceDate,
																						GroupLayout.PREFERRED_SIZE,
																						128,
																						GroupLayout.PREFERRED_SIZE)))
												.addComponent(
														label_2,
														GroupLayout.PREFERRED_SIZE,
														189,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtExecutedMaintenence,
														GroupLayout.PREFERRED_SIZE,
														441,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblEquipamento)
												.addComponent(
														cboEquip,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		label)
																.addGap(6)
																.addComponent(
																		txtLastMaintenence,
																		GroupLayout.PREFERRED_SIZE,
																		20,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		label_1)
																.addGap(6)
																.addComponent(
																		txtExpiredMaintenceDate,
																		GroupLayout.PREFERRED_SIZE,
																		20,
																		GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(label_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtExecutedMaintenence,
										GroupLayout.PREFERRED_SIZE, 56,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(105, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	private void initializeSub() {
		controller = new EquipmentMaintenceController(this.frame);
		controller.fillCbo(cboEquip);

	}

	private boolean verifyField() {
		try {
			Boolean result = true;
			Integer i = 0;
			while (i < panel.getComponentCount() && result) {
				if (panel.getComponent(i) instanceof JTextField) {
					JTextField txt = (JTextField) panel.getComponent(i);
					result = result && !txt.getText().isEmpty();
				} else if (panel.getComponent(i) instanceof JTextArea) {
					JTextArea txa = (JTextArea) panel.getComponent(i);
					result = result && !txa.getText().isEmpty();
				}
				i++;
			}
			return result;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean verifycbo() {
		return cboEquip.getSelectedIndex() == -1 ? false : true;
	}

	private void addEquipment() {
		if (verifyField()) {
			if (verifycbo()) {
				maintenance = getMaintenance();
				if (controller.persistMaintenance(maintenance))
					ShowMessage
							.successMessage(this, "Sucesso",
									"Manutenção de equipamento cadastrada com sucesso.");
			}
		}

	}

	private Maintenance getMaintenance() {
		maintenance = new Maintenance();

		java.util.Date daate = (java.util.Date) txtLastMaintenence.getValue();
		maintenance.setDateMaintenance(new java.sql.Date(daate.getTime()));
		daate = (java.util.Date) txtExpiredMaintenceDate.getValue();
		maintenance.setExpireDate(new java.sql.Date(daate.getTime()));
		maintenance.setEquipmentMaintenance((Equipment) cboEquip
				.getSelectedItem());
		maintenance.setDescriptionMaintenance(txtExecutedMaintenence.getText());
		return maintenance;

	}

	private void setListeners() {
		ActionListener ButtonActions = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancel))
					controller.closeFrame(frame);
				else if (e.getSource().equals(btnConfirm))
					addEquipment();
				else if (e.getSource().equals(btnClear)) {
					controller.clear();

					// txtExpiredMaintenceDate.
					// // txtExpiredMaintenceDate =
					// java.util.Date(System.currentTimeMillis());
				}

			}
		};
		btnConfirm.addActionListener(ButtonActions);
		btnCancel.addActionListener(ButtonActions);
		btnClear.addActionListener(ButtonActions);
	}

	public EquipmentMaintenanceRegisterFrame() {
		initialize();
		initializeSub();
		setListeners();

	}
}
