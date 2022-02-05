package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnListListener {
    Button add_course_btn;
    Button calculate;
    int total_courses=0;
    double grade_point=0;
    double total_grade_points=0;
    int total_ch =0;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new ListAdapter(total_courses, this);

        add_course_btn = findViewById(R.id.add_course_btn);
        RecyclerView recyclerView = findViewById(R.id.course_list);
        calculate = findViewById(R.id.calcbtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(listAdapter);
        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total_courses++;
                listAdapter.add_course(1);
                listAdapter.notifyItemInserted(total_courses);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<listAdapter.getItemCount();i++)
                {
                    View myview = recyclerView.getChildAt(i);
                    EditText course = (EditText) myview.findViewById(R.id.course_name_editText);
                    EditText grade = (EditText) myview.findViewById(R.id.grade_received_editText);
                    EditText credit = (EditText) myview.findViewById(R.id.credit_hours_editText);

                    String course_name = course.getText().toString();
                    String grade_received = grade.getText().toString();
                    int credit_hours=0;
                    try{
                        credit_hours = Integer.parseInt(credit.getText().toString());
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                    Log.d("Course Name", course_name);
                    switch (grade_received)
                    {
                        case "A":
                            grade_point=4.00;
                            break;
                        case "A-":
                            grade_point = 3.67;
                            break;
                        case "B+":
                            grade_point = 3.33;
                            break;
                        case "B":
                            grade_point = 3.00;
                            break;
                        case "B-":
                            grade_point = 2.67;
                            break;
                        case "C+":
                            grade_point = 2.33;
                            break;
                        case "C":
                            grade_point = 2.00;
                            break;
                        case "C-":
                            grade_point = 1.67;
                            break;
                        case "D+":
                            grade_point = 1.33;
                            break;
                        case "D":
                            grade_point = 1.00;
                            break;
                        case "F":
                            grade_point = 0.00;
                            break;
                        default:
                            break;
                    }
                    total_ch+=credit_hours;
                    total_grade_points+=(credit_hours*grade_point);

                    //Toast.makeText(this, "Course Name: " ,Toast.LENGTH_SHORT).show();


                }
                double sgpa = total_grade_points/total_ch;
                Log.d("GPA is", String.valueOf(sgpa));
                Toast.makeText(getApplicationContext(), String.format("GPA is:\n %.2f",sgpa), Toast.LENGTH_LONG).show();
                total_ch=0;
                total_grade_points=0;
            }
        });





    }

    @Override
    public void onListClick(int position) {


    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            listAdapter.remove_course(1);
            EditText course = viewHolder.itemView.findViewById(R.id.course_name_editText);
            EditText grade = viewHolder.itemView.findViewById(R.id.grade_received_editText);
            EditText credit = viewHolder.itemView.findViewById(R.id.credit_hours_editText);
            course.setText("");
            grade.setText("");
            credit.setText("");
            listAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

        }
    };
}