package org.jeecg.modules.chemical.entity;

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
 * @Description: 重点监管化学品
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Data
@TableName("m_chemical")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="m_chemical对象", description="重点监管化学品")
public class MChemical {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private String id;
	/**chemicalName*/
	@Excel(name = "chemicalName", width = 15)
    @ApiModelProperty(value = "chemicalName")
	private String chemicalName;
	/**cas*/
	@Excel(name = "cas", width = 15)
    @ApiModelProperty(value = "cas")
	private String cas;
	/**importantFlag*/
	@Excel(name = "importantFlag", width = 15)
    @ApiModelProperty(value = "importantFlag")
	private Integer importantFlag;
	/**alias*/
	@Excel(name = "alias", width = 15)
    @ApiModelProperty(value = "alias")
	private String alias;
	/**chemicalNameEn*/
	@Excel(name = "chemicalNameEn", width = 15)
    @ApiModelProperty(value = "chemicalNameEn")
	private String chemicalNameEn;
	/**dangerType*/
	@Excel(name = "dangerType", width = 15)
    @ApiModelProperty(value = "dangerType")
	private String dangerType;
	/**storageLocation*/
	@Excel(name = "storageLocation", width = 15)
    @ApiModelProperty(value = "storageLocation")
	private String storageLocation;
	/**storageMethod*/
	@Excel(name = "storageMethod", width = 15)
    @ApiModelProperty(value = "storageMethod")
	private String storageMethod;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**0*/
	@Excel(name = "0", width = 15)
    @ApiModelProperty(value = "0")
	private Integer delFlag;
}
