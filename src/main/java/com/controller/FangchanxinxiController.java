package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
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

import com.entity.FangchanxinxiEntity;
import com.entity.view.FangchanxinxiView;

import com.service.FangchanxinxiService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;
import com.service.StoreupService;

/**
 * 房产信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
@RestController
@RequestMapping("/fangchanxinxi")
public class FangchanxinxiController {
    @Autowired
    private FangchanxinxiService fangchanxinxiService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, FangchanxinxiEntity fangchanxinxi,
                         HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("fangchanjingli")) {
			fangchanxinxi.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<FangchanxinxiEntity> ew = new EntityWrapper<FangchanxinxiEntity>();

		PageUtils page = fangchanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangchanxinxi), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, FangchanxinxiEntity fangchanxinxi,
                         HttpServletRequest request){
        EntityWrapper<FangchanxinxiEntity> ew = new EntityWrapper<FangchanxinxiEntity>();

		PageUtils page = fangchanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangchanxinxi), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(FangchanxinxiEntity fangchanxinxi){
       	EntityWrapper<FangchanxinxiEntity> ew = new EntityWrapper<FangchanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fangchanxinxi, "fangchanxinxi")); 
        return Response.ok().put("data", fangchanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(FangchanxinxiEntity fangchanxinxi){
        EntityWrapper< FangchanxinxiEntity> ew = new EntityWrapper< FangchanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fangchanxinxi, "fangchanxinxi")); 
		FangchanxinxiView fangchanxinxiView =  fangchanxinxiService.selectView(ew);
		return Response.ok("查询房产信息成功").put("data", fangchanxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        FangchanxinxiEntity fangchanxinxi = fangchanxinxiService.selectById(id);
        return Response.ok().put("data", fangchanxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        FangchanxinxiEntity fangchanxinxi = fangchanxinxiService.selectById(id);
        return Response.ok().put("data", fangchanxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody FangchanxinxiEntity fangchanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(fangchanxinxi);
        fangchanxinxiService.insert(fangchanxinxi);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody FangchanxinxiEntity fangchanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(fangchanxinxi);
        fangchanxinxiService.insert(fangchanxinxi);
        return Response.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Response update(@RequestBody FangchanxinxiEntity fangchanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangchanxinxi);
        fangchanxinxiService.updateById(fangchanxinxi);//全部更新
        return Response.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public Response update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<FangchanxinxiEntity> list = new ArrayList<FangchanxinxiEntity>();
        for(Long id : ids) {
            FangchanxinxiEntity fangchanxinxi = fangchanxinxiService.selectById(id);
            fangchanxinxi.setSfsh(sfsh);
            fangchanxinxi.setShhf(shhf);
            list.add(fangchanxinxi);
        }
        fangchanxinxiService.updateBatchById(list);
        return Response.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        fangchanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	










}
