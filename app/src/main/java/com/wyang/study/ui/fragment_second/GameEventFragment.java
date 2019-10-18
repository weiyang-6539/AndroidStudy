package com.wyang.study.ui.fragment_second;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wyang.study.R;
import com.wyang.study.bean.Team;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.widget.GameEventView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by weiyang on 2019-10-17.
 */
public class GameEventFragment extends BaseFragment {
    @BindView(R.id.mGameEventView)
    GameEventView mGameEventView;

    private List<Team> list = new ArrayList<>();

    private LayoutInflater mInflater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game_event;
    }

    @Override
    protected void initView() {

        mInflater = LayoutInflater.from(getContext());

        int teamCount =  16;
        for (int i = 0; i < teamCount; i++) {
            int serial = i + 1;

            Team team = new Team("队伍-" + serial, String.valueOf(serial));
            list.add(team);
        }

        mGameEventView.setLowestCount(teamCount, new GameEventView.GameEventAdapter<Team>(list) {
            @Override
            public View getView(int position) {
                View v = mInflater.inflate(R.layout.item_team, null);
                TextView tv = v.findViewById(R.id.tv_name);
                if (position <= list.size() - 1)
                    tv.setText(list.get(position).name);
                return v;
            }
        });

    }

}
