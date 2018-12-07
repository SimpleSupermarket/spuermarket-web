package org.simplesupermarket.web.app.service.datahandle;

import org.simplesupermarket.web.auth.UserDetail;

public interface DataHandle {
    <T> T handleData(T data, UserDetail user);
}
