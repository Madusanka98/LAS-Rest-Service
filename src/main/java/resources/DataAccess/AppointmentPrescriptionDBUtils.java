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
import resources.Models.AppointmentPrescription;
import resources.Models.AppointmentPrescriptionDoc;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentPrescriptionDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public AppointmentPrescriptionDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    
    
    public boolean addAppointmentPrescription(AppointmentPrescription st) {
        String insertQuery = "INSERT INTO apPrescription ( testResults, technicians,comment , appointmentId,tId)  VALUES ('"+ st.getTestResults()+"','"+ st.getTechnicians()+"','"+ st.getComment()+"','"+ st.getAppointmentId()+"', '"+ st.gettId()+"');";
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
                                addAppointmentPrescriptionDocs(st.getApprointmentDocList(),lastInsertedId);
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
    
    public  void addAppointmentPrescriptionDocs(List<AppointmentPrescriptionDoc> docs,int aPrescriptionId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO apPrescriptionDoc ( fileName, fileType,document ,active, aPrescriptionId)  VALUES (?,?,?,?,?)";
            try (PreparedStatement pre = connection.prepareStatement(sql)) {
                for (AppointmentPrescriptionDoc model : docs) {
                    pre.setString(1, model.getFileName());
                    pre.setString(2, model.getFileType());
                    pre.setString(3, model.getDocument());
                    pre.setInt(4, 1);
                    pre.setInt(5,aPrescriptionId);
                    pre.addBatch();
                }
                pre.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    public AppointmentPrescription getAppointmentPrescription(int id) throws SQLException {
        List<AppointmentPrescriptionDoc> docs = null; 
        AppointmentPrescription st = new AppointmentPrescription();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
        Statement stmt = conn.createStatement(); 
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM apPrescription WHERE appointmentId="+ id);) {
            while (rs.next()) {
                
                //st = new Appointment();
                st.setId( rs.getInt("id"));
                st.setTestResults( rs.getString("testResults"));
                st.setTechnicians(rs.getString("technicians"));
                st.setComment(rs.getString("comment"));
                st.setAppointmentId(rs.getInt("appointmentId"));
                List<AppointmentPrescriptionDoc> docses =  getAppointmentPrescriptionDocs(rs.getInt("id"));
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
    
    public List<AppointmentPrescriptionDoc> getAppointmentPrescriptionDocs(int id) {
        List<AppointmentPrescriptionDoc> getdocs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM apPrescriptionDoc WHERE aPrescriptionId="+ id);) {
            while (rs.next()) {
                AppointmentPrescriptionDoc doc = new AppointmentPrescriptionDoc();
                doc.setId( rs.getInt("id"));
                doc.setFileName( rs.getString("fileName"));
                doc.setFileType( rs.getString("fileType"));
                doc.setDocument( rs.getString("document"));
                doc.setActive(rs.getInt("active"));
                doc.setaPrescriptionId(rs.getInt("aPrescriptionId"));
                getdocs.add(doc);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return getdocs;
    }
    
    public List<AppointmentPrescription> getAppointmentPrescriptions() {
        List<AppointmentPrescription> appointments = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM apPrescription");) {
            while (rs.next()) {
                AppointmentPrescription st = new AppointmentPrescription();
                st.setId( rs.getInt("id"));
                st.setTestResults(rs.getString("testResults"));
                st.setTechnicians(rs.getString("technicians"));
                st.setComment(rs.getString("comment"));
                st.setAppointmentId(rs.getInt("appointmentId"));
                
                List<AppointmentPrescriptionDoc> docses =  getAppointmentPrescriptionDocs(rs.getInt("id"));
                st.setApprointmentDocList(docses);
                appointments.add(st);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return appointments;
    }
    
    public List<AppointmentPrescription> getPrescriptionByUserId(int id) {
        List<AppointmentPrescription> appointments = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM apPrescription WHERE userId="+ id);) {
            while (rs.next()) {
                AppointmentPrescription st = new AppointmentPrescription();
                st.setId( rs.getInt("id"));
                st.setTestResults(rs.getString("testResults"));
                st.setTechnicians(rs.getString("technicians"));
                st.setComment(rs.getString("comment"));
                st.setAppointmentId(rs.getInt("appointmentId"));
                
                List<AppointmentPrescriptionDoc> docses =  getAppointmentPrescriptionDocs(rs.getInt("id"));
                st.setApprointmentDocList(docses);
                appointments.add(st);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return appointments;
    }
    
    public AppointmentPrescription getAppointmentPrescriptionbyUserId(int id) throws SQLException {
        AppointmentPrescription st = new AppointmentPrescription();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM apPrescription WHERE userId="+ id);) {
            while (rs.next()) {
                st.setId( rs.getInt("id"));
                st.setTestResults(rs.getString("testResults"));
                st.setTechnicians(rs.getString("technicians"));
                st.setComment(rs.getString("comment"));
                st.setAppointmentId(rs.getInt("appointmentId"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return st;
    }
    
    public boolean updateAppointmentPrescription(AppointmentPrescription st,int id) {
        try {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement();) 
            {
                stmt.executeUpdate("UPDATE apPrescription SET testResults = '" +st.getTestResults()+ "', technicians = '" + st.getTechnicians()+ "' , comment = '" + st.getComment()+ "' , appointmentId = '" + st.getAppointmentId()+ "' , tId = '" + st.gettId()+ "' WHERE (id = '" + id +"');");
                updateAppointmentPrescriptionDocs(st.getApprointmentDocList(),st.getId());
                return true;
            } catch (SQLException e) {
                System.err.print(e);
            }
        } catch (Exception e) {

        }
        return false;
    }
    
    public void updateAppointmentPrescriptionDocs(List<AppointmentPrescriptionDoc> aDocs,int aId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            for (AppointmentPrescriptionDoc doc : aDocs) {
                if (doc.getActive()==1) {
                    if (!checkIfExists(connection,doc.getId())) {
                        // Add to the database
                        addToAppointmentPrescriptionDoc(connection, doc,aId);
                    }
                } else {
                    if (checkIfExists(connection,doc.getId())) {
                        // Remove from the database
                        removeAppointmentPrescriptionDoc(connection, doc,doc.getId());
                    }else{
                        addToAppointmentPrescriptionDoc(connection, doc,aId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkIfExists(Connection connection,int dID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM apPrescriptionDoc WHERE id = "+dID+" AND active = "+1+";";
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

    private void addToAppointmentPrescriptionDoc(Connection connection, AppointmentPrescriptionDoc model,int aId) throws SQLException {
        String sql = "INSERT INTO apPrescriptionDoc ( fileName, fileType,document ,active, aPrescriptionId)  VALUES (?,?,?,?,?)";
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

    private void removeAppointmentPrescriptionDoc(Connection connection, AppointmentPrescriptionDoc model,int dId) throws SQLException {
        String sql = "DELETE FROM apPrescriptionDoc WHERE id ="+dId+";";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.executeUpdate();
        }
    }
    
    public boolean deleteAppointmentPrescription(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); ) {
            Statement stmt = conn.createStatement(); 
                
            stmt.executeUpdate("DELETE FROM apPrescription WHERE (id = '"+ id + "');");
            //String sql = "SELECT COUNT(*) FROM approintmentdoc WHERE id = "+id+" AND active = "+1+";";
            if (checkIfExistsDelete(conn,id)) {
                String deleteDocs = "DELETE FROM apPrescriptionDoc WHERE aPrescriptionId in ("+id+");";
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
    
    private boolean checkIfExistsDelete(Connection connection,int dID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM apPrescriptionDoc WHERE aPrescriptionId = "+dID+" AND active = "+1+";";
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
}
