package com.construtech.buildsphere.platform.documents.interfaces.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/*
* Document Controller
* <p>
* This class is responsible for handling the document requests
* <ul>
*       <li>POST /api/v1/projects/{id}/documents</li>
*       <li>GET /api/v1/documents</li>
*       <li>GET /api/v1/projects/{id}/documents</li>
*       <li>PUT /api/v1/projects/{id}/documents/{documentId}</li>
*       <li>DELETE /api/v1/projects/{id}/documents/{documentId}</li>
*  </ul>
*  </p>
 *
 */
@RestController
@RequestMapping(value = "api/v1/documents", produces = APPLICATION_JSON_VALUE)
public class DocumentController {


}
