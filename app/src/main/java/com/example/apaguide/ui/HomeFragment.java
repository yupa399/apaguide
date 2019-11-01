package com.example.apaguide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.apaguide.R;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = root.findViewById(R.id.btnBook);
        button.setOnClickListener(this);
        button = root.findViewById(R.id.btnWebpage);
        button.setOnClickListener(this);
        button = root.findViewById(R.id.btnMagazine);
        button.setOnClickListener(this);
        button = root.findViewById(R.id.btnJournal);
        button.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int navId = 0;
        if(id == R.id.btnBook){       // Book
            navId = R.id.nav_book;
        }
        else if(id == R.id.btnWebpage){    // Web page
            navId = R.id.nav_webpage;
        }
        else if(id == R.id.btnMagazine){    // Magazine
            navId = R.id.nav_magazine;
        }
        else if(id == R.id.btnJournal){    // Journal
            navId = R.id.nav_journal;
        }
        if(navId == 0) return;
        NavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.getMenu().performIdentifierAction(navId, 0);

//        navView.getMenu().findItem(R.id.nav_book).setChecked(false);
//        navView.getMenu().findItem(R.id.nav_webpage).setChecked(false);
//        navView.getMenu().findItem(R.id.nav_magazine).setChecked(false);
//        navView.getMenu().findItem(R.id.nav_journal).setChecked(false);
//        MenuItem mi = navView.getMenu().findItem(navId);
//        mi.setChecked(true);
    }
}
