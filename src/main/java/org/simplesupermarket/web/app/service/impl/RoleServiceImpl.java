package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.UserView;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.db.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 董文强
 * @Time 2018/12/9 1:40
 */
@Service
public class RoleServiceImpl extends AbstractSuperServiceImpl<Role> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    @ViewClass(Role.class)
    public List getDbData(Map<String, String> sd) {
        return super.mapper.selectAll();
    }
}
