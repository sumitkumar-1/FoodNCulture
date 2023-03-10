package com.dalhousie.foodnculture.apifacade;

import com.dalhousie.foodnculture.apifacade.api.AmenitiesApi;
import com.dalhousie.foodnculture.apifacade.api.AuthenticationApi;
import com.dalhousie.foodnculture.apifacade.api.CommunityApi;
import com.dalhousie.foodnculture.apifacade.api.DonationApi;
import com.dalhousie.foodnculture.apifacade.api.EventApi;
import com.dalhousie.foodnculture.apifacade.api.EventMemberApi;
import com.dalhousie.foodnculture.apifacade.api.FeedbackApi;
import com.dalhousie.foodnculture.apifacade.api.FriendsApi;
import com.dalhousie.foodnculture.apifacade.api.HTTPRequest;
import com.dalhousie.foodnculture.apifacade.api.MessageApi;
import com.dalhousie.foodnculture.apifacade.api.UsersApi;
import com.dalhousie.foodnculture.apifacade.api.VenuesApi;
import com.dalhousie.foodnculture.apifacade.contract.IAmenityOperation;
import com.dalhousie.foodnculture.apifacade.contract.IAuthenticationOperation;
import com.dalhousie.foodnculture.apifacade.contract.ICommunityOperation;
import com.dalhousie.foodnculture.apifacade.contract.IDonationOperation;
import com.dalhousie.foodnculture.apifacade.contract.IEventMemberOperation;
import com.dalhousie.foodnculture.apifacade.contract.IEventOperation;
import com.dalhousie.foodnculture.apifacade.contract.IFeedbackOperation;
import com.dalhousie.foodnculture.apifacade.contract.IFriendOperation;
import com.dalhousie.foodnculture.apifacade.contract.IMessagesOperation;
import com.dalhousie.foodnculture.apifacade.contract.IRequest;
import com.dalhousie.foodnculture.apifacade.contract.IUserOperation;
import com.dalhousie.foodnculture.apifacade.contract.IVenueOperation;

public class ApiFacade {

    private static ApiFacade apiFacade;
    private final IRequest request;
    private final IUserOperation userOperation;
    private final IAmenityOperation amenityOperation;
    private final IAuthenticationOperation authenticationOperation;
    private final IDonationOperation donationOperation;
    private final IEventMemberOperation eventMemberOperation;
    private final IEventOperation eventOperation;
    private final IFeedbackOperation feedbackOperation;
    private final IVenueOperation venueOperation;
    private final ICommunityOperation communityOperation;
    private final IFriendOperation friendOperation;
    private final IMessagesOperation messagesOperation;

    private ApiFacade(IRequest request) {
        this.request = request;
        this.userOperation = new UsersApi(this.request);
        this.amenityOperation = new AmenitiesApi(this.request);
        this.authenticationOperation = new AuthenticationApi(this.request);
        this.donationOperation = new DonationApi(this.request);
        this.eventMemberOperation = new EventMemberApi(this.request);
        this.eventOperation = new EventApi(this.request);
        this.feedbackOperation = new FeedbackApi(this.request);
        this.venueOperation = new VenuesApi(this.request);
        this.communityOperation = new CommunityApi(this.request);
        this.friendOperation = new FriendsApi(this.request);
        this.messagesOperation = new MessageApi(this.request);
    }

    public static ApiFacade getInstance() {
        if (apiFacade == null) {
            apiFacade = new ApiFacade(HTTPRequest.getInstance());
        }
        return apiFacade;
    }

    public IUserOperation getUserApi() {
        return this.userOperation;
    }

    public IAmenityOperation getAmenitiesApi() {
        return this.amenityOperation;
    }

    public IAuthenticationOperation getAuthenticationApi() {
        return this.authenticationOperation;
    }

    public IDonationOperation getDonationApi() {
        return this.donationOperation;
    }

    public IEventMemberOperation getEventMemberApi() {
        return this.eventMemberOperation;
    }

    public IEventOperation getEventApi() {
        return this.eventOperation;
    }

    public IFeedbackOperation getFeedbackApi() {
        return this.feedbackOperation;
    }

    public IVenueOperation getVenueApi() {
        return this.venueOperation;
    }

    public ICommunityOperation getCommunityApi() {
        return this.communityOperation;
    }

    public IFriendOperation getFriendApi() {
        return this.friendOperation;
    }

    public IMessagesOperation getMessagesApi() {
        return this.messagesOperation;
    }
}
