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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.acme.order.pizza.PizzaOrder;

@Slf4j
@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {

	private final String url = "jdbc:mysql://localhost:3306/pizza-tutorial";

	private final String user = "dbuser";

	private final String password = "dbpass";

	private JdbcTemplate jdbcTemplate;

	final String SQL = "SELECT o.id as order_id,o.customer_id as customer_id,o.status,o.type,o.estimatedDeliveryTime,"
			+ "o.finishTime,c.name,c.email,c.address from order_t o,customer_t c where o.customer_id = c.id";

	// @Autowired
	// private DataSource ds;

	@Override
	public String save(PizzaOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public PizzaOrder get(String pizzaOrderId) {
		// int countOfActorsNamedJoe = this.jdbcTemplate.queryForObject(
		// "select count(*) from t_actor where first_name = ?", Integer.class,
		// "Joe");

		Customer customer = this.jdbcTemplate.queryForObject("select * from customer_t where id = ?",
				new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

						int id = rs.getInt("id");
						String name = rs.getString("name");
						String email = rs.getString("email");
						String address = rs.getString("address");

						String customerId = Integer.toString(id);
						Customer c = new Customer(customerId, name, email, address);
						return c;
					}

				}, "1");

		System.out.println("Customer from database: " + customer);

		return null;
	}

	@Override
	public List<PizzaOrder> findAll() {

		return null;

	}

	@Override
	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
