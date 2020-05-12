package com.ego.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "welcome";
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;
    }


}

