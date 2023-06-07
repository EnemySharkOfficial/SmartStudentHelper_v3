package com.example.smartstudenthelper.CourseTask.screens.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.CourseTask.CourseTask;
import com.example.smartstudenthelper.CourseTask.screens.details.CourseTaskDetailsActivity;
import com.example.smartstudenthelper.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CourseTaskViewHolder>
{
    private SortedList<CourseTask> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(CourseTask.class, new SortedList.Callback<CourseTask>() {
            @Override
            public int compare(CourseTask o1, CourseTask o2) {
                if (!o2.isDone() && o1.isDone()) {
                    return 1;
                }
                if (o2.isDone() && !o1.isDone()) {
                    return -1;
                }
                return (int) (o2.getTimestamp() - o1.getTimestamp());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(CourseTask oldItem, CourseTask newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(CourseTask item1, CourseTask item2) {
                return item1.getUid() == item2.getUid();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public CourseTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseTaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseTaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<CourseTask> courseTasks) {
        sortedList.replaceAll(courseTasks);
    }

    static class CourseTaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox completed;
        View delete;

        CourseTask courseTask;

        boolean silentUpdate;

        public CourseTaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.task_text);
            completed = itemView.findViewById(R.id.completed);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CourseTaskDetailsActivity.start((Activity) itemView.getContext(), courseTask);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getCourseTaskDao().delete(courseTask);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!silentUpdate) {
                        courseTask.setDone(checked);
                        App.getInstance().getCourseTaskDao().update(courseTask);
                    }
                    updateStrokeOut();
                }
            });

        }

        public void bind(CourseTask courseTask) {
            this.courseTask = courseTask;

            taskText.setText(courseTask.getName());
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(courseTask.isDone());
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (courseTask.isDone()) {
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
