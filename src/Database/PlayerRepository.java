
package Database;

import Models.Player;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerRepository extends RepositoryBase implements IRepository<Player> {

    public PlayerRepository() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        super();
    }

    @Override
    public Player get(Player model) {
        try {
            open();
            String call = "{call selectPlayer(?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(model.getId());
            setCallableParams(params);
            callSelectProcedure();
            nextRow();
            
            model.setId(resultSet.getInt("playerId"));
            model.setInitialPos(resultSet.getInt("initialPos"));
            model.setVirtualX(resultSet.getInt("posX"));
            model.setVirtualY(resultSet.getInt("posY"));
            model.setDead(resultSet.getBoolean("isDead"));
            model.setFacingDirection(resultSet.getString("facingDirection"));
            model.setImageArrayIndex(resultSet.getInt("imageArrayIndex"));
            
        } catch (SQLException ex) {            
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
          finally{
            closeCallableStatement();
            closeResultSet();
            close();
        }
        return model;
    }
    
    @Override
    public int create(Player model){
        try {
            open();
            String call = "{call insertPlayer(?, ?, ?, ?, ?, ?, ?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(model.getId());
            params.add(model.getInitialPos());
            params.add(model.getVirtualX());
            params.add(model.getVirtualY());
            params.add(model.isDead());
            params.add("");
            params.add(model.getImageArrayIndex());
            
            setCallableParams(params);
            callDMLProcedure();
            return affectedRows;
        } catch (SQLException ex) {
            System.out.println("HI");
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeCallableStatement();
            close();
        }
        return affectedRows;
    }

    @Override
    public int update(Player model) {
        try {
            open();
            String call = "{call updatePlayer(?, ?, ?, ?, ?, ?, ?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(model.getId());
            params.add(model.getInitialPos());
            params.add(model.getVirtualX());
            params.add(model.getVirtualY());
            params.add(model.isDead());
            params.add(model.getFacingDirection());
            params.add(model.getImageArrayIndex());
            
            setCallableParams(params);
            callDMLProcedure();
            return affectedRows;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeCallableStatement();
            close();
        }
        return affectedRows;
    }
    
    @Override
    public int checkId(int id){
        try{
            open();
            String call = "{call checkId(?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(id);
            setCallableParams(params);
            callSelectProcedure();
            nextRow();
            return resultSet.getInt("playerID");
        } catch (SQLException ex) {
            return 0;
        } finally{
            closeCallableStatement();
            close();
        }
    }
    
    public int createCollision(int id, String collisionDirection){
        try {
            open();
            String call = "{call insertCollision(?, ?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(id);
            params.add(collisionDirection);
            setCallableParams(params);
            callDMLProcedure();
            return affectedRows;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeCallableStatement();
            close();
        }
        return affectedRows;
    }   
    
    public List<String> getCollision(int id){
        List<String> collision = new ArrayList();
        try {
            open();
            String call = "{call getCollisions(?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(id);
            setCallableParams(params);
            callSelectProcedure();
            while(nextRow())
                collision.add(resultSet.getString("direction"));               
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return collision;
    }
    
    public int deleteCollision(int id){
        try {
            open();
            String call = "{call deleteCollision(?)}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            params.add(id);
            setCallableParams(params);
            callDMLProcedure();
            return affectedRows;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affectedRows;
    }
    
    public void deletePlayerDB(){
        try {
            open();
            String call = "{call deletePlayerDB()}";
            createCallableStatement(call);
            List<Object> params = new ArrayList<>();
            setCallableParams(params);
            callDMLProcedure();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public List<Player> getAll() {
//        List<Player> players = new ArrayList();
//        Player model = new Player(0, 0, 0, "", 17);
//        try {
//            open();
//            String call = "{call getAllPlayers()}";
//            createCallableStatement(call);
//            callSelectProcedure();
//            while(nextRow()){
//                model.setId(resultSet.getInt("playerId"));
//                model.setVirtualX(resultSet.getInt("posX"));
//                model.setVirtualY(resultSet.getInt("posY"));
//                model.setDead(resultSet.getBoolean("isDead"));
//                model.setClimbing(resultSet.getBoolean("isClimbing"));
//                model.setCanClimb(resultSet.getBoolean("canClimb"));
//                model.setCanJump(resultSet.getBoolean("isDead"));
//                model.setJumping(resultSet.getBoolean("isJumping"));
//                model.setImageArrayIndex(resultSet.getInt("imageArrayIndex"));
//                model.setFileLoc(resultSet.getString("fileLocation"));
//                model.populateImageFiles(model.getFileLoc());
//                players.add(model);
//            }
//            
//        } catch (SQLException ex) {            
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          finally{
//            closeCallableStatement();
//            closeResultSet();
//            close();
//        }
//        return players;
//    }
}
