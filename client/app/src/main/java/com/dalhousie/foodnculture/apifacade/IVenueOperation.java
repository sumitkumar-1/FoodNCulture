package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.models.Venues;

import java.util.List;

public interface IVenueOperation extends ICrudOperation<Venues, Integer> {
    public List<Venues> getVenuesByUserId(Integer userId);
}