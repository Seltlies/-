package com.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;

import com.entity.FangwuleixingEntity;
import com.entity.view.FangwuleixingView;

import com.service.FangwuleixingService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 房屋类型
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
@RestController
@RequestMapping("/fangwuleixing")
public class FangwuleixingController {
    @Autowired
    private FangwuleixingService fangwuleixingService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, FangwuleixingEntity fangwuleixing,
                         HttpServletRequest request){
        EntityWrapper<FangwuleixingEntity> ew = new EntityWrapper<FangwuleixingEntity>();

		PageUtils page = fangwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangwuleixing), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, FangwuleixingEntity fangwuleixing,
                         HttpServletRequest request){
        EntityWrapper<FangwuleixingEntity> ew = new EntityWrapper<FangwuleixingEntity>();

		PageUtils page = fangwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangwuleixing), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(FangwuleixingEntity fangwuleixing){
       	EntityWrapper<FangwuleixingEntity> ew = new EntityWrapper<FangwuleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fangwuleixing, "fangwuleixing")); 
        return Response.ok().put("data", fangwuleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(FangwuleixingEntity fangwuleixing){
        EntityWrapper< FangwuleixingEntity> ew = new EntityWrapper< FangwuleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fangwuleixing, "fangwuleixing")); 
		FangwuleixingView fangwuleixingView =  fangwuleixingService.selectView(ew);
		return Response.ok("查询房屋类型成功").put("data", fangwuleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        FangwuleixingEntity fangwuleixing = fangwuleixingService.selectById(id);
        return Response.ok().put("data", fangwuleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        FangwuleixingEntity fangwuleixing = fangwuleixingService.selectById(id);
        return Response.ok().put("data", fangwuleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody FangwuleixingEntity fangwuleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(fangwuleixing);
        fangwuleixingService.insert(fangwuleixing);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody FangwuleixingEntity fangwuleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(fangwuleixing);
        fangwuleixingService.insert(fangwuleixing);
        return Response.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Response update(@RequestBody FangwuleixingEntity fangwuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangwuleixing);
        fangwuleixingService.updateById(fangwuleixing);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        fangwuleixingService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	










}
