package map;

// This is just a wrapper class for an Order.
// It currently only stores a order ID and total amount, but could store
// many other things about an order.

public class Order
{
	private int orderID;
	private double amount;

	public Order()
	{
	}
	public Order(int orderID, double amount)
	{
		this.orderID = orderID;
		this.amount = amount;
	}
	public String toString()
	{
		return "Order ID: " + orderID + " Amount: $" + amount;
	}
}