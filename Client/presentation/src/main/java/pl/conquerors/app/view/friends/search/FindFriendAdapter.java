package pl.conquerors.app.view.friends.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.CollectionViewHolder;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;

public class FindFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User>friends = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend_to_invite, viewGroup, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final CollectionViewHolder recyclerHolder = (CollectionViewHolder) viewHolder;
        recyclerHolder.bind(friends.get(position));
    }

    public void setItems(final List<User> friends) {
        if(friends == null) {
            return;
        }
        this.friends.clear();
        this.friends.addAll(friends);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder implements CollectionViewHolder<User> {

        @BindView(R.id.profile_avatar)
        ImageView avatar;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.invite)
        View invite;

        @BindView(R.id.joined_status)
        View friendStatus;

        @OnClick(R.id.invite)
        protected void onInviteButtonClick() {
            if(invite.getVisibility()==View.VISIBLE){
                Navigator.startFriendProfile(itemView.getContext(), userId);
            }
        }

        @OnClick(R.id.joined_status)
        protected void onStatusButtonClick() {
            if(friendStatus.getVisibility()==View.VISIBLE){
                Navigator.startFriendProfile(itemView.getContext(), userId);
            }
        }


        private long userId;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(User user) {
            userId = user.getUserId();
            //TODO load image
            name.setText(user.getUserNick());
            friendStatus.setVisibility(user.canInvite() ? View.GONE : View.VISIBLE);
            invite.setVisibility(friendStatus.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }
}
