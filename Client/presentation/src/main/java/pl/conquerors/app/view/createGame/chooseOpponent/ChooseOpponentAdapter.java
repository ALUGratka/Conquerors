package pl.conquerors.app.view.createGame.chooseOpponent;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.createGame.chooseCharacter.ChooseCharacterActivity;

public class ChooseOpponentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Object> itemsToDisplay = new ArrayList<>();
    private final List<User> friends = new ArrayList<>();

    private int selected = -1;

    public ChooseOpponentAdapter() { setUpItems();}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        final View view;
        final RecyclerView.ViewHolder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend, viewGroup, false);
        holder = new ChooseOpponentAdapter.FriendViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final CollectionViewHolder recycleHolder = (CollectionViewHolder) viewHolder;
        recycleHolder.bind(itemsToDisplay.get(position));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = position;
                notifyDataSetChanged();
            }
        });

        if(selected==position){
            viewHolder.itemView.setBackgroundResource(R.color.theme_transparent_grey);
        }
        else {
            viewHolder.itemView.setBackgroundResource(0);
        }
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    void setItems(final List<User> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
        setUpItems();
        notifyDataSetChanged();
    }

    private void setUpItems() {
        if(!friends.isEmpty()) {
            for (User user : friends) {
                itemsToDisplay.add(new ChooseOpponentAdapter.AdapterUser(user));
            }
        }
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder implements CollectionViewHolder<ChooseOpponentAdapter.AdapterUser> {

        @BindView(R.id.friend_image)
        ImageView image;

        @BindView(R.id.friend_name)
        TextView name;

        @BindView(R.id.friend_characters)
        TextView characters;

        @BindView(R.id.friend_games)
        TextView games;

        @BindView(R.id.item_view)
        View itemView;

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @OnClick(R.id.item_view)
        void userSelected() {
            SharedPreferenceUtil.setOpponent(itemView.getContext(), boundUser);
        }

        private User boundUser;

        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(final ChooseOpponentAdapter.AdapterUser adapterUser) {
            User user = adapterUser.getUser();
            name.setText(user.getUserNick());
            boundUser = user;


        }
    }

    private static class AdapterUser {
        private User user;

        public AdapterUser(final User user) {
            this.user = user;
        }

        public User getUser() { return user; }
    }
}
