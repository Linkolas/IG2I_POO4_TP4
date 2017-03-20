/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

/**
 *
 * @author Nicolas
 */
public class DaoFactoryJpa extends DaoFactory {
    public static JpaAtelierDao getAtelierDao() {
        return JpaAtelierDao.getInstance();
    }
    
    public static JpaMachineDao getMachineDao() {
        return JpaMachineDao.getInstance();
    }
    
    public static JpaTacheDao getTacheDao() {
        return JpaTacheDao.getInstance();
    }
}
