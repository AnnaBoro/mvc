package lesson6_9.adapter.mvc.shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StoreUI {

    private Store store;
    private ButtonGroup group = new ButtonGroup();

    public StoreUI(Store store) {

        this.store = store;

        JFrame frame = new JFrame("STORE");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(createSellingPanel());
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createSellingPanel() {

        final JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        JLabel jName = new JLabel("Enter name: ");
        final JTextField jTField = new JTextField(30);
        gbl.setConstraints(jName, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jTField, c);

        panel.add(jName);
        panel.add(jTField);

        java.util.List<Bird> birds = store.getBirds();
        JLabel jBirds = new JLabel("Birds");
        JPanel jPBirds = new JPanel();
        jPBirds.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jPBirds.setLayout(new GridLayout(birds.size(), 0));


        for (Bird b : birds) {
            JRadioButton birdButton = new JRadioButton(b.getName() + " " + b.getPrice());
            birdButton.setActionCommand(b.getName());
            jPBirds.add(birdButton);
            group.add(birdButton);
        }

        c.gridwidth = 1;
        gbl.setConstraints(jBirds, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jPBirds, c);

        panel.add(jBirds);
        panel.add(jPBirds);

        JLabel jNumber = new JLabel("Enter quantity: ");
        NumberFormat nf = NumberFormat.getInstance();
        final JFormattedTextField jTField2 = new JFormattedTextField(nf);
        jTField2.setValue(1);
        c.gridwidth = 1;
        gbl.setConstraints(jNumber, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jTField2, c);

        panel.add(jNumber);
        panel.add(jTField2);

        final JButton buyButton = new JButton("BUY");
        c.ipadx = 100;
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(buyButton, c);

        buyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Buyer b = new Buyer();
                b.setName(jTField.getText());

                String birdSTR = group.getSelection().getActionCommand();

                int num = Integer.parseInt(jTField2.getText());

                store.sell(birdSTR, num, b);
            }
        });

        panel.add(buyButton);

        JButton btnViewPurchase = new JButton("Purchases");
        btnViewPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                PurchaseTable purchaseTable = new PurchaseTable();
//                purchaseTable.getMyTableModel().fireTableDataChanged();
            }
        });
        panel.add(btnViewPurchase);
        return panel;
    }

    private class PurchaseTable {

        Object[][] purchaseView;
        List<Purchase> purchases;
        JTable myTable;
        MyTableModel myTableModel;
        String[] columns;
        SimpleDateFormat c = new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.ENGLISH);

        public PurchaseTable() {

            JFrame tableFrame = new JFrame("Purchases");
            tableFrame.setMinimumSize(new Dimension(300, 300));
            JPanel topPanel = new JPanel();
            topPanel.setLayout( new BorderLayout() );
            tableFrame.getContentPane().add( topPanel );

            myTableModel = new MyTableModel();
            columns = new String[]{"ID", "Date", "Bird", "Number", "Buyer"};
            myTableModel.setColumnsName(columns);

            purchases = store.getPurchases();
            purchaseView = new Object[purchases.size()][];

            for (int i = 0; i < purchases.size(); i++) {
                purchaseView[i] = new Object[]{i + 1, c.format(purchases.get(i).getDate()), purchases.get(i).getBird().getName(),
                purchases.get(i).getNumber(), purchases.get(i).getBuyer().getName()};
            }
            myTableModel.setPurchases(purchases);
            myTableModel.setPurchaseView(purchaseView);

            myTable = new JTable(myTableModel);
            JScrollPane scrollPane = new JScrollPane(myTable);
            myTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
            topPanel.add(scrollPane, BorderLayout.CENTER);
            myTable.setVisible(true);
            tableFrame.setVisible(true);
        }

        public MyTableModel getMyTableModel() {
            return myTableModel;
        }
    }
}