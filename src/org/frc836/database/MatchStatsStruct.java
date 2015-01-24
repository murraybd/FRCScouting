/*
 * Copyright 2015 Daniel Logan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.frc836.database;

import org.frc836.database.FRCScoutingContract.FACT_MATCH_DATA_2015_Entry;
import org.robobees.recyclerush.MatchStatsRR;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class MatchStatsStruct {

	public int team;
	public String event;
	public int match;
	// public boolean autonomous; //not used in 2015
	public String notes;
	public boolean tipOver;
	public boolean foul;
	// public boolean tech_foul; //not used in 2015
	public boolean yellowCard;
	public boolean redCard;
	public boolean practice_match; // new in 2015

	public static final String TABLE_NAME = FACT_MATCH_DATA_2015_Entry.TABLE_NAME;
	public static final String COLUMN_NAME_ID = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_ID;
	public static final String COLUMN_NAME_EVENT_ID = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_EVENT_ID;
	public static final String COLUMN_NAME_MATCH_ID = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_MATCH_ID;
	public static final String COLUMN_NAME_TEAM_ID = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TEAM_ID;
	public static final String COLUMN_NAME_PRACTICE_MATCH = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_PRACTICE_MATCH;
	public static final String COLUMN_NAME_INVALID = FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_INVALID;

	public static MatchStatsStruct getNewMatchStats() {
		return new MatchStatsRR();
	}

	public MatchStatsStruct() {
		init();
	}

	public MatchStatsStruct(int team, String event, int match) {
		init();
		this.team = team;
		this.event = event;
		this.match = match;
	}

	public MatchStatsStruct(int team, String event, int match, boolean practice) {
		init();
		this.team = team;
		this.event = event;
		this.match = match;
		this.practice_match = practice;
		// autonomous = auto;
	}

	public void init() {
		// autonomous = true;
		tipOver = false;
		notes = "";
		foul = false;
		// tech_foul = false;
		yellowCard = false;
		redCard = false;
		practice_match = false;
	}

	public ContentValues getValues(DB db, SQLiteDatabase database) {
		ContentValues args = new ContentValues();
		long ev = db.getEventIDFromName(event, database);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_ID, ev * 10000000
				+ match * 10000 + team);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TEAM_ID, team);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_EVENT_ID, ev);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_MATCH_ID, match);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_NOTES, notes);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TIP_OVER, tipOver ? 1
				: 0);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_FOUL, foul ? 1 : 0);
		// args.put(FACT_MATCH_DATA_Entry.COLUMN_NAME_TECH_FOUL, tech_foul ? 1 :
		// 0);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_YELLOW_CARD,
				yellowCard ? 1 : 0);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_RED_CARD, redCard ? 1
				: 0);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_PRACTICE_MATCH,
				practice_match ? 1 : 0);
		args.put(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_INVALID, 1);

		return args;
	}

	public void fromCursor(Cursor c, DB db, SQLiteDatabase database) {
		c.moveToFirst();

		team = c.getInt(c
				.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TEAM_ID));
		event = DB
				.getEventNameFromID(
						c.getInt(c
								.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_EVENT_ID)),
						database);
		match = c
				.getInt(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_MATCH_ID));
		notes = c
				.getString(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_NOTES));
		tipOver = c
				.getInt(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TIP_OVER)) != 0;
		foul = c.getInt(c
				.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_FOUL)) != 0;
		// tech_foul = c
		// .getInt(c
		// .getColumnIndexOrThrow(FACT_MATCH_DATA_Entry.COLUMN_NAME_TECH_FOUL))
		// != 0;
		yellowCard = c
				.getInt(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_YELLOW_CARD)) != 0;
		redCard = c
				.getInt(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_RED_CARD)) != 0;
		practice_match = c
				.getInt(c
						.getColumnIndexOrThrow(FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_PRACTICE_MATCH)) != 0;
	}

	public String[] getProjection() {
		String[] projection = { FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TEAM_ID,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_EVENT_ID,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_MATCH_ID,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_NOTES,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_TIP_OVER,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_FOUL,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_YELLOW_CARD,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_RED_CARD,
				FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_PRACTICE_MATCH };
		return projection;
	}
	
	public boolean isTextField(String column_name) {
		return FACT_MATCH_DATA_2015_Entry.COLUMN_NAME_NOTES.equalsIgnoreCase(column_name);
	}
}
