package io.github.juanpmarin.evaluapp.ui.results;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.FragmentResultsBinding;
import io.github.juanpmarin.evaluapp.di.Injectable;

public class ResultsFragment extends Fragment implements Injectable, ResultsController.AdapterCallbacks {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentResultsBinding binding;
    private ResultsViewModel resultsViewModel;
    private ResultsController resultsController;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getContext() != null) {
            resultsController = new ResultsController(getContext(), this);

            resultsViewModel = ViewModelProviders.of(this, viewModelFactory)
                    .get(ResultsViewModel.class);

            initTestsList();
        }
    }

    private void initTestsList() {
        binding.setAdapter(resultsController.getAdapter());
        resultsViewModel.findAllResults().observe(this, results -> {
            if (results != null) {
                resultsController.setData(results);
            }
        });
    }

    @Override
    public void resultClicked(String id) {
        Intent intent = new Intent(getActivity(), ViewResultActivity.class);
        intent.putExtra(ViewResultActivity.RESULT_ID, id);

        startActivity(intent);
    }

}
