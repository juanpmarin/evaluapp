package io.gitlab.innom.evaluapp.ui.results;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.gitlab.innom.evaluapp.R;
import io.gitlab.innom.evaluapp.ui.tests.TestsFragment;

public class ResultsFragment extends Fragment {

    public static ResultsFragment newInstance() {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            ResultsController resultsController = new ResultsController(context);

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(resultsController.getAdapter());

            resultsController.setData(null);
        }

        return view;
    }

}
