/*
 * Copyright 2016 Daniel Logan
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
package org.robobees.stronghold;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.growingstems.scouting.R;


public class EndMatchFragment extends MatchFragment {

    private boolean displayed = false;

    public EndMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PreMatch.
     */
    public static EndMatchFragment newInstance() {
        EndMatchFragment fragment = new EndMatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        displayed = true;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void saveData(MatchStatsSH data) {
        if (getView() == null || data == null || !displayed)
            return;
        data.challenge = ((CheckBox)getView().findViewById(R.id.challenge_end)).isChecked();
        data.scale = ((CheckBox)getView().findViewById(R.id.scale_end)).isChecked();
        data.notes = ((EditText)getView().findViewById(R.id.notes)).getText().toString();
        data.tipOver = ((CheckBox)getView().findViewById(R.id.botTip)).isChecked();
        data.foul = ((CheckBox)getView().findViewById(R.id.foul)).isChecked();
        data.yellowCard = ((CheckBox)getView().findViewById(R.id.yellow_card)).isChecked();
        data.redCard = ((CheckBox)getView().findViewById(R.id.red_card)).isChecked();
    }

    @Override
    public void loadData(MatchStatsSH data) {
        if (getView() == null || data == null || !displayed)
            return;
        ((CheckBox)getView().findViewById(R.id.challenge_end)).setChecked(data.challenge);
        ((CheckBox)getView().findViewById(R.id.scale_end)).setChecked(data.scale);
        ((EditText)getView().findViewById(R.id.notes)).setText(data.notes);
        ((CheckBox)getView().findViewById(R.id.botTip)).setChecked(data.tipOver);
        ((CheckBox)getView().findViewById(R.id.foul)).setChecked(data.foul);
        ((CheckBox)getView().findViewById(R.id.red_card)).setChecked(data.redCard);
        ((CheckBox)getView().findViewById(R.id.yellow_card)).setChecked(data.yellowCard);
    }

}
