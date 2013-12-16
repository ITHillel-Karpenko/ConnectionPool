import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 15.12.13
 * Time: 14:52
 */
public class ReusableConnection implements Connection {
    private Connection connection;
    private boolean busy;

    public ReusableConnection(Connection connection) {
        this.connection = connection;
        busy = true;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean isBusy() {
        return busy;
    }




}
