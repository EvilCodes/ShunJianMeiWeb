package com.wenyuankeji.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.service.IMyTaskService;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.util.BaseException;  
 /**
  * 系统定时任务 每天00:10:00执行一次
  * 1、订单成功后7天之后做分成处理（更新订单状态，分别添加美发店和美发师的钱包，添加2条流水记录）
  * 
  */
@Component  
public class FenChengTask {  
	
	@Autowired
	private IOrderinfoService orderinfoService;
	
	@Value("#{configProperties['allocationtime']}")
	private String allocationtime;   	//分成时间（默认7天）
	
	@Value("#{configProperties['allocationstate']}")
	private String allocationstate;   	//分成类型
	
	@Autowired
	private IMyTaskService myTaskSerivce;
	
    @Scheduled(cron="0 10 0 * * ?") //每天00:10:00执行  
    public void taskCycle(){
		String sdate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(new Date());
    	try {
    		if (allocationstate.equals("0")) {//执行定时器
    			allocationstate = "1";
    			
    			//2、订单成功后，7天之后做分成处理，更新订单状态，分别添加美发店和美发师的钱包，添加2条流水记录
    			List<OrderinfoModel>  allocationtimeList = orderinfoService.searchOrderInfoByAllocationtime(allocationtime);
    			if (null != allocationtimeList && allocationtimeList.size() > 0) {
    				for (OrderinfoModel obj: allocationtimeList) {
    					//修改之前查询流水是否存在，不存在执行分成
    					if (!myTaskSerivce.searchUserTradelogByOrderId(obj.getOrderid(), 3)) {
    						//执行分成逻辑
    						myTaskSerivce.updateAllocationSet(obj);
    					}
    				}
    			}
    			
			}
    		
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//任务执行完，状态修改为0
		allocationstate = "0";

    }  
    
}