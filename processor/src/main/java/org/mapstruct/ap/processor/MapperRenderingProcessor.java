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
package org.mapstruct.ap.processor;

import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import org.mapstruct.ap.model.Mapper;
import org.mapstruct.ap.writer.ModelWriter;

/**
 * A {@link ModelElementProcessor} which creates a Java source file representing
 * the given {@link Mapper} object, unless the given mapper type is erroneous.
 *
 * @author Gunnar Morling
 */
public class MapperRenderingProcessor implements ModelElementProcessor<Mapper, Void> {

    @Override
    public Void process(ProcessorContext context, TypeElement mapperTypeElement, Mapper mapper) {
        if ( !context.isErroneous() ) {
            writeToSourceFile( context.getFiler(), mapper );
        }

        return null;
    }

    private void writeToSourceFile(Filer filer, Mapper model) {
        String fileName = model.getPackageName() + "." + model.getImplementationName();

        JavaFileObject sourceFile;
        try {
            sourceFile = filer.createSourceFile( fileName );
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }

        new ModelWriter().writeModel( sourceFile, model );
    }

    @Override
    public int getPriority() {
        return 10000;
    }
}
