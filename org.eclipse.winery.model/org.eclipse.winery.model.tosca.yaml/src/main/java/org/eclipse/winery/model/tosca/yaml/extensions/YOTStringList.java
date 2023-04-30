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

import java.io.Serializable;
import java.util.List;

public class YOTStringList implements Serializable {

    protected List<String> values;

    public YOTStringList(Builder builder) {
        this.values = builder.values;
    }

    public YOTStringList(List<String> options) {
        this.values = options;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public static class Builder {

        private List<String> values;

        public Builder() {
        }

        public Builder(List<String> values) {
            this.values = values;
        }

        public Builder self() {
            return this;
        }

        public Builder setValues(List<String> values) {
            this.values = values;
            return self();
        }

        public YOTStringList build() {
            return new YOTStringList(this);
        }
    }
}
