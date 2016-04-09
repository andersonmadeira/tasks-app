package br.com.andersonmadeira.tasks; 

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StartActivity extends AppCompatActivity {

    private List<String> objects;
    private ArrayAdapter<String> adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getSupportActionBar().setElevation(0);

        final ListView listView = (ListView) findViewById(R.id.listview);

        objects = new ArrayList<>(Arrays.asList( "Task Item 1", "Task Item 2", "Task Item 3",
                "Task Item 4", "Task Item 5", "Task Item 6", "Task Item 7", "Task Item 8"));

        adapter = new ArrayAdapter<String>(this, R.layout.listview_row, objects);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Toast.makeText(StartActivity.this, parent.getItemAtPosition(position) + " Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(1000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                objects.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
                return true;
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.add("Task Item " + (objects.size() + 1));
                adapter.notifyDataSetChanged();
            }
        });


    } 

} 