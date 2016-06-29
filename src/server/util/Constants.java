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

/**
 * Class with constants.
 */
public class Constants {

    public static final String MORE = ">";
    public static final String COLON = ":";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String EMPTY_STRING = "";
    public static final String CSV_SEPARATOR = ",";

    public static final String DIFF = "diff";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String GMT = "GMT";

    public static final String ENTITY = "entity";
    public static final String CONSTRAINT = "constraint";

    public static final String HARD_CONSTRAINT = "[hard]";
    public static final String SOFT_CONSTRAINT = "[soft]";

    public static final int ASCENDING_SORT = 1;
    public static final int DESCENDING_SORT = 0;

    public static final int CONSTRAINTS_INDEX = 0;
    public static final int CONSTRAINTS_CODE_SIZE = 6;
    public static final int CONSTRAINT_PRIORITY_SIZE = 3;

    public static final String MORE_THAN_SEVEN_HOURS = "7-10";
    public static final String MORE_THAN_TEN_HOURS = ">10";

    public static final String SCORE_CRITERIA = "Score";
    public static final String TIME_ZONE_CRITERIA = "TimeZone";
    public static final String PROGRAMMING_LANGUAGES_CRITERIA = "ProgrammingLanguages";

    public static final String TIME_ZONE_CONSTRAINT = "TimeZone";
    public static final String SCORE_ATTRIBUTE = "Score";
    public static final String GMT_MIN_CONSTRAINT = "gmtMin";
    public static final String MORE_DEDICATED_TIME_WITH_BEGINNER_CONSTRAINT = "moreTimeWithBeginner";
    public static final String MORE_DEDICATED_TIME = "moreTime";
    public static final String BIGGER_SCORE_CONSTRAINT = "biggerScore";
    public static final String LESS_PROGRAMMING_LANGUAGES = "lessProgrammingLanguages";

    public static final String DEDICATED_TIME_ATTRIBUTE = "DedicatedTime";
    public static final String PROGRAMMING_LEVEL_ATTRIBUTE = "ProgrammingLevel";
    public static final String PROGRAMMING_LANGUAGES_ATTRIBUTE = "ProgrammingLanguages";

    public static final String PROGRAMMING_LEVEL_BEGINNER = "Beginner";

    public static final String OUTPUT_FILE_PATH = "/home/geanina/Desktop/Licenta/EntityMatching/output.txt";
}
