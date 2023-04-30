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

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;
import org.eclipse.winery.model.tosca.yaml.visitor.VisitorNode;

public abstract class YOTPrmMapping implements VisitorNode {

    private String detectorNode;
    private String refinementNode;

    public YOTPrmMapping(Builder<?> builder) {
        this.detectorNode = builder.detectorElement;
        this.refinementNode = builder.refinementElement;
    }

    public String getDetectorNode() {
        return detectorNode;
    }

    public void setDetectorNode(String detectorNode) {
        this.detectorNode = detectorNode;
    }

    public String getRefinementNode() {
        return refinementNode;
    }

    public void setRefinementNode(String refinementNode) {
        this.refinementNode = refinementNode;
    }

    public abstract <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter);

    public static abstract class Builder<T extends Builder<T>> {
        private String detectorElement;
        private String refinementElement;

        public Builder() {
        }

        public Builder<T> setDetectorElement(String detectorElement) {
            this.detectorElement = detectorElement;
            return self();
        }

        public Builder<T> setRefinementElement(String refinementElement) {
            this.refinementElement = refinementElement;
            return self();
        }

        public abstract T self();
    }
}
