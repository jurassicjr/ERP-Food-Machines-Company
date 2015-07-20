package rh.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import model.User;
import rh.controller.UpdatePermissionsFrameController;
import util.Icon;
import util.ShowMessage;

public class UpdatePermissionsFrame extends JFrame {
	
	private static final long serialVersionUID = 1902760648084014248L;
	
	private UpdatePermissionsFrameController controller;
	
	private JComboBox<User> cbUsers;
	private JTree permissions;
	
	private JButton btnUpdate;
	private JButton btnReset;
	
	private int lastIndex;

	public UpdatePermissionsFrame() {
		
		controller = new UpdatePermissionsFrameController(this);
		
		lastIndex = -1;
		
		creteTree();
		permissions.setEditable(false);
		
		initialize();
		setListeners();
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 530, 443);
		setMinimumSize(new Dimension(530, 443));
		setTitle("Atualização de Permissões");
		Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFuncionrio = new JLabel("Funcionário:");
		
		cbUsers = new JComboBox<User>();
		
		JScrollPane panel = new JScrollPane();
		panel.setViewportView(permissions);
		
		btnUpdate = new JButton("Atualizar");
		btnUpdate.setIcon(new ImageIcon(UpdatePermissionsFrame.class.getResource("/resources/update.png")));
		
		btnReset = new JButton("Definir permissões originais");
		btnReset.setIcon(new ImageIcon(UpdatePermissionsFrame.class.getResource("/resources/clear.png")));
		
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblFuncionrio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbUsers, 0, 421, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(btnReset)
							.addGap(18)
							.addComponent(btnUpdate)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFuncionrio)
						.addComponent(cbUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnReset))
					.addGap(9))
		);
		contentPane.setLayout(layout);
		
		controller.setUsers(cbUsers);
		cbUsers.setSelectedIndex(-1);
	}
	
	private void setListeners() {
		
		cbUsers.addActionListener (new ActionListener () {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeUser();
		    }
		});
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
		});
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbUsers.getSelectedItem()!=null)
				{
					if(e.getSource().equals(btnReset))
						controller.resetPermissions((User) cbUsers.getSelectedItem(), permissions);
					else if(e.getSource().equals(btnUpdate)) updatePermission();
				}else
					ShowMessage.errorMessage(null,"Mensagem","Selecione um usuário.");
			}
		};
		
		btnReset.addActionListener(btnListener);
		btnUpdate.addActionListener(btnListener);
		
	}
	
	private void changeUser() {
		
		if(lastIndex != -1) {
						
			User user = cbUsers.getItemAt(lastIndex);
									
			if(controller.hasModification(user, permissions)) {
				boolean reset = controller.resetPermissions(user, permissions);
				if(!reset) cbUsers.setSelectedIndex(lastIndex);
				else controller.resetPermissions(permissions);
			}
			else controller.resetPermissions(permissions);
			
		}
		else permissions.setEditable(true);
		
		controller.setPermissions((User) cbUsers.getSelectedItem(), permissions);
		
		lastIndex = cbUsers.getSelectedIndex();
	}
		
	private void creteTree() {
		
		CheckBoxNode rhOptions[] = {
				new CheckBoxNode("Registrar Funcionario", false, "REG_EMP"),
				new CheckBoxNode("Normas Técnicas", false, "TEC_STD"),
				new CheckBoxNode("Registrar Usuário", false, "REG_USER"),
				new CheckBoxNode("Relatório de Funcionários", false, "EMP_REP"),
				new CheckBoxNode("Atualizar Permissões", false, "UPD_PERM"),
				new CheckBoxNode("Registrar EPI's", false, "EPI_REG"),
				new CheckBoxNode("Atualização/Remoção de EPI's", false, "EPI_UPD"),
				new CheckBoxNode("Inserção de Epi's", false, "IST_EPI"),
				new CheckBoxNode("Retirada de EPI's", false, "RMV_EPI"),
				new CheckBoxNode("Registro de Treinamento", false, "REG_TRA"),
				new CheckBoxNode("Atualização/Remoção de treinamento", false, "UPD_TRA"),
				new CheckBoxNode("Atualização/Remoção de Funcionário", false, "UPD_EMP"),
				new CheckBoxNode("Pesquisa de Satisfação Interna", false, "INT_RES"),
				new CheckBoxNode("Pesquisa de Satisfação Externa", false, "EXT_RES"),
				new CheckBoxNode("Registro de Descrição de Cargo", false, "REG_FUD")
		};
		    
		CheckBoxNode financialOptions[] = {
				new CheckBoxNode("Registrar Conta a Pagar", false, "REG_BILL"),
				new CheckBoxNode("Pagar Conta", false, "PAY_BILL"),
				new CheckBoxNode("Listar Contas a Pagar", false, "LIST_BILL"),
				new CheckBoxNode("Registrar Conta a Receber", false, "REG_DEBT"),
				new CheckBoxNode("Receber Conta", false, "REC_DEBT"),
				new CheckBoxNode("Listar Contas a Pagar", false, "LIST_DEBT"),
				new CheckBoxNode("Relatório Financeiro", false, "FIN_REP")
		};
		
		CheckBoxNode salesOptions[] = {
				new CheckBoxNode("Registrar Material", false, "REG_MAT"),
				new CheckBoxNode("Registrar Produto", false, "REG_PROD"),
				new CheckBoxNode("Registrar Kit", false, "REG_KIT"),
				new CheckBoxNode("Registrar Cliente", false, "REG_CLI"),
				new CheckBoxNode("Atualizar/Remover Material", false, "UPD_MAT"),
				new CheckBoxNode("Atualizar/Remover Produto", false, "UPD_PROD"),
				new CheckBoxNode("Relatório de Fornecedores", false, "SUP_REP"),
				new CheckBoxNode("Relatório de Materiais", false, "MAT_REP"),
				new CheckBoxNode("Inserir Material em Estoque", false, "INS_STOCK"),
				new CheckBoxNode("Relatório de Clientes", false, "CLI_REP"),
				new CheckBoxNode("Consulta de Material", false, "SEA_MAT"),
				new CheckBoxNode("Consulta de Produto", false, "SEA_PROD"),
				new CheckBoxNode("Consulta de Clientes", false, "SEA_CLI"),
				new CheckBoxNode("Atualização/Remoção de Kit", false, "UPD_KIT"),
				new CheckBoxNode("Atualizar/Remover Registro de Cliente", false, "UPD_CLI"),
				new CheckBoxNode("Consulta de Kits", false, "SEA_KIT")
		};
		
		CheckBoxNode productionOptions[] = {
				new CheckBoxNode("Estágios de Produção", false, "PROD_STG")
		};
		
		CheckBoxNode buyOptions[] = {
				new CheckBoxNode("Registrar Fornecedor", false, "REG_SUP"),
				new CheckBoxNode("Atualizar/Remover Fornecedor", false, "UPD_SUP"),
				new CheckBoxNode("Homologar Fornecedor", false, "HOM_SUP"),
				new CheckBoxNode("Requisição de Compra", false, "SALE_REQ"),
				new CheckBoxNode("Pedido de Compra", false, "SALE_DEM")
		};
		    
		CheckBoxNode maintenanceOptions[] = {
				new CheckBoxNode("Registrar Veiculo", false, "REG_VEH"),
				new CheckBoxNode("Atualizar/Remover Registro de Veículos", false, "UPD_VEH"),
				new CheckBoxNode("Consulta de Veículos", false, "SEA_VEH")
		};
		
		Vector<?> rhVector = new NamedVector("RH", rhOptions);
		Vector<?> financialVector = new NamedVector("Financeiro", financialOptions);
		Vector<?> salesVector = new NamedVector("Vendas", salesOptions);
		Vector<?> productionVector = new NamedVector("Produção", productionOptions);
		Vector<?> buyVector = new NamedVector("Compras", buyOptions);
		Vector<?> maintenanceVector = new NamedVector("Manutenção", maintenanceOptions);
		
		Object rootNodes[] = { rhVector, financialVector, salesVector, productionVector, buyVector, maintenanceVector};
		
		Vector<?> rootVector = new NamedVector("Root", rootNodes);
		permissions = new JTree(rootVector);

		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		permissions.setCellRenderer(renderer);

		permissions.setCellEditor(new CheckBoxNodeEditor(permissions));
		permissions.setEditable(true);		
	}
	
	private void updatePermission() {
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
		controller.updatePermissions((User) cbUsers.getSelectedItem(), permissions);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}
	
	public class CheckBoxNodeRenderer implements TreeCellRenderer {
		
		private JCheckBox leafRenderer = new JCheckBox();
	
		private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();
	
		@SuppressWarnings("unused") private Color selectionBorderColor;
		private Color selectionForeground;
		private Color selectionBackground;
		private Color textForeground;
		private Color textBackground;		  
	
		public CheckBoxNodeRenderer() {
				  
			Font fontValue;
			fontValue = UIManager.getFont("Tree.font");
				  
			if (fontValue != null) {
				leafRenderer.setFont(fontValue);
			}
				  
			Boolean booleanValue = (Boolean) UIManager.get("Tree.drawsFocusBorderAroundIcon");
			leafRenderer.setFocusPainted((booleanValue != null) && (booleanValue.booleanValue()));
	
			selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
			selectionForeground = UIManager.getColor("Tree.selectionForeground");
			selectionBackground = UIManager.getColor("Tree.selectionBackground");
			textForeground = UIManager.getColor("Tree.textForeground");
			textBackground = UIManager.getColor("Tree.textBackground");
		}
	
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	
			Component returnValue;
				  
			if (leaf) {
	
				String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, false);
					  
				leafRenderer.setText(stringValue);
				leafRenderer.setSelected(false);
				leafRenderer.setEnabled(tree.isEnabled());

				if (selected) {
					leafRenderer.setForeground(selectionForeground);
					leafRenderer.setBackground(selectionBackground);
				}
				else {
					leafRenderer.setForeground(textForeground);
					leafRenderer.setBackground(textBackground);
				}
	
				if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
					  
					Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
					  
					if (userObject instanceof CheckBoxNode) {
						CheckBoxNode node = (CheckBoxNode) userObject;
						leafRenderer.setText(node.getText());
						leafRenderer.setSelected(node.isSelected());
						leafRenderer.setName(((CheckBoxNode) userObject).getMenuTag());
					}
				}
					  
				returnValue = leafRenderer;
					  
			}
			else {
				returnValue = nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
			}
				  
			return returnValue;
		}
			  
		protected JCheckBox getLeafRenderer() {
			return leafRenderer;
		}
	}

	public class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

		private static final long serialVersionUID = -4294194646595256738L;

		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		ChangeEvent changeEvent = null;
			
		JTree tree;

		public CheckBoxNodeEditor(JTree tree) {
			this.tree = tree;
		}

		@Override
        public Object getCellEditorValue() {
			
			JCheckBox checkbox = renderer.getLeafRenderer();
			CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(), checkbox.isSelected(), checkbox.getName());
			return checkBoxNode;
		}

		@Override
		public boolean isCellEditable(EventObject event) {
				
			boolean returnValue = false;
				
			if (event instanceof MouseEvent) {
					
				MouseEvent mouseEvent = (MouseEvent) event;
				TreePath path = tree.getPathForLocation(mouseEvent.getX(),
				mouseEvent.getY());
					
				if (path != null) {
						
					Object node = path.getLastPathComponent();
						
					if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
						DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
						Object userObject = treeNode.getUserObject();
						returnValue = ((treeNode.isLeaf()) && (userObject instanceof CheckBoxNode));
					}
				}
			}
				
			return returnValue;
		}
			
		@Override
		public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row) {

			Component editor = renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);
	
			ItemListener itemListener = new ItemListener() {
			    	
				@Override
                public void itemStateChanged(ItemEvent itemEvent) {
					if (stopCellEditing()) fireEditingStopped();
			    }
			};
			    
			if (editor instanceof JCheckBox) {
				((JCheckBox) editor).addItemListener(itemListener);
			}
	
			return editor;
		}
			
	}
		
	public class CheckBoxNode {
			
		private String text;
		private String menuTag;
		private boolean selected;

		public CheckBoxNode(String text, boolean selected, String menuTag) {
			this.text = text;
			this.selected = selected;
			this.menuTag = menuTag;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		public String getMenuTag() {
			return menuTag;
		}

		@Override
		public String toString() {
			return getClass().getName() + "[" + text + "/" + selected + "] --> " + selected;
		}
	}
	
	public class NamedVector extends Vector<Object> {
		  
		private static final long serialVersionUID = -3317702308581111763L;
		private String name;

		public NamedVector(String name) {
			this.name = name;
		}

		public NamedVector(String name, Object elements[]) {
			this.name = name;
			for (int i = 0, n = elements.length; i < n; i++) {
				add(elements[i]);
			}
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
