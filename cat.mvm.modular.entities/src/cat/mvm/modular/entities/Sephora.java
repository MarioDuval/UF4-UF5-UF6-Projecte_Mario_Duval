package cat.mvm.modular.entities;

import cat.mvm.modular.products.Calculator;

import javax.swing.*;
import java.awt.*;

public class Sephora extends JFrame{
    private JLabel jlbCode;
    private JLabel jlbName;
    private JLabel jlbFamily;
    private JLabel jlbType;
    private JLabel jlbPrice;
    private JLabel jlbQuantity;
    private JTextField jtfCode;
    private JTextField jtfName;
    private JTextField jtfFamily;
    private JTextField jtfType;
    private JTextField jtfPrice;
    private JTextField jtfQuantity;
    private JButton jbtOK;

    public Sephora(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocation(500, 100);
        this.setTitle("Sephora");
        initComponents();
    }

    private void initComponents() {
        jlbCode = new JLabel();
        jlbName = new JLabel();
        jlbFamily = new JLabel();
        jlbType = new JLabel();
        jlbPrice = new JLabel();
        jlbQuantity = new JLabel();
        jtfCode = new JTextField();
        jtfName = new JTextField();
        jtfFamily = new JTextField();
        jtfType = new JTextField();
        jtfPrice = new JTextField();
        jtfQuantity = new JTextField();
        jbtOK = new JButton();

        getContentPane().setLayout(null);

        jlbCode.setText("Codi");
        getContentPane().add(jlbCode);
        jlbCode.setBounds(12, 28, 116, 14);

        jtfCode.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfCode);
        jtfCode.setBounds(80, 25, 350, 20);

        jlbName.setText("Nom");
        getContentPane().add(jlbName);
        jlbName.setBounds(12, 73, 104, 14);

        jtfName.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfName);
        jtfName.setBounds(80, 70, 350, 20);

        jlbFamily.setText("Familia");
        getContentPane().add(jlbFamily);
        jlbFamily.setBounds(12, 118, 104, 14);

        jtfFamily.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfFamily);
        jtfFamily.setBounds(80, 115, 350, 20);

        jlbType.setText("Tipus");
        getContentPane().add(jlbType);
        jlbType.setBounds(12, 163, 104, 14);

        jtfType.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfType);
        jtfType.setBounds(80, 160, 350, 20);

        jlbPrice.setText("Preu");
        getContentPane().add(jlbPrice);
        jlbPrice.setBounds(12, 208, 104, 14);

        jtfPrice.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfPrice);
        jtfPrice.setBounds(80, 205, 350, 20);

        jlbQuantity.setText("Quantitat");
        getContentPane().add(jlbQuantity);
        jlbQuantity.setBounds(12, 253, 104, 14);

        jtfQuantity.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfQuantity);
        jtfQuantity.setBounds(80, 250, 350, 20);

        jbtOK.setText("OK");
        jbtOK.setMnemonic('O');
        getRootPane().setDefaultButton(jbtOK);
        getContentPane().add(jbtOK);
        jbtOK.setBounds(355, 300, 75, 24);
    }


}
