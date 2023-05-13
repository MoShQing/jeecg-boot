package org.jeecg.modules.riskManagement.entity;

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
 * @Description: 安全风险管控清单
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Data
@TableName("m_risk_management")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="m_risk_management对象", description="安全风险管控清单")
public class MRiskManagement {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**风险分析对象名称*/
	@Excel(name = "风险分析对象名称", width = 15)
    @ApiModelProperty(value = "风险分析对象名称")
	private java.lang.String riskName;
	/**责任部门*/
	@Excel(name = "责任部门", width = 15)
    @ApiModelProperty(value = "责任部门")
	private java.lang.String chargeDepart;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	private java.lang.String chargeMan;
	/**责任人手机号*/
	@Excel(name = "责任人手机号", width = 15)
    @ApiModelProperty(value = "责任人手机号")
	private java.lang.String chargePhone;
	/**企业编码*/
	@Excel(name = "企业编码", width = 15)
    @ApiModelProperty(value = "企业编码")
	private java.lang.String enterpriseCode;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
	private java.lang.Integer createTimeStr;
	/**安全风险分析单元*/
	@Excel(name = "安全风险分析单元", width = 15)
    @ApiModelProperty(value = "安全风险分析单元")
	private java.lang.String riskUnit;
	/**危险源*/
	@Excel(name = "危险源", width = 15)
    @ApiModelProperty(value = "危险源")
	private java.lang.String riskSource;
	/**安全风险*/
	@Excel(name = "安全风险", width = 15)
    @ApiModelProperty(value = "安全风险")
	private java.lang.String securityRisks;
	/**诱发事故类型*/
	@Excel(name = "诱发事故类型", width = 15)
    @ApiModelProperty(value = "诱发事故类型")
	private java.lang.String type;
	/**是否为重点管控风险*/
	@Excel(name = "是否为重点管控风险", width = 15)
    @ApiModelProperty(value = "是否为重点管控风险")
	private java.lang.Integer importantFlag;
	/**详细位置*/
	@Excel(name = "详细位置", width = 15)
    @ApiModelProperty(value = "详细位置")
	private java.lang.String locationDetail;
	/**伤亡/财产损失预测*/
	@Excel(name = "伤亡/财产损失预测", width = 15)
    @ApiModelProperty(value = "伤亡/财产损失预测")
	private java.lang.String prediction;
	/**管控层级*/
	@Excel(name = "管控层级", width = 15)
    @ApiModelProperty(value = "管控层级")
	private java.lang.String managementLevel;
	/**危险因素*/
	@Excel(name = "危险因素", width = 15)
    @ApiModelProperty(value = "危险因素")
	private java.lang.String riskFactor;
	/**应急处置主要措施*/
	@Excel(name = "应急处置主要措施", width = 15)
    @ApiModelProperty(value = "应急处置主要措施")
	private java.lang.String scheme;
	/**分线等级*/
	@Excel(name = "分线等级", width = 15)
    @ApiModelProperty(value = "分线等级")
	private java.lang.String riskLevel;
	/**辨识时间*/
	@Excel(name = "辨识时间", width = 15)
    @ApiModelProperty(value = "辨识时间")
	private java.lang.Integer identificationTime;
	/**管控措施分类*/
	@Excel(name = "管控措施分类", width = 15)
    @ApiModelProperty(value = "管控措施分类")
	private java.lang.String managementMethodType;
	/**岗位责任人*/
	@Excel(name = "岗位责任人", width = 15)
    @ApiModelProperty(value = "岗位责任人")
	private java.lang.String jobManager;
	/**具体管控措施*/
	@Excel(name = "具体管控措施", width = 15)
    @ApiModelProperty(value = "具体管控措施")
	private java.lang.String schemeDetail;
	/**隐患排查内容*/
	@Excel(name = "隐患排查内容", width = 15)
    @ApiModelProperty(value = "隐患排查内容")
	private java.lang.String debugContent;
	/**隐患描述*/
	@Excel(name = "隐患描述", width = 15)
    @ApiModelProperty(value = "隐患描述")
	private java.lang.String riskDetail;
	/**记录状态*/
	@Excel(name = "记录状态", width = 15)
    @ApiModelProperty(value = "记录状态")
	private java.lang.Integer status;
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
	private java.lang.Integer delFlag;
}
