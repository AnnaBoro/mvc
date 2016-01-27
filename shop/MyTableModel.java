package lesson6_9.adapter.mvc.shop;


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel{

    private Object[][] purchaseView;
    private List<Purchase> purchases;
    private String []columnsName;

    @Override
    public int getRowCount() {
        if (purchases != null) {
            return purchases.size();
        }
        else return 2;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (purchaseView != null) {
            return purchaseView[rowIndex][columnIndex];
        }
        else return null;
    }

    public Object[][] getPurchaseView() {
        return purchaseView;
    }

    public void setPurchaseView(Object[][] purchaseView) {
        this.purchaseView = purchaseView;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String getColumnName(int column) {
        if (columnsName != null ) {
            return columnsName[column];
        }
        return null;
    }

    public void setColumnsName(String[] columnsName) {
        this.columnsName = columnsName;
    }
}
