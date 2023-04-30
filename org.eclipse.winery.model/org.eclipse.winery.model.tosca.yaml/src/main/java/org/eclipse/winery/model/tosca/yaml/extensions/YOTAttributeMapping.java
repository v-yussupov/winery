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

public class YOTAttributeMapping extends YOTPrmMapping {

    private YOTAttributeMappingType type;
    private String detectorProperty;
    private String refinementProperty;

    protected YOTAttributeMapping(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.detectorProperty = builder.detectorProperty;
        this.refinementProperty = builder.refinementProperty;
    }

    public YOTAttributeMappingType getType() {
        return type;
    }

    public void setType(YOTAttributeMappingType type) {
        this.type = type;
    }

    public String getDetectorProperty() {
        return detectorProperty;
    }

    public void setDetectorProperty(String detectorProperty) {
        this.detectorProperty = detectorProperty;
    }

    public String getRefinementProperty() {
        return refinementProperty;
    }

    public void setRefinementProperty(String refinementProperty) {
        this.refinementProperty = refinementProperty;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder extends YOTPrmMapping.Builder<Builder> {

        private YOTAttributeMappingType type;
        private String detectorProperty;
        private String refinementProperty;

        public Builder setType(YOTAttributeMappingType type) {
            this.type = type;
            return self();
        }

        public Builder setDetectorProperty(String detectorProperty) {
            this.detectorProperty = detectorProperty;
            return self();
        }

        public Builder setRefinementProperty(String refinementProperty) {
            this.refinementProperty = refinementProperty;
            return self();
        }

        public YOTAttributeMapping build() {
            return new YOTAttributeMapping(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
