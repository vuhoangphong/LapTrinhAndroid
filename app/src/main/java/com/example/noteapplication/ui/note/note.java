package com.example.noteapplication.ui.note;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.noteapplication.R;
import com.example.noteapplication.ui.category.CategoryOJ;
import com.example.noteapplication.ui.category.Category_dialog;
import com.example.noteapplication.ui.category.category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class note extends Fragment implements Note_dialog.Note_dialog_listen{

    private NoteViewModel mViewModel;
    private List<noteOJ> noteOJList = new ArrayList<noteOJ>();
    private noteAdapter adapter =null ;
    ListView listView;

    public static note newInstance() {
        return new note();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.note_fragment, container, false);
        FloatingActionButton btn = v.findViewById(R.id.addNote);
        listView =v.findViewById(R.id.listnote);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return  v;
    }

    // open dialog add note
    public  void openDialog(){
        Note_dialog noteDialog = new Note_dialog();
        noteDialog.show(getChildFragmentManager(),"example dialog");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void addNote(noteOJ noteOJ) {
        noteOJList.add(noteOJ);
        adapter = new noteAdapter();
        listView.setAdapter(adapter);
    }
    // adapter
    public class noteAdapter extends ArrayAdapter<noteOJ> {
        public noteAdapter(Context context , int textViewResourceId){
            super(context,textViewResourceId);
        }
        public noteAdapter(){
            super(note.this.getContext(), android.R.layout.simple_list_item_1,noteOJList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_note,null);
            }
            noteOJ note = noteOJList.get(position);
            ((TextView)row.findViewById(R.id.nameNote)).setText("Name: "+note.getName());
            ((TextView)row.findViewById(R.id.txtcategory)).setText("Category: "+note.getCategory());
            ((TextView)row.findViewById(R.id.txtpriority)).setText("Priority: "+note.getPriority());
            ((TextView)row.findViewById(R.id.txtsatatus)).setText("Status: "+note.getStatus());
            ((TextView)row.findViewById(R.id.txtplandate)).setText("Plan date: "+note.getPlanDate());
            ((TextView)row.findViewById(R.id.txtcreatedate)).setText("Create date: "+note.getCreateDate());
            return row;
        }
    }
}