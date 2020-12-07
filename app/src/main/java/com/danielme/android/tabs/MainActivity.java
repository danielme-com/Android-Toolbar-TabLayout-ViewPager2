package com.danielme.android.tabs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.viewpager2.widget.ViewPager2;

import com.danielme.tabs.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

  private TabLayout tabLayout;
  private ViewPager2 viewPager;
  private AppBarLayout appBarLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupViewPager();
    setupTabLayout();
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void setupViewPager() {
    viewPager = findViewById(R.id.viewpager);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
            DividerItemDecoration.HORIZONTAL);
    dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
    viewPager.addItemDecoration(dividerItemDecoration);
    viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle()));
    appBarLayout = findViewById(R.id.appBar);
    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position != ViewPagerAdapter.Tab.MOVIES.position) {
          appBarLayout.setExpanded(true);
        }
      }
    });
  }

  private void setupTabLayout() {
    tabLayout = findViewById(R.id.tabs);
    new TabLayoutMediator(tabLayout, viewPager,
            (tab, position) -> {
              tab.setText(ViewPagerAdapter.Tab.byPosition(position).title);
              tab.setIcon(ViewPagerAdapter.Tab.byPosition(position).icon);
            })
            .attach();

    tabLayout.getTabAt(ViewPagerAdapter.Tab.FAV.position)
            .getOrCreateBadge()
            .setVisible(true);
    tabLayout.getTabAt(ViewPagerAdapter.Tab.MOVIES.position)
            .getOrCreateBadge()
            .setNumber(20);
    applyBadgeColor(ViewPagerAdapter.Tab.FAV.position);
    applyBadgeColor(ViewPagerAdapter.Tab.MOVIES.position);
  }

  private void applyBadgeColor(int pos) {
    tabLayout.getTabAt(pos)
            .getOrCreateBadge()
            .setBackgroundColor(getResources().getColor(R.color.tab_badge));
  }

}
