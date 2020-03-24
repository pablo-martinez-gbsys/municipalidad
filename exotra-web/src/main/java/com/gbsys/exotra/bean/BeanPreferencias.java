/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gbsys.exotra.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class BeanPreferencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String theme = "blue";
    @Getter
    @Setter
    private String layout = "default";
    @Getter
    @Setter
    private String layoutMenu = "layout-menu-static";
    @Getter
    @Setter
    private boolean darkMenu = false;
}
