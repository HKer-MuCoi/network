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

public class client_wb extends JFrame {

	private JPanel content;
	private JTextField txtContent;
	private static Socket socket;
	private static client_wb frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socket = new Socket("127.0.0.1", 3000);

					frame = new client_wb();
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
	public client_wb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		GridBagLayout gbl_content = new GridBagLayout();
		gbl_content.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_content.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_content.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_content.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		content.setLayout(gbl_content);

		JLabel lblNewLabel = new JLabel("Content");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		content.add(lblNewLabel, gbc_lblNewLabel);

		txtContent = new JTextField();
		GridBagConstraints gbc_txtContent = new GridBagConstraints();
		gbc_txtContent.insets = new Insets(0, 0, 5, 0);
		gbc_txtContent.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContent.gridx = 7;
		gbc_txtContent.gridy = 0;
		content.add(txtContent, gbc_txtContent);
		txtContent.setColumns(10);

		JButton btnClose = new JButton("Close");
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
		gbc_btnClose.gridx = 7;
		gbc_btnClose.gridy = 2;
		content.add(btnClose, gbc_btnClose);

		JTextArea txtShow = new JTextArea();
		GridBagConstraints gbc_txtShow = new GridBagConstraints();
		gbc_txtShow.gridwidth = 3;
		gbc_txtShow.fill = GridBagConstraints.BOTH;
		gbc_txtShow.gridx = 5;
		gbc_txtShow.gridy = 5;
		content.add(txtShow, gbc_txtShow);

		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());
					DataInputStream datain = new DataInputStream(socket.getInputStream());

					String data_send = txtContent.getText();
					dataout.writeUTF(data_send);
					dataout.flush();
					txtContent.setText(null);

					String data_receive = datain.readUTF();
					String result = txtShow.getText() + "\nClient: " + data_send + "\nServer: " + data_receive;
					txtShow.setText(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 5, 0);
		gbc_btnSend.gridx = 7;
		gbc_btnSend.gridy = 1;
		content.add(btnSend, gbc_btnSend);
	}

}
