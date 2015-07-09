package com.acme.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.acme.order.pizza.PizzaOrder;

@Slf4j
@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {

	private final String url = "jdbc:mysql://localhost:3306/pizza-tutorial";

	private final String user = "dbuser";

	private final String password = "dbpass";

	final String SQL = "SELECT o.id as order_id,o.customer_id as customer_id,o.status,o.type,o.estimatedDeliveryTime,"
			+ "o.finishTime,c.name,c.email,c.address from order_t o,customer_t c where o.customer_id = c.id";

	@Autowired
	private DataSource ds;
	
	@Override
	public String save(PizzaOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public PizzaOrder get(String pizzaOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PizzaOrder> findAll() {
		Connection conn = null;
		Statement stmt = null;
		
		try {

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, customer_id, estimatedDeliveryTime, finishTime FROM order_t";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				
//				PizzaOrder pizzaOrder = new PizzaOrder(customer, pizzaType)
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("customer_id");
				String first = rs.getString("estimatedDeliveryTime");
				String last = rs.getString("finishTime");

				// Display values
				System.out.print("id: " + id);
				System.out.print(", customer_id: " + age);
				System.out.print(", estimatedDeliveryTime: " + first);
				System.out.println(", finishTime: " + last);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (

		SQLException se)

		{
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (

		Exception e)

		{
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally

		{
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("GoodBye");
		return null;

	}

	@Override
	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
