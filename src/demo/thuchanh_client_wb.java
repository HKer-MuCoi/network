package demo;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class thuchanh_client_wb extends JFrame {

	private JPanel contentPane;
	private JTextField txtContent;
	private JButton btnSend;
	private static Socket socket;
	private static thuchanh_client_wb frame;
	private JButton btnClose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socket = new Socket("127.0.0.1", 3000);
					frame = new thuchanh_client_wb();
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
	public thuchanh_client_wb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblContent = new JLabel("Tổng của chữ số trong chuỗi");
		GridBagConstraints gbc_lblContent = new GridBagConstraints();
		gbc_lblContent.insets = new Insets(0, 0, 5, 0);
		gbc_lblContent.gridx = 0;
		gbc_lblContent.gridy = 0;
		contentPane.add(lblContent, gbc_lblContent);

		txtContent = new JTextField();
		GridBagConstraints gbc_txtContent = new GridBagConstraints();
		gbc_txtContent.insets = new Insets(0, 0, 5, 0);
		gbc_txtContent.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContent.gridx = 0;
		gbc_txtContent.gridy = 2;
		contentPane.add(txtContent, gbc_txtContent);
		txtContent.setColumns(10);

		btnClose = new JButton("Đóng client");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());
					dataout.writeUTF("q");
					dataout.flush();
					socket.close();
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 4;
		contentPane.add(btnClose, gbc_btnClose);

		JTextArea txtResult = new JTextArea();
		GridBagConstraints gbc_txtResult = new GridBagConstraints();
		gbc_txtResult.fill = GridBagConstraints.BOTH;
		gbc_txtResult.gridx = 0;
		gbc_txtResult.gridy = 6;
		contentPane.add(txtResult, gbc_txtResult);

		btnSend = new JButton("Gửi");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					DataInputStream in = new DataInputStream(socket.getInputStream());

					String data_send = txtContent.getText();
					out.writeUTF(data_send);
					out.flush();
					txtContent.setText(null);

					String data_receive = in.readUTF();
					String result = txtResult.getText() + "\nClient: " + data_send + "\nServer: " + data_receive;
					txtResult.setText(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 5, 0);
		gbc_btnSend.gridx = 0;
		gbc_btnSend.gridy = 3;
		contentPane.add(btnSend, gbc_btnSend);

	}

}
