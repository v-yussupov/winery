import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { TTopologyTemplate } from '../models/ttopology-template';

/*******************************************************************************
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache Software License 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 *******************************************************************************/

export interface FaastenerResponse {
    url: string;
}

export interface QueryGenerationRequest {
    topology: SimplifiedToscaTopology;
    selectedNode: string;
}

export interface SimplifiedToscaTopology {
    nodeTemplates: Array<SimplifiedNodeTemplate>;
    relationshipTemplates: Array<SimplifiedRelationshipTemplate>;
}

export interface SimplifiedNodeTemplate {
    id: string;
    type: string;
    name: string;
    properties?: any;
}

export interface SimplifiedRelationshipTemplate {
    id: string;
    type: string;
    name: string;
    sourceElementRef: string;
    targetElementRef: string;
    properties?: any;
}

@Injectable({
    providedIn: 'root'
})
export class FaastenerService {
    readonly FAASTENER_URL: string = 'http://localhost:8082/api/search-queries';
    readonly FAASTENER_GUI_URL: string = 'http://localhost:4300/technologies';

    constructor(private http: HttpClient,
                private toastrService: ToastrService) {
    }

    private static buildQueryGenerationRequest(topology: TTopologyTemplate, selectedNodeTemplateIds: string[]): QueryGenerationRequest {
        const nodes: SimplifiedNodeTemplate[] = [];
        const rels: SimplifiedRelationshipTemplate[] = [];
        const capMap = {};
        const reqMap = {};

        console.log(topology);

        topology.nodeTemplates.map(
            nt => {
                nt.requirements.forEach(req => reqMap[req.id] = nt.id);
                nt.capabilities.forEach(cap => capMap[cap.id] = nt.id);

                const cur: SimplifiedNodeTemplate = {
                    id: nt.id,
                    name: nt.name,
                    type: nt.type,
                    properties: nt.properties['properties']
                };
                nodes.push(cur);
            }
        );

        topology.relationshipTemplates.map(
            rt => {
                const cur: SimplifiedRelationshipTemplate = {
                    id: rt.id,
                    name: rt.name,
                    type: rt.type,
                    sourceElementRef: reqMap[rt.sourceElement.ref],
                    targetElementRef: capMap[rt.targetElement.ref],
                    properties: rt.properties['properties']
                };
                rels.push(cur);
            }
        );
        const request: QueryGenerationRequest = {
            topology: {
                nodeTemplates: nodes,
                relationshipTemplates: rels
            },
            selectedNode: selectedNodeTemplateIds[0]
        };

        console.log(request);


        return request;
    }

    async sendToscaModel(topology: TTopologyTemplate, selectedNodeTemplateIds: string[]) {
        try {
            const request: QueryGenerationRequest = FaastenerService.buildQueryGenerationRequest(topology, selectedNodeTemplateIds);

            const apiQuery = await this.http.post<FaastenerResponse>(this.FAASTENER_URL, request).toPromise();
            const techs: any = await this.http.get(apiQuery.url).toPromise();

            console.log(techs);

            if (techs.length > 0) {
                let query = '?';
                for (const tech of techs) {
                    query += 'id=' + tech.id + '&';
                }
                query.substr(0, query.length - 1);
                window.open(this.FAASTENER_GUI_URL + query, '_blank');
            }

            // window.open(apiQuery.url, '_blank');
        } catch (err) {
            if (err instanceof HttpErrorResponse) {
                if (err.status === 500) {
                    this.toastrService.error('Winery is not properly configured for FaaStener usage', 'Configuration Error');
                }
            }
        }
    }
}
