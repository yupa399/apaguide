package com.example.apaguide.ui;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.apaguide.R;

public class MaterialFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_material, container, false);
        TextView tv = root.findViewById(R.id.tvResearch);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvWriting);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaOfficial);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaBlog6);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaBlog7);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return root;
    }
}
