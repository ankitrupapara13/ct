package com.hsbc.custom;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class MyCustomTag extends BodyTagSupport {

	String query;
	String database;
	String username;
	String password;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			java.sql.Connection con = DriverManager.getConnection(database, username, password);

			java.sql.PreparedStatement ps = con.prepareStatement(query);

			java.sql.ResultSet rs = ps.executeQuery();

//			RowSetFactory factory = RowSetProvider.newFactory();
//			CachedRowSet crs = factory.createCachedRowSet();
//			crs.setUrl(database);
//			crs.setCommand(query);
//			crs.execute();

			pageContext.getOut().print(
					"<table> <tr> <th> Product ID </th> <th> Product Name </th>  <th> Product Category </th> <th> Product Price </th> <th> Product Quantity </th> <th> Product ROL </th></tr>");
			while (rs.next()) {
				pageContext.getOut()
						.print("<tr> <td> " + rs.getInt(1) + " </td> <td> " + rs.getString(2) + " </td>  <td> "
								+ rs.getString(3) + " </td> <td> " + rs.getInt(4) + " </td> <td> " + rs.getInt(5)
								+ " </td> <td> " + rs.getInt(6) + " </td></tr>");
//				 pageContext.getOut().print("<tr> <td> " + crs.getInt(1) + " </td> <td> " + crs.getString(2) + " </td>  <td> " + crs.getString(3) + " </td> <td> " + crs.getInt(4) + " </td> <td> " + crs.getInt(5) + " </td> <td> " + crs.getInt(6) + " </td></tr>");

			}
			pageContext.getOut().print("</table>");

		} catch (SQLException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

}
