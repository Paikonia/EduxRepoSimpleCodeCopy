package com.example.edux;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.edux.fragments.mainpagefragments.HomeFragment;
import com.example.edux.fragments.mainpagefragments.NoteAndBooksFragment;
import com.example.edux.fragments.mainpagefragments.ExaminationFragment;
import com.example.edux.fragments.mainpagefragments.PersonalNotesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainPageActivity extends AppCompatActivity  {



    Toolbar tb;
    BottomNavigationView bottonNav;
    NavController navFrag;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

        bottonNav = findViewById(R.id.bottom_navigation);

        //NavController controller = Navigation.findNavController(this, R.id.main_page_fragment_container);

        //NavigationUI.setupWithNavController(bottonNav,controller);


        bottonNav.setOnNavigationItemSelectedListener(item -> {
            Fragment frag = null;
            switch(item.getItemId()){
                case R.id.main_page_news_feed:
                    frag = new HomeFragment();
                    break;
                case R.id.main_page_contacts_and_groups:

                    Toast.makeText(this, "Still waiting on ideas to implement here", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.main_page_notes:
                    frag = new NoteAndBooksFragment();
                    break;
                case R.id.main_page_papers:
                    frag= new ExaminationFragment();
                    break;

                case R.id.main_page_personal_notes:
                    frag = new PersonalNotesFragment();
                    break;
            }

            if(frag!=null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_page_fragment_container, frag)
                        .commit();
            }

            return true;
        });

        if(savedInstanceState==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_page_fragment_container, new HomeFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_popup_menu, menu);
        return true;
    }


}
