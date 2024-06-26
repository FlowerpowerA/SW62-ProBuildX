package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMaterialsByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMaterialByIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectRM;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialCommandService;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialQueryService;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.CreateMaterialResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.MaterialResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.UpdateMaterialResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.CreateMaterialCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.MaterialResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.UpdateMaterialCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/materials", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Materials", description = "Materials Management Endpoint")
public class MaterialController {
    private final MaterialCommandService materialCommandService;
    private final MaterialQueryService materialQueryService;

    public MaterialController(MaterialCommandService materialCommandService, MaterialQueryService materialQueryService) {
        this.materialCommandService = materialCommandService;
        this.materialQueryService = materialQueryService;
    }

    @PostMapping
    public ResponseEntity<MaterialResource> createMaterial(@RequestBody CreateMaterialResource createMaterialResource) {
        var createMaterialCommand = CreateMaterialCommandFromResourceAssembler.toCommandFromResource(createMaterialResource);
        var materialId = materialCommandService.handle(createMaterialCommand);
        if (materialId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMaterialByIdQuery = new GetMaterialByIdQuery(materialId);
        var material = materialQueryService.handle(getMaterialByIdQuery);
        if (material.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(material.get());
        return new ResponseEntity<>(materialResource, HttpStatus.CREATED);

    }

    @GetMapping("/{materialId}")
    public ResponseEntity<MaterialResource> getMaterial(@PathVariable Long materialId) {
        var getMaterialByIdQuery = new GetMaterialByIdQuery(materialId);
        var material = materialQueryService.handle(getMaterialByIdQuery);
        if (material.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(material.get());
        return ResponseEntity.ok(materialResource);
    }

    @GetMapping("/projectId/{projectId}")
    public ResponseEntity<List<MaterialResource>> getAllMaterialsByProjectId(@PathVariable Long projectId) {
        var project = new ProjectRM(projectId);
        var getAllMaterialsByProjectIdQuery = new GetAllMaterialsByProjectIdQuery(project);
        var materials = materialQueryService.handle(getAllMaterialsByProjectIdQuery);
        var materialResources = materials.stream().map(MaterialResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(materialResources);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialResource> updateMaterial(@PathVariable Long materialId, @RequestBody UpdateMaterialResource updateMaterialResource) {
        var updateMaterialCommand = UpdateMaterialCommandFromResourceAssembler.toCommandFromResource(materialId, updateMaterialResource);
        var updateMaterial = materialCommandService.handle(updateMaterialCommand);
        if (updateMaterial.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var materialResource = MaterialResourceFromEntityAssembler.toResourceFromEntity(updateMaterial.get());
        return ResponseEntity.ok(materialResource);
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long materialId) {
        var deleteMaterialCommand = new DeleteMaterialCommand(materialId);
        materialCommandService.handle(deleteMaterialCommand);
        return ResponseEntity.ok("Material deleted successfully");
    }
}
