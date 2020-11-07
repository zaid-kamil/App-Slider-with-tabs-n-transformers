package digipodium.sliderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.pager);
        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager(), 0);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        pager.setPageTransformer(true, new DepthPageTransformer());
    }

    class IntroAdapter extends FragmentStatePagerAdapter {


        public IntroAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new F1Fragment();
                case 1:
                    return new F2Fragment();
                case 2:
                    return new F3Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ONE";
                case 1:
                    return "TWO";
                case 2:
                    return "THREE";
            }
            return super.getPageTitle(position);
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    // step1 onCreateOptionsMenu - add menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        // getactivity().getMenuInflater() if u use fragment
        return true;
    }

    // step2 onOptionsItemSelected - handle click on items

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_exit:
                confirmAppExit();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void confirmAppExit() {
        AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setTitle("Warning");
        exitDialog.setMessage("Do you really want to exit?");
        exitDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", (df, i) -> {
            finish(); // closes app
        });
        exitDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No",(df,i)->{
            df.cancel(); // close the dialog
        });
        exitDialog.show();
    }

    @Override
    public void onBackPressed() {
        confirmAppExit();
    }
}