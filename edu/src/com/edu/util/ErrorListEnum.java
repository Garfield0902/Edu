package com.edu.util;

public enum ErrorListEnum {
	
    E200(200,"成功!"),
	E20001(20001,"系统未注册，不允许访问!"),
	E20002(20002,"服务访问的参数存在被篡改的情况，不允许访问!"),
	E30001(30001,"请求参数错误,必传的参数未传递!"),
	E20003(20003,"验证码失效!"),
	E20004(20004,"验证码错误!"),
	E20005(20005,"密码错误!"),
	E20006(20006,"信息不匹配!"),
	E20007(20007,"状态已变更!"),
	E20011(20011,"需要验证!"),
	E50001(50001,"服务请求报错!"),
	E50002(50002,"发送短信失败!"),
	E60001(60001,"存在相同数据!"),
	E20010(20010,"删除失败!"),
	E30002(30002,"添加异常!"),
	E30003(30003,"查询异常!"),
	E20008(20008,"账号未被审核!"),
	E20009(20009,"验证不通过!")
	;
	
	ErrorListEnum(int key, String value){
		this.key = key;
		this.value =  value;
	}
	
	private final int key;
	
	private final String value;

	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	// 普通方法
    public static String getValue(int key) {
        for (ErrorListEnum e : ErrorListEnum.values()) {
            if (e.getKey() == key) {
                return e.value;
            }
        }
        return null;
    }

	public static void main(String[] args) {
		System.out.println(ErrorListEnum.E20001.getKey() + ErrorListEnum.E20001.getValue());
		System.out.println(ErrorListEnum.getValue(ErrorListEnum.E20001.getKey()));
	}	
	
}

