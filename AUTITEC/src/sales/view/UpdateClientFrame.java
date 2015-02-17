package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UpdateClientFrame extends JFrame{
	
	
	private JPanel principalPanel;
	private JTextField txtName;
	private JTextField txtStreet;
	private JTextField txtNeighborhood;
	private JTextField txtCep;

	public UpdateClientFrame() {
	   initialize();
    }
	
	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("atualização/Remoção de Registro de Cliente");
		setBounds(100, 100, 506, 270);
		setMinimumSize(new Dimension(506, 270));
		setPreferredSize(new Dimension(506, 270));
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
	   principalPanel = new JPanel();
	   getContentPane().add(principalPanel, BorderLayout.CENTER);
	   
	   JLabel lblName = new JLabel("Nome");
	   
	   txtName = new JTextField();
	   txtName.setColumns(10);
	   
	   JLabel lblStreet = new JLabel("Rua");
	   
	   txtStreet = new JTextField();
	   txtStreet.setColumns(10);
	   
	   JLabel lblNeighborhood = new JLabel("Bairro");
	   
	   txtNeighborhood = new JTextField();
	   txtNeighborhood.setColumns(10);
	   
	   JLabel lblCep = new JLabel("C.E.P");
	   
	   txtCep = new JTextField();
	   txtCep.setColumns(10);
	   GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	   gl_principalPanel.setHorizontalGroup(
	   	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_principalPanel.createSequentialGroup()
	   			.addContainerGap()
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	   				.addGroup(gl_principalPanel.createSequentialGroup()
	   					.addComponent(lblName)
	   					.addPreferredGap(ComponentPlacement.RELATED)
	   					.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
	   				.addGroup(gl_principalPanel.createSequentialGroup()
	   					.addComponent(lblStreet)
	   					.addPreferredGap(ComponentPlacement.RELATED)
	   					.addComponent(txtStreet, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
	   				.addGroup(gl_principalPanel.createSequentialGroup()
	   					.addComponent(lblNeighborhood)
	   					.addPreferredGap(ComponentPlacement.RELATED)
	   					.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	   					.addGap(18)
	   					.addComponent(lblCep)
	   					.addPreferredGap(ComponentPlacement.RELATED)
	   					.addComponent(txtCep, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
	   			.addContainerGap())
	   );
	   gl_principalPanel.setVerticalGroup(
	   	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	   		.addGroup(gl_principalPanel.createSequentialGroup()
	   			.addContainerGap()
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	   				.addComponent(lblName)
	   				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addGap(18)
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	   				.addComponent(lblStreet)
	   				.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addGap(18)
	   			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	   				.addComponent(lblNeighborhood)
	   				.addComponent(txtNeighborhood, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	   				.addComponent(lblCep)
	   				.addComponent(txtCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	   			.addContainerGap(130, Short.MAX_VALUE))
	   );
	   principalPanel.setLayout(gl_principalPanel);
	   
    }
}
