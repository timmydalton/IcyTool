package icyGUI;

import database.Controller;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainGUI extends JFrame {
	
	Controller controller = new Controller();
	private Vector<String> header = controller.getHeader();
	private Vector<Vector<String>> data;

	public MainGUI(){
		// 
		//Khoi tao UI Main
		
		//
		super("IcyTool");
		this.setSize(800, 600);
		getContentPane().setLayout(null);
		
		//Tao scrollPane1
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setToolTipText("");
		scrollPane1.setBounds(10, 100, 764, 300);
		getContentPane().add(scrollPane1);
		
		//Set viewport table cho scrollPane1
		JTable table = new JTable();
		scrollPane1.setViewportView(table);
		
		//Tao button
		JButton btnExportAll = new JButton("Export");
		btnExportAll.setBounds(10, 15, 90, 30);
		getContentPane().add(btnExportAll);
			
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(10, 59, 90, 30);
		getContentPane().add(btnFind);
		
		JButton btnDelAll = new JButton("Del Table");
		btnDelAll.setBounds(664, 15, 90, 30);
		getContentPane().add(btnDelAll);
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(664, 59, 90, 30);
		getContentPane().add(btnImport);
		
		JButton btnNote = new JButton("?");
		btnNote.setBounds(614, 15, 40, 30);
		getContentPane().add(btnNote);
		
		//Tao textfield nhap du lieu
		JTextField textField1 = new JTextField();
		textField1.setBounds(110, 59, 250, 30);
		getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JTextField textField2 = new JTextField();
		textField2.setEnabled(false);
		textField2.setColumns(10);
		textField2.setBounds(404, 59, 250, 30);
		getContentPane().add(textField2);
		
		JTextField textField3 = new JTextField();
		textField3.setVisible(false);
		textField3.setColumns(10);
		textField3.setBounds(404, 15, 173, 30);
		getContentPane().add(textField3);

		//Tao scrollPane2
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 411, 764, 139);
		getContentPane().add(scrollPane2);
		
		//Set viewport textArea cho scrollPane2
		JTextArea textArea = new JTextArea();
		scrollPane2.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		//Label
		JLabel lblField2 = new JLabel("To:");
		lblField2.setEnabled(false);
		lblField2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblField2.setBounds(367, 59, 30, 30);
		getContentPane().add(lblField2);
		
		JLabel lblCombo = new JLabel("Filter option:");
		lblCombo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCombo.setBounds(110, 15, 96, 30);
		getContentPane().add(lblCombo);
		
		JLabel lblField3 = new JLabel("Year:");
		lblField3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblField3.setVisible(false);
		lblField3.setBounds(367, 15, 46, 30);
		getContentPane().add(lblField3);

		//Tao comboBox
		String s1[] = {"Year or ID", "Year range", "Year and ID"};
		JComboBox comboBox = new JComboBox(s1);	
		comboBox.setBounds(210, 15, 150, 30);
		getContentPane().add(comboBox);
					
		//
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Xu ly cac event
		
		//Event nut Export
		btnExportAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					data = controller.getAll();
					((DefaultTableModel)(table.getModel())).setDataVector(data, header);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Event combobox
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem() == "Year or ID") {
					lblField3.setVisible(false);
					textField3.setVisible(false);
					lblField2.setEnabled(false);
					textField2.setText("");
					textField2.setEnabled(false);
					textField3.setText("");
				}
				else if (comboBox.getSelectedItem() == "Year range"){
					lblField3.setVisible(false);
					textField3.setVisible(false);
					lblField2.setEnabled(true);
					textField2.setEnabled(true);
					textField3.setText("");
				}
				else if (comboBox.getSelectedItem() == "Year and ID"){
					lblField3.setVisible(true);
					textField3.setVisible(true);
					lblField2.setEnabled(true);
					textField2.setEnabled(true);
				}

			}
		});
		
		//Event nut Find
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == "Year or ID") {
					try {
						data = controller.getOne(textField1.getText());
						((DefaultTableModel)(table.getModel())).setDataVector(data, header);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (comboBox.getSelectedItem() == "Year range") {
					try {
						data = controller.getYearRange(textField1.getText(), textField2.getText());
						((DefaultTableModel)(table.getModel())).setDataVector(data, header);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (comboBox.getSelectedItem() == "Year and ID") {
					try {
						data = controller.getRangeByIdYear(textField1.getText(), textField2.getText(),textField3.getText());
						((DefaultTableModel)(table.getModel())).setDataVector(data, header);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(scrollPane1, "Get data failure\nDetails: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//Event nut DelAll
		btnDelAll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.DelAll();
					JOptionPane.showMessageDialog(MainGUI.this, "Delete table successful");
				} catch(Exception er) {
					JOptionPane.showMessageDialog(MainGUI.this, "Delete failed, please check your database with a CVEList table");
				}
			}
		});
		
		//Event nut Import
		btnImport.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new ImportGUI();
			}
		});
		
		//Event khi click vao mot row trong table
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				textArea.setText(controller.displaySelectedRow(data, selectedRow));
				textArea.setCaretPosition(0);
			}
		});
		
		//Event nut ?
		btnNote.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(MainGUI.this, "Note:\n - Input ID must be typed in full length\n E.g. CVE-1999-0015 have ID number is \"15\"");
			}
		});

	}
}
