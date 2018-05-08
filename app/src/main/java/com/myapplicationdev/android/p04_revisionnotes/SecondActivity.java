package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO implement the Custom ListView
		ListView lv;
		lv=findViewById(R.id.lv);
		DBHelper db2 = new DBHelper(SecondActivity.this);
		ArrayList<Note> NotesArray;
		NotesArray = db2.getColumnStars();
		ArrayAdapter<Note> AA;

		AA = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, NotesArray);
		lv.setAdapter(AA);
		Toast.makeText(SecondActivity.this, "" + NotesArray.size(), Toast.LENGTH_LONG).show();

		db2.close();


	}


}
