import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {

	private JPanel contentPane;
	private Store store=new Store();//store
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(10, 45));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(null);
		JButton btnLoadlog = new JButton("LoadLog");
		JButton btnExport = new JButton("Export");
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				store.export();
				btnExport.setEnabled(false);
				btnLoadlog.setEnabled(true);
			}
		});
		btnExport.setBounds(10, 10, 93, 23);
		panelTop.add(btnExport);
		
		
		btnLoadlog.setEnabled(false);
		btnLoadlog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				store.loadLog();
				btnExport.setEnabled(true);
				btnLoadlog.setEnabled(false);
			}
		});
		btnLoadlog.setBounds(118, 10, 93, 23);
		panelTop.add(btnLoadlog);
		
		JLabel lblCapital = new JLabel("$100000");//dollar label
		lblCapital.setBounds(241, 14, 81, 15);
		panelTop.add(lblCapital);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();//data table
		scrollPane.setViewportView(table);
		store.setDefaultTableModel((DefaultTableModel)table.getModel());
		store.setLblCapital(lblCapital);
	}
}
