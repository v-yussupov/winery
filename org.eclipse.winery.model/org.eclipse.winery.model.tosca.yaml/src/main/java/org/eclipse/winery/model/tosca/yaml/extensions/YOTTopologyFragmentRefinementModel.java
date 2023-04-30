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

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.winery.model.tosca.yaml.YTTopologyTemplateDefinition;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class YOTTopologyFragmentRefinementModel extends YOTRefinementModel {

    protected YTTopologyTemplateDefinition refinementStructure;
    protected List<YOTAttributeMapping> attributeMappings;
    protected List<YOTStayMapping> stayMappings;
    protected List<YOTDeploymentArtifactMapping> deploymentArtifactMappings;
    protected List<YOTStringList> permutationOptions;
    protected List<YOTStringList> componentSets;

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

    @NonNull
    @XmlTransient
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

    @Nullable
    public List<YOTAttributeMapping> getAttributeMappings() {
        return attributeMappings;
    }

    public void setAttributeMappings(List<YOTAttributeMapping> attributeMappings) {
        this.attributeMappings = attributeMappings;
    }

    @Nullable
    public List<YOTStayMapping> getStayMappings() {
        return stayMappings;
    }

    public void setStayMappings(List<YOTStayMapping> stayMappings) {
        this.stayMappings = stayMappings;
    }

    @Nullable
    public List<YOTDeploymentArtifactMapping> getDeploymentArtifactMappings() {
        return deploymentArtifactMappings;
    }

    public void setDeploymentArtifactMappings(List<YOTDeploymentArtifactMapping> deploymentArtifactMappings) {
        this.deploymentArtifactMappings = deploymentArtifactMappings;
    }

    public List<YOTStringList> getPermutationOptions() {
        return permutationOptions;
    }

    public void setPermutationOptions(List<YOTStringList> permutationOptions) {
        this.permutationOptions = permutationOptions;
    }

    public List<YOTStringList> getComponentSets() {
        return componentSets;
    }

    public void setComponentSets(List<YOTStringList> componentSets) {
        this.componentSets = componentSets;
    }

    public static class Builder extends YOTRefinementModel.Builder<Builder> {

        private YTTopologyTemplateDefinition refinementStructure;
        private List<YOTAttributeMapping> attributeMappings;
        private List<YOTStayMapping> stayMappings;
        private List<YOTDeploymentArtifactMapping> deploymentArtifactMappings;
        private List<YOTStringList> permutationOptions;

        public Builder() {
            super();
        }

        public Builder setRefinementStructure(YTTopologyTemplateDefinition refinementStructure) {
            this.refinementStructure = refinementStructure;
            return self();
        }

        public Builder setAttributeMappings(List<YOTAttributeMapping> attributeMappings) {
            this.attributeMappings = attributeMappings;
            return self();
        }

        public Builder setStayMappings(List<YOTStayMapping> stayMappings) {
            this.stayMappings = stayMappings;
            return self();
        }

        public Builder setDeploymentArtifactMappings(List<YOTDeploymentArtifactMapping> deploymentArtifactMappings) {
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
