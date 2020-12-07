package com.danielme.android.tabs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.danielme.tabs.R;

public class TabNameFragment extends Fragment {

  private static final String ARG_TAB_NAME = "ARG_TAB_NAME";

  public static TabNameFragment newInstance(@StringRes int tabName) {
    TabNameFragment frg = new TabNameFragment();

    Bundle args = new Bundle();
    args.putInt(ARG_TAB_NAME, tabName);
    frg.setArguments(args);

    return frg;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
          Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_tab, container, false);

    String title = getString(getArguments().getInt(ARG_TAB_NAME));
    ((TextView) layout.findViewById(R.id.textView)).setText(title);

    return layout;
  }

}
