package Data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        String url = System.getProperty("db.url");
        String name = System.getProperty("username");
        String pass = System.getProperty("password");
        return DriverManager
                .getConnection(url, name, pass);
    }

    @SneakyThrows
    public static DataHelper.Status getCardStatusEntity() {
        var codeSQL = "SELECT status from payment_entity order by created DESC LIMIT 1";
        var conn = getConn();
        var status = QUERY_RUNNER
                .query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.Status(status);

    }

    @SneakyThrows
    public static DataHelper.Status getCreditStatusEntity() {
        var codeSQL = "SELECT status from credit_request_entity order by created DESC LIMIT 1";
        var conn = getConn();
        var status = QUERY_RUNNER
                .query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.Status(status);

    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        QUERY_RUNNER.execute(connection, "DELETE FROM credit_request_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM order_entity");
        QUERY_RUNNER.execute(connection, "DELETE FROM payment_entity");
    }


}