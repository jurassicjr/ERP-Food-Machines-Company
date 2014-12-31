package sales.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import model.Product;
import sales.controller.SalesController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.ShowMessage;

public class RegisterOfProductFrame extends JFrame {
	/**
	 * 
	 */
    private static final long serialVersionUID = 532262099381038089L;
	
    private JPanel principalPanel;
    private JPanel bottonPanel;
	
    private UpperTextField txtName;
	private JTextField txtQuantidade;
	
	private JLabel lblQuantidade;
	private JLabel lblDescrio;

	private JButton btnConfirmar;
	private JButton btnCancelar;
	
	private JTextArea txtDescricao;
	
	private SalesController controller;
	private RegisterOfProductFrame frame;
	private ShowMessage message = new ShowMessage();
	
	public RegisterOfProductFrame() {
	frame = this;
	controller = new SalesController();
	initializePrincipal();
	setListeners();
	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */
	
	private void initializePrincipal() {
		this.setTitle("Cadastro de Produto");		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JLabel lblNome = new JLabel("Nome");
		
		txtName = new UpperTextField();
		txtName.setColumns(10);
		
		lblQuantidade = new JLabel("Quantidade");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		lblDescrio = new JLabel("Descrição");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDescrio))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescrio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtDescricao = new JTextArea();
		scrollPane.setViewportView(txtDescricao);
		principalPanel.setLayout(gl_principalPanel);
		initializeBotton();
	}
	
	/**
	 * Inicializa o Jpanel inferior com os componentes de confirmar e sair.
	 */
	
	private void initializeBotton() {
		bottonPanel = new JPanel();
		this.getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegisterOfProductFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancelar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(RegisterOfProductFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnConfirmar);
	}
	
	/**
	 * Adiciona os listeners aos componentes da classe.
	 */
	
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    controller.closeFrame(frame);
			}
		});
		ActionListener btnAction = new ActionListener() {
			
			private ClearFrame faxineira;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancelar)) controller.closeFrame(frame);
				else if(e.getSource().equals(btnConfirmar)) {
					Product produto = new Product();
					produto.setDescrition(txtDescricao.getText());
					produto.setName(txtName.getText());
					produto.setAmmount(Integer.parseInt(txtQuantidade.getText()));
					try {
					controller.doProductRegister(produto);
					ClearFrame.clear(frame);
					//message.successMessage(frame, "Gravação", "Gravação concluida com sucesso!");
					}catch(Exception erro) {
						erro.printStackTrace();
					}
				}
			}
		};
		btnConfirmar.addActionListener(btnAction);
		btnCancelar.addActionListener(btnAction);
	}
}
