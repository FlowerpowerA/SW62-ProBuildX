package com.construtech.buildsphere.platform.profiles.interfaces.rest.transform;

import com.construtech.buildsphere.platform.profiles.domain.model.aggregates.Profile;
import com.construtech.buildsphere.platform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmailAddress(),
                entity.getStreetAddress());
    }
}
