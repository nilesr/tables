package org.opendatakit.tables.fragments;

import android.app.Fragment;
import android.view.View;

import org.opendatakit.activities.IOdkCommonActivity;
import org.opendatakit.database.data.BaseTable;
import org.opendatakit.database.queries.BindArgs;
import org.opendatakit.database.service.DbHandle;
import org.opendatakit.database.service.UserDbInterface;
import org.opendatakit.logging.WebLogger;
import org.opendatakit.tables.R;
import org.opendatakit.tables.application.Tables;

/**
 * Created by Niles on 8/4/17.
 */

class TableAddAccessChecker implements Runnable {
    private Fragment fragment;
    public TableAddAccessChecker(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void run() {
        IOdkCommonActivity act = (IOdkCommonActivity) fragment.getActivity();
        String tableId = act.getTableId();
        boolean can_add = true;
        UserDbInterface dbInt = Tables.getInstance().getDatabase();
        try {
            DbHandle db = dbInt.openDatabase(act.getAppName());
            BaseTable result = dbInt.simpleQuery(act.getAppName(), db, tableId, "0", new BindArgs(), new String[0], "0", new String[0], new String[0], 0, 0);
            can_add = result.getEffectiveAccessCreateRow();
        } catch (Exception e) {
            WebLogger.getLogger(act.getAppName()).printStackTrace(e);
        }
        if (!can_add) {
            View view = fragment.getView();
            if (view != null) {
                View add_button = view.findViewById(R.id.top_level_table_menu_add);
                if (add_button != null) {
                    add_button.setVisibility(View.GONE);
                }
            }
        }
    }
    public static void check(Fragment fragment) {
        View view = fragment.getView();
        if (view != null) {
            view.post(new TableAddAccessChecker(fragment));
        }
    }
}
