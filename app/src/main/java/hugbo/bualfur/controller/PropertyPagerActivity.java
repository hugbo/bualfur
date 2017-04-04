package hugbo.bualfur.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import hugbo.bualfur.R;
import hugbo.bualfur.model.Property;
import hugbo.bualfur.storage.PropertyFetcher;

/**
 * Created by Hildur on 4/4/2017.
 */

public class PropertyPagerActivity extends FragmentActivity {
    private static final String EXTRA_PROPERTY_ID=
            "hugbo.bualfur.property_id";

    private ViewPager mViewPager;
    private List<Property> mProperties;

    public static Intent newIntent(Context packageContext, UUID propertyId) {
        Intent intent = new Intent(packageContext, PropertyPagerActivity.class);
        intent.putExtra(EXTRA_PROPERTY_ID, propertyId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_pager);

        UUID propertyId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_PROPERTY_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_property_pager_view_pager);
        mProperties = PropertyFetcher.getInstance(this).getProperties();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Property property = mProperties.get(position);
                return ViewPropertyFragment.newInstance(property.getmId());
            }
            @Override
            public int getCount() {
                return mProperties.size();
            }
        });
        for (int i = 0; i < mProperties.size(); i++) {
            if (mProperties.get(i).getmId().equals(propertyId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
