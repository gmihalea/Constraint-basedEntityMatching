/**
 * Copyright (c) 2016, Geanina Mihalea <geanina.mihalea@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package server.util;

import java.util.ArrayList;
import java.util.Map;
import server.core.*;

/**
 * Class that makes sanity check for the entities.
 */
public class Checker {

    /**
     * Checks if an entity is correctly defined.
     * @param e the entity to check
     * @return true if the entity is correct, false otherwise
     */
    public static boolean checkEntityCorrectness(final Item e) {

        if (e.getAttributes().size() == 0) {
            System.out.println("[ERROR] The Entity has no attributes.");
            return false;
        }

        for (final Map.Entry<String, ArrayList<String>> entry : e.getAttributes().entrySet()) {
            if (entry.getValue().size() == 0) {
                System.out.println("[ERROR] The key " + entry.getKey() + " has no value.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a list of entities is correctly defined.
     * @param entities the entities to check
     * @return true if the list is correct, false otherwise
     */
    public static boolean checkAllEntitiesCorrectness(final ArrayList<? extends Item> entities) {
        for (int i = 0; i < entities.size(); ++i) {
            if (!Checker.checkEntityCorrectness(entities.get(i))) {
                System.out.println("[ERROR] Entity " + i + " is incorrect.");
                return false;
            }
        }
        return true;
    }
}
