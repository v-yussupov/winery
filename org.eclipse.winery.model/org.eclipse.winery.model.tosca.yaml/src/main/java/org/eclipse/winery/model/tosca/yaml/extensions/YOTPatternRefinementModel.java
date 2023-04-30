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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;

public class YOTPatternRefinementModel extends YOTTopologyFragmentRefinementModel {

    private boolean isPdrm;
    private Map<String, YOTBehaviorPatternMapping> behaviorPatternMappings;

    public YOTPatternRefinementModel(Builder builder) {
        super(builder);
        this.isPdrm = builder.isPdrm;
        this.behaviorPatternMappings = builder.behaviorPatternMappings;
    }

    public boolean isPdrm() {
        return isPdrm;
    }

    public void setIsPdrm(boolean isPdrm) {
        this.isPdrm = isPdrm;
    }

    public Map<String, YOTBehaviorPatternMapping> getBehaviorPatternMappings() {
        if (this.behaviorPatternMappings == null) {
            this.behaviorPatternMappings = new LinkedHashMap<>();
        }
        return behaviorPatternMappings;
    }

    public void setBehaviorPatternMappings(Map<String, YOTBehaviorPatternMapping> behaviorPatternMappings) {
        this.behaviorPatternMappings = behaviorPatternMappings;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder extends YOTTopologyFragmentRefinementModel.Builder {

        private boolean isPdrm;
        private Map<String, YOTBehaviorPatternMapping> behaviorPatternMappings;

        public Builder setIsPdrm(boolean isPdrm) {
            this.isPdrm = isPdrm;
            return self();
        }

        public Builder setBehaviorPatternMappings(Map<String, YOTBehaviorPatternMapping> behaviorPatternMappings) {
            this.behaviorPatternMappings = behaviorPatternMappings;
            return self();
        }

        public YOTPatternRefinementModel build() {
            return new YOTPatternRefinementModel(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
