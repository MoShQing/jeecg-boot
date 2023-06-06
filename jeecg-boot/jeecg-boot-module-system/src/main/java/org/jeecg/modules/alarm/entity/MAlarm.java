package org.jeecg.modules.alarm.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
@Data
@TableName("m_alarm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="m_alarm对象", description="报警数据")
public class MAlarm {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**报警类别*/
	@Excel(name = "报警类别", width = 15)
    @ApiModelProperty(value = "报警类别")
	private java.lang.Integer alarmType;
	/**摄像头ID*/
	@Excel(name = "摄像头ID", width = 15)
    @ApiModelProperty(value = "摄像头ID")
	private java.lang.String cameraId;
	/**摄像头名称*/
	@Excel(name = "摄像头名称", width = 15)
    @ApiModelProperty(value = "摄像头名称")
	private java.lang.String cameraName;
	/**报警图片*/
	@Excel(name = "报警图片", width = 15)
    @ApiModelProperty(value = "报警图片")
	private java.lang.String srcPicData;
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
	/**delFlag*/
	@Excel(name = "delFlag", width = 15)
    @ApiModelProperty(value = "delFlag")
	@TableLogic
	private java.lang.Integer delFlag;
	@JsonIgnore
	private String json;

	@ApiModelProperty(value = "status")
	private java.lang.Integer status;
}
