import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DatabaseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfDrClass;
	private JTextField tfConURL;
	private JTextField tfUname;
	private JTextField tfPword;
	private JTextField tfQuery;

	/**
	 * Create the frame.
	 */
	public DatabaseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfDrClass = new JTextField();
		tfDrClass.setBounds(117, 12, 296, 20);
		contentPane.add(tfDrClass);
		tfDrClass.setColumns(10);
		
		JLabel lbldrClass = new JLabel("Driver Class:");
		lbldrClass.setBounds(10, 15, 97, 14);
		contentPane.add(lbldrClass);
		
		tfConURL = new JTextField();
		tfConURL.setBounds(117, 43, 296, 20);
		contentPane.add(tfConURL);
		tfConURL.setColumns(10);
		
		JLabel lblconUrl = new JLabel("Connection URL:");
		lblconUrl.setBounds(10, 46, 97, 14);
		contentPane.add(lblconUrl);
		
		tfUname = new JTextField();
		tfUname.setBounds(117, 74, 296, 20);
		contentPane.add(tfUname);
		tfUname.setColumns(10);
		
		JLabel lbluName = new JLabel("Username:");
		lbluName.setBounds(10, 77, 97, 14);
		contentPane.add(lbluName);
		
		tfPword = new JTextField();
		tfPword.setBounds(117, 105, 296, 20);
		contentPane.add(tfPword);
		tfPword.setColumns(10);
		
		JLabel lblpWord = new JLabel("Password:");
		lblpWord.setBounds(10, 108, 97, 14);
		contentPane.add(lblpWord);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					DBconnect(tfDrClass.getText(),tfConURL.getText(),tfUname.getText(),tfPword.getText(),tfQuery.getText());
			}
		});
		btnConnect.setBounds(10, 181, 97, 47);
		contentPane.add(btnConnect);
		
		tfQuery = new JTextField();
		tfQuery.setBounds(117, 136, 296, 114);
		contentPane.add(tfQuery);
		tfQuery.setColumns(10);
		
		JLabel lblQuery = new JLabel("Write Query:");
		lblQuery.setBounds(28, 144, 72, 14);
		contentPane.add(lblQuery);
	}
	
	/**
	 * Connects to the database and performs query
	 * @param drClass -driver
	 * @param conURL - connection URL
	 * @param uName - username
	 * @param pWord - password
	 * @param query - query
	 */
	private static void DBconnect(String drClass, String conURL, String uName, String pWord, String query){
		try{  
			Class.forName(drClass);  //Driver Class
			Connection con=DriverManager.getConnection(conURL,uName,pWord);
			Statement stmt=con.createStatement();  
			ResultSet rs = stmt.executeQuery(query);
			con.close();
			}catch(Exception e){ JOptionPane.showMessageDialog(null, "Cannot connect to databse!" + "\n"+ e);
		}  
	}
	
	/**
	 * Launch the database gui
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseGUI frame = new DatabaseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

