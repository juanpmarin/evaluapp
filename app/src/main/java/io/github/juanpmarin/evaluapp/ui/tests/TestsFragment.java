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
import io.github.juanpmarin.evaluapp.domain.Status;

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

        binding.setAdapter(testsController.getAdapter());

        return binding.getRoot();
    }

    private void initTestsList() {
        testsViewModel.getTests().observe(this, testsResource -> {
            if (testsResource != null && testsResource.data != null) {
                testsController.setData(testsResource.data);
                binding.list.post(() -> binding.setShowHint(testsResource.status != Status.LOADING &&
                        testsResource.data.isEmpty()));
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
