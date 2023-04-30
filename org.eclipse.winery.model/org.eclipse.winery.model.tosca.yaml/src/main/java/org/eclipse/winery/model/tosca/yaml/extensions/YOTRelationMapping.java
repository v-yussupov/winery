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

import javax.xml.namespace.QName;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;

public class YOTRelationMapping extends YOTPrmMapping {

    private QName relationType;
    private YOTRelationDirection direction;
    private QName validSourceOrTarget;

    protected YOTRelationMapping(Builder builder) {
        super(builder);
        this.relationType = builder.relationType;
        this.direction = builder.direction;
        this.validSourceOrTarget = builder.validSourceOrTarget;
    }

    public QName getRelationType() {
        return relationType;
    }

    public void setRelationType(QName relationType) {
        this.relationType = relationType;
    }

    public YOTRelationDirection getDirection() {
        return direction;
    }

    public void setDirection(YOTRelationDirection direction) {
        this.direction = direction;
    }

    public QName getValidSourceOrTarget() {
        return validSourceOrTarget;
    }

    public void setValidSourceOrTarget(QName validSourceOrTarget) {
        this.validSourceOrTarget = validSourceOrTarget;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder extends YOTPrmMapping.Builder<Builder> {

        private QName relationType;
        private YOTRelationDirection direction;
        private QName validSourceOrTarget;

        public Builder setRelationType(QName relationType) {
            this.relationType = relationType;
            return self();
        }

        public Builder setDirection(YOTRelationDirection direction) {
            this.direction = direction;
            return self();
        }

        public Builder setValidSourceOrTarget(QName validSourceOrTarget) {
            this.validSourceOrTarget = validSourceOrTarget;
            return self();
        }

        public YOTRelationMapping build() {
            return new YOTRelationMapping(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
