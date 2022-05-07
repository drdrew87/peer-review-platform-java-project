package com.fdm.peer_review.view;

import com.fdm.peer_review.model.Employee;

public class ProfileView {
    private Employee user;
    private String profilePic;
    
    public ProfileView(Employee user) {
	super();
	if (user.getGender()=="M") {
	
	    this.profilePic="https://www.pngrepo.com/download/18909/avatar.png";
	
	} else {
	    this.profilePic="https://www.pngrepo.com/download/63357/avatar.png";
	}
	this.user = user;
    }

    public Employee getUser() {
        return user;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    
    
}
