//
//package Database;
//
//import Models.Player;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class DirectionRepository extends RepositoryBase{
//
//    public DirectionRepository() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
//        super();
//    }
//
//    public Player get(Player model) {
//        try {
//            open();
//            String call = "{call selectPlayer(?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(model.getId());
//            setCallableParams(params);
//            callSelectProcedure();
//            nextRow();
//            
//            model.setId(resultSet.getInt("id"));
//            model.setVirtualX(resultSet.getInt("posX"));
//            model.setVirtualY(resultSet.getInt("posY"));
//            model.setDead(resultSet.getBoolean("isDead"));
//            model.setClimbing(resultSet.getBoolean("isClimbing"));
//            model.setCanClimb(resultSet.getBoolean("canClimb"));
//            model.setCanJump(resultSet.getBoolean("isDead"));
//            model.setJumping(resultSet.getBoolean("isJumping"));
//            model.setImageArrayIndex(resultSet.getInt("imageArrayIndex"));
//            
//        } catch (SQLException ex) {            
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          finally{
//            closeCallableStatement();
//            closeResultSet();
//            close();
//        }
//        return model;
//    }
//    
//    public int create(Player model){
//        try {
//            open();
//            String call = "{call insertPlayer(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(model.getId());
//            params.add(model.getVirtualX());
//            params.add(model.getVirtualY());
//            params.add(model.isDead());
//            params.add(model.isClimbing());
//            params.add(model.canClimb());
//            params.add(model.canJump());
//            params.add(model.isJumping());
//            switch (model.getFacingDirection()) {
//                case UP:
//                    params.add(1);
//                    break;
//                case DOWN:
//                    params.add(2);
//                    break;
//                case LEFT:
//                    params.add(3);
//                    break;
//                case RIGHT:
//                    params.add(4);
//                    break;
//                default:
//                    break;
//            }
//            params.add(model.getImageArrayIndex());
//            
//            setCallableParams(params);
//            callDMLProcedure();
//            return affectedRows;
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            closeCallableStatement();
//            close();
//        }
//        return affectedRows;
//    }
//
//    public int update(Player player) {
//        try {
//            open();
//            String call = "{call updatePlayer(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(player.getId());
//            params.add(player.getVirtualX());
//            params.add(player.getVirtualY());
//            params.add(player.isDead());
//            params.add(player.isClimbing());
//            params.add(player.canClimb());
//            params.add(player.canJump());
//            params.add(player.isJumping());
//            switch (player.getFacingDirection()) {
//                case UP:
//                    params.add(1);
//                    break;
//                case DOWN:
//                    params.add(2);
//                    break;
//                case LEFT:
//                    params.add(3);
//                    break;
//                case RIGHT:
//                    params.add(4);
//                    break;
//                default:
//                    break;
//            }
//            params.add(player.getImageArrayIndex());
//            
//            setCallableParams(params);
//            callDMLProcedure();
//            return affectedRows;
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            closeCallableStatement();
//            close();
//        }
//        return affectedRows;
//    }
//    
//    public int createCollision(int id, Direction collision){
//        try {
//            open();
//            String call = "{call insertCollision(?, ?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(id);
//            switch (collision) {
//                case UP:
//                    params.add(1);
//                    break;
//                case DOWN:
//                    params.add(2);
//                    break;
//                case LEFT:
//                    params.add(3);
//                    break;
//                case RIGHT:
//                    params.add(4);
//                    break;
//                default:
//                    break;
//            }            
//            setCallableParams(params);
//            callDMLProcedure();
//            return affectedRows;
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            closeCallableStatement();
//            close();
//        }
//        return affectedRows;
//    }
//    
//        public int updateCollision(int id, Direction collision){
//        try {
//            open();
//            String call = "{call updateCollision(?, ?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(id);
//            switch (collision) {
//                case UP:
//                    params.add(1);
//                    break;
//                case DOWN:
//                    params.add(2);
//                    break;
//                case LEFT:
//                    params.add(3);
//                    break;
//                case RIGHT:
//                    params.add(4);
//                    break;
//                default:
//                    break;
//            }            
//            setCallableParams(params);
//            callDMLProcedure();
//            return affectedRows;
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            closeCallableStatement();
//            close();
//        }
//        return affectedRows;
//    }
//    
//    public List<Direction> getCollision(int id){
//        List<Direction> collision = new ArrayList();
//        try {
//            open();
//            String call = "{call getCollisions(?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            params.add(id);
//            setCallableParams(params);
//            callSelectProcedure();
//            while(nextRow()){
//                switch (resultSet.getInt("directionId")) {
//                case 1:
//                    collision.add(Direction.UP);
//                    break;
//                case 2:
//                    collision.add(Direction.DOWN);
//                    break;
//                case 3:
//                    collision.add(Direction.LEFT);
//                    break;
//                case 4:
//                    collision.add(Direction.RIGHT);
//                    break;
//                default:
//                    break;
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return collision;
//    }
//    
//    public int delete(int id){
//        try {
//            open();
//            String call = "{call deleteCollision(?)}";
//            createCallableStatement(call);
//            List<Object> params = new ArrayList<>();
//            
//            params.add(id);
//            setCallableParams(params);
//            callDMLProcedure();
//            return affectedRows;
//        } catch (SQLException ex) {
//            Logger.getLogger(DirectionRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return affectedRows;
//    }
//}
