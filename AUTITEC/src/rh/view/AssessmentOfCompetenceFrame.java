package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Employee;
import net.miginfocom.swing.MigLayout;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.AssessmentOfCompetenceController;
import util.Icon;

public class AssessmentOfCompetenceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4375143092538685334L;
	private JTextField txtSector;
	private JTextField txtFunction;
	private JTextField txtScholarity;
	private JTextField txtExperience;
	private JTextField txtPoints;

	private DateField txtHireDate;

	private JTable table;

	private JComboBox<Employee> cboEmployee;

	private JLabel lblSector;
	private JLabel lblNome;
	private JLabel lblFunction;
	private JLabel lblHireDate;
	private JLabel lblScholarity;
	private JLabel lblExperience;
	private JLabel lblIsEnable;
	private JLabel lblPoints;
	
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	
	private JPanel subPanel;
	private JPanel principalPanel;

	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private AssessmentOfCompetenceController controller;

	public AssessmentOfCompetenceFrame() {
		controller = new AssessmentOfCompetenceController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Avaliação de Competência");
		setBounds(100, 100, 616, 592);
		setMinimumSize(new Dimension(616, 592));
		setPreferredSize(new Dimension(616, 592));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblNome = new JLabel("Nome");

		cboEmployee = new JComboBox<Employee>();

		lblSector = new JLabel("Departamento/Setor");

		txtSector = new JTextField();
		txtSector.setEnabled(false);
		txtSector.setColumns(10);

		lblFunction = new JLabel("Cargo");

		txtFunction = new JTextField();
		txtFunction.setEnabled(false);
		txtFunction.setColumns(10);

		lblHireDate = new JLabel("Data da Admissão");

		txtHireDate = CalendarFactory.createDateField();

		lblScholarity = new JLabel("Escolaridade Apresentada");

		txtScholarity = new JTextField();
		txtScholarity.setEnabled(false);
		txtScholarity.setColumns(10);

		lblExperience = new JLabel("Experiência Apresentada");

		txtExperience = new JTextField();
		txtExperience.setColumns(10);

		JScrollPane scrollPaneQualification = new JScrollPane();
		scrollPaneQualification.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3),
		        "Qualifica\u00E7\u00F5es Profissionais Apresentadas", TitledBorder.LEADING, TitledBorder.TOP, null,
		        null));
		scrollPaneQualification.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollPane scrollPaneTrainer = new JScrollPane();
		scrollPaneTrainer.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Treinamentos",
		        TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneTrainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollPane scrollPaneHabilities = new JScrollPane();
		scrollPaneHabilities.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3),
		        "Habilidades Pessoais Apresentadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblIsEnable = new JLabel("O colaborador está apto à trabalhar na empresa ?");

		rdbtnYes = new JRadioButton("Sim");

		rdbtnNo = new JRadioButton("Não");

		lblPoints = new JLabel("Se sim, qual a nota da avaliação");

		txtPoints = new JTextField();
		txtPoints.setColumns(10);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel
		        .setHorizontalGroup(gl_principalPanel
		                .createParallelGroup(Alignment.TRAILING)
		                .addGroup(
		                        gl_principalPanel
		                                .createSequentialGroup()
		                                .addContainerGap()
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createParallelGroup(Alignment.LEADING)
		                                                .addComponent(scrollPaneHabilities, GroupLayout.DEFAULT_SIZE,
		                                                        580, Short.MAX_VALUE)
		                                                .addComponent(scrollPaneTrainer, GroupLayout.DEFAULT_SIZE, 580,
		                                                        Short.MAX_VALUE)
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
		                                                                                                        lblScholarity)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtScholarity))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblNome)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        cboEmployee,
		                                                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                                                        261,
		                                                                                                        GroupLayout.PREFERRED_SIZE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblFunction)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtFunction)))
		                                                                .addGap(18)
		                                                                .addGroup(
		                                                                        gl_principalPanel
		                                                                                .createParallelGroup(
		                                                                                        Alignment.LEADING)
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblSector)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtSector,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        167,
		                                                                                                        Short.MAX_VALUE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblHireDate)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtHireDate,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        180,
		                                                                                                        Short.MAX_VALUE))
		                                                                                .addGroup(
		                                                                                        gl_principalPanel
		                                                                                                .createSequentialGroup()
		                                                                                                .addComponent(
		                                                                                                        lblExperience)
		                                                                                                .addPreferredGap(
		                                                                                                        ComponentPlacement.RELATED)
		                                                                                                .addComponent(
		                                                                                                        txtExperience,
		                                                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                                                        146,
		                                                                                                        Short.MAX_VALUE))))
		                                                .addComponent(scrollPaneQualification,
		                                                        GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
		                                                .addGroup(
		                                                        gl_principalPanel.createSequentialGroup()
		                                                                .addComponent(lblIsEnable)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnYes)
		                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
		                                                                .addComponent(rdbtnNo))
		                                                .addGroup(
		                                                        gl_principalPanel
		                                                                .createSequentialGroup()
		                                                                .addComponent(lblPoints)
		                                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                                .addComponent(txtPoints,
		                                                                        GroupLayout.PREFERRED_SIZE,
		                                                                        GroupLayout.DEFAULT_SIZE,
		                                                                        GroupLayout.PREFERRED_SIZE)))
		                                .addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblNome)
		                                .addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblSector)
		                                .addComponent(txtSector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblFunction)
		                                .addComponent(txtFunction, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblHireDate)
		                                .addComponent(txtHireDate, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblScholarity)
		                                .addComponent(txtScholarity, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                .addComponent(lblExperience)
		                                .addComponent(txtExperience, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addComponent(scrollPaneQualification, GroupLayout.PREFERRED_SIZE, 113,
		                        GroupLayout.PREFERRED_SIZE)
		                .addGap(18)
		                .addComponent(scrollPaneTrainer, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
		                .addGap(18)
		                .addComponent(scrollPaneHabilities, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblIsEnable)
		                                .addComponent(rdbtnYes).addComponent(rdbtnNo))
		                .addGap(18)
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblPoints)
		                                .addComponent(txtPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                                        GroupLayout.PREFERRED_SIZE)).addContainerGap(27, Short.MAX_VALUE)));

		JPanel habilitiesPanel = new JPanel();
		scrollPaneHabilities.setViewportView(habilitiesPanel);
		habilitiesPanel.setLayout(new MigLayout("wrap 3", "[] 100 [] 20 []"));

		table = new JTable();
		scrollPaneTrainer.setViewportView(table);

		JPanel QualificationPanel = new JPanel();
		scrollPaneQualification.setViewportView(QualificationPanel);
		QualificationPanel.setLayout(new MigLayout("wrap 3", "[] 100 [] 20 []"));

		principalPanel.setLayout(gl_principalPanel);

		intializeSub();
	}

	private void intializeSub() {
		subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(subPanel, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/ok.png")));
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/cancel.png")));
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(AssessmentOfCompetenceFrame.class.getResource("/resources/ClearFrame.png")));

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
	}
}
