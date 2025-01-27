package com.example.recyclerviewstudentversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import java.util.ArrayList;

// Todo create a player class that will hold info about the player
public class MainActivity extends AppCompatActivity {
    // Todo initialize these variables
    private RecyclerView recyclerView;
    private MyRecyclerAdapter mAdapter;
    private GridLayoutManager layoutManager;
    private ArrayList<Player> list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<>();
        getPlayers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        mAdapter = new MyRecyclerAdapter(this, list);
        layoutManager = new GridLayoutManager(this, 1);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                return mAdapter.swapPositions(fromPos, toPos);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT | direction == ItemTouchHelper.RIGHT) {
                    mAdapter.removeFromList(viewHolder.getAdapterPosition());
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                MyRecyclerAdapter.MyViewHolder vh = (MyRecyclerAdapter.MyViewHolder) viewHolder;
                LinearLayout ll = vh.linearLayout;

                Paint p = new Paint();
                if(dX > 0) {
                    p.setARGB(255, 255, 0, 0);
                } else if (dX < 0){
                    p.setARGB(255, 0, 255, 0);
                } else {
                    p.setARGB(255, 250, 250, 250);
                }

                c.drawRect(ll.getLeft(), ll.getTop(), ll.getRight(), ll.getBottom(), p);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }
    //Todo create method that will fill list of players
    public void getPlayers(){
        String[] name = new String[] {"Tristan Thompson", "James Harden","Kyrie Irving","Stephen Curry","Russell Westbrook",
                "Derrick Rose","Kevin Durant","Dwyane Wade","Kobe Bryant","LeBron James",
                "Magic Johnson","Miro Jurisic","Kareem Abdul-Jabbar","Shaquille O'Neal","Chris Paul"};
        int[] age = new int[]{28,30,27,31,30,
                31,31,37,41,34,
                60,18,72,47,34};
        int[] imageResource = new int[]{R.drawable.tristanthompson,R.drawable.jamesharden,R.drawable.kyrieirving,R.drawable.stephencurry,R.drawable.russellwestbrook,
                R.drawable.derrickrose,R.drawable.kevindurant,R.drawable.dwyanewade,R.drawable.kobebryant,R.drawable.lebronjames,
                R.drawable.magicjohnson,R.drawable.mirojurisic,R.drawable.kareemabduljabbar,R.drawable.shaquilleoneal,R.drawable.chrispaul};
        int[] worth = new int[]{8,120,70,130,150,
                85,170,120,500,480,
                600,1000,20,400,120};
        String[] url = new String[]{"https://www.biography.com/athlete/tristan-thompson","https://www.biography.com/athlete/james-harden","https://www.biography.com/athlete/kyrie-irving","https://www.biography.com/athlete/stephen-curry","https://www.biography.com/athlete/russell-westbrook",
                "https://www.biography.com/athlete/derrick-rose","https://www.biography.com/athlete/kevin-durant","https://www.biography.com/athlete/dwyane-wade","https://www.biography.com/athlete/kobe-bryant","https://www.biography.com/athlete/lebron-james",
                "https://www.biography.com/athlete/magic-johnson","https://www.biography.com/athlete/miro-jurisic","https://www.biography.com/athlete/kareem-abdul-jabbar","https://www.biography.com/athlete/shaquille-oneal","https://www.biography.com/athlete/chris-paul"};
        for(int x = 0; x < 15 ; x++){
            list.add(new Player(name[x], age[x], worth[x], "Basketball", imageResource[x], url[x]));
        }
    }
}
