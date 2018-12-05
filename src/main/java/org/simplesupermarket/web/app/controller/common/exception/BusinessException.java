/**  
* <p>Class: com.kuliao.notice.exception.BusinessException</p> 
* <p>Copyright: Copyright (c) 2018</p>
* <p>Company: www.kuliao.com</p>
*/ 
package org.simplesupermarket.web.app.controller.common.exception;

/**  
* Description: 
* @author Zhang.Yong
* @date 2018年5月17日
* @version 1.0 
*/
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = -2871707180084083077L;

	/**
	 * 错误码
	 */
	private String errCode;
	
	/**
	 * 错误信息
	 */
	private String errMsg;
	
	public BusinessException(String errCode, String errMsg) {
	    this.errCode = errCode;
	    this.errMsg = errMsg;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s %s", this.errCode, this.errMsg);
	}
}
