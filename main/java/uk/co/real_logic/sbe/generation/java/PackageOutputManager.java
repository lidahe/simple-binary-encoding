/*
 * Copyright 2013 Real Logic Ltd.
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
package uk.co.real_logic.sbe.generation.java;

import uk.co.real_logic.sbe.generation.OutputManager;
import uk.co.real_logic.sbe.util.Verify;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * {@link OutputManager} for managing the creation of Java source files as the target of code generation.
 */
public class PackageOutputManager implements OutputManager
{
    private final File outputDir;

    /**
     * Create a new {@link OutputManager} for generating Java source files into a given package.
     *
     * @param baseDirectoryName for the generated source code.
     * @param packageName for the generated source code relative to the baseDirectoryName.
     */
    public PackageOutputManager(final String baseDirectoryName, final String packageName) throws IOException
    {
        Verify.notNull(baseDirectoryName, "baseDirectoryName");
        Verify.notNull(packageName, "packageName");

        final String dirName = baseDirectoryName + packageName.replace('.', File.separatorChar);

        outputDir = new File(dirName);
        if (!outputDir.exists())
        {
            if (!outputDir.mkdirs())
            {
                throw new IllegalStateException("Unable to create directory: " + dirName);
            }
        }
    }

    /**
     * Create a new output which will be a Java source file in the given package.
     * <p>
     * The {@link Writer} should be close once the caller has finished with it.
     *
     * @param name the name of the Java class.
     * @return a {@link Writer} to which the source code should be written.
     */
    public Writer newOutput(final String name) throws IOException
    {
        return new FileWriter(new File(outputDir, name + ".java"));
    }
}
