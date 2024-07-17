/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author R312-Lab
 */
public interface IAction<T>{  
    void moveUp(T model);
    void moveDown(T model);
    void moveLeft(T model);
    void moveRight(T model);
    void jump(T model, int gameMode);
}
