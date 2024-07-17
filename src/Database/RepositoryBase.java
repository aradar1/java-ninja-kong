
package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryBase {
    private Connection connection;
    private PreparedStatement prepStatement;
    private CallableStatement callStatement;
    private Statement statement;
    protected ResultSet resultSet;
    protected int affectedRows;
    protected int latestIdGenerated;
    
    public RepositoryBase() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class.forName(Config.DRIVER).newInstance();
    }
    
    public void open() throws SQLException{
        connection = DriverManager.getConnection(Config.URL, Config.USER_NAME, Config.PASSWORD);
    }
    
    public void close(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createStatement(String query) throws SQLException{
        statement = connection.createStatement();
    }
    
    public void closeStatement() throws SQLException{
        statement.close();
    }
    
    public void prepareCreateStatement(String query) throws SQLException{
        prepStatement = connection.prepareStatement(query);
    }
    
    public void closePrepStatement(){
        try {
            prepStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createCallableStatement(String procedure) throws SQLException{
        callStatement = connection.prepareCall(procedure);
    }
    
    public void closeCallableStatement(){
        try {
            callStatement.close();
            callStatement = null;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeResultSet(){
        try {
            resultSet.close();
            resultSet = null;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setParams(List<Object> params) throws SQLException{
        for(int index = 0; index < params.size(); index++){
            Object temp = params.get(index);
            int paramIndex = index + 1;
            if(temp.getClass() == String.class){
                prepStatement.setString(paramIndex, temp.toString());
            }else if(temp.getClass() == Integer.class){
                prepStatement.setInt(paramIndex, (int)temp);
            }else if(temp.getClass() == Float.class){
                prepStatement.setFloat(paramIndex, (float)temp);
            }else if(temp.getClass() == Double.class){
                prepStatement.setDouble(paramIndex, (double)temp);
            }else if(temp.getClass() == Boolean.class){
                prepStatement.setBoolean(paramIndex, (boolean)temp);
            }else if(temp.getClass() == Date.class){
                prepStatement.setDate(paramIndex, (java.sql.Date)temp);
            }else if(temp.getClass() == Timestamp.class){
                prepStatement.setTimestamp(paramIndex, (java.sql.Timestamp)temp);
            }
        }
    }
    
    public void setCallableParams(List<Object> params) throws SQLException{
        for(int index = 0; index < params.size(); index++){
            Object temp = params.get(index);
            int paramIndex = index + 1;
            if(temp.getClass() == String.class){
                callStatement.setString(paramIndex, temp.toString());
            }else if(temp.getClass() == Integer.class){
                callStatement.setInt(paramIndex, (int)temp);
            }else if(temp.getClass() == Float.class){
                callStatement.setFloat(paramIndex, (float)temp);
            }else if(temp.getClass() == Double.class){
                callStatement.setDouble(paramIndex, (double)temp);
            }else if(temp.getClass() == Boolean.class){
                callStatement.setBoolean(paramIndex, (boolean)temp);
            }else if(temp.getClass() == Date.class){
                callStatement.setDate(paramIndex, (java.sql.Date)temp);
            }else if(temp.getClass() == Timestamp.class){
                callStatement.setTimestamp(paramIndex, (java.sql.Timestamp)temp);
            }else if(temp.getClass() == null){
                
            }
        }
    }
    
    protected int executeStatement() throws SQLException{
        prepStatement.executeUpdate();
        resultSet = prepStatement.getGeneratedKeys();
        return 0;
    }
    
    protected void prepareResultSet() throws SQLException{
        resultSet = prepStatement.executeQuery();
    }
    
    protected void callSelectProcedure() throws SQLException{
        boolean hasResult = callStatement.execute();
        if(hasResult){
            resultSet = callStatement.getResultSet();
            // if expecting multiple result sets
            //hasResult = callStatement.getMoreResults();
        }
    }
    
    protected void callDMLProcedure() throws SQLException{
        affectedRows = callStatement.executeUpdate();
        resultSet = callStatement.getGeneratedKeys(); // for latest id generated
        // if the procedure is INSERT, UPDATE, DELETE use this
    }
    
    protected boolean nextRow() throws SQLException{
        return resultSet.next();
    }
}
