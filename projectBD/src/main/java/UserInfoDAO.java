import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class UserInfoDAO {

    static final String DB_URL = "jdbc:mysql://localhost:3306/project";

    private Connection con = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet res = null;
    private void connect() {
        try {
            con = DriverManager.getConnection(DB_URL, "root","Root1234");
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void close() {
        try {
            if(res != null)
                res.close();
            if(stm != null)
                stm.close();
            if(pstm != null)
                pstm.close();
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertUserInfo( UserInfo userInfo)
    {
        connect();

        try{
            pstm = con.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            pstm.setString(1, userInfo.getUsername());
            pstm.setString(2, userInfo.getPassword());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        }
    public Vector<UserInfo> getUserInfoByName(String searchName){
        Vector<UserInfo> usersInfoList = new Vector<>();
        connect();
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM users WHERE username='" + searchName + "'");

            while(res.next()) {
                int id = res.getInt(1);
                String username = res.getString(2);
                String password = res.getString(3);
                UserInfo userInfo = new UserInfo(id,username, password);

                usersInfoList.add(userInfo);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return usersInfoList;
    }


}
