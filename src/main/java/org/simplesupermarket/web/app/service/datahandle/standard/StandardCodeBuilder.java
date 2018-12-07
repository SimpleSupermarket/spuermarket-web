package org.simplesupermarket.web.app.service.datahandle.standard;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.simplesupermarket.web.app.service.datahandle.CodeBuilder;
import org.simplesupermarket.web.auth.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月07日
 */
public class StandardCodeBuilder implements CodeBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(StandardCodeBuilder.class);

    private String username = null;
    private UserDetail user = null;
    private String name = null;
    private int length = 20;
    private static final SimpleDateFormat format =new SimpleDateFormat("YYMDHmssSSS");
    static {
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区

    }
    @Override
    public CodeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CodeBuilder setUser(UserDetail user) {
        if (user == null) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("设置user为空");
            }
            LOGGER.info("{}",user.getName());
        } else if (user.getUsername() != null && !user.getName().isEmpty()) {
            this.username = user.getName();
        }
        this.user = user;
        return this;
    }

    @Override
    public CodeBuilder setLength(Integer length) {
        if (length == null || length < 2) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("设置设置长度不合理,length:{}", length);
            }
            length = 20;
        }
        this.length = length;
        return this;
    }


    /**
     * name 0-4 汉字转拼音
     * username  0-10 汉字转拼音
     * userRole  long数字
     * 当前时间 YYHHmmssSSSS
     * 不足的UUID倒截取补齐
     *
     * */
    @Override
    public String build() {
        StringBuilder code = new StringBuilder(length);
        if (name != null && !name.isEmpty()) {
            char[] n = name.toCharArray();
            for (int i = 0; i < n.length && i < 4; i++) {
                String[] item = PinyinHelper.toHanyuPinyinStringArray(n[i]);
                if (item == null || item.length == 0) continue;
                code.append(item[0].charAt(0));
            }
        }
        if (username != null) {
            char[] n = username.toCharArray();
            for (int i = 0; i < n.length && i < 10; i++) {
                String[] item = PinyinHelper.toHanyuPinyinStringArray(n[i]);
                if (item == null || item.length == 0) continue;
                code.append(item[0].charAt(0));
            }
        }
        if (user.getRoleId() != null) {
            code.append(user.getRoleId());
        }
        code.append(format.format(new Date()));

        if (code.length() < length) {
            code.append(getUUID(length-code.length()));
        } else {
            code.substring(0, length);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("code.length:{},code:{}", code.length(), code.toString());
        }
        return code.toString();
    }

    public static String getUUID(Integer size) {
        UUID id = UUID.randomUUID();
        String uuid = id.toString().replace("-", "");
        return uuid.substring(uuid.length()-size,uuid.length());
    }
}
