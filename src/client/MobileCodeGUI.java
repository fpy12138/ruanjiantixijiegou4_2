package client;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MobileCodeGUI extends JFrame {

    private JTextField mobileCodeField;
    private JButton queryButton;
    private JTextArea resultArea;

    public MobileCodeGUI() {
        setTitle("手机号码归属地查询");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        mobileCodeField = new JTextField();
        queryButton = new JButton("查询归属地");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        add(new JLabel("手机号码:"));
        add(mobileCodeField);
        add(new JLabel(""));
        add(queryButton);
        add(new JLabel("查询结果:"));
        add(new JScrollPane(resultArea));

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryMobileCode();
            }
        });
    }

    private void queryMobileCode() {
        String mobileCode = mobileCodeField.getText();
        if (mobileCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入手机号码！");
            return;
        }

        try {
            MobileCodeWS service = new MobileCodeWS();
            MobileCodeWSSoap port = service.getMobileCodeWSSoap();
            String result = port.getMobileCodeInfo(mobileCode, "");
            resultArea.setText(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "查询失败: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MobileCodeGUI().setVisible(true);
            }
        });
    }
}