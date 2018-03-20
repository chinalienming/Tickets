package nju.controller;

import nju.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by lienming on 2018/3/11.
 */
@Controller

public class ManagerController {

    @Autowired
    private ManagerService managerService ;

//    @RequestMapping({"/", "/index"})
//    public String index() {
//
//        return "/index" ;
//    }



}
