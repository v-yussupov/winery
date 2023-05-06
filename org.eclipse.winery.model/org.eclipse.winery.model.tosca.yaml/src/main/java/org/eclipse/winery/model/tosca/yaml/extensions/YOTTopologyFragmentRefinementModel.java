/*******************************************************************************
 * Copyright (c) 2018-2020 Contributors to the Eclipse Foundation
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

package org.eclipse.winery.model.tosca.yaml.extensions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.winery.model.tosca.yaml.YTTopologyTemplateDefinition;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;

public class YOTTopologyFragmentRefinementModel extends YOTRefinementModel {

    protected YTTopologyTemplateDefinition refinementStructure;
    protected Map<String, YOTAttributeMapping> attributeMappings;
    protected Map<String, YOTStayMapping> stayMappings;
    protected Map<String, YOTDeploymentArtifactMapping> deploymentArtifactMappings;
    protected List<YOTStringList> permutationOptions;
    protected Map<String, YOTStringList> componentSets;

    protected YOTTopologyFragmentRefinementModel(Builder builder) {
        super(builder);
        this.refinementStructure = builder.refinementStructure;
        this.attributeMappings = builder.attributeMappings;
        this.stayMappings = builder.stayMappings;
        this.deploymentArtifactMappings = builder.deploymentArtifactMappings;
        this.permutationOptions = builder.permutationOptions;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public YTTopologyTemplateDefinition getRefinementTopology() {
        if (refinementStructure == null) {
            refinementStructure = new YTTopologyTemplateDefinition.Builder().build();
        }
        return refinementStructure;
    }

    public YTTopologyTemplateDefinition getRefinementStructure() {
        return getRefinementTopology();
    }

    public void setRefinementTopology(YTTopologyTemplateDefinition refinementStructure) {
        this.refinementStructure = refinementStructure;
    }

    public Map<String, YOTAttributeMapping> getAttributeMappings() {
        return Objects.nonNull(attributeMappings) ? attributeMappings : new HashMap<>();
    }

    public void setAttributeMappings(Map<String, YOTAttributeMapping> attributeMappings) {
        this.attributeMappings = attributeMappings;
    }

    public Map<String, YOTStayMapping> getStayMappings() {
        return Objects.nonNull(stayMappings) ? stayMappings : new HashMap<>();
    }

    public void setStayMappings(Map<String, YOTStayMapping> stayMappings) {
        this.stayMappings = stayMappings;
    }

    public Map<String, YOTDeploymentArtifactMapping> getDeploymentArtifactMappings() {
        return Objects.nonNull(deploymentArtifactMappings) ? deploymentArtifactMappings : new HashMap<>();
    }

    public void setDeploymentArtifactMappings(Map<String, YOTDeploymentArtifactMapping> deploymentArtifactMappings) {
        this.deploymentArtifactMappings = deploymentArtifactMappings;
    }

    public List<YOTStringList> getPermutationOptions() {
        return permutationOptions;
    }

    public void setPermutationOptions(List<YOTStringList> permutationOptions) {
        this.permutationOptions = permutationOptions;
    }

    public Map<String, YOTStringList> getComponentSets() {
        return componentSets;
    }

    public void setComponentSets(Map<String, YOTStringList> componentSets) {
        this.componentSets = componentSets;
    }

    public static class Builder extends YOTRefinementModel.Builder<Builder> {

        private YTTopologyTemplateDefinition refinementStructure;
        private Map<String, YOTAttributeMapping> attributeMappings;
        private Map<String, YOTStayMapping> stayMappings;
        private Map<String, YOTDeploymentArtifactMapping> deploymentArtifactMappings;
        private List<YOTStringList> permutationOptions;

        public Builder() {
            super();
        }

        public Builder setRefinementStructure(YTTopologyTemplateDefinition refinementStructure) {
            this.refinementStructure = refinementStructure;
            return self();
        }

        public Builder setAttributeMappings(Map<String, YOTAttributeMapping> attributeMappings) {
            this.attributeMappings = attributeMappings;
            return self();
        }

        public Builder setStayMappings(Map<String, YOTStayMapping> stayMappings) {
            this.stayMappings = stayMappings;
            return self();
        }

        public Builder setDeploymentArtifactMappings(Map<String, YOTDeploymentArtifactMapping> deploymentArtifactMappings) {
            this.deploymentArtifactMappings = deploymentArtifactMappings;
            return self();
        }

        public Builder setPermutationOptions(List<YOTStringList> permutationOptions) {
            this.permutationOptions = permutationOptions;
            return self();
        }

        public YOTTopologyFragmentRefinementModel build() {
            return new YOTTopologyFragmentRefinementModel(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
