/**  
* <p>Class: com.kuliao.notice.domain.SysEnum</p> 
* <p>Copyright: Copyright (c) 2018</p>
* <p>Company: www.kuliao.com</p>
*/ 
package org.simplesupermarket.web.app.exception;

/**  
* Description: 错误码枚举类

*/
public enum RetCode {
	/**
	 * 成功
	 */
	SUCCESS("000000"),
	/**
	 * 默认数据库错误
	 */
	DB_NOT_FOUND("800404"),
	/**
	 * 默认数据库错误
	 */
	DEFAULT_DB_ERR("888888"),
	/**
	 * 非法参数
	 */
	ILLEGAL_ARGUMENT("900001"),
	/**
	 * 默认错误
	 */
	DEFAULT_ERR("999999");
	
	/**
	 * 错误码
	 */
	private String retCode;
	
	private RetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * @return the retCode
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * 重载Object的toString,返回变量名和其errorCode
	 */
	@Override
	public String toString() {
		return String.format("%s:%s", super.name(), this.retCode);
	}

}
