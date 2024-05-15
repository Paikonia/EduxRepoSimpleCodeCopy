package com.example.edux;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.edux.recyclerView.DataClasses.NotesAndReferences;
import com.example.edux.recyclerView.DataClasses.NewsFeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class GetLocalData {




    /*This is a very temporary class, we are going to create a local data base with the room api*/
    private static String openNotesData(Context context) {

        Resources re = context.getResources();

        InputStream is = re.openRawResource(R.raw.notes);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        while(true){
            String b;
            try {
                if((b=br.readLine())==null)
                    break;
                    sb.append(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    private static void parseOpenNotesData(Context context, List<NotesAndReferences> file){

        String parse = GetLocalData.openNotesData(context);


        try {
            JSONArray ab = new JSONArray(parse);


            for(int i = 0; i<ab.length(); i++) {

                JSONObject abb = ab.getJSONObject(i);
                if(abb==null)
                    Toast.makeText(context, "There was an error getting the object", Toast.LENGTH_SHORT).show();

                JSONArray raa = abb.getJSONArray("Reference books");

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < raa.length() ; j++) {
                    sb.append(raa.get(j)+" ");
                }

                NotesAndReferences js = new NotesAndReferences(abb.getString("Title"),
                           abb.getString("Subject"),
                            sb.toString(),
                            abb.getString("Notes"));

                   file.add(js);
              }



    } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", "parseOpenNotesData: "+e);
        }

    }






    private static String openNewsData(Context context)  {


        Resources re = context.getResources();

        InputStream is = re.openRawResource(R.raw.news);
        InputStreamReader irs = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(irs);
        StringBuffer sb = new StringBuffer();

        String rea = "";
        while(true){
            try {
                if ((rea = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(rea);
            sb.append(" ");
        }

        return sb.toString();
    }

    private static void parseOpenNewsData(Context context, List<NewsFeed> file){

        String parse = GetLocalData.openNewsData(context);
        try {
            JSONArray ab = new JSONArray(parse);
            for(int i = 0; i<ab.length(); i++) {

                JSONObject abb = ab.getJSONObject(i);
                if(abb==null)
                    throw new NullPointerException("GetLocalData: in parseOpenNewsData: Failed to get data");

                NewsFeed js = new NewsFeed(abb.getString("Title"), abb.getString("Notes"));
                file.add(js);
            }



        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", "parseOpenNotesData: "+e);
        }

    }


    private static class getNotesAndReferenceAsync extends AsyncTask<Void, Void, Void>{

        List<NotesAndReferences> list;
        Context context;

        getNotesAndReferenceAsync(Context context, List<NotesAndReferences> news){
            this.context = context;
            this.list = news;
        }



        @Override
        protected Void doInBackground(Void... parameterClasses) {
            parseOpenNotesData(context, list);
            return null;
        }
    }


    public static void initialiseNews(Context context, List<NewsFeed> list){
        parseOpenNewsData(context, list);
    }

    public static void initialiseNotesAndReferences(Context context, List<NotesAndReferences> notes){
        new getNotesAndReferenceAsync(context, notes).execute();
    }


}
