import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 15.12.13
 * Time: 14:02
 */
public interface ConnectionPool {
    Connection getConnection();
    void releaseConnection(Connection connection);
    void closePool();


}
