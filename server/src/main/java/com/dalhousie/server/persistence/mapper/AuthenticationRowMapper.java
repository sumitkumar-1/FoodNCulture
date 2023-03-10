package com.dalhousie.server.persistence.mapper;

import com.dalhousie.server.model.Authentication;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationRowMapper implements RowMapper<Authentication> {
    
    @Override
    @Nullable
    public Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
        Authentication auth = new Authentication();
        auth.setId(rs.getInt("id"));
        auth.setUserId(rs.getInt("user_id"));
        auth.setOtp(rs.getString("otp"));
        auth.setExpired(rs.getBoolean("expired"));
        auth.setCreatedAt(rs.getString("created_at"));
        return auth;
    }

}
