package com.dalhousie.foodnculture.apifacade.contract;

import com.dalhousie.foodnculture.models.Venues;

import java.util.List;

public interface IVenueOperation extends ICrudOperation<Venues, Integer> {
    List<Venues> getVenuesByUserId(Integer userId);
}
