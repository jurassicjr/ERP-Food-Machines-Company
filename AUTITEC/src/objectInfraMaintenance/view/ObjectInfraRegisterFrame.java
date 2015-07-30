package objectInfraMaintenance.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maintenance.view.register.VehicleRegisterFrame;
import model.ObjectInfra;
import objectInfraMaintenence.controller.ObjectInfraRegisterFrameController;
import userInterface.components.FrameController;
import util.Icon;
import util.ShowMessage;

public class ObjectInfraRegisterFrame extends JFrame 
{
	private ObjectInfraRegisterFrame thisFrame = this;
	private String frameName = "Registro de Móveis";
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtDescription;
	private ObjectInfraRegisterFrameController controller;
	
	public ObjectInfraRegisterFrame() 
	{
		initialize();
		setListeners();
		controller = new ObjectInfraRegisterFrameController();
	}
	
	private void initialize() 
	{
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 530, 209);
		setMinimumSize(new Dimension(530, 209));
		setPreferredSize(new Dimension(530, 209));
		initializePrincipal();		
	}
	
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel panelData = new JPanel();
		panelData.setBounds(10, 11, 500, 96);
		getContentPane().add(panelData);
		panelData.setLayout(null);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(10, 48, 257, 20);
		panelData.add(txtDescription);
		txtDescription.setColumns(10);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(10, 23, 80, 14);
		panelData.add(lblDescription);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 118, 500, 41);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setBounds(369, 11, 121, 23);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(248, 11, 111, 23);
		panel.add(btnCancel);	
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		this.setLocationRelativeTo(null);
	}
	
	public void setListeners()
	{
		ActionListener buttonAction = new ActionListener() 
		{		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource().equals(btnConfirm))
				{
					if(!txtDescription.getText().isEmpty())
					{
						
							insertObjectRegister();
					}
					else
						ShowMessage.errorMessage(null, "Mensagem", "Preencha o campo descrição");
				}
				else
				{
					FrameController.close(thisFrame, frameName);
					
				}
			}
		};
		
		KeyListener keylistener = new KeyAdapter() 
		{		
			public void keyPressed(KeyEvent e) 
			{
				if(e.getSource().equals(txtDescription))
				{
					if(e.getKeyChar()== KeyEvent.VK_ENTER)
						insertObjectRegister();
				}				
			}
		};
		
		btnConfirm.addActionListener(buttonAction);
		btnCancel.addActionListener(buttonAction);
		FrameController.addConfirmationOnClose(thisFrame, frameName);
		txtDescription.addKeyListener(keylistener);
	}
	
	private void insertObjectRegister()
	{
		if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja registrar o móvel ?")== JOptionPane.YES_OPTION)
		{
			controller.insert(getRegister());
			ShowMessage.successMessage(null, "Mensagem", "Registro Inserido com Sucesso!");
			txtDescription.setText("");
			txtDescription.grabFocus();
		}
	}
	
	private ObjectInfra getRegister()
	{
		ObjectInfra obj = new ObjectInfra();
		obj.setDescription(txtDescription.getText());
		return obj;
	}
}
