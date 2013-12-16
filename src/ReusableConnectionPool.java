import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 15.12.13
 * Time: 14:26
 */
public class ReusableConnectionPool implements ConnectionPool {
    private int maxPoolSize;
    private List<ReusableConnection> connections;
    private String url;

    public ReusableConnectionPool(int maxPoolSize, String url) {
        if (maxPoolSize < 0)
            throw new RuntimeException("PoolSize can't be less then 0");
        if (url == null)
            throw new RuntimeException("Url should not be null");
        this.maxPoolSize = maxPoolSize;
        this.url = url;
    }

    /**
     * Returns free connection from pool, if no free connection returns null
     */
    @Override
    public Connection getConnection() {
        if (connections==null) {
            connections = new ArrayList<>(maxPoolSize);
        }

        for (ReusableConnection connection : connections) {
            if (!connection.isBusy()) {
                connection.setBusy();
                return connection;
            }
        }

        if (connections.size()<maxPoolSize) {
            Connection connection = DriverManager.getConnection(url);
            ReusableConnection reusableConnection = new ReusableConnection(connection);
            connections.add(reusableConnection);
            return reusableConnection;
        }
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) {

    }

    @Override
    public void closePool() {

    }
}
