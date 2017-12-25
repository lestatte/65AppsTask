package ru.sample.elestatte.test65apps.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.utility.Utils;

/**
 * Fragment for displaying detail info of employer (appears after click on item in list).
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class DetailEmployerFragment extends Fragment {

    @BindView(R.id.title_last_name)
    TextView mLastName;

    @BindView(R.id.title_first_name)
    TextView mFirstName;

    public static DetailEmployerFragment create(Employer item) {
        DetailEmployerFragment fragment = new DetailEmployerFragment();
        Bundle args = new Bundle();
        args.putString("l_name", Utils.capitalize(item.lName).trim());
        args.putString("f_name", Utils.capitalize(item.fName).trim());
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detail_employer, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (null != args) {
            mLastName.setText(args.getString("l_name"));
            mFirstName.setText(args.getString("f_name"));
        }
        return view;
    }

}
