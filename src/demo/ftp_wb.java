package demo;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ftp_wb extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ftp_wb frame = new ftp_wb();
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
	public ftp_wb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblName = new JLabel("Tên ảnh");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);

		JLabel lblINameInfo = new JLabel("Chưa có");
		GridBagConstraints gbc_lblINameInfo = new GridBagConstraints();
		gbc_lblINameInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblINameInfo.gridx = 5;
		gbc_lblINameInfo.gridy = 1;
		contentPane.add(lblINameInfo, gbc_lblINameInfo);

		JLabel lblParent = new JLabel("Đường dẫn thư mục");
		GridBagConstraints gbc_lblParent = new GridBagConstraints();
		gbc_lblParent.insets = new Insets(0, 0, 5, 5);
		gbc_lblParent.gridx = 0;
		gbc_lblParent.gridy = 2;
		contentPane.add(lblParent, gbc_lblParent);

		JLabel lblPathInfo = new JLabel("Chưa có");
		GridBagConstraints gbc_lblPathInfo = new GridBagConstraints();
		gbc_lblPathInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPathInfo.gridx = 5;
		gbc_lblPathInfo.gridy = 2;
		contentPane.add(lblPathInfo, gbc_lblPathInfo);

		JLabel lblPath = new JLabel("Đường dẫn full");
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 3;
		contentPane.add(lblPath, gbc_lblPath);

		JLabel lblParantInfo = new JLabel("Chưa có");
		GridBagConstraints gbc_lblParantInfo = new GridBagConstraints();
		gbc_lblParantInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblParantInfo.gridx = 5;
		gbc_lblParantInfo.gridy = 3;
		contentPane.add(lblParantInfo, gbc_lblParantInfo);

		JLabel lblSize = new JLabel("Kích thước");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 0;
		gbc_lblSize.gridy = 4;
		contentPane.add(lblSize, gbc_lblSize);

		JLabel lblSizeInfo = new JLabel("Chưa có");
		GridBagConstraints gbc_lblSizeInfo = new GridBagConstraints();
		gbc_lblSizeInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeInfo.gridx = 5;
		gbc_lblSizeInfo.gridy = 4;
		contentPane.add(lblSizeInfo, gbc_lblSizeInfo);

		JLabel lblImage = new JLabel("Ảnh hiển thị");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 0, 0, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 5;
		contentPane.add(lblImage, gbc_lblImage);

		JButton btnView = new JButton("Xem ảnh");
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.insets = new Insets(0, 0, 5, 0);
		gbc_btnView.gridx = 7;
		gbc_btnView.gridy = 1;
		contentPane.add(btnView, gbc_btnView);
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Socket socket = new Socket("127.0.0.1", 3000);
					DataInputStream datain = new DataInputStream(socket.getInputStream());

					FileOutputStream output = new FileOutputStream("/home/tuancong/Downloads/output.jpg");

					int is;
					byte[] data = new byte[10000];

					while ((is = datain.read(data)) > 0) {
						output.write(data, 0, is);
					}

					System.out.println("Run finished");

					File image = new File("/home/tuancong/Downloads/output.jpg");
					if (image.exists()) {
						lblINameInfo.setText(image.getName());
						lblParantInfo.setText(image.getParent());
						lblPathInfo.setText(image.getPath());
						lblSizeInfo.setText(image.length() + "bytes");
						lblImage.setText(null);
						lblImage.setIcon(new ImageIcon("/home/tuancong/Downloads/output.jpg"));
					}
					output.close();
					datain.close();
					socket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
