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
import io.github.juanpmarin.evaluapp.domain.UserType;
import io.github.juanpmarin.evaluapp.ui.MainActivity;
import io.github.juanpmarin.evaluapp.ui.solve.SolveTestActivity;

public class TestsFragment extends Fragment implements Injectable, TestsController.AdapterCallbacks {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentTestsBinding binding;
    private TestsViewModel testsViewModel;
    private TestsController testsController;

    private UserType userType;
    private String userId;

    public static TestsFragment newInstance(UserType userType, String userId) {
        TestsFragment fragment = new TestsFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.USER_TYPE, userType.ordinal());
        args.putString(MainActivity.USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userType = UserType.values()[getArguments().getInt(MainActivity.USER_TYPE)];
            userId = getArguments().getString(MainActivity.USER_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tests, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getContext() != null) {
            testsController = new TestsController(getContext(), this);

            testsViewModel = ViewModelProviders.of(this, viewModelFactory)
                    .get(TestsViewModel.class);


            if (userType == UserType.STUDENT) {
                binding.fab.setVisibility(View.GONE);
            }

            binding.setOnAddClicked(v -> editTest());
            initTestsList();
        }
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

    private void solveTest(String id) {
        Intent intent = new Intent(getContext(), SolveTestActivity.class);
        intent.putExtra(SolveTestActivity.TEST_ID, id);
        intent.putExtra(MainActivity.USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void testClicked(String id) {
        if (userType == UserType.STUDENT) {
            solveTest(id);
        }
    }

}
