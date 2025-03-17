package com.example.triviaquiz.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.triviaquiz.AppController;
import com.example.triviaquiz.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private String url = "https://opentdb.com/api.php?amount=50&category=9&type=boolean";
    ArrayList<Question>questionArrayList = new ArrayList<>();

    public List<Question> getQuestion( final QuestionListAsyncResponse callBack){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        int responseCode = jsonObject.getInt("response_code");
                        if (responseCode == 0) {
                            JSONArray resultsArray = jsonObject.getJSONArray("results");
                            if (resultsArray.length() > 0) {
                                // Process questions and answers
                                for (int i = 0; i < resultsArray.length(); i++) {
                                    JSONObject questionObject = resultsArray.getJSONObject(i);
                                    Question question = new Question(questionObject.getString("question"),questionObject.getBoolean("correct_answer"));
//                                    Log.d("CheckUP", "Question: " + question.getAnswer() + "\nAnswer: " + question.getCorrect_answer());
                                    questionArrayList.add(question);

//                                    Log.d("chacha", "getQuestion: "+questionArrayList);
                                    // Do something with question and answer
                                }
                            } else {
                                Log.d("CheckUP", "No results found");
                            }
                        } else {
                            Log.e("CheckUP", "API error: " + responseCode);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } if (null!= callBack) {
                        callBack.processFinished(questionArrayList);
                    }
                }
                , error -> {
            Log.e("CheckUP", "Error Fetching data: " + error.getMessage());
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        return questionArrayList;

//        AppController.getInstance().addToRequestQueue(jsonobjectRequest);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.toString());
//                        if (jsonObject.has("results")) {
//                            resultsArray = jsonObject.getJSONArray("results");
//
//                        } else {
//                            Log.e("Repository", "API Error: " + jsonObject.getInt("response_code"));
//                        }
//                    } catch (JSONException e) {
//                        Log.e("Repository", "Error fetching questions: " + e.getMessage());
//
//                    }
//                }, error -> {
//            Log.e("Repository", "Error Fetching data: " + error.getMessage());
//        });
//
//        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}



