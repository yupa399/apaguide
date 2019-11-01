package com.example.apaguide.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.apaguide.R;
import com.example.apaguide.model.Example;
import com.example.apaguide.model.OptionFilter;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PlaceholderFragment extends Fragment implements View.OnClickListener, OptionFilterDialog.OptionFilterDialogListener {
    private static final String ARG_CATEGORY_ID = "categoryId";
    private static final String ARG_SUBCATEGORY_ID = "subCategoryId";
    private PageViewModel viewModel;

    public static PlaceholderFragment newInstance(int categoryId, int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_CATEGORY_ID, categoryId);
        bundle.putInt(ARG_SUBCATEGORY_ID, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        int categoryId = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SUBCATEGORY_ID);
            categoryId = getArguments().getInt(ARG_CATEGORY_ID);
        }
        viewModel.setIndex(index);
        viewModel.setCategoryId(categoryId);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        viewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_placeholder, container, false);
        Button btn = root.findViewById(R.id.btnSearch);
        btn.setOnClickListener(this);

        viewModel.getExamples().observe(this, new Observer<List<Example>>() {
            @Override
            public void onChanged(@Nullable List<Example> examples) {
                createContents(root, examples);
            }
        });

        return root;
    }

    public void createContents(View root, List<Example> examples){
        LinearLayout layout = root.findViewById(R.id.contents);
        Context context = getActivity();

        for(Example cont: examples){
            TextView txt;
            txt = new TextView(context);
            txt.setText(cont.description1);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txt.setPadding(0,24,0,16);
            layout.addView(txt);

            MaterialCardView cardView;
            cardView = new MaterialCardView(context);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorAisPink1));
            cardView.setContentPadding(16, 8,16, 16);
            ViewGroup.LayoutParams ppp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(ppp);

            txt = new TextView(context);
            txt.setText(Html.fromHtml(cont.example));
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            txt.setTextColor(Color.BLACK);
            cardView.addView(txt);
            layout.addView(cardView);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSearch){       // Option Filter
            openOptionFilterDialog();   // open Option Filter Dialog
        }
    }

    // Open Option Filter Dialog
    public void openOptionFilterDialog() {
        final OptionFilterDialog dialog = new OptionFilterDialog();
        Bundle args = new Bundle();
        args.putSerializable("option", viewModel.GetOptionFilter());
        dialog.setArguments(args);
        dialog.setOnOkButtonClickListerner(this);
        dialog.show(getChildFragmentManager(), "OptionFilterDialog");
    }

    // Click Apply in Option Filter dialog
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, OptionFilter optionFilter) {
        viewModel.setOptionFiletr(optionFilter);
    }
}
