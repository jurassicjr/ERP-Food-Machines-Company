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

import model.TechnicalStandard;
import rh.controller.RegisterTechnicalStandardFrameController;
import util.Icon;
import util.ShowMessage;

/**
 * Classe para o registro de uma nova Norma Técnica
 */
public class RegisterTechnicalStandardFrame extends Thread {
	
	private JFrame frame;	
	
	private JPanel contentPane;
	
	private JTextField txTechicalStandard;
	private JTextField txFile;
	
	private JButton btnFile;
	private JButton btnCancel;
	private JButton btnRegister;
	
	private boolean registered;
	private boolean update;
	private String technicalStandard;
	private File file;
		
	private TechnicalStandard ts;
	private RegisterTechnicalStandardFrameController controller;

	/**
	 * Cria o frame para o registo de uma nova Norma Técnica
	 */
	public RegisterTechnicalStandardFrame() {
		
		frame = new JFrame();
		controller = new RegisterTechnicalStandardFrameController(this);
		registered = false;
		update = false;
		
		initialize(null);
		setListeners();
	}
	
	/**
	 * Cria o frame para a atualização de uma Norma Técnica
	 */
	public RegisterTechnicalStandardFrame(TechnicalStandard technicalStandard) {
		
		frame = new JFrame();
		controller = new RegisterTechnicalStandardFrameController(this);
		
		ts = technicalStandard;
		registered = false;
		update = true;
		
		initialize(technicalStandard.getTechnicalStandard());
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
		
		JLabel lblTechnicalStandard = new JLabel("Norma Técnica:");
		txTechicalStandard = new JTextField();
		if(technicalStandard != null && !technicalStandard.isEmpty()) {
			txTechicalStandard.setEditable(false);
			txTechicalStandard.setText(technicalStandard);
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
	 * Registra no banco de dados a norma técnica
	 */
	private void register() {
		
		registered = controller.register(txTechicalStandard.getText(), new File(txFile.getText()));
		
		if(registered) {
			file = new File(txFile.getText());
			technicalStandard = txTechicalStandard.getText();
		}
		
		start();
	}
	
	/**
	 * Registra uma nova versão da norma técnica
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
	 * Define o caminho do arquivo selecionado para a norma técnica 
	 * 
	 * @param filePath O caminho do arquivo selecionado para a norma técnica
	 */
	public void setFilePath(String filePath) {
		txFile.setText(filePath);
	}
	
	/**
	 * Retorna o frame do registro de norma técnica 
	 * 
	 * @return O frame do registro de norma técnica
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Verifica se houve o registro de uma norma técnica
	 * 
	 * @return true se houve o registro de uma norma técnica ou false se não houve registro de norma técnica
	 */
	public boolean isRegistered() {
		return registered;
	}
	
	/**
	 * Retorna o nome da norma técnica registrada
	 * 
	 * @return O nome da norma técnica registrada
	 */
	public String getTechnicalStandard() {
		return technicalStandard;
	}
	
	/**
	 * Retorna o arquivo selecionado para a norma técnica
	 * 
	 * @return O arquivo selecionado para a norma técnica
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Fecha a janela de registro da norma técnica
	 */
	private void closeFrame() {
		
		int response = ShowMessage.questionMessage(frame, "Cancelar o registro", "Deseja cancelar o registro da Norma Técnica?\nOs dados da Norma Técnica serão perdidos");
		
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
