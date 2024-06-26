package com.construtech.buildsphere.platform.resources;


import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialCommandService;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialQueryService;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.MaterialController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;

import static org.mockito.Mockito.doNothing;

public class MaterialControllerTest {
    @Mock
    private MaterialQueryService materialQueryService;

    @Mock
    private MaterialCommandService materialCommandService;

    @InjectMocks
    private MaterialController materialController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteMaterialSuccessfulTest() {
        doNothing().when(materialCommandService).handle(any(DeleteMaterialCommand.class));

        ResponseEntity<?> responseEntity = materialController.deleteMaterial(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}



