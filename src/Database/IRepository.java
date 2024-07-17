/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.List;

/**
 *
 * @author R312-Lab
 */
public interface IRepository<T> {
    T get(T model);
    int checkId(int id);
//    List<T> getAll();
    int create(T model);
    int update(T model);
}
