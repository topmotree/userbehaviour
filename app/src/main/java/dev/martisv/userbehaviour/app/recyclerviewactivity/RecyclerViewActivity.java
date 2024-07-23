package dev.martisv.userbehaviour.app.recyclerviewactivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.martisv.userbehaviour.app.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view_example);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        exampleList = generateRandomData();
        adapter = new ExampleAdapter(exampleList);
        recyclerView.setAdapter(adapter);
    }

    private List<ExampleItem> generateRandomData() {
        List<ExampleItem> items = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            items.add(new ExampleItem(
                    "Number: " + (i + 1),
                    "Title: " + (random.nextInt(100) + 1),
                    "Subtitle: " + (random.nextInt(100) + 1)
            ));
        }
        return items;
    }
}
