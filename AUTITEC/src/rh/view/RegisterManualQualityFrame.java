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

import model.ManualQuality;
import rh.controller.RegisterManualQualityFrameController;
import util.Icon;
import util.ShowMessage;

public class RegisterManualQualityFrame extends Thread{


	private JFrame frame;	
	
	private JPanel contentPane;
	
	private JTextField txManualQuality;
	private JTextField txFile;
	
	private JButton btnFile;
	private JButton btnCancel;
	private JButton btnRegister;
	
	private boolean registered;
	private boolean update;
	private String manualQuality;
	private File file;
		
	private ManualQuality ts;
	private RegisterManualQualityFrameController controller;

	/**
	 * Cria o frame para o registo de uma nova Norma Técnica
	 */
	public RegisterManualQualityFrame() {
		
		frame = new JFrame();
		controller = new RegisterManualQualityFrameController(this);
		registered = false;
		update = false;
		
		initialize(null);
		setListeners();
	}
	
	/**
	 * Cria o frame para a atualização de uma Norma Técnica
	 */
	public RegisterManualQualityFrame(ManualQuality manualQuality) {
		
		frame = new JFrame();
		controller = new RegisterManualQualityFrameController(this);
		
		ts = manualQuality;
		registered = false;
		update = true;
		
		initialize(manualQuality.getManualQuality());
		setListeners();
		
	}
	
	/**
	 * Inicializa os componentes gráficos do frame
	 */
	private void initialize(String manualQuality) {
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 521, 186);
		frame.setMinimumSize(new Dimension(521, 186));
		frame.setTitle("Registro do Manual de Qualidade");
		Icon.setIcon(frame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblManualQuality = new JLabel("Manual de Qualidade:");
		txManualQuality = new JTextField();
		if(manualQuality != null && !manualQuality.isEmpty()) {
			txManualQuality.setEditable(false);
			txManualQuality.setText(manualQuality);
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
							.addComponent(lblManualQuality)
							.addGap(18)
							.addComponent(txManualQuality, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
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
						.addComponent(lblManualQuality)
						.addComponent(txManualQuality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
	 * Registra no banco de dados o manual de qualidade
	 */
	private void register() {
		
		registered = controller.register(txManualQuality.getText(), new File(txFile.getText()));
		
		if(registered) {
			file = new File(txFile.getText());
			manualQuality = txManualQuality.getText();
		}
		
		start();
	}
	
	/**
	 * Registra uma nova versão do manual de qualidade
	 */
	private void update() {
				
		registered = controller.update(ts, new File(txFile.getText()));
		
		start();		
	}
	
	@Override
	public void run() {
		synchronized (this) {
			notifyAll();
		}
	}
	
	/**
	 * Define o caminho do arquivo selecionado para o manual de qualidade
	 * 
	 * @param filePath O caminho do arquivo selecionado para o manual de qualidade
	 */
	public void setFilePath(String filePath) {
		txFile.setText(filePath);
	}
	
	/**
	 * Retorna o frame do registro do manual de qualidade 
	 * 
	 * @return O frame do registro do manual de qualidade
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Verifica se houve o registro de um manual de qualidade
	 * 
	 * @return true se houve o registro de um manual de qualidade ou false se não houve registro de manual de qualidade
	 */
	public boolean isRegistered() {
		return registered;
	}
	
	/**
	 * Retorna o nome do manual de qualidade registrado
	 * 
	 * @return O nome do manual de qualidade registrado
	 */
	public String getManualQuality() {
		return manualQuality;
	}
	
	/**
	 * Retorna o arquivo selecionado para o manual de qualidade
	 * 
	 * @return O arquivo selecionado para o manual de qualidade
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Fecha a janela de registro do manual de qualidade
	 */
	
	private void closeFrame() {
		
		int response = ShowMessage.questionMessage(frame, "Cancelar o registro", "Deseja cancelar o registro do Manual de Qualidade?\nOs dados do Manual de Qualidade serão perdidos");
		
		if(response == JOptionPane.YES_OPTION) {
			registered = false;
			start();	
		}
		
	}
	
	/**
	 * Verifica se o manual de qualidade foi registrada ou não
	 * 
	 * @return true se o manual de qualidade foi registrado e false se nenhum manual de qualidade foi registrado
	 */
	
	public boolean isRegistred() {
		return registered;
	}

}
