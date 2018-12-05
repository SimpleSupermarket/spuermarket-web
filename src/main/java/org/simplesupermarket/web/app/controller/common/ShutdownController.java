/**  
* <p>Class: com.kuliao.notice.controller.SysAppController</p> 
* <p>Copyright: Copyright (c) 2018</p>
* <p>Company: www.kuliao.com</p>
*/ 
package org.simplesupermarket.web.app.controller.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**  
* Description: Shutdown控制器
* @version 1.0 
*/
@RestController
@RequestMapping("/")
public class ShutdownController {

	private final static Logger log = LoggerFactory.getLogger(ShutdownController.class);
	
	
	/**
	 * 无法获取上下文返回
	 */
	private static final Map<String, String> NO_CONTEXT_MESSAGE = Collections.unmodifiableMap(Collections.singletonMap("message", "No context to shutdown."));
	
	/**
	 * 无权限返回
	 */
	private static final Map<String, String> NO_PERMISSION_MESSAGE = Collections.unmodifiableMap(Collections.singletonMap("message", "No Permission to shutdown."));

	/**
	 *停止成功 返回 
	 */
	private static final Map<String, String> SHUTDOWN_MESSAGE = Collections.unmodifiableMap(Collections.singletonMap("message", "Shutting down, bye..."));


	/**
	 * 停止失败返回
	 */
	private static final Map<String, String> UNABLE_SHUTDOWN_MESSAGE = Collections.unmodifiableMap(Collections.singletonMap("message", "Sorry, unable to shutting down"));


	/**
	 * 回环IP地址
	 */
	private final static String LOOP_BACK_ADDRESS = "127.0.0.1";
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	@Value("${shutdown.permission:true}")
	private boolean isPermission;
	
	/**
	 * 关闭应用
	 * @return
	 */
	@RequestMapping(value = {"/shutdown"}, method={RequestMethod.GET})
	public Map<String, String> shutdownSelf(HttpServletRequest request) {
		
		if(log.isWarnEnabled()) {
			log.warn("start to shutdown application");
		}
		
		if(null == this.context) {//上下文为空无法停机
			log.error("ConfigurableApplicationContext is null, unable shutdown");
			return NO_CONTEXT_MESSAGE;
		}
		
		
		String remoteIp =request.getRemoteAddr();
		
		if(log.isDebugEnabled()) {
			log.error(String.format("remote ipaddress [%s]",  remoteIp));
		}
		
		//无法获取IP则不允许停止
		if(StringUtils.isEmpty(remoteIp)){
			log.error(String.format("remote ipaddress [%s] is empty", remoteIp));
			return UNABLE_SHUTDOWN_MESSAGE;
		}
		
		
		boolean isAllow = false;
		
		if(isPermission) {//检查权限
			if(log.isTraceEnabled()) {
				log.trace("check the remote ipaddress is the local ipaddress");
			}
			log.info("check the remote ipaddress is the local ipaddress");

			
			//IP控制，暂时只允许本机停止
			if(remoteIp.equals(LOOP_BACK_ADDRESS)) {//若远程地址与回环地址相同
				isAllow = true; 
			}else {
				//与回环地址不等时，尝试与本地IP对比
				String localIp = this.getLocalIp();
				
				if(StringUtils.isEmpty(localIp)) {
					log.error(String.format("local ipaddress [%s], is empty", localIp));

					return UNABLE_SHUTDOWN_MESSAGE;
				}else {
					if(remoteIp.equals(localIp)) {
						isAllow = true; 
					}else {
						return NO_PERMISSION_MESSAGE;
					}
				}
			}
		}else {
			log.info("not check the remote ipaddress, shutdown immediate");

			if(log.isTraceEnabled()) {
				log.trace("not check the remote ipaddress, shutdown immediate");
			}
			
			isAllow = true;
		}
		


		if(isAllow) {//若允许则停止
			try {
				return SHUTDOWN_MESSAGE;
			}finally {
				Thread thread = new Thread(this::performShutdown);
				thread.setContextClassLoader(getClass().getClassLoader());
				thread.start();
			}
			
		}else {//否则返回无权限停止
			return NO_PERMISSION_MESSAGE;
		}
	}
	
	/**
	 * 获取本机IP地址若失败则返回空
	 * @return
	 */
	private String getLocalIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("获取本机IP地址失败", e);
			return "";
		}
	}

	private void performShutdown() {
		try {
			Thread.sleep(500L);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		this.context.close();
	}
}
