package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;

import com.entity.KanfangxinxiEntity;
import com.entity.view.KanfangxinxiView;

import com.service.KanfangxinxiService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 看房信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@RestController
@RequestMapping("/kanfangxinxi")
public class KanfangxinxiController {
    @Autowired
    private KanfangxinxiService kanfangxinxiService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, KanfangxinxiEntity kanfangxinxi,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangshijianstart,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangshijianend,
                         HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			kanfangxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("fangchanjingli")) {
			kanfangxinxi.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KanfangxinxiEntity> ew = new EntityWrapper<KanfangxinxiEntity>();
                if(kanfangshijianstart!=null) ew.ge("kanfangshijian", kanfangshijianstart);
                if(kanfangshijianend!=null) ew.le("kanfangshijian", kanfangshijianend);

		PageUtils page = kanfangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kanfangxinxi), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, KanfangxinxiEntity kanfangxinxi,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangshijianstart,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangshijianend,
                         HttpServletRequest request){
        EntityWrapper<KanfangxinxiEntity> ew = new EntityWrapper<KanfangxinxiEntity>();
                if(kanfangshijianstart!=null) ew.ge("kanfangshijian", kanfangshijianstart);
                if(kanfangshijianend!=null) ew.le("kanfangshijian", kanfangshijianend);

		PageUtils page = kanfangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kanfangxinxi), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(KanfangxinxiEntity kanfangxinxi){
       	EntityWrapper<KanfangxinxiEntity> ew = new EntityWrapper<KanfangxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kanfangxinxi, "kanfangxinxi")); 
        return Response.ok().put("data", kanfangxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(KanfangxinxiEntity kanfangxinxi){
        EntityWrapper< KanfangxinxiEntity> ew = new EntityWrapper< KanfangxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kanfangxinxi, "kanfangxinxi")); 
		KanfangxinxiView kanfangxinxiView =  kanfangxinxiService.selectView(ew);
		return Response.ok("查询看房信息成功").put("data", kanfangxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        KanfangxinxiEntity kanfangxinxi = kanfangxinxiService.selectById(id);
        return Response.ok().put("data", kanfangxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        KanfangxinxiEntity kanfangxinxi = kanfangxinxiService.selectById(id);
        return Response.ok().put("data", kanfangxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody KanfangxinxiEntity kanfangxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kanfangxinxi);
        kanfangxinxiService.insert(kanfangxinxi);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody KanfangxinxiEntity kanfangxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kanfangxinxi);
        kanfangxinxiService.insert(kanfangxinxi);
        return Response.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Response update(@RequestBody KanfangxinxiEntity kanfangxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kanfangxinxi);
        kanfangxinxiService.updateById(kanfangxinxi);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        kanfangxinxiService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	










}
