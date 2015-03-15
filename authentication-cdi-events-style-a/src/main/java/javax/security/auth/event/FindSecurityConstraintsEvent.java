/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.security.auth.event;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindSecurityConstraintsEvent {

    private final ServletRequest request;
    private final String context;

    private final List<String> roles = new ArrayList<String>();
    private String userConstraint;

    public FindSecurityConstraintsEvent(final ServletRequest request, final String context) {
        this.request = request;
        this.context = context;
    }

    public ServletRequest getRequest() {
        return request;
    }

    public String getContext() {
        return context;
    }

    public List<String> getRoles() {
        return roles;
    }

    public FindSecurityConstraintsEvent addRoles(final String... roles) {
        this.roles.addAll(Arrays.asList(roles));
        return this;
    }

    public void setUserConstraint(final String userConstraint) {
        if (this.userConstraint != null && !this.userConstraint.equals(userConstraint)) {
            throw new IllegalStateException("User constraint already set to > " + this.userConstraint);
        }
        this.userConstraint = userConstraint;
    }

    public String getUserConstraint() {
        return userConstraint;
    }
}
