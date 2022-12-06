package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.Connect;

public class ChronometerDAO extends Chronometer{

    private final static String INSERT = "INSERT INTO mark (id,date,time) VALUES (NULL,?,?)";
    private final static String GETALL = "SELECT * FROM mark";
    public ChronometerDAO(int id, int hours, int minutes, int seconds, int milliseconds, LocalDate date) {
        super(id, hours, minutes, seconds, milliseconds, date);
    }
    public ChronometerDAO() {
    }
    public ChronometerDAO(Chronometer c) {
        this(c.getId(),c.getHours(),c.getMinutes(),c.getSeconds(),c.getMilliseconds(),c.getDate());
    }

    public boolean insert() {
        Connection con = Connect.getConnection();
        boolean insertado = false;
        if(con!=null){
            try {
                PreparedStatement ps = con.prepareStatement(INSERT);
                ps.setString(1, this.getDate().toString());
                ps.setString(2, this.getTime());
                ps.executeUpdate();
                insertado = true;
            } catch (SQLException e) {
                insertado = false;
                e.printStackTrace();
            }
        }else{
            insertado = false;
        }
        return insertado;
    }
    public List<ChronometerDAO> getAll(){
        Connection con = Connect.getConnection();
        List<ChronometerDAO> Marks = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(GETALL);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    ChronometerDAO c = new ChronometerDAO();
                    c.setId(rs.getInt("id"));
                    c.setDate(LocalDate.parse(rs.getString("date")));
                    c.setTimeString(rs.getString("time"));
                    Marks.add(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Marks;
    }


}
