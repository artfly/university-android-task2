package io.github.noveo.artfly.task2.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import io.github.noveo.artfly.task2.app.fragments.DetailFragment;
import io.github.noveo.artfly.task2.app.fragments.ListFragment;
import io.github.noveo.artfly.task2.app.utils.TransactionUtilities;

public class MainActivity extends Activity implements ListFragment.ListFragmentListener {
    private static final String DETAILFRAGMENT_TAG = "DETAILFRAGMENT_TAG";
    private static final String LISTFRAGMENT_TAG = "LISTFRAGMENT_TAG";
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoPane = findViewById(R.id.detail_container) != null;
        if (twoPane) {
            if (!TransactionUtilities.isFragmentExisting(getFragmentManager(), DETAILFRAGMENT_TAG)) {
                TransactionUtilities.addFragment(getFragmentManager(),
                        R.id.detail_container, DetailFragment.newInstance(), DETAILFRAGMENT_TAG);
            }
        }
        if (!TransactionUtilities.isFragmentExisting(getFragmentManager(), LISTFRAGMENT_TAG)) {
            TransactionUtilities.addFragment(getFragmentManager(), R.id.list_container,
                    ListFragment.newInstance(), LISTFRAGMENT_TAG);
        }
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public AdapterView.OnItemClickListener getItemListener() {
        if (!twoPane) {
            return null;
        }
        return (AdapterView.OnItemClickListener) getFragmentManager().findFragmentByTag(DETAILFRAGMENT_TAG);
    }
}
