package org.jeecg.modules.riskLevel.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.riskLevel.entity.MmRiskLevel;
import org.jeecg.modules.riskLevel.service.IMmRiskLevelService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 安全风险四色分布图
 * @Author: jeecg-boot
 * @Date:   2023-05-14
 * @Version: V1.0
 */
@RestController
@RequestMapping("/riskLevel/mmRiskLevel")
@Slf4j
public class MmRiskLevelController extends JeecgController<MmRiskLevel, IMmRiskLevelService> {
	@Autowired
	private IMmRiskLevelService mmRiskLevelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mmRiskLevel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MmRiskLevel mmRiskLevel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MmRiskLevel> queryWrapper = QueryGenerator.initQueryWrapper(mmRiskLevel, req.getParameterMap());
		Page<MmRiskLevel> page = new Page<MmRiskLevel>(pageNo, pageSize);
		IPage<MmRiskLevel> pageList = mmRiskLevelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mmRiskLevel
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MmRiskLevel mmRiskLevel) {
		mmRiskLevelService.save(mmRiskLevel);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mmRiskLevel
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MmRiskLevel mmRiskLevel) {
		mmRiskLevelService.updateById(mmRiskLevel);
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
		mmRiskLevelService.removeById(id);
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
		this.mmRiskLevelService.removeByIds(Arrays.asList(ids.split(",")));
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
		MmRiskLevel mmRiskLevel = mmRiskLevelService.getById(id);
		if(mmRiskLevel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mmRiskLevel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mmRiskLevel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmRiskLevel mmRiskLevel) {
        return super.exportXls(request, mmRiskLevel, MmRiskLevel.class, "安全风险四色分布图");
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
        return super.importExcel(request, response, MmRiskLevel.class);
    }

}
