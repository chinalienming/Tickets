package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/10.
 */
@Entity
@Table(name = "manager",schema = "tickets")
public class Manager {


    private int managerID ;

    private String password ;


    @GeneratedValue
    @Id
    @Column(name = "manager_id", nullable = false)
    public int getManagerID(){return this.managerID;}
    public void setManagerID(int managerID){this.managerID=managerID;}

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword(){
        return this.password ;
    }
    public void setPassword(String pwd) {
        this.password = pwd ;
    }



}
