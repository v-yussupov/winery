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

package org.eclipse.winery.model.tosca.yaml.extensions;

import java.util.List;
import java.util.Objects;

import org.eclipse.winery.model.tosca.yaml.YTTopologyTemplateDefinition;
import org.eclipse.winery.model.tosca.yaml.support.Metadata;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;
import org.eclipse.winery.model.tosca.yaml.visitor.VisitorNode;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class YOTRefinementModel implements VisitorNode {

    protected YTTopologyTemplateDefinition detector;
    protected List<YOTRelationMapping> relationMappings;
    protected List<YOTPermutationMapping> permutationMappings;
    private Metadata metadata;

    protected YOTRefinementModel(Builder<?> builder) {
        this.detector = builder.detector;
        this.relationMappings = builder.relationMappings;
        this.permutationMappings = builder.permutationMappings;
        this.metadata = builder.metadata;
    }

    public List<YOTPermutationMapping> getPermutationMappings() {
        return permutationMappings;
    }

    public void setPermutationMappings(List<YOTPermutationMapping> permutationMappings) {
        this.permutationMappings = permutationMappings;
    }

    public YTTopologyTemplateDefinition getDetector() {
        if (detector == null) {
            detector = new YTTopologyTemplateDefinition.Builder().build();
        }
        return detector;
    }

    public void setDetector(YTTopologyTemplateDefinition detector) {
        this.detector = detector;
    }

    public abstract YTTopologyTemplateDefinition getRefinementTopology();

    public abstract void setRefinementTopology(YTTopologyTemplateDefinition topology);

    public List<YOTRelationMapping> getRelationMappings() {
        return relationMappings;
    }

    public void setRelationMappings(List<YOTRelationMapping> relationMappings) {
        this.relationMappings = relationMappings;
    }

    @NonNull
    public Metadata getMetadata() {
        if (!Objects.nonNull(metadata)) {
            this.metadata = new Metadata();
        }

        return metadata;
    }

    public void setMetadata(@Nullable Metadata metadata) {
        this.metadata = metadata;
    }

    public abstract <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter);

    public static abstract class Builder<T extends Builder<T>> {
        private List<YOTPermutationMapping> permutationMappings;
        private YTTopologyTemplateDefinition detector;
        private List<YOTRelationMapping> relationMappings;
        private Metadata metadata;

        public Builder() {
        }

        public T setDetector(YTTopologyTemplateDefinition detector) {
            this.detector = detector;
            return self();
        }

        public T setRelationMappings(List<YOTRelationMapping> relationMappings) {
            this.relationMappings = relationMappings;
            return self();
        }

        public T setPermutationMappings(List<YOTPermutationMapping> permutationMappings) {
            this.permutationMappings = permutationMappings;
            return self();
        }

        public T setMetadata(Metadata metadata) {
            this.metadata = metadata;
            return self();
        }

        public T addMetadata(Metadata metadata) {
            if (Objects.isNull(this.metadata)) {
                this.metadata = metadata;
            } else {
                this.metadata.putAll(metadata);
            }
            return self();
        }

        public T addMetadata(String key, String value) {
            if (Objects.isNull(key) || Objects.isNull(value)) return self();
            return addMetadata(new Metadata().add(key, value));
        }

        public abstract T self();
    }
}
