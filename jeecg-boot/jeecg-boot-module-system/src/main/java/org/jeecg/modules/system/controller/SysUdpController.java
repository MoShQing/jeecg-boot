package org.jeecg.modules.system.controller;


import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.UdpConfig;
import org.jeecg.modules.system.entity.SysPermission;
import org.jeecg.modules.system.entity.SysPermissionDataRule;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysRolePermission;
import org.jeecg.modules.system.model.TreeModel;
import org.jeecg.modules.system.service.ISysPermissionDataRuleService;
import org.jeecg.modules.system.service.ISysPermissionService;
import org.jeecg.modules.system.service.ISysRolePermissionService;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * udp发送
 * </p>
 */
@RestController
@RequestMapping("/sys/udp")
@Slf4j
public class SysUdpController {

	/**
	  *   发送
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Result<SysRole> add(@RequestBody Map<String,String> form) {
		Result<SysRole> result = new Result<SysRole>();

		String ip = form.get("ip");
		String port = form.get("port");
		String mess = form.get("mess");

		byte[] bytes = Convert.hexToBytes(mess);

		UnicastSendingMessageHandler unicastSendingMessageHandler = new UnicastSendingMessageHandler(ip, Integer.parseInt(port));
		try {
			unicastSendingMessageHandler.handleMessage(MessageBuilder.withPayload(bytes).build());
			result.success("发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.error500("发送失败！");
		}
		return result;
	}
}
