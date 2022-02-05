package com.example.gpacalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private int total_courses;
    private OnListListener mOnListListener;
    public ListAdapter(int total_courses, OnListListener onListListener)
    {
        this.total_courses = total_courses;
        this.mOnListListener = onListListener;
    }

    public void add_course(int course)
    {
        total_courses+=course;

    }
    public  void remove_course(int course)
    {
        total_courses-=course;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);

        return new ListViewHolder(view, mOnListListener);
    }



    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return total_courses;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        Button course_delete_btn;
        EditText course_name;
        EditText credit_hours;
        AutoCompleteTextView grade_received;
        OnListListener onListListener;
        public ListViewHolder(@NonNull View itemView, OnListListener onListListener) {

            super(itemView);

            course_name = itemView.findViewById(R.id.course_name_editText);
            credit_hours = itemView.findViewById(R.id.credit_hours_editText);

            String[] type = new String[] {"A", "A-", "B+", "B","B-", "C+","C","C-","D+","D","F"};
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(itemView.getContext(), R.layout.grades_drop_down_layout, type);
            grade_received = itemView.findViewById(R.id.grade_drop_down);
            grade_received.setAdapter(adapter);
            //course_delete_btn = itemView.findViewById(R.id.course_delete_btn);
            this.onListListener = onListListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            onListListener.onListClick(getAdapterPosition());
            total_courses--;

            course_name.setText("");
            credit_hours.setText("");
            grade_received.setText("");

            notifyItemChanged(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            return true;
        }
    }

    public interface OnListListener{
        void onListClick(int position);
    }


}
