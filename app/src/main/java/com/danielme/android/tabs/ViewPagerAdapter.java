package com.danielme.android.tabs;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.danielme.android.tabs.fragments.RecyclerViewFragment;
import com.danielme.android.tabs.fragments.TabNameFragment;
import com.danielme.tabs.R;

import java.util.HashMap;
import java.util.Map;

class ViewPagerAdapter extends FragmentStateAdapter {

  enum Tab {

    HOME(0, R.string.tab_home, R.drawable.baseline_home_white_24),
    FAV(1, R.string.tab_fav, R.drawable.baseline_favorite_white_24),
    MUSIC(2, R.string.tab_music, R.drawable.baseline_audiotrack_white_24),
    MOVIES(3, R.string.tab_movies, R.drawable.baseline_movie_white_24);
    final int title;
    final int icon;
    final int position;

    Tab(int position, @StringRes int title, @DrawableRes int icon) {
      this.position = position;
      this.title = title;
      this.icon = icon;
    }

    private static final Map<Integer,Tab> map;
    static {
      map = new HashMap<>();
      for (Tab t : Tab.values()) {
        map.put(t.position, t);
      }
    }

    static Tab byPosition(int position) {
      return map.get(position);
    }
  }

  public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    if (position == Tab.HOME.position)
      return TabNameFragment.newInstance(Tab.HOME.title);
    else if (position == Tab.FAV.position)
      return TabNameFragment.newInstance(Tab.FAV.title);
    else if (position == Tab.MUSIC.position)
      return TabNameFragment.newInstance(Tab.MUSIC.title);
    else if (position == Tab.MOVIES.position)
      return new RecyclerViewFragment();
    else
      throw new IllegalArgumentException("unknown position " + position);
  }

  @Override
  public int getItemCount() {
    return Tab.values().length;
  }
}
