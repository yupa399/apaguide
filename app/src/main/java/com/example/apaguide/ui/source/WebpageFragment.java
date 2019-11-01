package com.example.apaguide.ui.source;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.apaguide.R;
import com.example.apaguide.ui.ReferencingFragment;

public class WebpageFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ref_base, container, false);
        if (root.findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return root;
            }
            // Create a new Fragment to be placed in the layout
            ReferencingFragment fragment = new ReferencingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("category", "Web Page");
            bundle.putInt("categoryId", 2);
            fragment.setArguments(bundle);
            // Add the fragment to the 'fragment_container' FrameLayout
            getChildFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }
        return root;
    }
}
