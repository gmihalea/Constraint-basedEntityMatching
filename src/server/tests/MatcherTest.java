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

package server.tests;

import server.core.Constraint;
import org.junit.Test;

import server.parsers.CSVParser;
import server.util.Checker;
import server.util.Constants;
import server.core.Entity;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for server.core.Matcher class.
 */
public class MatcherTest {

    //Parse all the CSV actions.
//    final ArrayList<Entity> mentees = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTEES, Constants.ENTITY);
//    final ArrayList<Entity> mentors = (ArrayList<Entity>) CSVParser.parseCSV(Constants.CSV_MENTORS, Constants.ENTITY);
//    final ArrayList<Constraint> constraints = (ArrayList<Constraint>) CSVParser.parseCSV(Constants.CSV_CONSTRAINTS,
//                                                                                         Constants.CONSTRAINT);

    //final Matcher matcher = new Matcher(mentees, mentors, constraints);

//    @Test
//    public void checkMenteesCorrectness() throws IOException {
//        assertTrue(Checker.checkAllEntitiesCorrectness(mentees));
//    }
//
//    @Test
//    public void checkMentorsCorrectness() throws IOException {
//        assertTrue(Checker.checkAllEntitiesCorrectness(mentors));
//    }
//
//    @Test
//    public void checkConstraintsCorrectness() throws IOException {
//        assertTrue(Checker.checkAllEntitiesCorrectness(constraints));
//    }
}
