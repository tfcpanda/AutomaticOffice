package cn.edu.hzvtc.action;

import java.io.ByteArrayInputStream;

import cn.edu.hzvtc.util.ValidationCodeUtil;

public class ValidationCodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputStream;
	@SuppressWarnings("unused")
	private String code;
	
	public void setCode(String code){
		this.code=code;
	}
	//生成验证码方法
	public String execute() throws Exception{
		ValidationCodeUtil vcUtil = ValidationCodeUtil.Instance();
		this.setInputStream(vcUtil.getImage());//取得带有随机字符串的图片
		this.getSession().put("validationCode", vcUtil.getRnds());//将随机码设置在Session中
		return SUCCESS;
	}
	
	public void setInputStream(ByteArrayInputStream inputStream){
		this.inputStream=inputStream;
	}
	public ByteArrayInputStream getInputStream(){
		return inputStream;
	}
}
