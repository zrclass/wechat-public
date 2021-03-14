package org.zrclass.wechat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zrclass.wechat.web.service.AccessTokenService;

import java.io.IOException;

/**
 * @auther: zhourui
 * @blame: zhourui
 * @date: 2020-10-11 10:56
 * @Description:
 */
@RestController
@RequestMapping("/accessToken")
public class AccessTokenController {
    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping
    public String getAccessToken() throws IOException {
        return accessTokenService.getAccessToken();
    }
}
