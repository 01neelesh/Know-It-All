package com.example.triviaquiz.data;

import com.example.triviaquiz.model.Question;

import java.util.ArrayList;

public interface QuestionListAsyncResponse {

    void processFinished(ArrayList<Question>questionArrayList);
}
