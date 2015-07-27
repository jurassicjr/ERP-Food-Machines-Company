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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import maintenance.view.register.VehicleRegisterFrame;
import model.Brand;
import model.Equipment;
import model.Maintenance;
import net.sf.nachocalendar.components.DateField;
import product.view.RegisterKitFrame;
import product.view.RegisterProductFrame;
import util.ShowMessage;
import financial.view.GenerateFinancialReportFrame;

public class EquipmentRegisterFrame extends JFrame {

	private static final long serialVersionUID = 8401963044005454604L;
	private JTextField txtNameEquipament;
	private JTextField txtSerieNumber;
	private JPanel panelPrincipal;
	private JLabel lblNome;
	private JComboBox cboBrand;
	private JLabel lblMarca;
	private JButton btnAddBrand;
	private JLabel lblNSrie;
	private JLabel lblDescrio;
	private JTextArea txaModelDesc;
	private JPanel panelBottom;
	private JButton btnCancel;
	private JButton btnConfirm;

	private EquipmentRegisterController controller;
	private final EquipmentRegisterFrame thisFrame = this;

	private Equipment equip;
	private Maintenance maintenance;
	private JButton btnClear;
	private JTextField txtInvoice;
	private DateField txtWarranty;

	public EquipmentRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		controller = new EquipmentRegisterController(thisFrame);
		initializePrincipal();
		initialize();
		setListener();

	}

	private void initialize() {
		controller.fillCboBrand(cboBrand);
	}

	private void initializePrincipal() {
		this.setTitle("Registro de Equipamento");
		this.setPreferredSize(new Dimension(497, 303));
		setBounds(100, 100, 497, 303);
		setMinimumSize(new Dimension(497, 303));

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 461, 199);

		lblNome = new JLabel("Nome Equipamento");

		txtNameEquipament = new JTextField();
		txtNameEquipament.setColumns(10);

		cboBrand = new JComboBox();

		lblMarca = new JLabel("Marca");

		btnAddBrand = new JButton("");
		btnAddBrand.setIcon(new ImageIcon(RegisterProductFrame.class
				.getResource("/resources/plus.png")));

		lblNSrie = new JLabel("N° Série");

		txtSerieNumber = new JTextField();
		txtSerieNumber.setColumns(10);

		lblDescrio = new JLabel("Descrição do Modelo");

		txaModelDesc = new JTextArea();

		JLabel lblNotaFiscal = new JLabel("Nota Fiscal");

		txtInvoice = new JTextField();
		txtInvoice.setColumns(10);

		JLabel lblPrazoGarantia = new JLabel("Prazo Garantia");

		txtWarranty = new DateField();

		GroupLayout gl_panelPrincipal = new GroupLayout(panelPrincipal);
		gl_panelPrincipal
				.setHorizontalGroup(gl_panelPrincipal
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelPrincipal
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panelPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																txaModelDesc,
																GroupLayout.DEFAULT_SIZE,
																441,
																Short.MAX_VALUE)
														.addGroup(
																gl_panelPrincipal
																		.createSequentialGroup()
																		.addComponent(
																				lblNome,
																				GroupLayout.PREFERRED_SIZE,
																				97,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtNameEquipament,
																				GroupLayout.PREFERRED_SIZE,
																				342,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lblDescrio,
																GroupLayout.PREFERRED_SIZE,
																128,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panelPrincipal
																		.createSequentialGroup()
																		.addGroup(
																				gl_panelPrincipal
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addGroup(
																								gl_panelPrincipal
																										.createSequentialGroup()
																										.addComponent(
																												lblNotaFiscal)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												txtInvoice))
																						.addGroup(
																								gl_panelPrincipal
																										.createSequentialGroup()
																										.addComponent(
																												lblMarca,
																												GroupLayout.PREFERRED_SIZE,
																												40,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												cboBrand,
																												GroupLayout.PREFERRED_SIZE,
																												153,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGroup(
																				gl_panelPrincipal
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_panelPrincipal
																										.createSequentialGroup()
																										.addGap(6)
																										.addComponent(
																												btnAddBrand,
																												GroupLayout.PREFERRED_SIZE,
																												52,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblNSrie,
																												GroupLayout.PREFERRED_SIZE,
																												46,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(9)
																										.addComponent(
																												txtSerieNumber,
																												GroupLayout.DEFAULT_SIZE,
																												123,
																												Short.MAX_VALUE))
																						.addGroup(
																								gl_panelPrincipal
																										.createSequentialGroup()
																										.addGap(18)
																										.addComponent(
																												lblPrazoGarantia)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												txtWarranty,
																												GroupLayout.DEFAULT_SIZE,
																												153,
																												Short.MAX_VALUE)))))
										.addContainerGap()));
		gl_panelPrincipal
				.setVerticalGroup(gl_panelPrincipal
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelPrincipal
										.createSequentialGroup()
										.addGap(4)
										.addGroup(
												gl_panelPrincipal
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblNome)
														.addComponent(
																txtNameEquipament,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_panelPrincipal
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(lblNSrie)
														.addGroup(
																gl_panelPrincipal
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addComponent(
																				btnAddBrand,
																				0,
																				0,
																				Short.MAX_VALUE)
																		.addGroup(
																				gl_panelPrincipal
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblMarca)
																						.addComponent(
																								cboBrand,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_panelPrincipal
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				txtSerieNumber,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(14)
										.addGroup(
												gl_panelPrincipal
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panelPrincipal
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblNotaFiscal)
																		.addComponent(
																				txtInvoice,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblPrazoGarantia))
														.addComponent(
																txtWarranty,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(lblDescrio)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(txaModelDesc,
												GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addGap(154)));
		panelPrincipal.setLayout(gl_panelPrincipal);
		getContentPane().setLayout(null);

		panelBottom = new JPanel();
		panelBottom.setBounds(10, 216, 461, 42);

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(GenerateFinancialReportFrame.class
				.getResource("/resources/cancel.png")));
		panelBottom.add(btnCancel);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class
				.getResource("/resources/ok.png")));

		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterKitFrame.class
				.getResource("/resources/clear.png")));
		panelBottom.add(btnClear);

		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(gl_panelBottom.createParallelGroup(
				Alignment.TRAILING).addGroup(
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
		getContentPane().add(panelBottom);
		getContentPane().add(panelPrincipal);

	}

	private void setListener() {
		ActionListener ButtonActions = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancel))
					controller.closeFrame(thisFrame);
				else if (e.getSource().equals(btnConfirm))
					addEquipament();
				else if (e.getSource().equals(btnAddBrand))
					addBrand();
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
		btnAddBrand.addActionListener(ButtonActions);
		btnClear.addActionListener(ButtonActions);
	}

	private void addBrand() {
		BrandEquipmenteRegisterFrame frames = new BrandEquipmenteRegisterFrame();
		frames.setCbo(cboBrand);
		frames.setVisible(true);
	}

	private boolean verifyField() {
		try {
			Boolean result = true;
			Integer i = 0;
			while (i < panelPrincipal.getComponentCount() && result) {
				if (panelPrincipal.getComponent(i) instanceof JTextField) {
					JTextField txt = (JTextField) panelPrincipal
							.getComponent(i);
					result = result && !txt.getText().isEmpty();
				} else if (panelPrincipal.getComponent(i) instanceof JTextArea) {
					JTextArea txa = (JTextArea) panelPrincipal.getComponent(i);
					result = result && !txa.getText().isEmpty();
				}
				i++;
			}
			return result;
		} catch (Exception e) {
			return false;
		}
	}

	private Equipment getEquipament() {
		equip = new Equipment();
		equip.setDescriptionModel(txaModelDesc.getText());
		equip.setEquipamentBrand((Brand) cboBrand.getSelectedItem());
		equip.setName(txtNameEquipament.getText());
		equip.setSerialNumber(txtSerieNumber.getText());
		equip.setInvoice(txtInvoice.getText());
		java.util.Date daate = (java.util.Date) txtWarranty.getValue();
		equip.setWarrantyDate(new java.sql.Date(daate.getTime()));

		return equip;

	}

	// private Maintenance getMaintenance() {
	// maintenance = new Maintenance();
	//
	// java.util.Date daate = (java.util.Date) txtLastMaintenence.getValue();
	// maintenance.setDateMaintenance(new java.sql.Date(daate.getTime()));
	// daate = (java.util.Date) txtExpiredMaintenceDate.getValue();
	// maintenance.setExpireDate(new java.sql.Date(daate.getTime()));
	// // maintenance.setExpireDate((java.util.Date) txtExpiredMaintenceDate
	// // .getValue());
	// maintenance.setEquipmentMaintenance(equip);
	// maintenance.setDescriptionMaintenance(txaMaintenenceDesc.getText());
	// return maintenance;
	//
	// }

	private boolean verifycbo() {
		return cboBrand.getSelectedIndex() == -1 ? false : true;
	}

	private void addEquipament() {
		if (verifyField()) {
			if (verifycbo()) {
				equip = getEquipament();
				// maintenance = getMaintenance();
				int pk = controller.returnLastPK("equipment");
				if (controller.persistEquipament(equip)) {
					// maintenance.getEquipmentMaintenance().setId(pk);
					// controller.persistMaintenance(maintenance);
					ShowMessage
							.successMessage(this, "Sucesso",
									"Manutenção de equipamento cadastrada com sucesso.");

				}
			}
		} else {
			ShowMessage.errorMessage(thisFrame, "Atenção",
					"Preencha todos os campos ");
		}

	}
}
