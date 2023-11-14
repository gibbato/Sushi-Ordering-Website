package webtest.service;

import java.sql.Connection;

import webtest.model.SelectedItem;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import webtest.model.Review;
import webtest.model.User;
import webtest.model.MenuItem;
import webtest.model.Order;

public class DbService {
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/userdb";
	private String username = "root";
	private String password = "Gibbato1";
	private Connection connection;

	// Used to connect to MySQL
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Used to connect to MySQL
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// We need a function that finds duplicates and sends user an error
	public void addUser(User user) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		String sql = "insert into userdb.user (email, name, password, userId) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setInt(4, user.getID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getUserID(String email) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int userId = -1;
		try {
			String sql = "SELECT * FROM userdb.user WHERE email = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				userId = rs.getInt("userId");
			}

			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	// Figures out if there is a user under the same email and password and logins
	// them in/sends error
	public boolean loginUser(String email, String password) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		boolean status = false;
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("select * from userdb.user where email = ? and password = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			status = rs.next();
		} catch (Exception e) {
		}
		return status;
	}

	// Gets the current reviews in the list
	public List<Review> getReviews() {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		List<Review> reviews = new ArrayList<Review>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from userdb.review");
			while (rs.next()) {
				Review review = new Review();
				review.setId(rs.getInt("id"));
				review.setName(rs.getString("name"));
				review.setDescription(rs.getString("description"));
				review.setRating(rs.getString("rating"));
				reviews.add(review);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reviews;
	}

	// Adds review into MySQL
	public int addReview(String name, String description, String rating) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int id = 0;
		try {
			String sql = "insert into userdb.review (name, description, rating) values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setString(3, rating);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	// retrieves all menu items from database
	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>(); // initializes new array list
		loadDriver(dbDriver);
		Connection connection = getConnection(); // establishes connection to database

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM userdb.menu_item"); // sends query to db, requesting all
																				// menu items from table
			while (rs.next()) {
				MenuItem item = new MenuItem(); // creates new menu item object, used to store each database entry
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setImagePath(rs.getString("imagepath"));
				item.setPrice(rs.getInt("price")); // sets name, description, imagepath, price, type, featured, and auto
													// increments id for each entry
				item.setFoodType(rs.getString("foodtype"));
				item.setFeatured(rs.getBoolean("featured"));
				menuItems.add(item); // adds menu item to menuItems array list
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuItems; // returns array list menuItems
	}

	// takes id as parameter and returns that entry in menuitems
	public MenuItem getMenuItem(int menuId) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		MenuItem item = new MenuItem();
		try {
			String sql = "SELECT * FROM userdb.menu_item WHERE id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setImagePath(rs.getString("imagepath"));
				item.setPrice(rs.getInt("price")); // sets name, description, imagepath, price, type, featured, and auto
													// increments id for each entry
				item.setFoodType(rs.getString("foodtype"));
				item.setFeatured(rs.getBoolean("featured"));
			}
			pstmt.close();
			rs.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	// adds menu items to database
	public int addMenuItem(MenuItem menuItem) {
		loadDriver(dbDriver);
		Connection connection = getConnection(); // establishes connection
		int id = 0;
		try {
			String sql = "INSERT INTO userdb.menu_items (name, description, imagepath, price, type, featured) values (?, ?, ?, ?, ?, ?)"; // query
																																			// to
																																			// add
																																			// items
																																			// to
																																			// db
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, menuItem.getName()); // sets each attribute of each entry to its corresponding column row
													// in the db
			pstmt.setString(2, menuItem.getDescription());
			pstmt.setString(3, menuItem.getImagePath());
			pstmt.setDouble(4, menuItem.getPrice());
			pstmt.setString(5, menuItem.getFoodType());
			pstmt.setBoolean(6, menuItem.getFeatured());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public int addToCart(SelectedItem item, int userId) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int id = 0;
		try {
			String sql = "INSERT INTO userdb.cart (userId, itemId, quantity) values (?, ?, ?)"; // query to add items to
																								// db
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, userId); // sets each attribute of each entry to its corresponding column row in the db
			pstmt.setInt(2, item.getItem().getId());
			pstmt.setInt(3, item.getQty());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<SelectedItem> getCart(int userId) {
		List<SelectedItem> selectedItems = new ArrayList<SelectedItem>(); // initializes new array list
		List<SelectedItem> dummyList = new ArrayList<SelectedItem>(); // holds dummy items
		loadDriver(dbDriver);
		Connection connection = getConnection(); // establishes connection to database
		try {
			String sql = "SELECT * FROM userdb.cart WHERE userId = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				SelectedItem selItem = new SelectedItem();
				selItem.setItemId(rs.getInt("itemId"));
				selItem.setQty(rs.getInt("quantity"));
				dummyList.add(selItem);


			}
			pstmt.close();
			rs.close();

			for (SelectedItem dumItem : dummyList) {

				String sql2 = "SELECT * FROM userdb.menu_item WHERE id = ?";
				PreparedStatement pstmt2 = connection.prepareStatement(sql2);
				pstmt2.setInt(1, dumItem.getItemId());
				ResultSet rs2 = pstmt2.executeQuery();

				while (rs2.next()) {
					MenuItem item = new MenuItem();
					item.setId(rs2.getInt("id"));
					item.setName(rs2.getString("name"));
					item.setDescription(rs2.getString("description"));
					item.setImagePath(rs2.getString("imagepath"));
					item.setPrice(rs2.getInt("price"));
					item.setFoodType(rs2.getString("foodtype"));
					item.setFeatured(rs2.getBoolean("featured"));
					SelectedItem selItem = new SelectedItem(item, dumItem.getQty());

					selectedItems.add(selItem);
				}

				pstmt2.close();
				rs2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectedItems;
	}
	
	
	public int setOrderAmount(Order order) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int id = 0;
		try {
			String sql = "INSERT INTO userdb.cart (subtotal, tax, grandtotal) values (?, ?, ?) WHERE userId = ?"; // query to add items to
																								// db
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDouble(1, order.getTotal()); // sets each attribute of each entry to its corresponding column row in the db
			pstmt.setDouble(2, order.getTax());
			pstmt.setDouble(3, order.getGrandTotal());
			pstmt.setInt(4, order.getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public int deleteItemFromCart(Order order, int itemId, int userId) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int id = 0;
		try {
			String sql = "DELETE FROM cart WHERE itemId = ? AND userId = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, itemId); 
			pstmt.setInt(2, userId); 

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}


	// store order into DB
	public void addOrder(Order order, int userId, List<SelectedItem> SelectedList) {
		loadDriver(dbDriver);
		Connection connection = getConnection();

		try {
			String sql = "insert into userdb.order (orderId, userId) values (?,?);";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, order.getId());
			stmt.setInt(2, userId);
			stmt.executeUpdate();

			for (int i = 0; i < SelectedList.size(); i++) {
				sql = "SELECT COUNT(*) " + "FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE TABLE_SCHEMA = 'userdb' "
						+ "  AND TABLE_NAME = 'order' " + "  AND COLUMN_NAME = 'itemId" + (i + 1) + "';";
				stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {
					sql = "UPDATE userdb.order SET itemId" + (i + 1) + " = ?, qty" + (i + 1)
							+ " = ? WHERE orderId = ?;";
					stmt = connection.prepareStatement(sql);
					stmt.setInt(1, SelectedList.get(i).getItem().getId());
					stmt.setInt(2, SelectedList.get(i).getQty());
					stmt.setInt(3, order.getId());
					stmt.executeUpdate();
				} else {
					sql = "ALTER TABLE userdb.order ADD COLUMN itemId" + (i + 1) + " INT;";
					stmt = connection.prepareStatement(sql);
					stmt.executeUpdate();
					sql = "ALTER TABLE userdb.order ADD COLUMN qty" + (i + 1) + " INT;";
					stmt = connection.prepareStatement(sql);
					stmt.executeUpdate();
					sql = "UPDATE userdb.order SET itemId" + (i + 1) + " = ?, qty" + (i + 1)
							+ " = ? WHERE orderId = ?;";
					stmt = connection.prepareStatement(sql);
					stmt.setInt(1, SelectedList.get(i).getItem().getId());
					stmt.setInt(2, SelectedList.get(i).getQty());
					stmt.setInt(3, order.getId());
					stmt.executeUpdate();
				}

				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
