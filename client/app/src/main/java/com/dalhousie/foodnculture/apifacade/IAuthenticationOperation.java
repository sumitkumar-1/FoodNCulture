package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Authentication;

public interface IAuthenticationOperation extends ICrudOperation<Authentication, Integer> {
    public Authentication getOTPByUserId(Integer userId);
}
