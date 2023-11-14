package webtest.model;

import java.util.List;

public class Order{
	private int id;
	private static int idSeed = 1;
	private List<SelectedItem> items;
	private double total;
	private double tax;
	private double grandTotal;

	public Order(List<SelectedItem> itemList) {
		this.id = idSeed++;
		this.items = itemList;
		this.total = 0;
	}

	public double CalculateTotal() {
		for (SelectedItem item : items) {
			this.total +=  item.getQty() * item.getItem().getPrice();
		}
		return total;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double CalculateTax() {
		this.tax = .11 * this.total;
		return tax;
	}

	public double CalculateGrandTotal() {
		this.grandTotal = this.tax + this.total;
		return grandTotal;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<SelectedItem> getSelectedItemList() {
		return items;
	}
	public void setItemList(List<SelectedItem> items) {
		this.items = items;
	}
}