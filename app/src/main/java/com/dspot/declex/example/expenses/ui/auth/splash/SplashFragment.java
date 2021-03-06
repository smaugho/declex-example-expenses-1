package com.dspot.declex.example.expenses.ui.auth.splash;

import android.support.v4.app.Fragment;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends Fragment {

    @ViewModel
    SplashViewModel splashViewModel;

}
