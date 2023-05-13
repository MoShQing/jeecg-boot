package org.jeecg.modules.dangerssource.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 重大危险源
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Data
@TableName("m_dangers_source")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="m_dangers_source对象", description="重大危险源")
public class MDangersSource {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**危化源名称*/
	@Excel(name = "危化源名称", width = 15)
    @ApiModelProperty(value = "危化源名称")
	private java.lang.String dangersSourceName;
	/**注册编号*/
	@Excel(name = "注册编号", width = 15)
    @ApiModelProperty(value = "注册编号")
	private java.lang.String regNo;
	/**活动区域*/
	@Excel(name = "活动区域", width = 15)
    @ApiModelProperty(value = "活动区域")
	private java.lang.String area;
	/**危险类型*/
	@Excel(name = "危险类型", width = 15)
    @ApiModelProperty(value = "危险类型")
	private java.lang.Integer type;
	/**等级*/
	@Excel(name = "等级", width = 15)
    @ApiModelProperty(value = "等级")
	private java.lang.Integer level;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	private java.lang.String charger;
	/**目前状态*/
	@Excel(name = "目前状态", width = 15)
    @ApiModelProperty(value = "目前状态")
	private java.lang.Integer status;
	/**确认依据*/
	@Excel(name = "确认依据", width = 15)
    @ApiModelProperty(value = "确认依据")
	private java.lang.String basis;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**可能造成后果*/
	@Excel(name = "可能造成后果", width = 15)
    @ApiModelProperty(value = "可能造成后果")
	private java.lang.String result;
	/**控制措施*/
	@Excel(name = "控制措施", width = 15)
    @ApiModelProperty(value = "控制措施")
	private java.lang.String measure;
	/**周围环境*/
	@Excel(name = "周围环境", width = 15)
    @ApiModelProperty(value = "周围环境")
	private java.lang.String enviroment;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**0*/
	@Excel(name = "0", width = 15)
    @ApiModelProperty(value = "0")
	private java.lang.Integer delFlag;
}
