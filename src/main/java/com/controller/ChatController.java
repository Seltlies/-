package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;

import com.entity.ChatEntity;
import com.entity.view.ChatView;

import com.service.ChatService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 在线咨询
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, ChatEntity chat,
                         HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            chat.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();

		PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, ChatEntity chat,
                         HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		chat.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();

		PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(ChatEntity chat){
       	EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chat, "chat")); 
        return Response.ok().put("data", chatService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(ChatEntity chat){
        EntityWrapper< ChatEntity> ew = new EntityWrapper< ChatEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chat, "chat")); 
		ChatView chatView =  chatService.selectView(ew);
		return Response.ok("查询在线咨询成功").put("data", chatView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        ChatEntity chat = chatService.selectById(id);
        return Response.ok().put("data", chat);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        ChatEntity chat = chatService.selectById(id);
        return Response.ok().put("data", chat);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody ChatEntity chat, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chat);
    	if(StringUtils.isNotBlank(chat.getAsk())) {
			chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userid", request.getSession().getAttribute("userId")));
    		chat.setUserid((Long)request.getSession().getAttribute("userId"));
    		chat.setIsreply(1);
    	}
    	if(StringUtils.isNotBlank(chat.getReply())) {
    		chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userid", chat.getUserid()));
    		chat.setAdminid((Long)request.getSession().getAttribute("userId"));
    	}
        chatService.insert(chat);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody ChatEntity chat, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chat);
    	chat.setUserid((Long)request.getSession().getAttribute("userId"));
    	if(StringUtils.isNotBlank(chat.getAsk())) {
			chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userid", request.getSession().getAttribute("userId")));
    		chat.setUserid((Long)request.getSession().getAttribute("userId"));
    		chat.setIsreply(1);
    	}
    	if(StringUtils.isNotBlank(chat.getReply())) {
    		chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userid", chat.getUserid()));
    		chat.setAdminid((Long)request.getSession().getAttribute("userId"));
    	}
        chatService.insert(chat);
        return Response.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public Response security(@RequestParam String username){
        ChatEntity chat = chatService.selectOne(new EntityWrapper<ChatEntity>().eq("", username));
        return Response.ok().put("data", chat);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public Response update(@RequestBody ChatEntity chat, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chat);
        chatService.updateById(chat);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        chatService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public Response autoSort(@RequestParam Map<String, Object> params, ChatEntity chat, HttpServletRequest request, String pre){
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));
        return Response.ok().put("data", page);
    }










}
