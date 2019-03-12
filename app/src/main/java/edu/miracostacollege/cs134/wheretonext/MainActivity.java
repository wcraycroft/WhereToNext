package edu.miracostacollege.cs134.wheretonext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;

import edu.miracostacollege.cs134.wheretonext.model.College;
import edu.miracostacollege.cs134.wheretonext.model.JSONLoader;

public class MainActivity extends AppCompatActivity {

    //private DBHelper db;
    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;
    private ListView collegesListView;
    private EditText nameEditText;
    private EditText populationEditText;
    private EditText tuitionEditText;
    private RatingBar collegeRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        //db = new DBHelper(this);

        /* TODO: Comment this section out once the colleges below have been added to the database,
        // TODO: so they are not added multiple times (prevent duplicate entries)
        db.addCollege(new College("UC Berkeley", 42082, 14068, 4.7, "ucb.png"));
        db.addCollege(new College("UC Irvine", 31551, 15026.47, 4.3, "uci.png"));
        db.addCollege(new College("UC Los Angeles", 43301, 25308, 4.5, "ucla.png"));
        db.addCollege(new College("UC San Diego", 33735, 20212, 4.4, "ucsd.png"));
        db.addCollege(new College("CSU Fullerton", 38948, 6437, 4.5, "csuf.png"));
        db.addCollege(new College("CSU Long Beach", 37430, 6452, 4.4, "csulb.png")); */

        // TODO:  Fill the collegesList with all Colleges from the database
        // TODO:  Connect the list adapter with the list
        // TODO:  Set the list view to use the list adapter

        // Link views
        nameEditText = findViewById(R.id.nameEditText);
        populationEditText = findViewById(R.id.populationEditText);
        tuitionEditText = findViewById(R.id.tuitionEditText);
        collegeRatingBar = findViewById(R.id.collegeRatingBar);
        collegesListView = findViewById(R.id.collegeListView);


        // Populate college list from JSON
        try {
            collegesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("Where To Next", e.getMessage());
        }

        // Instantiate ListView using custom adapter
        collegesListAdapter = new CollegeListAdapter(this, R.layout.college_list_item, collegesList);
        collegesListView.setAdapter(collegesListAdapter);

    }

    public void viewCollegeDetails(View view) {

        // TODO: Implement the view college details using an Intent
        Intent detailsIntent = new Intent(this, CollegeDetailsActivity.class);
        int pos = (int) view.getTag();

        College selectedCollege = collegesList.get(pos);

        detailsIntent.putExtra("Name", selectedCollege.getName());
        detailsIntent.putExtra("Population", selectedCollege.getPopulation());
        detailsIntent.putExtra("Tuition", selectedCollege.getTuition());
        detailsIntent.putExtra("Rating", selectedCollege.getRating());
        detailsIntent.putExtra("ImageName", selectedCollege.getImageName());

        startActivity(detailsIntent);
    }

    public void addCollege(View view) {

        // TODO: Implement the code for when the user clicks on the addCollegeButton
        College newCollege = new College(nameEditText.getText().toString(),
                Integer.parseInt(populationEditText.getText().toString()),
                Double.parseDouble(tuitionEditText.getText().toString()),
                (double) collegeRatingBar.getRating());

        // TODO: update adapter list (?) and notify data change ( see ToDo2Day)
    }

}
