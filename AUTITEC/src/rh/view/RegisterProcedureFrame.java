package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Procedure;
import rh.controller.RegisterProcedureFrameController;
import util.Icon;
import util.ShowMessage;

public class RegisterProcedureFrame  extends Thread{

private JFrame frame;	
	
	private JPanel contentPane;
	
	private JTextField txProcedure;
	private JTextField txFile;
	
	private JButton btnFile;
	private JButton btnCancel;
	private JButton btnRegister;
	
	private boolean registered;
	private boolean update;
	private String technicalStandard;
	private File file;
		
	private Procedure pc;
	private RegisterProcedureFrameController controller;

	/**
	 * Cria o frame para o registo de uma nova Norma Técnica
	 */
	public RegisterProcedureFrame() {
		
		frame = new JFrame();
		controller = new RegisterProcedureFrameController(this);
		registered = false;
		update = false;
		
		initialize(null);
		setListeners();
	}
	
	/**
	 * Cria o frame para a atualização de um procedimento.
	 */
	public RegisterProcedureFrame(Procedure procedure) {
		
		frame = new JFrame();
		controller = new RegisterProcedureFrameController(this);
		
		pc = procedure;
		registered = false;
		update = true;
		
		initialize(procedure.getProcedure());
		setListeners();
		
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize(String technicalStandard) {
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 521, 186);
		frame.setMinimumSize(new Dimension(521, 186));
		frame.setTitle("Registro de Norma Técnica");
		Icon.setIcon(frame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblTechnicalStandard = new JLabel("Procedimento:");
		txProcedure = new JTextField();
		if(technicalStandard != null && !technicalStandard.isEmpty()) {
			txProcedure.setEditable(false);
			txProcedure.setText(technicalStandard);
		}
		
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
							.addComponent(txProcedure, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
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
						.addComponent(txProcedure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
				
				if(e.getSource().equals(btnCancel)) closeFrame();
				else if(e.getSource().equals(btnFile)) controller.selectFile();
				else if(e.getSource().equals(btnRegister)) {
					if(update) update();
					else register();
				}
				
			}
		};
		
		btnCancel.addActionListener(buttonsListeners);
		btnFile.addActionListener(buttonsListeners);
		btnRegister.addActionListener(buttonsListeners);
		
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				closeFrame();
			}
			
		});
		
	}
	
	/**
	 * Registra no banco de dados o procedimento.
	 */
	private void register() {
		
		registered = controller.register(txProcedure.getText(), new File(txFile.getText()));
		
		if(registered) {
			file = new File(txFile.getText());
			technicalStandard = txProcedure.getText();
		}
		
		start();
	}
	
	/**
	 * Registra uma nova versão do procedimento.
	 */
	private void update() {
				
		registered = controller.update(pc, new File(txFile.getText()));
		
		start();		
	}
	
	@Override
	public void run() {
		synchronized (this) {
			notifyAll();
		}
	}
	
	/**
	 * Define o caminho do arquivo selecionado para o procedimento
	 * 
	 * @param filePath O caminho do arquivo selecionado para o procedimento
	 */
	public void setFilePath(String filePath) {
		txFile.setText(filePath);
	}
	
	/**
	 * Retorna o frame do registro do procedimento
	 * 
	 * @return O frame do registro do procedimento
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Verifica se houve o registro de um procedimento
	 * 
	 * @return true se houve o registro de um procedimento ou false se não houve registro de procedimento
	 */
	public boolean isRegistered() {
		return registered;
	}
	
	/**
	 * Retorna o nome do procedimento registrada
	 * 
	 * @return O nome do procedimento registrada
	 */
	public String getTechnicalStandard() {
		return technicalStandard;
	}
	
	/**
	 * Retorna o arquivo selecionado para o procedimento
	 * 
	 * @return O arquivo selecionado para o procedimento
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Fecha a janela de registro de procedimento
	 */
	
	private void closeFrame() {
		
		int response = ShowMessage.questionMessage(frame, "Cancelar o registro", "Deseja cancelar o registro do Procedimento?\nOs dados do procedimento serão perdidos");
		
		if(response == JOptionPane.YES_OPTION) {
			registered = false;
			start();	
		}
		
	}
	
	/**
	 * Verifica se a norma técnica foi registrada ou não
	 * 
	 * @return true se a norma técnica foi registrado e false se nenhuma norma técnica foi registrada
	 */
	
	public boolean isRegistred() {
		return registered;
	}
}
