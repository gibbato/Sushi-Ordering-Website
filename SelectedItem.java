package webtest.model;

public class SelectedItem{
	private MenuItem item;
	private int itemId;
	private int qty;
	
	public SelectedItem() {
	}
	public SelectedItem(MenuItem item, int qty) {
		this.item = item;
		this.qty = qty;
	}
	public SelectedItem(int itemId, int qty) {
		this.itemId = itemId;
		this.qty = qty;
	}
	public MenuItem getItem() {
		return item;
	}
	public void setItem(MenuItem item) {
		this.item = item;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	

}