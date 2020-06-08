package pl.conquerors.app.view.showCharacters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.navigation.Navigator;

public class ShowCharactersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> itemsToDisplay = new ArrayList<>();
    private final List<Character> characters = new ArrayList<>();

    public ShowCharactersAdapter() {
        setUpItems();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view;
        final RecyclerView.ViewHolder viewHolder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_character, viewGroup, false);
        viewHolder = new CharacterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final CollectionViewHolder recycleHolder = (CollectionViewHolder) viewHolder;
        recycleHolder.bind(itemsToDisplay.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    void setItems(final List<Character> characters) {
        this.characters.clear();
        this.characters.addAll(characters);
        setUpItems();
        notifyDataSetChanged();
    }

    private void setUpItems() {
        if(!characters.isEmpty())
            for(Character character : characters) {
                itemsToDisplay.add(new AdapterCharacter(character));
            }
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder implements  CollectionViewHolder<AdapterCharacter>{

        @BindView(R.id.character_class_signature)
        ImageView characterClassSignatureImage;

        @BindView(R.id.character_name)
        TextView name;

        @BindView(R.id.character_class)
        TextView characterClass;

        @OnClick(R.id.show_statistics)
        void characterSelected() {
            Navigator.startCharacterStatistics(itemView.getContext(), characterId);
        }

        private int characterId;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(AdapterCharacter adapterCharacter) {
            Character character = adapterCharacter.getCharacter();
            name.setText(character.getmNickname());
            characterId = character.getmId();

            if(character.getmCharacterClass()==0){
                characterClassSignatureImage.setImageResource(R.drawable.ic_bard_class);
                characterClass.setText(itemView.getResources().getString(R.string.radio_bard));
            }
            else if (character.getmCharacterClass()==1){
                characterClassSignatureImage.setImageResource(R.drawable.ic_thief_class);
                characterClass.setText(itemView.getResources().getString(R.string.radio_thief));
            }
            else if (character.getmCharacterClass()==2){
                characterClassSignatureImage.setImageResource(R.drawable.ic_warior_class);
                characterClass.setText(itemView.getResources().getString(R.string.radio_warrior));
            }
            else if (character.getmCharacterClass()==3){
                characterClassSignatureImage.setImageResource(R.drawable.ic_wizard_class);
                characterClass.setText(itemView.getResources().getString(R.string.radio_wizard));
            }
            Log.i("class", String.valueOf(character.getmCharacterClass()));
        }
    }

    private static class AdapterCharacter {
        private Character character;

        public AdapterCharacter(final Character character) {
            this.character = character;
        }

        public Character getCharacter() { return character; }
    }
}
