package com.example.payroll.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.payroll.Database.OwnerDatabase;
import com.example.payroll.GlobalVariable;
import com.example.payroll.data.OwnerDao;
import com.example.payroll.data.Ownerd;

import java.util.concurrent.ExecutionException;

public class Repository {
    private OwnerDao noteDao;
    private LiveData<Ownerd> allNotes;
    private Ownerd getRecord;

    public Repository(Application application) {
        OwnerDatabase database = OwnerDatabase.getInstance(application);
        noteDao = database.ownerDao();
        allNotes = noteDao.getOwner(GlobalVariable.mob_no);
    }
    public void insert(Ownerd note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Ownerd note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Ownerd note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public LiveData<Ownerd> getAllNotes() {
        return allNotes;
    }
    public Ownerd getGetRecord()  {
        AsyncTask<Void, Void, Ownerd> r=new InsertNoteAsyncTask1(noteDao).execute();
        try {
            return r.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class InsertNoteAsyncTask1 extends AsyncTask<Void, Void, Ownerd> {
        private OwnerDao noteDao;
        private InsertNoteAsyncTask1(OwnerDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Ownerd doInBackground(Void... voids) {
            return  noteDao.getOwner1(GlobalVariable.mob_no);

        }
    }
        private static class InsertNoteAsyncTask extends AsyncTask<Ownerd, Void, Void> {
        private OwnerDao noteDao;
            private InsertNoteAsyncTask(OwnerDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Ownerd... ownerds) {
            noteDao.insert(ownerds[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Ownerd, Void, Void> {
        private OwnerDao noteDao;

        private UpdateNoteAsyncTask(OwnerDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Ownerd... ownerds) {
            noteDao.update(ownerds[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Ownerd, Void, Void> {
        private OwnerDao noteDao;
        private DeleteNoteAsyncTask(OwnerDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Ownerd... ownerds) {
            noteDao.delete(ownerds[0]);
            return null;
        }
    }
}
