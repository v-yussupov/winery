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

import javax.xml.namespace.QName;

import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;

public class YOTDeploymentArtifactMapping extends YOTPrmMapping {

    private QName artifactType;
    private QName targetArtifactType;

    protected YOTDeploymentArtifactMapping(Builder builder) {
        super(builder);
        this.artifactType = builder.artifactType;
        this.targetArtifactType = builder.targetArtifactType;
    }

    public QName getArtifactType() {
        return artifactType;
    }

    public QName getTargetArtifactType() {
        return targetArtifactType;
    }

    public void setArtifactType(QName artifactType) {
        this.artifactType = artifactType;
    }

    public void setTargetArtifactType(QName targetArtifactType) {
        this.targetArtifactType = targetArtifactType;
    }

    @Override
    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder extends YOTPrmMapping.Builder<Builder> {

        private QName artifactType;
        private QName targetArtifactType;

        public Builder setArtifactType(QName artifactType) {
            this.artifactType = artifactType;
            return self();
        }

        public Builder setTargetArtifactType(QName targetArtifactType) {
            this.targetArtifactType = targetArtifactType;
            return self();
        }

        @Override
        public Builder self() {
            return this;
        }

        public YOTDeploymentArtifactMapping build() {
            return new YOTDeploymentArtifactMapping(this);
        }
    }
}
