/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Player;
import java.util.List;

/**
 *
 * @author R312-Lab
 */
public class DBCaller{

    public DBCaller() {
    }
    
    public int checkId(IRepository repo, int id){
        return repo.checkId(id);
    }
    
    public int createPlayer(IRepository repo, Player p){
        return repo.create(p);
    }
    
    public Player getPlayer(IRepository repo, Player p){
        return (Player) repo.get(p);
    }
    
    public int updatePlayer(IRepository repo, Player p){
        return repo.update(p);
    }
    
    public int createCollision(PlayerRepository repo, int id, String collision){
        return repo.createCollision(id, collision);
    }
    
    public int deleteCollision(PlayerRepository repo, int id){
        return repo.deleteCollision(id);
    }
    
    public void deletePlayerDB(PlayerRepository repo){
        repo.deletePlayerDB();
    }
//    
//    public List<Player> getAll(IRepository repo){
//        return repo.getAll();
//    }
}
