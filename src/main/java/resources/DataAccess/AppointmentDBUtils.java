/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import resources.Models.Appointment;
import resources.Models.ApprointmentDoc;
import resources.Models.TestingType;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public AppointmentDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    
    
    public boolean addAppointment(Appointment st) {
        String insertQuery = "INSERT INTO appointment ( preferredDate, preferredTime,appointmentReason , appointmentType, referredDoctor, emgContactPer,emgMobileNum,userId)  VALUES ('"+ st.getPreferredDate()+"','"+ st.getPreferredTime()+"','"+ st.getAppointmentReason()+"','"+ st.getAppointmentType() +"','"+ st.getReferredDoctor()+"','"+ st.getEmgContactPer()+"','"+ st.getEmgMobileNum()+"', '"+ st.getUserId()+"');";
        try {
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                // Create a prepared statement with the option to retrieve generated keys
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    // Set the parameter values for the insert query
                    //preparedStatement.setString(1, "value1");
                    //preparedStatement.setString(2, "value2");

                    // Execute the insert query
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        // Retrieve the generated keys (including the last inserted ID)
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int lastInsertedId = generatedKeys.getInt(1);
                                addApprointmentDocs(st.getApprointmentDocList(),lastInsertedId);
                            } else {
                                System.err.println("Document upload failed");
                            }
                        }
                    } else {
                        System.err.println("");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public  void addApprointmentDocs(List<ApprointmentDoc> docs,int aId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO approintmentdoc ( fileName, fileType,document ,active, approintmentId)  VALUES (?,?,?,?,?)";
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                for (ApprointmentDoc model : docs) {
                    pre.setString(1, model.getFileName());
                    pre.setString(2, model.getFileType());
                    pre.setString(3, model.getDocument());
                    pre.setInt(4, 1);
                    pre.setInt(5,aId);
                    pre.addBatch();
                }
                pre.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public  void updateApprointmentDocs(List<ApprointmentDoc> docs,int aId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (ApprointmentDoc model : docs) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Appointment getAppointment(int id) throws SQLException {
        List<ApprointmentDoc> docs = null;
        Appointment st = new Appointment();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
        Statement stmt = conn.createStatement(); 
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE id="+ id);) {
            while (rs.next()) {
                
                //st = new Appointment();
                st.setId( rs.getInt("id"));
                st.setPreferredDate( rs.getDate("preferredDate"));
                st.setPreferredTime(rs.getString("preferredTime"));
                st.setAppointmentReason(rs.getString("appointmentReason"));
                if(rs.getInt("appointmentType") != 0){
                    TestingType type = getTestingType(rs.getInt("appointmentType"));
                    st.setAppointmentType(type.getId());
                }
                //st.setAppointmentType(rs.getString("appointmentType"));
                st.setReferredDoctor(rs.getString("referredDoctor"));
                st.setEmgContactPer(rs.getString("emgContactPer"));
                st.setEmgMobileNum(rs.getString("emgMobileNum"));
                st.setUserId(rs.getInt("userId"));
                TestingType type =  getTestingType((rs.getInt("appointmentType")));
                st.setTestingType(type);
                List<ApprointmentDoc> docses =  getApprointmentDocs(rs.getInt("id"));
                st.setApprointmentDocList(docses);

                //break;
            }
            //st.getApprointmentDocList().addAll( docs);
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return st;
    }
    
    public List<ApprointmentDoc> getApprointmentDocs(int id) {
        List<ApprointmentDoc> getdocs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM approintmentdoc WHERE approintmentId="+ id);) {
            while (rs.next()) {
                ApprointmentDoc doc = new ApprointmentDoc();
                doc.setId( rs.getInt("id"));
                doc.setFileName( rs.getString("fileName"));
                doc.setFileType( rs.getString("fileType"));
                doc.setDocument( rs.getString("document"));
                doc.setActive(rs.getInt("active"));
                doc.setApprointmentId( rs.getInt("approintmentId"));
                getdocs.add(doc);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return getdocs;
    }
    
    public List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM appointment");) {
            while (rs.next()) {
                Appointment st = new Appointment();
                st.setId( rs.getInt("id"));
                st.setPreferredDate( rs.getDate("preferredDate"));
                st.setPreferredTime(rs.getString("preferredTime"));
                st.setAppointmentReason(rs.getString("appointmentReason"));
                if(rs.getInt("appointmentType") != 0){
                    TestingType type = getTestingType(rs.getInt("appointmentType"));
                    st.setAppointmentType(type.getId());
                }
                st.setReferredDoctor(rs.getString("referredDoctor"));
                st.setEmgContactPer(rs.getString("emgContactPer"));
                st.setEmgMobileNum(rs.getString("emgMobileNum"));
                st.setUserId(rs.getInt("userId"));
                TestingType type =  getTestingType((rs.getInt("appointmentType")));
                st.setTestingType(type);
                List<ApprointmentDoc> docses =  getApprointmentDocs(rs.getInt("id"));
                st.setApprointmentDocList(docses);
                appointments.add(st);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return appointments;
    }
    
    public Appointment getAppointmentbyUserId(int id) throws SQLException {
        Appointment st = new Appointment();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM appointment WHERE userId="+ id);) {
            while (rs.next()) {
                st.setId( rs.getInt("id"));
                st.setPreferredDate( rs.getDate("preferredDate"));
                st.setPreferredTime(rs.getString("preferredTime"));
                st.setAppointmentReason(rs.getString("appointmentReason"));
                st.setAppointmentType(rs.getInt("appointmentType"));
                st.setReferredDoctor(rs.getString("referredDoctor"));
                st.setEmgContactPer(rs.getString("emgContactPer"));
                st.setEmgMobileNum(rs.getString("emgMobileNum"));
                st.setUserId(rs.getInt("userId"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return st;
    }
    
    public boolean updateAppointment(Appointment st) {
        try {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement();) 
            {
                stmt.executeUpdate("UPDATE appointment SET preferredDate = '" +st.getPreferredDate()+ "', preferredTime = '" + st.getPreferredTime()+ "' , appointmentReason = '" + st.getAppointmentReason()+ "', appointmentType = '" + st.getAppointmentType()+ "' , referredDoctor = '" + st.getReferredDoctor()+ "' , emgContactPer = '" + st.getEmgContactPer()+ "' , emgMobileNum = '" + st.getEmgMobileNum()+ "', userId = '" + st.getUserId()+ "' WHERE (id = '" + st.getId() +"');");
                updateAppointmentDocs(st.getApprointmentDocList(),st.getId());
                return true;
            } catch (SQLException e) {
                System.err.print(e);
            }
        } catch (Exception e) {

        }
        return false;
    }
    
    public void updateAppointmentDocs(List<ApprointmentDoc> aDocs,int aId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (ApprointmentDoc doc : aDocs) {
                if (doc.getActive()==1) {
                    if (!checkIfExists(connection,doc.getId())) {
                        // Add to the database
                        addToAppointmentDoc(connection, doc,aId);
                    }
                } else {
                    if (checkIfExists(connection,doc.getId())) {
                        // Remove from the database
                        removeAppointmentDoc(connection, doc,doc.getId());
                    }else{
                        addToAppointmentDoc(connection, doc,aId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkIfExists(Connection connection,int dID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM approintmentdoc WHERE id = "+dID+" AND active = "+1+";";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
//            pre.setLong(1, model.getId());
//            pre.setLong(2, model.getFileName());
//            pre.setString(3, model.getFileType());
//            pre.setString(3, model.getFileType());
            //pre.setInt(5, 1);

            try (ResultSet resultSet = pre.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }
    
    private boolean checkIfExistsDelete(Connection connection,int dID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM approintmentdoc WHERE approintmentId = "+dID+" AND active = "+1+";";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
//            pre.setLong(1, model.getId());
//            pre.setLong(2, model.getFileName());
//            pre.setString(3, model.getFileType());
//            pre.setString(3, model.getFileType());
            //pre.setInt(5, 1);

            try (ResultSet resultSet = pre.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    private void addToAppointmentDoc(Connection connection, ApprointmentDoc model,int aId) throws SQLException {
        String sql = "INSERT INTO approintmentdoc ( fileName, fileType,document ,active, approintmentId)  VALUES (?,?,?,?,?)";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
                pre.setString(1, model.getFileName());
                pre.setString(2, model.getFileType());
                pre.setString(3, model.getDocument());
                pre.setInt(4, 1);
                pre.setInt(5,aId);
                pre.addBatch();
            pre.executeBatch();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeAppointmentDoc(Connection connection, ApprointmentDoc model,int dId) throws SQLException {
        String sql = "DELETE FROM approintmentdoc WHERE id ="+dId+";";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.executeUpdate();
        }
    }
    
    public boolean deleteAppointment(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); ) {
            Statement stmt = conn.createStatement(); 
                
            stmt.executeUpdate("DELETE FROM appointment WHERE (id = '"+ id + "');");
            //String sql = "SELECT COUNT(*) FROM approintmentdoc WHERE id = "+id+" AND active = "+1+";";
            if (checkIfExistsDelete(conn,id)) {
                String deleteDocs = "DELETE FROM approintmentdoc WHERE approintmentId in ("+id+");";
                conn.prepareStatement(deleteDocs);
                PreparedStatement pre = conn.prepareStatement(deleteDocs) ;
                pre.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public TestingType getTestingType(int id) throws SQLException {
        TestingType type = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM testingtype WHERE id="+ id);) {
            while (rs.next()) {
                type = new TestingType();
                type.setId( rs.getInt("id"));
                type.setName(rs.getString("name"));
                type.setPrice(rs.getDouble("price"));
                type.setActive(rs.getInt("active"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return type;
    }
    
    public List<TestingType> getTestingTypes() {
        List<TestingType> types = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM testingtype");) {
            while (rs.next()) {
                TestingType type = new TestingType();
                type.setId( rs.getInt("id"));
                type.setName(rs.getString("name"));
                type.setPrice(rs.getDouble("price"));
                type.setActive(rs.getInt("active"));
                types.add(type);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return types;
    }
    
}
