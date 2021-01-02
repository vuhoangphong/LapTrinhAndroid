package com.example.noteapplication.ui.note;

import androidx.core.app.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapplication.MainActivity;
import com.example.noteapplication.R;
import com.example.noteapplication.StatusActivity;
import com.example.noteapplication.ui.category.CategoryOJ;
import com.example.noteapplication.ui.category.Category_dialog;
import com.example.noteapplication.ui.category.category;
import com.example.noteapplication.ui.status.StatusViewModel;
import com.example.noteapplication.ui.status.status_dialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class note extends Fragment implements note_dialog.dialog_Add_Note_Listener {

    private NoteViewModel mViewModel;
    private List<NoteViewModel> listNote = new ArrayList<NoteViewModel>();

    private ListView listView;
    public static com.example.noteapplication.ui.note.note newInstance() {
        return new com.example.noteapplication.ui.note.note();
    }
    ArrayList<NoteViewModel> arrayNote = new ArrayList<NoteViewModel>();
    private com.example.noteapplication.ui.note.note.NoteAdapter adapter = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.note_fragment,container,false);
        FloatingActionButton myFab = view.findViewById(R.id.btnNote);
        listView = view.findViewById(R.id.lvNote);
        CalendarView calendarViewPlandate = (CalendarView)plandateView.findViewById(R.id.calendarView_note);
        calendarViewPlandate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayofMonth) {
                String date = year+"/"+(month+1)+"/"+dayofMonth;
                textViewPlandate.setText(date);
            }
        });
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openDialog();
            }
        });

        return view;


    }
    public  void openDialog(){
        note_dialog dialogAddNote = new note_dialog();
        dialogAddNote.show(getChildFragmentManager(),"example dialog");
    }

    public class NoteAdapter extends ArrayAdapter<NoteViewModel>{
        public NoteAdapter(Context context ,int textViewResourceId){
            super(context,textViewResourceId);
        }
        public NoteAdapter(){
            super(note.this.getContext(), android.R.layout.simple_list_item_1,listNote);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if( row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_note,null);
            }
            NoteViewModel c = listNote.get(position);
            ((TextView)row.findViewById(R.id.name)).setText(c.getName());
            ((TextView)row.findViewById(R.id.date)).setText(c.getCreatedate());
            return row;
        }
    }

    @Override
    public void addNote(String category, String date) {
        NoteViewModel s = new NoteViewModel();
        s.setName(category);
        s.setCategory(category);
        listNote.add(s);
        adapter = new com.example.noteapplication.ui.note.note.NoteAdapter() ;
        listView.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        // TODO: Use the ViewModel
    }

}