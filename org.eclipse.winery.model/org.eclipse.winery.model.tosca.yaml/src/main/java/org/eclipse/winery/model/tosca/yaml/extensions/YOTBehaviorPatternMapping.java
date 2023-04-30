/*******************************************************************************
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
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

public class YOTBehaviorPatternMapping extends YOTPrmMapping {

    private String behaviorPattern;
    private YOTPropertyKV property;

    protected YOTBehaviorPatternMapping(Builder builder) {
        super(builder);
        this.behaviorPattern = builder.behaviorPattern;
        this.property = builder.property;
    }

    public String getBehaviorPattern() {
        return behaviorPattern;
    }

    public void setBehaviorPattern(String behaviorPattern) {
        this.behaviorPattern = behaviorPattern;
    }

    public YOTPropertyKV getProperty() {
        return property;
    }

    public void setProperty(YOTPropertyKV property) {
        this.property = property;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder extends YOTPrmMapping.Builder<Builder> {

        private String behaviorPattern;
        private YOTPropertyKV property;

        public Builder setBehaviorPattern(String behaviorPattern) {
            this.behaviorPattern = behaviorPattern;
            return self();
        }

        public Builder setProperty(YOTPropertyKV property) {
            this.property = property;
            return self();
        }

        public YOTBehaviorPatternMapping build() {
            return new YOTBehaviorPatternMapping(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
