package com.example.smartstudenthelper.СontrolTask;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class ControlTask implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "timestamp")
    private long timestamp;
    @ColumnInfo(name = "done")
    private boolean done;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "commentary")
    private String commentary;
    @ColumnInfo(name = "deadline")
    private long deadline;
    @ColumnInfo(name = "executionTime")
    private long executionTime;
    @ColumnInfo(name = "reminderDate")
    private long reminderDate;

    public ControlTask() {
    }

    // Геттеры и сеттеры для полей класса
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlTask that = (ControlTask) o;
        return uid == that.uid &&
                timestamp == that.timestamp &&
                done == that.done &&
                deadline == that.deadline &&
                executionTime == that.executionTime &&
                reminderDate == that.reminderDate &&
                Objects.equals(name, that.name) &&
                Objects.equals(commentary, that.commentary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, timestamp, done, name, commentary, deadline, executionTime, reminderDate);
    }

    protected ControlTask(Parcel in) {
        uid = in.readInt();
        timestamp = in.readLong();
        done = in.readByte() != 0;
        name = in.readString();
        commentary = in.readString();
        deadline = in.readLong();
        executionTime = in.readLong();
        reminderDate = in.readLong();
    }

    public static final Creator<ControlTask> CREATOR = new Creator<ControlTask>() {
        @Override
        public ControlTask createFromParcel(Parcel in) {
            return new ControlTask(in);
        }

        @Override
        public ControlTask[] newArray(int size) {
            return new ControlTask[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (done ? 1 : 0));
        dest.writeString(name);
        dest.writeString(commentary);
        dest.writeLong(deadline);
        dest.writeLong(executionTime);
        dest.writeLong(reminderDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
