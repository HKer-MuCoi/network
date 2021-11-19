package btl_hou;

import com.sun.security.ntlm.Client;
import demo.client_wb;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class client_bai2 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtRemoteIP;
    private JTextField txtRemotePort;
    private JTextField txtLocalPort;
    private JTextField txtSend;
    private static client_bai2 frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new client_bai2();
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
    public client_bai2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblRemoteIP = new JLabel("Remote IP");
        GridBagConstraints gbc_lblRemoteIP = new GridBagConstraints();
        gbc_lblRemoteIP.anchor = GridBagConstraints.EAST;
        gbc_lblRemoteIP.insets = new Insets(0, 0, 5, 5);
        gbc_lblRemoteIP.gridx = 0;
        gbc_lblRemoteIP.gridy = 0;
        contentPane.add(lblRemoteIP, gbc_lblRemoteIP);

        txtRemoteIP = new JTextField();
        txtRemoteIP.setText("127.0.0.1");
        GridBagConstraints gbc_txtRemoteIP = new GridBagConstraints();
        gbc_txtRemoteIP.insets = new Insets(0, 0, 5, 0);
        gbc_txtRemoteIP.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRemoteIP.gridx = 1;
        gbc_txtRemoteIP.gridy = 0;
        contentPane.add(txtRemoteIP, gbc_txtRemoteIP);
        txtRemoteIP.setColumns(10);

        JLabel lblRemotePort = new JLabel("Remote Port");
        GridBagConstraints gbc_lblRemotePort = new GridBagConstraints();
        gbc_lblRemotePort.anchor = GridBagConstraints.EAST;
        gbc_lblRemotePort.insets = new Insets(0, 0, 5, 5);
        gbc_lblRemotePort.gridx = 0;
        gbc_lblRemotePort.gridy = 1;
        contentPane.add(lblRemotePort, gbc_lblRemotePort);

        txtRemotePort = new JTextField();
        txtRemotePort.setText("3000");
        GridBagConstraints gbc_txtRemotePort = new GridBagConstraints();
        gbc_txtRemotePort.insets = new Insets(0, 0, 5, 0);
        gbc_txtRemotePort.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRemotePort.gridx = 1;
        gbc_txtRemotePort.gridy = 1;
        contentPane.add(txtRemotePort, gbc_txtRemotePort);
        txtRemotePort.setColumns(10);
        gbc_txtRemoteIP.insets = new Insets(0, 0, 5, 0);
        gbc_txtRemoteIP.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRemoteIP.gridx = 1;
        gbc_txtRemoteIP.gridy = 0;
        contentPane.add(txtRemoteIP, gbc_txtRemoteIP);
        txtRemoteIP.setColumns(10);

        txtSend = new JTextField();
        GridBagConstraints gbc_txtSend = new GridBagConstraints();
        gbc_txtSend.insets = new Insets(0, 0, 5, 0);
        gbc_txtSend.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSend.gridx = 1;
        gbc_txtSend.gridy = 4;
        contentPane.add(txtSend, gbc_txtSend);
        txtSend.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.gridheight = 2;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 6;
        contentPane.add(scrollPane, gbc_scrollPane);

        JTextArea txtall = new JTextArea();
        scrollPane.setViewportView(txtall);

        JButton btnSend = new JButton("Send");
        btnSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int port = Integer.parseInt(txtRemotePort.getText());
                    System.out.println(port);
                    DatagramSocket socket = new DatagramSocket();
                    socket.setSoTimeout(10000);
                    String str = txtSend.getText();
                    byte[] bufOut = str.getBytes();
                    InetAddress ip = InetAddress.getByName("localhost");
                    DatagramPacket dataout = new DatagramPacket(bufOut, bufOut.length, ip, port);
                    socket.send(dataout);
                    txtall.append("\nClient: " + str);

                    byte[] bufIn = new byte[1024];
                    DatagramPacket dataIn = new DatagramPacket(bufIn, bufIn.length);
                    socket.receive(dataIn);
                    String str1 = new String(dataIn.getData(), 0, dataIn.getLength());
                    txtall.append("\nServer: " + str1);
                    socket.close();
                } catch (SocketException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 5, 0);
        gbc_btnSend.gridx = 1;
        gbc_btnSend.gridy = 5;
        contentPane.add(btnSend, gbc_btnSend);
    }
}