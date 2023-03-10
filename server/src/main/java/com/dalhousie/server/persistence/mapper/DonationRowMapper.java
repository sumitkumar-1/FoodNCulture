package com.dalhousie.server.persistence.mapper;

import com.dalhousie.server.model.Donation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DonationRowMapper implements RowMapper<Donation> {
    
    @Override
    @Nullable
    public Donation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Donation donation = new Donation();
        donation.setId(rs.getInt("id"));
        donation.setEventId(rs.getInt("event_id"));
        donation.setName(rs.getString("name"));
        donation.setAmount(rs.getDouble("amount"));
        donation.setEmail(rs.getString("email"));
        donation.setNote(rs.getString("note"));
        donation.setCreatedAt(rs.getString("created_at"));
        donation.setUpdatedAt(rs.getString("updated_at"));
        return donation;
    }

}
