package org.jeecg.modules.enterprise.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.enterprise.entity.MmEnterprise;
import org.jeecg.modules.enterprise.service.IMmEnterpriseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 企业信息表
 * @Author: jeecg-boot
 * @Date:   2023-05-12
 * @Version: V1.0
 */
@RestController
@RequestMapping("/enterprise/mmEnterprise")
@Slf4j
public class MmEnterpriseController extends JeecgController<MmEnterprise, IMmEnterpriseService> {
	@Autowired
	private IMmEnterpriseService mmEnterpriseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mmEnterprise
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MmEnterprise mmEnterprise,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MmEnterprise> queryWrapper = QueryGenerator.initQueryWrapper(mmEnterprise, req.getParameterMap());
		Page<MmEnterprise> page = new Page<MmEnterprise>(pageNo, pageSize);
		IPage<MmEnterprise> pageList = mmEnterpriseService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mmEnterprise
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MmEnterprise mmEnterprise) {
		mmEnterpriseService.save(mmEnterprise);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mmEnterprise
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MmEnterprise mmEnterprise) {
		mmEnterpriseService.updateById(mmEnterprise);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mmEnterpriseService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mmEnterpriseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MmEnterprise mmEnterprise = mmEnterpriseService.getById(id);
		if(mmEnterprise==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mmEnterprise);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mmEnterprise
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmEnterprise mmEnterprise) {
        return super.exportXls(request, mmEnterprise, MmEnterprise.class, "企业信息表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MmEnterprise.class);
    }

}
