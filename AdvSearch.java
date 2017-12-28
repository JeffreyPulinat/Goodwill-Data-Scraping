import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AdvSearch extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private JTextField tfItemNum;
	private JTextField tfMinPrice;
	private JTextField tfMaxPrice;
	private String mode ="";

	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvSearch frame = new AdvSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param mode 
	 */
	public AdvSearch() {
		setTitle("Goodwill Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(98, 26, 307, 20);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 29, 78, 14);
		contentPane.add(lblSearch);
		
		tfItemNum = new JTextField();
		tfItemNum.setBounds(98, 54, 307, 20);
		contentPane.add(tfItemNum);
		tfItemNum.setColumns(10);
		
		JLabel lblItemNumber = new JLabel("Item Number:");
		lblItemNumber.setBounds(10, 57, 78, 14);
		contentPane.add(lblItemNumber);
		
		tfMinPrice = new JTextField();
		tfMinPrice.setBounds(98, 96, 107, 20);
		contentPane.add(tfMinPrice);
		tfMinPrice.setColumns(10);
		
		JLabel lblMinPrice = new JLabel("Min Price:");
		lblMinPrice.setBounds(10, 99, 78, 14);
		contentPane.add(lblMinPrice);
		
		JLabel lblMaxPrice = new JLabel("Max Price:");
		lblMaxPrice.setBounds(10, 124, 78, 14);
		contentPane.add(lblMaxPrice);
		
		tfMaxPrice = new JTextField();
		tfMaxPrice.setBounds(98, 121, 107, 20);
		contentPane.add(tfMaxPrice);
		tfMaxPrice.setColumns(10);
		
		JCheckBox chxbxOffline = new JCheckBox("Offline (Last Search)");
		chxbxOffline.setBounds(98, 159, 154, 23);
		contentPane.add(chxbxOffline);
		chxbxOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chxbxOffline.isSelected())mode ="offline";
			}
		});
	
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(106, 207, 89, 23);
		contentPane.add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					BuildString.goodwillURLbuild(tfSearch.getText(),tfItemNum.getText(),tfMinPrice.getText(),tfMaxPrice.getText(), mode);
			}
		});
	}
}