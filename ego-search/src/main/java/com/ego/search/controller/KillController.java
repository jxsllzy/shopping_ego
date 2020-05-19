package com.ego.search.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.rpc.service.KindsService;
import com.ego.rpc.vo.KindsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("shopInformation")
public class KillController {

    @Reference(interfaceClass = KindsService.class)
    private KindsService kindsService;

    @RequestMapping("kinds")
    @ResponseBody
    public List<KindsVo> queryAllKindsList(){
        return kindsService.queryAllKindsList();
    }

}
