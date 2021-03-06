/**
 *  Copyright 2012-2013 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.model;

import java.beans.Introspector;
import java.util.Set;

import org.mapstruct.ap.util.Collections;
import org.mapstruct.ap.util.Strings;

/**
 * Mapper reference which is retrieved via the {@code Mappers#getMapper()} method. Used by default if no other component
 * model is specified via {@code Mapper#uses()}.
 *
 * @author Gunnar Morling
 */
public class DefaultMapperReference extends AbstractModelElement implements MapperReference {

    private final Type type;

    public DefaultMapperReference(Type type) {
        this.type = type;
    }

    @Override
    public Type getMapperType() {
        return type;
    }

    @Override
    public Set<Type> getImportTypes() {
        return Collections.asSet( type );
    }

    public String getVariableName() {
        return Strings.getSaveVariableName( Introspector.decapitalize( type.getName() ) );
    }
}
