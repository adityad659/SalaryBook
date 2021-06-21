package com.example.payroll.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.payroll.Repository.Repository;
import com.example.payroll.data.Ownerd;

public class VerifyOTPviewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Ownerd> allNotes;
    private Ownerd getRecord;

    public VerifyOTPviewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allNotes = repository.getAllNotes();

    }
    public void insert(Ownerd note) {
        repository.insert(note);
    }
    public void update(Ownerd note) {
        repository.update(note);
    }
    public void delete(Ownerd note) {
        repository.delete(note);
    }

    public LiveData<Ownerd> getAllNotes() {
        return allNotes;
    }
    public Ownerd getGetRecord() {
        return repository.getGetRecord();
    }

}
