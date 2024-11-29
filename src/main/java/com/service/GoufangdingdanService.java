package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GoufangdingdanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GoufangdingdanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GoufangdingdanView;


/**
 * 购房订单
 *
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
public interface GoufangdingdanService extends IService<GoufangdingdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GoufangdingdanVO> selectListVO(Wrapper<GoufangdingdanEntity> wrapper);
   	
   	GoufangdingdanVO selectVO(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);
   	
   	List<GoufangdingdanView> selectListView(Wrapper<GoufangdingdanEntity> wrapper);
   	
   	GoufangdingdanView selectView(@Param("ew") Wrapper<GoufangdingdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GoufangdingdanEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<GoufangdingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<GoufangdingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<GoufangdingdanEntity> wrapper);



}

