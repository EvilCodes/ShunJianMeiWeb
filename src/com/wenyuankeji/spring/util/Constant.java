package com.wenyuankeji.spring.util;

import java.util.HashMap;
import java.util.Map;



public class Constant {
	
	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String MSG_0 = "0|操作成功";
	public static final String MSG_1 = "1|操作成功";
	public static final String MSG_2 = "0|操作失败";
	public static final String MSG_3 = "1|支付成功";
	public static final String MSG_4 = "1|支付失败";
	public static final String MSG_0_0 = "0|";
	public static final String MSG_1_1 = "1|";
	public static final String DATA = "data";
	public static final String TOTAL = "total";
	public static final String MSG_ERROR = "1|非法请求";
	public static final String MSG_AMOUNT = "1|钱包余额不足";
	public static final String ORDER_ERROR = "1|订单不存在";
	
	public static final int CODE_1000 = 1000;
	public static final int CODE_1100 = 1100;
	public static final int CODE_1101 = 1101;
	public static final int CODE_1102 = 1102;
	public static final int CODE_1103 = 1103;
	public static final int CODE_1104 = 1104;
	public static final int CODE_1105 = 1105;
	public static final int CODE_1200 = 1200;
	public static final int CODE_2000 = 2000;
	public static final int CODE_2100 = 2100;
	public static final String CONFIGCODE_01 = "hobby";
	public static final String CONFIGCODE_02 = "hairstyle";
	public static final String CONFIGCODE_03 = "city";
	public static final String CONFIGCODE_04 = "homepage";
	public static final String CONFIGCODE_05 = "cashfund";
	public static final String CONFIGCODE_06 = "storecashfund";
	
	public static final String CONFIGCODE_07 = "ShareCouponMin";
	public static final String CONFIGCODE_08 = "ShareCouponMax";
	
	public static final String CONFIGCODE_COUPON_PAY_CUSTOM = "coupon_pay_custom";//用户优惠金额
	public static final String CONFIGCODE_COUPON_PAY_STORE = "coupon_pay_store";//商家优惠的金额
	public static final String CONFIGCODE_COUPON_PAY_CUS_DESC = "coupon_pay_cus_desc";//优惠策略描述
	public static final String CONFIGCODE_ENOUGH_MONEY = "enough_money";//优惠需要满足的金额
	public static final int PAYSTE_SERVICE_FINISH_06 = 6; //6是订单服务完成
	public static final int PAYSTE_EVALUATE_FINISH_07 = 7;//7是评价完成
	public static final int PAYSTE_ORDER_FINISH_08 = 8;//8是订单完成
	public static final int PAYSTE_CANCELL_09 = 9;//9是取消订单的编号
	public static final int IMAGE_TYPE_07 = 7;//我的形象 
	public static final int IMAGE_TYPE_06 = 6;//我的代表作品 
	public static final int IMAGE_TYPE_01 = 1;//我的头像
	
	
	public static final int SERVICE_START_TIME = 10;//美发师开始服务时间
	public static final int SERVICE_END_TIME = 21;//美发师结束服务时间
	public static final int SERVICE_END_HOUR = 24;	// 每日工作的结束时间，不超过24点
	
	public static final int WALLET_OWNERTYPE_USER = 1;//钱包所有者的类型为顾客
	public static final int WALLET_OWNERTYPE_STORE = 3;//钱包所有者的类型为商店
	public static final int PAYTYPE_WALLET = 3;//支付类型为钱包支付
	public static final int PAYTYPE_ALIPAY = 2;//支付类型为支付宝支付
	public static final int PAYTYPE_WECHAT = 1;//支付类型为微信支付
	public static final int LOGTYPE_CUSTOM_PAY = 1;//交易日志中交易类型为顾客支付
	public static final int LOGTYPE_STORE_SHARE = 3;//交易日志中交易类型为美发店分成
	public static final int MAX_PIC = 3;//个人形象图片最大数量
	
	public static final int FUTURE_SEVEN_DAY = 7;//未来七天
	public static final int STORE_ISTYPE_ZYINGD = 0; //店铺类型：自营店
	public static final int STORE_ISTYPE_ZYOUD = 1; //店铺类型：自由店
	// add by jiazhaohui
	public static final int NORMAL_PAYSTATE_SUCCESS = 0;	// 普通支付
	public static final int QUICKPAY_PAYSTATE_SUCCESS = 1;//闪惠支付状态 成功
	// add by jiazhaohui
	// 最高星级：五星
	public static final int MAX_EVAL_STAR = 5;
	
	// add by jiazhaohui
	public static final int ORDER_PAY = 0;		// 正常的订单支付
	public static final int QUICK_PAY = 1;		// 快捷支付
	
	
	public static final Map<String, Boolean> CHECK_SESSION_MAP = new HashMap<String, Boolean>(){

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put("goUsersubinfo_verification",true);
			//put("doSaveUserSubInfo",true);
			//put("doSaveUserInfo",true);
			put("goStoreinfoValidateInfo",true);
			//put("StoreinfoValidateInfo",true);
			put("goRiZhiGuanLi",true);
			put("goOrderinfoDetail",true);
			put("goOrderinfoManage",true);
			put("goStoreinfoWodeyue",true);
			put("goStoreinfoTixian",true);
		}
	};
	
	
	public static final Map<Integer, String> NEXT_STEP_MAP = new HashMap<Integer, String>(){

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put(1, "已预约");
			put(3, "美发师到店签到");
			put(4, "开始服务");
			put(5, "结束服务");
			put(6, "评价完成");
			put(7, "订单完成");
			put(8, "");
			put(9, "");
			put(10, "");
			
		}
	};
	
	public static final Map<Integer, String> PAYSTATE_MAP = new HashMap<Integer, String>(){

		private static final long serialVersionUID = 2315647126169419915L;
		{
			put(1, "待支付");
			put(3, "已预约");
			put(4, "美发师已签到");
			put(5, "服务中");
			put(6, "待评价");
			put(7, "评价完成");
			put(8, "订单完成");
			put(9, "已取消");
			put(10, "异常处理");
		}
	};
	
	public static final Map<String, String> METHOD_MAP = new HashMap<String, String>(){
		private static final long serialVersionUID = 2315647126169419915L;
		{
		    put("UserLogin", "1.0");
		    put("CommonSendVCode","1.0");
		    put("FindPassWord", "1.0");
		    put("SaveUserPhoto", "1.0");
		    put("UserRegister", "1.0");
		    put("SaveUserInfo","1.0");
		    put("Feedback","1.0");
		    put("ChangePassWord","1.0");
		    put("SaveFavorite","1.0");
		    put("HairdresserList","1.0");
		    put("OpenCityList","1.0");
		    put("CommitOrder","1.0");
		    put("OrderList","1.0");
		    put("UpdateOrderState","1.0");
		    put("SaveEvaluation","1.0");
		    put("BusinessArea","1.0");
		    put("StoreList","1.0");
		    put("ReservationStore","1.0");
		    put("OrderDetail","1.0");
		    put("HomePage","1.0");
		    put("HairStyle","1.0");
		    put("HairStyleList","1.0");
		    put("HairStyleDetail","1.0");
		    put("HairdresserDetail","1.0");
		    put("StoreDetail","1.0");
		    put("CanUseCoupons","1.0");
		    put("CashRecord","1.0");
		    put("AdditionalOrder","1.0");
		    put("HairdresserSchedule","1.0");
		    put("ReservationService","1.0");
		    put("ReservationStore","1.0");
		    put("ReservationMaterial","1.0");
		    put("UpdateOrderCoupon","1.0");
		    put("WalletPay","1.0");
		    put("WechatPay","1.0");
		    
		    
		    
		   //美发师端接口定义 
		   put("H_UserLogin","1.0");
		   put("H_HomePage","1.0");
		   put("H_HairStyleList","1.0");
		   put("H_HairStyleDetail","1.0");
		   put("H_SaveHairStyle","1.0");
		   put("UploadImage","1.0");
		   put("H_StoreList","1.0");
		   put("H_StoreDetail","1.0");
		   put("H_MaterialInfo","1.0");
		   put("H_SaveMaterialDelivery","1.0");
		   put("H_MaterialApply","1.0");
		   put("H_MaterialRecord","1.0");
		   put("H_UpdateRecordState","1.0");
		   put("H_ChangePassWord","1.0");
		   put("H_Feedback","1.0");
		   put("H_UpdateOrderState","1.0");
		   put("H_CashRecord","1.0");
		   put("H_MyBalance","1.0");
		   put("H_OrderList","1.0");
		   put("H_OrderDetail","1.0");
		   put("H_TradeRecord","1.0");
		   put("H_BusinessAreaStoreList","1.0");
		   put("H_HairdresserSchedule","1.0");
		   put("H_SaveHairdresserSchedule","1.0");
		   put("H_ScheduleOrderList","1.0");
		   put("H_GetMasterDefaultSetting","1.0");
		   put("H_SetMasterDefaultSetting","1.0");
		   put("H_QuickSetting","1.0");
		   put("H_GetDayWorking","1.0");
		   put("H_DeleteSelfWorking","1.0");
		   put("H_SaveSelfWorking","1.0");
		   put("H_GetSelfWorking","1.0");
		   put("H_SetInformation","1.0");
		   put("H_FinishOrderList","1.0");
		   put("H_GetInformation","1.0");
		   //用户端接口定义
		   put("FavoriteHairStyleList","1.0");
		   put("FavoriteHairdresserList","1.0");
		   put("FavoriteStoreList","1.0");
		   put("Mine","1.0");
		   put("CouponList","1.0");
		   put("MyBalance","1.0");
		   put("CashRecord","1.0");
		   put("TradeRecord","1.0");
		   put("GetCustomerServicePhone","1.0");
		   
		   
		   put("ReservationServiceAgain","1.0");
		   put("ReservationMaterialAgain","1.0");
		   put("OrderPay","1.0");
		   put("EvaluationList","1.0");
		   put("StoreEvaluationList","1.0");
		   put("InitEvaluation","1.0");

		   put("SaveShareRecord","1.0");
		   put("CheckVersion","1.0");
		   put("StartQuickPay","1.0");
		   put("QuickpayWithWallet","1.0");
		   put("QuickpayWithAlipay","1.0");
		   put("QuickpayWithWechat","1.0");
		   ///////////////////////////
		   put("HairdresserList2","2.0");
		   put("StoreList2","2.0");
		   put("GetAllusersubinfoPackedBysubid","2.0");
		   put("H_StoreList2","2.0");
		   put("HairStyleList2", "2.0");
	    }
	};
	
	
	public static final String ALL_PERMISSION = "ALL";
	
	public static final int INDEXPAGESIZE = 9;// 每页显示几条数据
	
	// add by jiazhaohui
	public static final int MAX_MASTER_NEXT_WORKING_DAYS = 14;	// 美发师可以设置接下来多少天的工作记录
	
	// 座位占用情况的枚举
	public static final int HOUR_OUTOFTIME_STATE = -1;	// 该时间已经过期 暂时改为休息-1
	public static final int HOUR_REST_STATE = -1;		// 休息状态
	public static final int HOUR_WORK_STATE = 0;		// 工作可约状态
	public static final int HOUR_LOCK_STATE = 1;		// 锁定状态
	public static final int HOUR_ORDER_STATE = 2;		// 已经被约状态
	
	// 某一个时间点的工作日程是被平台预约占用还是自编辑日程占用
	public static final int HOUR_ORDER_BY_PLATFORM = 0;	// 时间被平台订单占用
	public static final int HOUR_ORDER_BY_EDIT_WORK = 1;	// 时间被自编辑订单占用
	
	public static final int DB_INSERT_ERROR = -1;	// DB插入失败返回的ID
	
	public static final int ORDER_BY_LEVEL = 1;		//根据美发师职称等级排序
	public static final int ORDER_BY_ORDERNUM = 2;		//根据订单数量排序
	public static final int ORDER_BY_SCORE = 3;		//根据评分排序
	public static final int SELECT_MAN = 4;		//查询性别为男
	public static final int SELECT_WOMAN = 5;		//查询性别为女
	
	public static final int UPDATE_ERROR = -1;		// 一个更新失败之后返回的错误值
	
	// 微信支付相关
	public static final String appid = "wxe99c393389decb57";//appid
    public static final String mch_id = "1279859301";      	//微信支付商户号
    public static final String key = "7ce9b9a25197308edf37e3248368046d";//API密钥
}