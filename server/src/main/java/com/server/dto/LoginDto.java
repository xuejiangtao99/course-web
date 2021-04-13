package com.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto{

    private String id;

    private String loginName;


    private String name;

    /**
     * 登录凭证
     */
    private String token;

}
