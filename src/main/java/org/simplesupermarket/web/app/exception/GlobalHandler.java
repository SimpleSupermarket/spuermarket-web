/**  
* <p>Class: com.kuliao.notice.controller.GlobalException</p> 
* <p>Copyright: Copyright (c) 2018</p>
* <p>Company: www.kuliao.com</p>
*/ 
package org.simplesupermarket.web.app.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**  
* Description: 全局异常

*/
@ControllerAdvice
@ResponseBody
@Order(1)
public class GlobalHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalHandler.class);

	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(IllegalArgumentException.class)
    public RetEntity processException(IllegalArgumentException e){
		//打印异常堆栈
		log.error(e.getMessage(), e);
		
		return new RetEntity(RetCode.ILLEGAL_ARGUMENT.getRetCode(), e.getMessage());
    }

	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public RetEntity processException(BusinessException e){
		//打印异常堆栈
		String fmtStr = String.format("%s-%s", e.getErrCode(), e.getMessage());
		log.error(fmtStr, e);
		
		return new RetEntity(e.getErrCode(), e.getErrMsg());
    }
	
	/**
	 * 捕获所有异常
	 * @param e
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public RetEntity processException(Exception e){
		//打印异常堆栈
		log.error(e.getMessage(), e);

		//Just For Dev
		return new RetEntity(RetCode.DEFAULT_ERR.getRetCode(), String.format("系统异常:%s-%s", e.getClass().getName(), e.getMessage()));
		//return new RetEntity(RetCode.DEFAULT_ERR.getRetCode(), "系统内部异常");
    }
}
