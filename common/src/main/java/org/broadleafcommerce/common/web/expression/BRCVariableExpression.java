/*
 * Copyright 2008-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.common.web.expression;

import org.apache.commons.beanutils.PropertyUtils;
import org.broadleafcommerce.common.sandbox.domain.SandBox;
import org.broadleafcommerce.common.time.SystemTime;
import org.broadleafcommerce.common.web.BroadleafRequestContext;

import java.util.Date;


/**
 * This Thymeleaf variable expression class serves to expose elements from the BroadleafRequestContext
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class BRCVariableExpression implements BroadleafVariableExpression {
    
    @Override
    public String getName() {
        return "brc";
    }
    
    public SandBox getSandbox() {
        BroadleafRequestContext brc = BroadleafRequestContext.getBroadleafRequestContext();
        if (brc != null) {
            return brc.getSandbox();
        }
        return null;
    }
    
    public Date getCurrentTime() {
        return SystemTime.asDate(true);
    }
    
    public Object get(String propertyName) {
        BroadleafRequestContext brc = BroadleafRequestContext.getBroadleafRequestContext();
        if (brc != null) {
            try {
                return PropertyUtils.getProperty(brc, propertyName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}