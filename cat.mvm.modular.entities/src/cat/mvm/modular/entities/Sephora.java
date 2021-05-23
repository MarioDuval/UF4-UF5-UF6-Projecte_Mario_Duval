package cat.mvm.modular.entities;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sephora extends JFrame{
    private JLabel jlbCode;
    private JLabel jlbName;
    private JLabel jlbFamily;
    private JLabel jlbType;
    private JLabel jlbPrice;
    private JLabel jlbQuantity;
    private JTextField jtfCode;
    private JTextField jtfName;
    private JComboBox jtfFamily;
    private JTextField jtfType;
    private JTextField jtfPrice;
    private JTextField jtfQuantity;
    private JButton jbtOK;
    private JButton jbtStats;




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
        jtfFamily = new JComboBox();
        jtfType = new JTextField();
        jtfPrice = new JTextField();
        jtfQuantity = new JTextField();
        jbtOK = new JButton();
        jbtStats = new JButton();

        getContentPane().setLayout(null);

        jlbCode.setText("Codi");
        getContentPane().add(jlbCode);
        jlbCode.setBounds(12, 28, 116, 14);

        jtfCode.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfCode);
        jtfCode.setBounds(80, 25, 350, 20);
        System.out.println(jtfCode);
        String code = jtfCode.getText();

        jlbName.setText("Nom");
        getContentPane().add(jlbName);
        jlbName.setBounds(12, 73, 104, 14);

        jtfName.setHorizontalAlignment(JTextField.LEFT);
        getContentPane().add(jtfName);
        jtfName.setBounds(80, 70, 350, 20);
        System.out.println(jtfName.getText());

        jlbFamily.setText("Familia");
        getContentPane().add(jlbFamily);
        jlbFamily.setBounds(12, 118, 300, 14);

        /*String[] choices = { "1", "2", "3"};
        final JComboBox<String> jtfFamily = new JComboBox<String>(choices);*/
        jtfFamily = new JComboBox<String>();
        jtfFamily.addItem("1 Cosmetica");
        jtfFamily.addItem("2 Perfumeria");
        jtfFamily.addItem("3 Maquillatje");
        jtfFamily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==jtfFamily) {

                }
            }
        });
        getContentPane().add(jtfFamily);
        jtfFamily.setVisible(true);
        jtfFamily.setBounds(80, 115, 100, 20);

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
        jbtOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jbtOKActionPerformed(e);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        getContentPane().add(jbtOK);
        jbtOK.setBounds(355, 300, 75, 24);

        jbtStats.setText("Estadistiques");
        jbtStats.setMnemonic('S');
        getRootPane().setDefaultButton(jbtStats);
        jbtStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jbtStatsActionPerformed(e);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        getContentPane().add(jbtStats);
        jbtStats.setBounds(20, 300, 115, 24);


    }

    private void jbtOKActionPerformed(ActionEvent e) throws SQLException {
        boolean codeBool = false;
        boolean nameBool = false;
        boolean typeBool = false;
        boolean priceBool = false;
        boolean quantityBool = false;
        int family = jtfFamily.getSelectedIndex();
        try {
            if (e.getSource() == jbtOK) {
                String data = jtfCode.getText();
                int code = Integer.parseInt(data);
                if ((code > 0) && (code < 1000)) {
                    codeBool = true;
                    //System.out.println("Codi: " + code);
                } else {
                    JOptionPane.showMessageDialog(null, "El codi té que ser més gran que 0 i més petit de 999", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String name = jtfName.getText();
                Pattern pat = Pattern.compile("^[^\\d].*");
                Matcher mat = pat.matcher(name);
                if (mat.matches()) {
                    //System.out.println("Nom: " + name);
                    nameBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Té que ser el nom d'un producte", "Error", JOptionPane.ERROR_MESSAGE);
                }

                //String family = (String) jtfFamily.getSelectedItem();
                //System.out.println("Familia: " + family);

               if (family == 0) {
                    family += 1;
                } else if (family == 1) {
                    family += 1;
                } else if (family == 2) {
                    family += 1;
                }


                String type = jtfType.getText();
                Pattern pat2 = Pattern.compile("^[^\\d].*");
                Matcher mat2 = pat2.matcher(type);
                if (mat2.matches()) {
                    //System.out.println("Tipus: " + type);
                    typeBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Introdueix el tipus de producte", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String data2 = jtfPrice.getText();
                double price = Double.parseDouble(data2);
                if (price > 0) {
                    //System.out.println("Preu: " + price);
                    priceBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El preu té que ser més gran que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String data3 = jtfQuantity.getText();
                int quantity = Integer.parseInt(data3);
                if (quantity >= 0) {
                    //System.out.println("Quantitat: " + quantity);
                    quantityBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "La quantitat té que ser igual o més gran que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Té que ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (codeBool == true && nameBool == true &&  typeBool == true && priceBool == true && quantityBool == true) {

            Connection connection = null;
            PreparedStatement pstatement = null;
            ResultSet rs = null;
            String server = "jdbc:mysql://localhost:3306/";
            String bbdd = "sephora";
            String user = "dba_java";
            String password = "@MVM2021";
            String sql;


            try {
                connection = DriverManager.getConnection(server + bbdd, user, password);

                sql = "INSERT INTO productes (Codi, Nom, Familia, Tipus, Preu, Quantitat) VALUES (?,?,?,?,?,?)";

                try {
                    pstatement = connection.prepareStatement(sql);
                    pstatement.setInt(1, Integer.parseInt(jtfCode.getText()));
                    pstatement.setString(2, jtfName.getText());
                    pstatement.setInt(3, family);
                    pstatement.setString(4, jtfType.getText());
                    pstatement.setDouble(5, Double.parseDouble(jtfPrice.getText()));
                    pstatement.setInt(6, Integer.parseInt(jtfQuantity.getText()));
                    pstatement.executeUpdate();
                    JOptionPane.showConfirmDialog(null, "Dades introuides correctament", "Resultat", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                sql = "SELECT * FROM productes";

                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                while (rs.next()) {
                    System.out.println(String.format("Codi: %d", rs.getInt(1)));
                    System.out.println(String.format("Nom: %s", rs.getString(2)));
                    System.out.println(String.format("Familia: %s", rs.getInt(3)));
                    System.out.println(String.format("Tipus: %s", rs.getString(4)));
                    System.out.println(String.format("Preu: %.2f€", rs.getDouble(5)));
                    System.out.println(String.format("Quantitat: %d %n", rs.getInt(6)));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();

            } finally {
                rs.close();
                pstatement.close();
                connection.close();
            }
        }
    }

    private void jbtStatsActionPerformed(ActionEvent e) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;
        String server = "jdbc:mysql://localhost:3306/";
        String bbdd = "sephora";
        String user = "dba_java";
        String password = "@MVM2021";
        String sql;
        if (e.getSource() == jbtStats) {
            try {

                connection = DriverManager.getConnection(server + bbdd, user, password);

                sql = "SELECT * FROM productes WHERE Quantitat = 0";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                while (rs.next()) {
                    System.out.println(String.format("Codi: %d", rs.getInt(1)));
                    System.out.println(String.format("Nom: %s", rs.getString(2)));
                    System.out.println(String.format("Familia: %s", rs.getInt(3)));
                    System.out.println(String.format("Tipus: %s", rs.getString(4)));
                    System.out.println(String.format("Preu: %.2f€", rs.getDouble(5)));
                    System.out.println(String.format("Quantitat: %d %n", rs.getInt(6)));
                }



                sql = "SELECT Preu FROM productes Where Familia = 1";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                List<Double> prices = new ArrayList<>();
                while (rs.next()) {
                    prices.add(rs.getDouble(1));
                }

                double avgPrice = prices.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .average()
                        .getAsDouble();
                double maxPrice = prices.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .max()
                        .getAsDouble();
                double minPrice = prices.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .min()
                        .getAsDouble();

                System.out.printf("Estadística Familia 1 Cosmetica:%n");
                System.out.printf("--------------------------------%n");
                System.out.printf("- Preu mig: %.2f€%n", avgPrice);
                System.out.printf("- Preu màx: %.2f€%n", maxPrice);
                System.out.printf("- Preu min: %.2f€%n", minPrice);

                Path path = Paths.get( "C:\\Users\\mario\\IdeaProjects\\UF4-UF5-UF6-Projecte_Mario_Duval\\estadistiques.txt");
                try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName( "UTF-8" ))) {
                    writer.write(("Estadística Familia 1 Cosmetica:\n") +
                            ("--------------------------------\n") +
                            ("- Preu mig: " + avgPrice + "€\n") +
                            ("- Preu màx: " + maxPrice + "€\n") +
                            ("- Preu min: " + minPrice + "€\n"));
                }

                sql = "SELECT Preu FROM productes Where Familia = 2";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                List<Double> prices2 = new ArrayList<>();
                while (rs.next()) {
                    prices2.add(rs.getDouble(1));
                }

                double avgPrice2 = prices2.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .average()
                        .getAsDouble();
                double maxPrice2 = prices2.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .max()
                        .getAsDouble();
                double minPrice2 = prices2.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .min()
                        .getAsDouble();



                System.out.printf("%nEstadística Familia 2 Perfumeria:%n");
                System.out.printf("--------------------------------%n");
                System.out.printf("- Preu mig: %.2f€ %n", avgPrice2);
                System.out.printf("- Preu màx: %.2f€ %n", maxPrice2);
                System.out.printf("- Preu min: %.2f€%n", minPrice2);

                try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName( "UTF-8" ), StandardOpenOption.APPEND)) {
                    writer.write(("\nEstadística Familia 2 Perfumeria:\n") +
                            ("--------------------------------\n") +
                            ("- Preu mig: " + avgPrice2 + "€\n") +
                            ("- Preu màx: " + maxPrice2 + "€\n") +
                            ("- Preu min: " + minPrice2 + "€\n"));
                }

                sql = "SELECT Preu FROM productes Where Familia = 3";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                List<Double> prices3 = new ArrayList<>();
                while (rs.next()) {
                    prices3.add(rs.getDouble(1));
                }

                double avgPrice3 = prices3.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .average()
                        .getAsDouble();
                double maxPrice3 = prices3.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .max()
                        .getAsDouble();
                double minPrice3 = prices3.stream()
                        .mapToDouble(p -> p.doubleValue())
                        .min()
                        .getAsDouble();

                System.out.printf("%nEstadística Familia 3 Maquillatje:%n");
                System.out.printf("--------------------------------%n");
                System.out.printf("- Preu mig: %.2f€ %n", avgPrice3);
                System.out.printf("- Preu màx: %.2f€ %n", maxPrice3);
                System.out.printf("- Preu min: %.2f€", minPrice3);

                try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName( "UTF-8" ), StandardOpenOption.APPEND)) {
                    writer.write(("\nEstadística Familia 3 Maquillatje:\n") +
                            ("--------------------------------\n") +
                            ("- Preu mig: " + avgPrice3 + "€\n") +
                            ("- Preu màx: " + maxPrice3 + "€\n") +
                            ("- Preu min: " + minPrice3 + "€\n"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                rs.close();
                pstatement.close();
                connection.close();
            }
        }
    }
}

