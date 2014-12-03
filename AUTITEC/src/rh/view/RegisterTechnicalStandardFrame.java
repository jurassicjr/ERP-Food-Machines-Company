package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import rh.controller.RegisterTechnicalStandardFrameController;

/**
 * Classe para o registro de uma nova Norma Técnica
 */
public class RegisterTechnicalStandardFrame extends JFrame {
	
	private static final long serialVersionUID = -6409333598021749949L;
	
	private JPanel contentPane;
	
	private JTextField txTechicalStandard;
	private JTextField txFile;
	
	private JButton btnFile;
	private JButton btnCancel;
	private JButton btnRegister;
	
	private RegisterTechnicalStandardFrameController controller;

	/**
	 * Cria o frame para o registo de uma nova Norma Técnica
	 */
	public RegisterTechnicalStandardFrame() {
		
		controller = new RegisterTechnicalStandardFrameController(this);
		
		initialize();
		setListeners();
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 186);
		setMinimumSize(new Dimension(521, 186));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblTechnicalStandard = new JLabel("Norma Técnica:");
		txTechicalStandard = new JTextField();
		
		JLabel lblFile = new JLabel("Arquivo:");
		txFile = new JTextField();
		txFile.setEditable(false);
		
		btnFile = new JButton("Carregar Arquivo");
		btnFile.setIcon(new ImageIcon(RegisterTechnicalStandardFrame.class.getResource("/resources/open.png")));
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblTechnicalStandard)
							.addGap(18)
							.addComponent(txTechicalStandard, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblFile)
							.addGap(18)
							.addComponent(txFile, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnFile)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTechnicalStandard)
						.addComponent(txTechicalStandard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFile)
						.addComponent(txFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFile))
					.addContainerGap(188, Short.MAX_VALUE))
		);
		panel.setLayout(layout);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterTechnicalStandardFrame.class.getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterTechnicalStandardFrame.class.getResource("/resources/ok.png")));
		btnPanel.add(btnRegister);
		
	}

	/**
	 * Define os listeners da aplicação
	 */
	private void setListeners() {
		
		ActionListener buttonsListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel));
				else if(e.getSource().equals(btnFile)) controller.selectFile();
				else if(e.getSource().equals(btnCancel)) controller.register();
				
			}
		};
		
		btnCancel.addActionListener(buttonsListeners);
		btnFile.addActionListener(buttonsListeners);
		btnRegister.addActionListener(buttonsListeners);		
		
	}
	
	/**
	 * Define o caminho do arquivo selecionado para a norma técnica 
	 * 
	 * @param filePath O caminho do arquivo selecionado para a norma técnica
	 */
	public void setFilePath(String filePath) {
		txFile.setText(filePath);
	}
}
