import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {

    public static void main(String[] args) {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	try {

	    Class.forName("org.mariadb.jdbc.Driver");
	    String url = "jdbc:mariadb://127.0.0.1:3306/mysql";
	    String id = "root";
	    String password = "mypassword1234!";

	    connection = DriverManager.getConnection(url, id, password);

	    connection.setAutoCommit(false);

	    preparedStatement = connection
		    .prepareStatement("update account set balance = balance - 100000 where id = 'A'");

	    preparedStatement.executeUpdate();

	    preparedStatement = connection
		    .prepareStatement("update account set balance = balance + 100000 where id = 'B'");

	    preparedStatement.executeUpdate();

	    connection.commit();

	} catch (Exception ex) {
	    if (connection != null) {
		try {
		    connection.rollback();
		} catch (SQLException e) {
		    // 에러 코드 작성
		}
	    }
	}
    }
}