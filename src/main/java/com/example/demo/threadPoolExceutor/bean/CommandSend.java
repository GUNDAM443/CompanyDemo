package com.example.demo.threadPoolExceutor.bean;

import com.example.demo.validate.validate_interface.Add;
import com.example.demo.validate.validate_interface.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author : pp
 * @date : Created in 2020/7/7 10:57
 */
@Document("command_send")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandSend {
    //@Validated(Update.class) 会对该校验生效
    @NotNull(groups = Update.class,message = "修改时id不能为空")
    private String id;

    @Indexed
    @Field("sn")
    @NotEmpty(message = "sn不能为空")
    @Length(min = 1,max = 6,message = "sn长度为1-6")
    private String sn;


    @Field("buildId")
    @NotEmpty(groups = Add.class,message = "建筑id不能为空")
    //Add 接口没有继承 javax.validation.groups.Default 的话  下面的@length 会失效
    @Length(min = 1,max = 6,message = "sn长度为1-6")
    private String buildId;

    @Field("sendTime")
    private String time;
}
