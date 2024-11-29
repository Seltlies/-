package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import java.util.List;
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

import com.entity.YuyuekanfangEntity;
import com.entity.view.YuyuekanfangView;

import com.service.YuyuekanfangService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 预约看房
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@RestController
@RequestMapping("/yuyuekanfang")
public class YuyuekanfangController {
    @Autowired
    private YuyuekanfangService yuyuekanfangService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, YuyuekanfangEntity yuyuekanfang,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangriqistart,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangriqiend,
                         HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yuyuekanfang.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("fangchanjingli")) {
			yuyuekanfang.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YuyuekanfangEntity> ew = new EntityWrapper<YuyuekanfangEntity>();
                if(kanfangriqistart!=null) ew.ge("kanfangriqi", kanfangriqistart);
                if(kanfangriqiend!=null) ew.le("kanfangriqi", kanfangriqiend);

		PageUtils page = yuyuekanfangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuekanfang), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, YuyuekanfangEntity yuyuekanfang,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangriqistart,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date kanfangriqiend,
                         HttpServletRequest request){
        EntityWrapper<YuyuekanfangEntity> ew = new EntityWrapper<YuyuekanfangEntity>();
                if(kanfangriqistart!=null) ew.ge("kanfangriqi", kanfangriqistart);
                if(kanfangriqiend!=null) ew.le("kanfangriqi", kanfangriqiend);

		PageUtils page = yuyuekanfangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuekanfang), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(YuyuekanfangEntity yuyuekanfang){
       	EntityWrapper<YuyuekanfangEntity> ew = new EntityWrapper<YuyuekanfangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuekanfang, "yuyuekanfang")); 
        return Response.ok().put("data", yuyuekanfangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(YuyuekanfangEntity yuyuekanfang){
        EntityWrapper< YuyuekanfangEntity> ew = new EntityWrapper< YuyuekanfangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuekanfang, "yuyuekanfang")); 
		YuyuekanfangView yuyuekanfangView =  yuyuekanfangService.selectView(ew);
		return Response.ok("查询预约看房成功").put("data", yuyuekanfangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        YuyuekanfangEntity yuyuekanfang = yuyuekanfangService.selectById(id);
        return Response.ok().put("data", yuyuekanfang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        YuyuekanfangEntity yuyuekanfang = yuyuekanfangService.selectById(id);
        return Response.ok().put("data", yuyuekanfang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody YuyuekanfangEntity yuyuekanfang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuekanfang);
        yuyuekanfangService.insert(yuyuekanfang);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody YuyuekanfangEntity yuyuekanfang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yuyuekanfang);
        yuyuekanfangService.insert(yuyuekanfang);
        return Response.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Response update(@RequestBody YuyuekanfangEntity yuyuekanfang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuekanfang);
        yuyuekanfangService.updateById(yuyuekanfang);//全部更新
        return Response.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public Response update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YuyuekanfangEntity> list = new ArrayList<YuyuekanfangEntity>();
        for(Long id : ids) {
            YuyuekanfangEntity yuyuekanfang = yuyuekanfangService.selectById(id);
            yuyuekanfang.setSfsh(sfsh);
            yuyuekanfang.setShhf(shhf);
            list.add(yuyuekanfang);
        }
        yuyuekanfangService.updateBatchById(list);
        return Response.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        yuyuekanfangService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	










}
