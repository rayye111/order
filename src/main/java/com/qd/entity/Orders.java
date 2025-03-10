package com.qd.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deskId;

    private Integer propleCount;

    private String descr;

    private BigDecimal total;

    @ApiModelProperty(value = "0待支付，1已取消 2已完成")
    private String states;

    private LocalDateTime orderTime;

    private LocalDateTime payTime;

    private LocalDateTime finialTime;

    private LocalDateTime calTime;

    //额外的属性
    @TableField(exist = false)
    private String dname;


}