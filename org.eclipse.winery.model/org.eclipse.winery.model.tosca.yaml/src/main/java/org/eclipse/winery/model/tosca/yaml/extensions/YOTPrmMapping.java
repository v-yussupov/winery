/*******************************************************************************
 * Copyright (c) 2019-2020 Contributors to the Eclipse Foundation
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

import java.util.Map;
import java.util.Objects;

import org.eclipse.winery.model.tosca.yaml.YTNodeTemplate;
import org.eclipse.winery.model.tosca.yaml.YTRelationshipTemplate;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;
import org.eclipse.winery.model.tosca.yaml.visitor.VisitorNode;

public abstract class YOTPrmMapping implements VisitorNode {

    private Map.Entry<String, YTNodeTemplate> detectorModelNode;
    private Map.Entry<String, YTNodeTemplate> refinementModelNode;

    private Map.Entry<String, YTRelationshipTemplate> detectorRelationshipNode;
    private Map.Entry<String, YTRelationshipTemplate> refinementRelationshipNode;

    private boolean isNodeToNode;

    public YOTPrmMapping(Builder<?> builder) {
        this.detectorModelNode = builder.detectorModelNode;
        this.refinementModelNode = builder.refinementModelNode;

        this.detectorRelationshipNode = builder.detectorRelationshipNode;
        this.refinementRelationshipNode = builder.refinementRelationshipNode;

        if (Objects.nonNull(this.detectorModelNode) && Objects.nonNull(this.refinementModelNode)) {
            this.isNodeToNode = true;
        }
    }

    public Map.Entry<String, YTNodeTemplate> getDetectorModelNode() {
        return detectorModelNode;
    }

    public void setDetectorModelNode(Map.Entry<String, YTNodeTemplate> detectorModelNode) {
        this.detectorModelNode = detectorModelNode;
    }

    public Map.Entry<String, YTNodeTemplate> getRefinementModelNode() {
        return refinementModelNode;
    }

    public void setRefinementModelNode(Map.Entry<String, YTNodeTemplate> refinementModelNode) {
        this.refinementModelNode = refinementModelNode;
    }

    public Map.Entry<String, YTRelationshipTemplate> getDetectorRelationshipNode() {
        return detectorRelationshipNode;
    }

    public void setDetectorRelationshipNode(Map.Entry<String, YTRelationshipTemplate> detectorRelationshipNode) {
        this.detectorRelationshipNode = detectorRelationshipNode;
    }

    public Map.Entry<String, YTRelationshipTemplate> getRefinementRelationshipNode() {
        return refinementRelationshipNode;
    }

    public void setRefinementRelationshipNode(Map.Entry<String, YTRelationshipTemplate> refinementRelationshipNode) {
        this.refinementRelationshipNode = refinementRelationshipNode;
    }

    public boolean isNodeToNode() {
        return isNodeToNode;
    }

    public void setNodeToNode(boolean nodeToNode) {
        isNodeToNode = nodeToNode;
    }

    public abstract <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter);

    public static abstract class Builder<T extends Builder<T>> {
        private Map.Entry<String, YTNodeTemplate> detectorModelNode;
        private Map.Entry<String, YTNodeTemplate> refinementModelNode;

        private Map.Entry<String, YTRelationshipTemplate> detectorRelationshipNode;
        private Map.Entry<String, YTRelationshipTemplate> refinementRelationshipNode;

        public Builder() {
        }
        
        public Builder<T> setDetectorModelNode(Map.Entry<String, YTNodeTemplate> detectorModelNode) {
            this.detectorModelNode = detectorModelNode;
            return self();
        }

        public Builder<T> setRefinementModelNode(Map.Entry<String, YTNodeTemplate> refinementModelNode) {
            this.refinementModelNode = refinementModelNode;
            return self();
        }

        public Builder<T> setDetectorRelationshipNode(Map.Entry<String, YTRelationshipTemplate> detectorRelationshipNode) {
            this.detectorRelationshipNode = detectorRelationshipNode;
            return self();
        }

        public Builder<T> setRefinementRelationshipNode(Map.Entry<String, YTRelationshipTemplate> refinementRelationshipNode) {
            this.refinementRelationshipNode = refinementRelationshipNode;
            return self();
        }

        public abstract T self();
    }
}
