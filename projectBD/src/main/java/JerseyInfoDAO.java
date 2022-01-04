import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class JerseyInfoDAO {

    static final String DB_URL = "jdbc:mysql://localhost:3306/project";

    private Connection con = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet res = null;

    private void connect() {
        try {
            con = DriverManager.getConnection(DB_URL, "root", "Root1234");
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            if (res != null)
                res.close();
            if (stm != null)
                stm.close();
            if (pstm != null)
                pstm.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJerseyInfo( JerseyInfo jerseyInfo)
    {
        connect();

        try{
            pstm = con.prepareStatement("INSERT INTO jersey(club_id, name, brand, color, description) VALUES (?, ?, ?, ?, ?)");
            pstm.setInt(1, jerseyInfo.getClub_id());
            pstm.setString(2, jerseyInfo.getName());
            pstm.setString(3, jerseyInfo.getBrand());
            pstm.setString(4, jerseyInfo.getColor());
            pstm.setString(5, jerseyInfo.getDescription());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public Vector<JerseyInfo> selectJerseyInfo( ) {
        connect();
        Vector<JerseyInfo> vJerseyInfoList = new Vector<>();
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM jersey");

            while(res.next()) {
                int idJersey = res.getInt(1);
                int idClub = res.getInt(2);
                String name = res.getString(3);
                String brand = res.getString(4);
                String color = res.getString(5);
                String description = res.getString(6);
                JerseyInfo jerseyInfo = new JerseyInfo(idJersey,idClub, name, brand, color, description);
                vJerseyInfoList.add(jerseyInfo);
            }
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return vJerseyInfoList;
    }

    public void updateJerseyInfo(JerseyInfo jerseyInfo ) {
        connect();
        try{
            pstm = con.prepareStatement("UPDATE jersey SET club_id=?, name=?, brand=?, color=?, description=? WHERE jersey_id=?");
            pstm.setInt(1, jerseyInfo.getClub_id());
            pstm.setString(2, jerseyInfo.getName());
            pstm.setString(3, jerseyInfo.getBrand());
            pstm.setString(4, jerseyInfo.getColor());
            pstm.setString(5, jerseyInfo.getDescription());
            pstm.setInt(6, jerseyInfo.getJersey_id());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public void deleteJerseyInfo( int id){
        connect();
        try{
            pstm = con.prepareStatement("DELETE FROM jersey WHERE jersey_id=?");
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        close();
    }
}