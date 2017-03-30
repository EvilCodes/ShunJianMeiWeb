package com.wenyuankeji.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.util.BaseException;

/**
 * 系统定时任务 2分钟执行一次
 * 1、查询订单超过30分钟，做自动取消订单状态功能（修改订单状态，释放美发师和美发店工时）
 * 
 */
@Component
public class MyTask {

	@Autowired
	private IOrderinfoService orderinfoService;

	@Value("#{configProperties['ordertime']}")
	private String ordertime;		// 订单取消时间（默认30分钟）
	
	@Value("#{configProperties['orderstate']}")
	private String orderstate;   	//分成类型

	@Scheduled(cron = "0 0/2 * * * ? ")
	// 间隔2分钟执行
	public void taskCycle() {

		try {
			
    		if (orderstate.equals("0")) {//执行定时器
    			orderstate = "1";
				// 1、查询订单表，状态为待支付的超过30分钟的订单
				List<OrderinfoModel> ordertimeList = orderinfoService.searchOrderInfoWaitTime(ordertime);
				if (null != ordertimeList && ordertimeList.size() > 0) {
					for (OrderinfoModel objects : ordertimeList) {
						// 执行修改订单状态
						orderinfoService.updateOrderinfoState(objects.getOrderid(), 9);
					}
				}
    		}

		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//任务执行完，状态修改为0
		orderstate = "0";
		// System.out.println("2分钟执行一次订单取消--------" + orderstate);
	}

}