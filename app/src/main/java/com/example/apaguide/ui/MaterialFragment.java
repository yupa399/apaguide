package com.example.apaguide.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.apaguide.R;

import java.util.HashMap;
import java.util.Map;

public class MaterialFragment extends Fragment implements View.OnClickListener {
    private Map<Integer, String> mapVideoId;    // YouTube video ID for Video Resources

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_material, container, false);

        mapVideoId = new HashMap<Integer, String>() {{
            Resources r = getResources();
            put(R.id.tvVideo1, r.getString(R.string.video_id_1));
            put(R.id.tvVideo2, r.getString(R.string.video_id_2));
            put(R.id.tvVideo3, r.getString(R.string.video_id_3));
            put(R.id.tvVideo4, r.getString(R.string.video_id_4));
        }};

        TextView tv;
        tv = root.findViewById(R.id.tvResearch);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvWriting);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaOfficial);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaBlog6);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv = root.findViewById(R.id.tvApaBlog7);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        // Set ClickListener on Video links
        for(Map.Entry<Integer, String> e : mapVideoId.entrySet()){
            tv = root.findViewById(e.getKey());
            tv.setOnClickListener(this);
            tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        return root;
    }

    @Override
    public void onClick(View v) {
        String videoId = mapVideoId.get(v.getId()); // Get YouTube video ID
        Intent appIntent = new Intent(Intent.ACTION_VIEW);
        appIntent.setData(Uri.parse("vnd.youtube:" + videoId));
        appIntent.putExtra("VIDEO_ID", videoId);
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + videoId));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
        startActivity(webIntent);  // Start YouTube Intent
    }
}
