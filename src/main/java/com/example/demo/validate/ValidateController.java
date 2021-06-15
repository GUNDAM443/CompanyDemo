package com.example.demo.validate;

import com.example.demo.threadPoolExceutor.bean.CommandSend;
import com.example.demo.validate.validate_interface.Add;
import com.example.demo.validate.validate_interface.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : pp
 * @date : Created in 2021/2/20 13:32
 */
@RestController
@RequestMapping("/validate")
@Slf4j
public class ValidateController {
    @PostMapping("add")
    public String addCommand(@Validated(Add.class) @RequestBody CommandSend commandSend){
        log.info("validated 分组校验  add分组成功!");
        return "你真棒!";
    }

    @PostMapping("edit")
    public String updateCommand(@Validated(Update.class) @RequestBody CommandSend commandSend){
        log.info("validated 分组校验  update分组成功!");
        return "nbclass";
    }
}
