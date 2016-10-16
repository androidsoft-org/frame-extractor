/* Copyright (c) 2016 Pierre LEVY androidsoft.org
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.androidsoft.frameextractor;

import java.io.IOException;

/**
 * Extractor interface
 * @author Pierre LEVY
 */

public interface Extractor
{
    /**
     * Extract images from a given video file
     * @param filePath The video absolute path
     * @param task The asynchronous task launching the extractor
     * @param settings The generation settings
     * @throws IOException if a file error occurs
     */
    void extractMpegFrames(String filePath, ExtractAsyncTask task, Settings settings) throws IOException;
}
