package com.construtech.buildsphere.platform.iam.interfaces.rest.transform;

import com.construtech.buildsphere.platform.iam.domain.model.aggregates.User;
import com.construtech.buildsphere.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token){
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
