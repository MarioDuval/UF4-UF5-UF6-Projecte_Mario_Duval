package cat.mvm.modular.entities;

/**
 * Una classe per fer una aplicaci&oacute; d'escriptori amb entorn gr&agrave;fic on els usuaris
 * hauran de poder introduir les dades d'un article a la base de dades a partir
 * d'un formulari.
 * @author: Mario Duval
 * @version: 1.0, 25/05/2021
 */

import javax.swing.*;
import java.awt.*;
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




/**
 * Declaraci&oacute; de la classe extendida per l'interf&iacute;cie gr&agrave;fica
 */
public class Sephora extends JFrame{
    //Declaració variables
    /** Etiqueta per mostrar text */
    private JLabel jlbCode;
    /** Etiqueta per mostrar text */
    private JLabel jlbName;
    /** Etiqueta per mostrar text */
    private JLabel jlbFamily;
    /** Etiqueta per mostrar text */
    private JLabel jlbType;
    /** Etiqueta per mostrar text */
    private JLabel jlbPrice;
    /** Etiqueta per mostrar text */
    private JLabel jlbQuantity;
    /** Camp de text per a que l'usuari escrigui */
    private JTextField jtfCode;
    /** Camp de text per a que l'usuari escrigui */
    private JTextField jtfName;
    /** Per fer el desplegable */
    private JComboBox jtfFamily;
    /** Camp de text per a que l'usuari escrigui */
    private JTextField jtfType;
    /** Camp de text per a que l'usuari escrigui */
    private JTextField jtfPrice;
    /** Camp de text per a que l'usuari escrigui */
    private JTextField jtfQuantity;
    /** Bot&oacute; per inserir les dades*/
    private JButton jbtOK;
    /** Bot&oacute; per mostrar les estad&iacute;stiques*/
    private JButton jbtStats;

    /**
     * Li donem mida i localitzaci&oacute; a la finestra, i fem
     * la crida del m&egrave;tode
     */
    public Sephora(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocation(500, 100);
        this.setTitle("Sephora");
        Toolkit screen = Toolkit.getDefaultToolkit();
        Image myIcon = screen.getImage("cat.mvm.modular.entities/src/cat/mvm/modular/entities/sephora.png");
        this.setIconImage(myIcon);
        initComponents();
    } //Tancament constructor

    /**
     * M&egrave;tode cridat per el constructor per afegir els continguts
     * dins la finestra, per inicar el formulari.
     */
    private void initComponents() {
        //Als JLabel mostrem el text que surt al costat dels camps
        jlbCode = new JLabel();
        jlbName = new JLabel();
        jlbFamily = new JLabel();
        jlbType = new JLabel();
        jlbPrice = new JLabel();
        jlbQuantity = new JLabel();
        //Als JTextField corresponen un camp de text i es pot escriure
        jtfCode = new JTextField();
        jtfName = new JTextField();
        jtfFamily = new JComboBox();
        jtfType = new JTextField();
        jtfPrice = new JTextField();
        jtfQuantity = new JTextField();
        //Creem els botons
        jbtOK = new JButton();
        jbtStats = new JButton();

        getContentPane().setLayout(null);

        //Li donem mida i posició dintre de la finestra tant als Jlabels, com als JTextFields, com els JButtons
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

        //Creació del desplegable del valor "Familia"
        jtfFamily = new JComboBox<String>();
        jtfFamily.addItem("1 Cosmetica");       //Li introduim els items que es mostren dintre
        jtfFamily.addItem("2 Perfumeria");      //del despegable
        jtfFamily.addItem("3 Maquillatje");


        /**
         * Listener per a que el programa detecti la posici&oacute; escollida per l'usuari
         * @param e ens proporciona informaci&oacute; de l'acci&oacute; produ&iuml;da, que en aquest
         *          cas ve d'un control d'una llista
         */
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

        /**
         * Afegim els listeners als components
         * @param e ens proporciona informaci&oacute; de l'acci&oacute; produ&iuml;da, en el bot&oacute;.
         * @throws SQLException Una excepci&oacute; que proporciona informaci&oacute; sobre
         * un error d'acc&eacute;s a la base de dades o altres errors.
         */
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


    } //Tancament del mètode

    /**
     * M&egrave;tode on donem funci&oacute; al bot&oacute; "OK"
     * @param e ens proporciona informaci&oacute; de l'acci&oacute; produ&iuml;da, en el bot&oacute;.
     * @throws SQLException Una excepci&oacute; que proporciona informaci&oacute; sobre
     * un error d'acc&eacute;s a la base de dades o altres errors.
     */
    private void jbtOKActionPerformed(ActionEvent e) throws SQLException {
        boolean codeBool = false;
        boolean nameBool = false;
        boolean typeBool = false;       //Booleans per la condició
        boolean priceBool = false;
        boolean quantityBool = false;
        /* Variable on guarda la posició del JComboBox comença desde la
        * posició 0 pero si no retorna cap valor seleccionat retorna la
        * posició -1. Per això es fa un listener en el desplegable.*/
        int family = jtfFamily.getSelectedIndex();

        /* Les condicions fetes dins del try-catch, son per fer el control d'errors
         * guardem les dades introduides per l'usuari en una variable
         * si la condició es compleix les variables booleanes passen a ser true
         * i és farà l'acció següent. Si no es compleix la condició saltarà
         * una finestra d'error i els booleans seguiran sent false per lo que no deixarà
         * introduir les dades
         */

        try {
            if (e.getSource() == jbtOK) {
                /*Variable per guardar les dades introduides per teclat per l'usuari
                * dint-re del JTextField*/
                String data = jtfCode.getText();
                //Funció per convertir String en integer i ho guardem a la variable
                int code = Integer.parseInt(data);
                if ((code > 0) && (code < 1000)) {
                    codeBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El codi té que ser més gran que 0 i més petit de 999", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String name = jtfName.getText();
                Pattern pat = Pattern.compile("^[^\\d].*");
                Matcher mat = pat.matcher(name);
                if (mat.matches()) {
                    nameBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Té que ser el nom d'un producte", "Error", JOptionPane.ERROR_MESSAGE);
                }

                /* Condició per detectar la posició seleccionada al JComboBox
                * amb la funció getSelectedIndex(), depenen de la posició que retorni
                * que en el nostre cas pot ser 0, 1 o 2 se li sumarà 1 per a que sigui
                * 1, 2 i 3 que son les families que tenim*/
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
                    typeBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Introdueix el tipus de producte", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String data2 = jtfPrice.getText();
                double price = Double.parseDouble(data2);  //Funció per convertir String a Double
                if (price > 0) {
                    priceBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El preu té que ser més gran que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String data3 = jtfQuantity.getText();
                int quantity = Integer.parseInt(data3);
                if (quantity >= 0) {
                    quantityBool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "La quantitat té que ser igual o més gran que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Té que ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        }

        /* Condició que una vegada superat el control d'errors, els booleans passen a ser true
            llavors realitza la funció d'inserció dins la base de dades
         */
        if (codeBool == true && nameBool == true &&  typeBool == true && priceBool == true && quantityBool == true) {

            //Connexió amb la base de dades mysql
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
                    //Obtenim els valors introduits per l'usuari
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

                /* Bucle per mostrar tots els valors que es troben dins la base de dades
                    antics i nous
                * */
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
        } //Tancament de la condició
    } //Tancament del mètode

    /**
     * M&egrave;tode on donem funci&oacute; al bot&oacute; "Estad&iacute;stiques"
     * @param e ens proporciona informaci&oacute; de l'acci&oacute; produ&iuml;da, en el bot&oacute;.
     * @throws SQLException Una excepci&oacute; que proporciona informaci&oacute; sobre
     * un error d'acc&eacute;s a la base de dades o altres errors.
     * @throws IOException Aquesta classe &eacute;s la classe general d'excepcions
     * produ&iuml;des per operacions I/0 fallides o interrompudes
     */
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

                //Select per retornar els productes sense stock
                sql = "SELECT * FROM productes WHERE Quantitat = 0";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();


                System.out.printf("Productes sense stock:%n");
                System.out.printf("----------------------%n");
                //Bucle on retorna i mostra per pantalla les dades
                while (rs.next()) {
                    System.out.println(String.format("Codi: %d", rs.getInt(1)));
                    System.out.println(String.format("Nom: %s", rs.getString(2)));
                    System.out.println(String.format("Familia: %s", rs.getInt(3)));
                    System.out.println(String.format("Tipus: %s", rs.getString(4)));
                    System.out.println(String.format("Preu: %.2f€", rs.getDouble(5)));
                    System.out.println(String.format("Quantitat: %d %n", rs.getInt(6)));
                }


                //Select per guardar els preus de la familia corresponent
                sql = "SELECT Preu FROM productes Where Familia = 1";
                pstatement = connection.prepareStatement(sql);
                rs = pstatement.executeQuery();

                //Emmagatzemem la llista de valors Double en una ArrayList guardada en la variable
                List<Double> prices = new ArrayList<>();
                //Bucle per recorrer tots els valors i retorna la primera i única columna que es on estàn els preus
                while (rs.next()) {
                    prices.add(rs.getDouble(1));
                }

                //Fem les operacions per obtenir el promedi, màxim i mínim
                double avgPrice = prices.stream()
                        .mapToDouble(p -> p.doubleValue())  //Indiquem que volem retornar un valor Double
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

                //Mostrem per pantalla els resultats de la Familia 1
                System.out.printf("Estadística Familia 1 Cosmetica:%n");
                System.out.printf("--------------------------------%n");
                System.out.printf("- Preu mig: %.2f€%n", avgPrice);
                System.out.printf("- Preu màx: %.2f€%n", maxPrice);
                System.out.printf("- Preu min: %.2f€%n", minPrice);

                //Creem i escrivim fitxer .txt de les estadistiques
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

                //Aquí afegim les estadistiques de la Familia 2
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
        } //Tancament de la condició
    } //Tancament del mètode
} //Tancament de la classe

