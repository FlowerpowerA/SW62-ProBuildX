package com.construtech.buildsphere.platform.profiles.interfaces.rest.transform;

import com.construtech.buildsphere.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.construtech.buildsphere.platform.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country());
    }
}
