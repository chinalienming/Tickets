package nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lienming on 2018/3/19.
 */

@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String hello(){
        return "user/index";        //跳转到user/index.html
    }


}

