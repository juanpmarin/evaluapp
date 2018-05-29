package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.FragmentTestsBinding;
import io.github.juanpmarin.evaluapp.di.Injectable;

public class TestsFragment extends Fragment implements Injectable, TestsController.AdapterCallbacks {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentTestsBinding binding;
    private TestsViewModel testsViewModel;
    private TestsController testsController;

    public static TestsFragment newInstance() {
        TestsFragment fragment = new TestsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        testsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TestsViewModel.class);

        binding.setOnAddClicked(v -> editTest());
        initTestsList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tests, container, false);

        if (getContext() != null) {
            testsController = new TestsController(getContext(), this);
        }

        return binding.getRoot();
    }

    private void initTestsList() {
        binding.setAdapter(testsController.getAdapter());
        testsViewModel.getTests().observe(this, tests -> {
            if (tests != null) {
                testsController.setData(tests);
                binding.list.post(() -> binding.setShowHint(tests.isEmpty()));
            }
        });
    }

    private void editTest() {
        editTest(null);
    }

    private void editTest(String id) {
        Intent intent = new Intent(getContext(), EditTestActivity.class);

        if (!TextUtils.isEmpty(id)) {
            intent.putExtra(EditTestActivity.TEST_ID, id);
        }

        startActivity(intent);
    }

    @Override
    public void testClicked(String id) {
        editTest(id);
    }

}
