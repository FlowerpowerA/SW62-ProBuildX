package com.construtech.buildsphere.platform.iam.interfaces.rest.transform;

import com.construtech.buildsphere.platform.iam.domain.model.commands.SignUpCommand;
import com.construtech.buildsphere.platform.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.password(), resource.roles());
    }
}
