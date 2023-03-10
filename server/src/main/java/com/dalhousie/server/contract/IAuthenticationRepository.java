package com.dalhousie.server.contract;

import com.dalhousie.server.model.Authentication;

public interface IAuthenticationRepository extends ICrudRepository<Authentication, Integer> {
    public Authentication getOTPByUserId(Integer userId);
    public int deleteAllOTPByuserId(Integer userId);
}
