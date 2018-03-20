package nju.entity;

import javax.persistence.*;

/**
 * Created by lienming on 2018/3/10.
 */
@Entity
@Table(name = "manager",schema = "tickets")
public class Manager {


    private String managerID ;

    private String managerName ;

    @GeneratedValue
    @Id
    @Column(name = "manager_id", nullable = false)
    public String getManagerID(){return this.managerID;}
    public void setManagerID(String managerID){this.managerID=managerID;}

    @Basic
    @Column(name = "manager_name", nullable = false, length = 20)
    public String getManagerName(){
        return this.managerName ;
    }
    public void setManagerName(String name) {
        this.managerName = name ;
    }



}
